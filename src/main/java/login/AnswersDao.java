package login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AnswersDao extends ConnectionDao {

	/**
	 * コンストラクタ
	 */
	public AnswersDao() throws Exception {
		setConnection();
	}

	/**
	 * users テーブルを全件取得
	 */
	public ArrayList<AnswersBean> findAll() throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questions_id, answer FROM correct_answers";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			/** SQL 実行 **/
			rs = st.executeQuery();
			/** select文の結果をArrayListに格納 **/
			ArrayList<AnswersBean> list = new ArrayList<AnswersBean>();
			while (rs.next()) {
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				AnswersBean bean = new AnswersBean(id, questions_id, answer);
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
	public ArrayList<AnswersBean> findByQuestions(int ques_id) throws Exception {
		if (con == null) {
			setConnection();
		}
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT id, questions_id, answer FROM correct_answers WHERE questions_id = ?";
			/** PreparedStatement オブジェクトの取得**/
			st = con.prepareStatement(sql);
			st.setInt(1, ques_id);
			rs = st.executeQuery();
			ArrayList<AnswersBean> list = new ArrayList<AnswersBean>();
			AnswersBean bean = new AnswersBean();
			while (rs.next()) {
				int id = rs.getInt("id");
				int questions_id = rs.getInt("questions_id");
				String answer = rs.getString("answer");
				bean.setId(id);
				bean.setQuestions_id(questions_id);
				bean.setAnswer(answer);
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
	 * テストの回答を登録する
	 */
public void insertResultAnswer(int user_id, int point) throws Exception {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			if (con == null) {
				setConnection();
			}
			//historiesに回答結果をinsertするSQL
			String sql = "insert into histories(user_id,point,created_at) values(?,?,CURRENT_TIME);";
			
			//SQLを実行する			
				st = con.prepareStatement(sql);
				st.setInt(1, user_id);
				st.setInt(2, point);
				st.executeUpdate();
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
}	
