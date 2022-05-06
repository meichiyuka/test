package login;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login2
 */
@WebServlet("/Login2")
public class Login2 extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public void doPost(HttpServletRequest req,HttpServletResponse res ) throws ServletException,IOException{
		res.setContentType("text/html; charset = UTF=8");

		//画面から入力されたIDとPWを取得する
		String userName = req.getParameter("ID");
		String password = req.getParameter("PW");

		UsersDao usersDao = null;
		UsersBean usersBean = null;
		
		try {
			usersDao =  new UsersDao();
			usersBean = usersDao.findName(userName);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		//listが0行か？
		if(usersBean.getId() == 0) {
			req.setAttribute("fromServlet",  "1");
			RequestDispatcher dispacher = req.getRequestDispatcher("./login.jsp");
			dispacher.forward(req,res);
			return;
		}	
		
		//listのパスワードと入力されたパスワードが一致するか
		String str = usersBean.getPassword();
		if(password.equals(str)) {
			req.setAttribute("fromServlet",  "2");
			req.setAttribute("user_name",  userName);
			RequestDispatcher dispacher = req.getRequestDispatcher("./top.jsp");
			dispacher.forward(req,res);
		}else {
			req.setAttribute("fromServlet",  "3");
			RequestDispatcher dispacher = req.getRequestDispatcher("./login.jsp");
			dispacher.forward(req,res);
		}

		
	}	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login2() {
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
