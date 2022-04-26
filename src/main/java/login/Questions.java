package login;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Questions
 */
@WebServlet("/Questions")
public class Questions extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doPost(HttpServletRequest req,HttpServletResponse res ) throws ServletException,IOException{
		res.setContentType("text/html; charset = UTF=8");

		//問題文のリスト格納用に、リスト型のaListを作成
		ArrayList<QuestionsBean> qlist = null;
		
		//QuestionsDao型のオブジェクトdaoを作成してnull代入
		QuestionsDao dao = null;
		
		//答えのリスト格納用に、リスト型のaListを作成
		ArrayList<AnswersBean> aList = null;
		
		//AnswersBean型のオブジェクトdaoを作成してnull代入
		AnswersDao aDao = null;

		try {
			//QuestionsDaoをインスタンス化
			dao = new QuestionsDao();
			
			//QuestionsDao内の、findAll()メソッドをlistに代入
			qlist = dao.findAll();
			
			//AnswersDaoをインスタンス化
			aDao = new AnswersDao();
			
			//AnswersDao内の、findAll()メソッドをlistに代入
			//DBの値を全件取得してリストに入れる
			aList = aDao.findAll();
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		//問題と答えそれぞれのリストをjspに渡している
		req.setAttribute("qList", qlist);
		req.setAttribute("aList", aList);
		RequestDispatcher disList = req.getRequestDispatcher("./questionList.jsp");
		disList.forward(req,res);
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Questions() {
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
