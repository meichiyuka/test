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
 * Servlet implementation class CreateList
 */
@WebServlet("/CreateList")
public class CreateList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res ) throws ServletException,IOException{
		res.setContentType("text/html; charset = UTF=8");
		
		//画面から入力された答えを取得する
		String[] data = req.getParameterValues("data[]");
		//画面から入力された問題を取得する
		String[] qData = req.getParameterValues("qData");
		
		QuestionsDao dao = null;
		ConnectionDao con = null;
		int id = 0;
		
		try {
			//daoを初期化
			dao = new QuestionsDao();
			//conを初期化
			con = new ConnectionDao();

			PreparedStatement st = null;
			
			//問題番号の最大値を取得する
			id = dao.register(qData[0]);

			//答えDBにdataをinsertするSQL
			String sql = "insert into correct_answers(questions_id,answer,created_at,updated_at) values(?,?,CURRENT_TIME,CURRENT_TIME);";
			
			//答えが複数の場合があるので、ループ回す			
			for(int i = 0; i < data.length; i++) {
				st = con.con.prepareStatement(sql);
				st.setInt(1, id);
				st.setString(2, data[i]);
				st.executeUpdate();
			}
			
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
    public CreateList() {
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
