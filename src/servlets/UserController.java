package servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDaoImp;
import entities.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns={ ""})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_ACCUEIL = "/WEB-INF/accueil.jsp";
	
	@EJB
	private UserDaoImp userDao;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("users", userDao.findAll());
		getServletContext().getRequestDispatcher(PAGE_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setRole(request.getParameter("role"));
		
		if (request.getParameter("username") != null && !request.getParameter("username").trim().isEmpty()) {
			userDao.addUser(user);	
			response.sendRedirect(request.getContextPath());
		} else {
			response.getWriter().print("Vous devez renseigner le nom du participant");
		}
	}

}
