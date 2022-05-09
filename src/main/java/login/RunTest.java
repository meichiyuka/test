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
 * Servlet implementation class RunTest
 */
@WebServlet("/RunTest")
public class RunTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html; charset = UTF=8");
		
		//テスト画面から、問題No、答えを取得。userid取得
		String[] question_id = req.getParameterValues("question_id[]");
		String[] answer = req.getParameterValues("answer[]");
		String user_name = req.getParameter("user_name");
		
		UsersDao usersDao = null;
		UsersBean usersBean = null;

		try {
			//AnswersDaoをインスタンス化。AnswersBean型のリストを作成。
			AnswersDao answersDao = null;
			answersDao = new AnswersDao();
			ArrayList<AnswersBean> ArrayList_answersbean = null;
			
			//usersDaoをインスタンス化。UsersBean型のusersBeanに代入。
			usersDao =  new UsersDao();
			usersBean = usersDao.findName(user_name);

			//正解した問題数を入れる変数
			int n = 0; 

			//画面から持ってきたquestion_idの個数分ループさせたい
			for(int i = 0; i < question_id.length; i++) {

				//答え全件取得して、ArrayList_answersbean型のリストに代入
				ArrayList_answersbean = answersDao.findAll();

				//画面から持ってきたanswerが答えDBのanswerと一致しているか
				for(int j = 0; j < ArrayList_answersbean.size(); j++){					
					//答え全件リストから１行ずつ取得
					AnswersBean answersBean = ArrayList_answersbean.get(j);
					//画面から取得したanswerとanswersBeanの照会
					if(answersBean.getAnswer().equals(answer[i])) {
						//一致していたら正解数に加算
						n++;
					}
				}
			}
			
			//問題数、正解数、得点をそれぞれ変数に代入
			double questions_number = question_id.length;
			double answers_number = n;
			Double score = (answers_number/questions_number) * 100;
			
			//historiesテーブルに新規登録
			answersDao.insertResultAnswer(usersBean.getId(), score.intValue());
			
			//jspに渡している
			req.setAttribute("user_name", user_name);
			req.setAttribute("questions_number", questions_number);
			req.setAttribute("answers_number", answers_number);
			req.setAttribute("score", score);
			RequestDispatcher disList = req.getRequestDispatcher("./result.jsp");
			disList.forward(req,res);
			
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}	
		
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RunTest() {
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
