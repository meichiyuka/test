package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class QuestionsDao extends ConnectionDao {

	/**
	 * コンストラクタ
	 */
	public QuestionsDao() throws Exception {
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	public ArrayList<QuestionsBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question FROM questions";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			ArrayList<QuestionsBean> list = new ArrayList<QuestionsBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				QuestionsBean bean = new QuestionsBean(id, question);
				list.add(bean);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");

			}
		}
	}

	/**
	 * 指定IDのレコードを取得する
	 */
	public QuestionsBean find(int ques_id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, question FROM questions WHERE id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, ques_id);
			rs = st.executeQuery();
			QuestionsBean bean = new QuestionsBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				String question = rs.getString("question");
				bean.setId(id);
				bean.setQuestion(question);
			}
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの取得に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}				
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 問題文を新規登録&最大idを取得
	 */
	public int register(String question) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			//問題文を新規登録
			String sql = "insert into questions(question,created_at,update_at) values(?,CURRENT_TIME,CURRENT_TIME);";
			//idの最大値を取得
			String sql2 = "select max(id) as id from questions;";
			
			/** PreparedStatement オブジェクトの取得 (sql)**/
			st = con.prepareStatement(sql);
			st.setString(1, question);
			st.executeUpdate();
			st.close();

			/** PreparedStatement オブジェクトの取得 (sql2)**/
			st = con.prepareStatement(sql2);
			rs = st.executeQuery();
			int id = 0;
			while (rs.next()) {
				id = rs.getInt("id");
			}
			//問題Noの最大値を返したい
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの登録に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}				
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 答えを新規登録する
	 * @throws Exception 
	 */
	public void insertAnswer(String[] question, String[] answer) throws Exception {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		int id = 0; 
		try {
			//問題番号の最大値を取得する
			id = register(question[0]);
			if (con == null) {
				setConnection();
			}
			//答えDBにdataをinsertするSQL
			String sql = "insert into correct_answers(questions_id,answer,created_at,updated_at) values(?,?,CURRENT_TIME,CURRENT_TIME);";
			
			//答えが複数の場合があるので、ループ回す			
			for(int i = 0; i < answer.length; i++) {
				st = con.prepareStatement(sql);
				st.setInt(1, id);
				st.setString(2, answer[i]);
				st.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの登録に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}				
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}
	}
	
	/**
	 * 問題文と答えを更新する
	 * @throws Exception 
	 */
	
	public void update(int question_id, String question, String answer[]) throws Exception {
		
		if (con == null) {
			setConnection();
		}
		
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			//問題DBにdataをupdateするSQL
			String sql1 = "update questions set question = ?, update_at = CURRENT_TIME where id = ?;";
			//該当するquestions_idに対応する答えの削除をするSQL
			String sql2 = "delete from correct_answers where questions_id = ?";
			//答えを新規登録するSQL
			String sql3 = "insert into correct_answers (questions_id, answer, created_at, updated_at) value(?, ?, CURRENT_TIME, CURRENT_TIME);";
			
			//sql1を実行
			st = con.prepareStatement(sql1);
			st.setString(1,question);
			st.setInt(2, question_id);
			st.executeUpdate();
			st.close();

			//sql2を実行
			st = con.prepareStatement(sql2);
			st.setInt(1, question_id);
			st.executeUpdate();
			st.close();
			
			//答えが複数の場合があるので、ループ回す			
			for(int i = 0; i < answer.length; i++) {
				//sql3を実行
				st = con.prepareStatement(sql3);
				st.setInt(1, question_id);
				st.setString(2, answer[i]);
				st.executeUpdate();
				st.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("レコードの登録に失敗しました");
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}				
				if (st != null) {
					st.close();
				}
				close();
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("リソースの開放に失敗しました");
			}
		}

	}
	
	/**
	 * 問題文と答えを削除する
	 */
	public int delete(int question_id) {
		  if (con == null) {
		    try {
				setConnection();
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		  }
		  int status = 0;
		  try {
			PreparedStatement ps = con.prepareStatement("DELETE FROM questions where id = ?");
			ps.setInt(1, question_id);
			status = ps.executeUpdate();
		  } catch (Exception e) {
			e.printStackTrace();
		 }
		return status;
		}
	
}