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
 * Servlet implementation class Histories
 */
@WebServlet("/Histories")
public class Histories extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public  void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException{
		res.setContentType("text/html; charset = UTF=8");

		String user_name = req.getParameter("user_name");
		
		UsersBean usersBean = null;
		UsersDao usersDao = null;
		ArrayList<HistoriesBean> historiesList = null;
		HistoriesDao historiesDao = null;

		try {
			usersDao = new UsersDao();
			usersBean = usersDao.findName(user_name);			
			historiesDao = new HistoriesDao();
			historiesList = historiesDao.findAll();

		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//問題と答えそれぞれのリストをjspに渡している
		req.setAttribute("historiesList", historiesList);
		req.setAttribute("users_id", usersBean.getId());
		req.setAttribute("user_name", user_name);
		RequestDispatcher dispatcher = req.getRequestDispatcher("./histories.jsp");
		dispatcher.forward(req,res);
	}
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Histories() {
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
