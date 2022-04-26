package login;

import java.io.IOException;
import java.sql.PreparedStatement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleatList
 */
@WebServlet("/DeleatList")
public class DeleatList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res ) throws ServletException,IOException{
		res.setContentType("text/html; charset = UTF=8");

		//問題一覧画面から、問題Noを取得
		int editNo = Integer.parseInt(req.getParameter("edit"));

		ConnectionDao con = null;

		try {
			//conを初期化
			con = new ConnectionDao();

			PreparedStatement st = null;

			//問題DBをdeleatするSQL
			String sql1 = "delete from questions where id = ?";
			//該当するquestions_idに対応する答えの削除をするSQL
			String sql2 = "delete from correct_answers where questions_id = ?";

			//sql1を実行
			st = con.con.prepareStatement(sql1);
			st.setInt(1, editNo);
			st.executeUpdate();
			st.close();

			//sql2を実行
			st = con.con.prepareStatement(sql2);
			st.setInt(1, editNo);
			st.executeUpdate();
			st.close();


		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("Questions");
		dispatcher.forward(req,res);
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleatList() {
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
