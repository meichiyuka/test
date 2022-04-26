package login;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditList
 */
@WebServlet("/EditList")
public class EditList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res ) throws ServletException,IOException{
		res.setContentType("text/html; charset = UTF=8");
		

		//問題一覧画面から、問題Noを取得
		int editNo = Integer.parseInt(req.getParameter("edit"));
		
		QuestionsDao dao = null;
		QuestionsBean list = null;
		AnswersDao daoA = null;
		ArrayList<AnswersBean> listA = null;
		
		try {
			dao = new QuestionsDao();
			daoA = new AnswersDao();
			list = dao.find(editNo);
			listA = daoA.findAll();
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		if( req.getParameter("editFlag") == null) {
			req.setAttribute("editNo", editNo);
			req.setAttribute("editQ", list.getQuestion());
			req.setAttribute("editA", listA);
			RequestDispatcher disList = req.getRequestDispatcher("./editList.jsp");
			disList.forward(req,res);
			return;
		}

		
		//更新処理
		
		//画面から入力された問題を取得する
		String[] qData = req.getParameterValues("qData");
		//画面から入力された答えを取得する
		String[] data = req.getParameterValues("data[]");

		ConnectionDao con = null;
		
		try {
			//conを初期化
			con = new ConnectionDao();

			PreparedStatement st = null;

			//問題DBにdataをupdateするSQL
			String sql1 = "update questions set question = ?, update_at = CURRENT_TIME where id = ?;";
			//該当するquestions_idに対応する答えの削除をするSQL
			String sql2 = "delete from correct_answers where questions_id = ?";
			//答えを新規登録するSQL
			String sql3 = "insert into correct_answers (questions_id, answer, created_at, updated_at) value(?, ?, CURRENT_TIME, CURRENT_TIME);";
			
			//sql1を実行
			st = con.con.prepareStatement(sql1);
			st.setString(1,qData[0]);
			st.setInt(2, editNo);
			st.executeUpdate();
			st.close();

			//sql2を実行
			st = con.con.prepareStatement(sql2);
			st.setInt(1, editNo);
			st.executeUpdate();
			st.close();
			
			//答えが複数の場合があるので、ループ回す			
			for(int i = 0; i < data.length; i++) {
				//sql3を実行
				st = con.con.prepareStatement(sql3);
				st.setInt(1, editNo);
				st.setString(2, data[i]);
				st.executeUpdate();
				st.close();
			}
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("Questions");
			dispatcher.forward(req,res);
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}		
		
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
