package servlets;

import java.io.IOException;
import java.io.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import entities.Album;
import entities.User;
import services.AlbumWS;
import services.ImageWS;
import services.UserWS;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = { "", "/index", "/accueil", "/login", "/createAcount", "/gallery", "/deconnection", "/addalbum", "/addPhoto" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_ACCUEIL = "/WEB-INF/accueil.jsp";
	private static final String PAGE_LOGIN = "/WEB-INF/login.jsp";
	private static final String PAGE_GALLERY = "/WEB-INF/gallery.jsp";
	private static final String PAGE_INDEX = "/WEB-INF/index.jsp";
	private static final String PAGE_ACOUNT = "/WEB-INF/createAcount.jsp";
	private static final String PAGE_AJOUT_ALBUM = "/WEB-INF/addAlbum.jsp";
	private static final String PAGE_AJOUT_PHOTO = "/WEB-INF/addPhoto.jsp";

	@EJB
	private UserWS userDao;

	@EJB
	private ImageWS imageDao;
	
	@EJB
	private AlbumWS albumDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getServletPath();

		if (requestedUrl.equals("/index") || requestedUrl.equals("")) {
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
		} else if (requestedUrl.equals("/login")) {
			getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);

		} else if (requestedUrl.equals("/createAcount")) {
			getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

		} else if (requestedUrl.equals("/accueil")) {
			request.setAttribute("users", userDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_ACCUEIL).forward(request, response);
		}else if (requestedUrl.equals("/addalbum")) {	
			getServletContext().getRequestDispatcher(PAGE_AJOUT_ALBUM).forward(request, response);
		}else if (requestedUrl.equals("/addPhoto")) {
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_AJOUT_PHOTO).forward(request, response);
		}
		else if (requestedUrl.equals("/deconnection")) {
			HttpSession session = request.getSession();
			session.invalidate();
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
		}
//		List<User> users =  userDao.findAll();
//		byte[] photo;
//		for(User user: users) {
//			photo = user.getPhoto();
//			response.setContentType("image/jpg");
//			response.setContentLength(photo.length);
//            response.getOutputStream().write(photo);
//            response.getOutputStream().flush();
//		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getServletPath();
		User user = new User();
		HttpSession session = request.getSession();
		if (requestedUrl.equals("/accueil")) {
			
			response.setContentType("text/html;charset=UTF-8");

			Part filePart = request.getPart("photo");
			String photoName = extractFileName(filePart);
			String savePath = "C:\\JavaEE\\java_ee_project\\Boomboom_gallery\\WebContent\\images\\" + File.separator
					+ photoName;
			// File fileSaveDir = new File(savePath);

			// filePart.write(savePath + File.separator);

			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRole(request.getParameter("role"));
			user.setPhotoUrl(savePath);

			if (request.getParameter("username") != null && !request.getParameter("username").trim().isEmpty()) {
				userDao.addUser(user);
				getServletContext().getRequestDispatcher(PAGE_GALLERY).forward(request, response);
			} else {
				response.getWriter().print("Vous devez renseigner le nom du participant");
			}
		} else if (requestedUrl.equals("/login")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			user = userDao.findUserByUsernammeAndPassword(username, password);

			if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				session.setAttribute("username", username);
				session.setAttribute("user", user);
				request.setAttribute("albums", albumDao.findAll());
				getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
			} else {
				getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
			}
		}else if (requestedUrl.equals("/gallery")) {
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");

			if (password1.equals(password2)) {
				user.setUsername(username);
				user.setEmail(email);
				user.setPassword(password1);
				user.setRole("user");
				session.setAttribute("username", username);
				userDao.addUser(user);
				
				getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
			} else {
				System.out.println("Les mots de passes ne correspondent pas ");
			}
		}else if (requestedUrl.equals("/addalbum")) {
			String albumName = request.getParameter("albumName");
			boolean shared = request.getParameter("shared") != null;
				Album album = new Album();
				user = (User)session.getAttribute("user");
				album.setUser(user);
				album.setAlbumName(albumName);
				album.setShared(shared);
				albumDao.addAlbum(album);
				request.setAttribute("albums", albumDao.findAll());
				getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
			
		}else if (requestedUrl.equals("/addPhoto")) {
			
			
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
		}

	}

	private String extractFileName(Part filePart) {
		String contentDisp = filePart.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("photoName")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
