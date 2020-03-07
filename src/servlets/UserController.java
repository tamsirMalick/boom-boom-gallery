package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
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
import entities.Image;
import entities.User;
import services.AlbumWS;
import services.ImageWS;
import services.UserWS;

/**
 * Servlet implementation class UserController
 */
@WebServlet(urlPatterns = { "", "/index", "/accueil", "/login", "/createAcount", "/gallery", "/deconnection",
		"/addalbum", "/addPhoto", "/image-detail", "/delete", "/update", "/userslist", "/deleteuser", "/updateuser",
		"/user-album", "/gallery-user", "/deletealbum", "/updatealbum" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024
		* 50, location = "C:\\fichierstemp")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_ACCUEIL = "/WEB-INF/accueil.jsp";
	private static final String PAGE_LOGIN = "/WEB-INF/login.jsp";
	private static final String PAGE_GALLERY = "/WEB-INF/gallery.jsp";
	private static final String PAGE_INDEX = "/WEB-INF/index.jsp";
	private static final String PAGE_ACOUNT = "/WEB-INF/createAcount.jsp";
	private static final String PAGE_AJOUT_ALBUM = "/WEB-INF/addAlbum.jsp";
	private static final String PAGE_AJOUT_PHOTO = "/WEB-INF/addPhoto.jsp";
	private static final String PAGE_IMAGE_DETAIL = "/WEB-INF/image-detail.jsp";
	private static final String PAGE_USERS_LIST = "/WEB-INF/userslist.jsp";
	private static final String PAGE_USER_ALBUM = "/WEB-INF/user-album.jsp";

	private static final int TAILLE_TAMPON = 10240;
	private static final String CHEMIN_FICHIERS = "C:\\JavaEE\\java_ee_project\\Boomboom_gallery\\WebContent\\images\\";

	private HttpSession session;

	@EJB
	private UserWS userDao;

	@EJB
	private ImageWS imageDao;

	@EJB
	private AlbumWS albumDao;

	private int albumId;
	private int imgId;
	private int userId;

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
		HttpSession session = request.getSession();
		User user = new User();
		String albumName = request.getParameter("album");
		request.setAttribute("albumName", albumName);

		if (requestedUrl.equals("/gallery") || requestedUrl.equals("/index") || requestedUrl.equals("")) {
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
		} else if (requestedUrl.equals("/updateuser")) {
			userId = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("use", userDao.findUserById(userId));
			getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

		} else if (requestedUrl.equals("/deleteuser")) {
			int userid = Integer.parseInt(request.getParameter("userId"));
			userDao.deleteUser(userDao.findUserById(userid));
			request.setAttribute("users", userDao.findAll());
			response.sendRedirect(request.getContextPath() + "/userslist");

		} else if (requestedUrl.equals("/userslist")) {
			request.setAttribute("users", userDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_USERS_LIST).forward(request, response);

		} else if (requestedUrl.equals("/login")) {
			getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);

		} else if (requestedUrl.equals("/createAcount")) {
			getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

		} else if (requestedUrl.equals("/accueil")) {
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("users", userDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_ACCUEIL).forward(request, response);
		} else if (requestedUrl.equals("/delete")) {
			int imageId = Integer.parseInt(request.getParameter("imageId"));
			imageDao.deleteImage(imageDao.findImageById(imageId));

			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			response.sendRedirect(request.getContextPath() + "/index");
		} else if (requestedUrl.equals("/addalbum")) {
			getServletContext().getRequestDispatcher(PAGE_AJOUT_ALBUM).forward(request, response);
		} else if (requestedUrl.equals("/deletealbum")) {
			int id = Integer.parseInt(request.getParameter("albumId"));
			albumDao.deleteAlbum(albumDao.findAlbumById(id));
			response.sendRedirect(request.getContextPath() + "/user-album");
		} else if (requestedUrl.equals("/updatealbum")) {
			if (request.getParameter("albumId") != null) {
				albumId = Integer.parseInt(request.getParameter("albumId"));
				Album album = albumDao.findAlbumById(albumId);
				request.setAttribute("album", album);
			}
			getServletContext().getRequestDispatcher(PAGE_AJOUT_ALBUM).forward(request, response);
		} else if (requestedUrl.equals("/user-album")) {
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_USER_ALBUM).forward(request, response);
		} else if (requestedUrl.equals("/update")) {
			if (request.getParameter("imageId") != null) {
				imgId = Integer.parseInt(request.getParameter("imageId"));
				Image img = imageDao.findImageById(imgId);
				if (img != null) {
					request.setAttribute("img", img);
				}
			}
			user = (User) session.getAttribute("user");
			request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
			getServletContext().getRequestDispatcher(PAGE_AJOUT_PHOTO).forward(request, response);
		} else if (requestedUrl.equals("/addPhoto")) {
			user = (User) session.getAttribute("user");
			request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
			getServletContext().getRequestDispatcher(PAGE_AJOUT_PHOTO).forward(request, response);
		} else if (requestedUrl.equals("/image-detail")) {
			if (request.getParameter("imageId") != null) {
				int imageId = Integer.parseInt(request.getParameter("imageId"));
				request.setAttribute("image", imageDao.findImageById(imageId));
			}
			getServletContext().getRequestDispatcher(PAGE_IMAGE_DETAIL).forward(request, response);
		} else if (requestedUrl.equals("/deconnection")) {
			session = request.getSession();
			session.invalidate();
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			response.sendRedirect(request.getContextPath() + "/index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestedUrl = request.getServletPath();
		User user = new User();
		Album album = new Album();
		Image image = new Image();
		session = request.getSession();

		if (requestedUrl.equals("/accueil")) {
			response.setContentType("text/html;charset=UTF-8");

			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRole(request.getParameter("role"));

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

			if (user != null) {
				session.setAttribute("username", username);
				session.setAttribute("user", user);
				user = (User) session.getAttribute("user");
				request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
				request.setAttribute("images", imageDao.getAll());
				request.setAttribute("albums", albumDao.findAll());
				response.sendRedirect(request.getContextPath() + "/index");
			} else {
				getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
			}
		} else if (requestedUrl.equals("/createAcount") || requestedUrl.equals("/updateuser")) {
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");

			if (requestedUrl.equals("/updateuser")) {
				if (password1.equals(password2)) {
					user = userDao.findUserById(userId);
					String role = request.getParameter("role");
					if (user != null) {
						user.setUsername(username);
						user.setEmail(email);
						user.setPassword(password1);
						user.setRole(role);
						session.setAttribute("username", username);
						userDao.updateUser(user);
						request.setAttribute("users", userDao.findAll());
						response.sendRedirect(request.getContextPath() + "/userslist");
					}
				}
			} else {
				if (password1.equals(password2)) {
					user.setUsername(username);
					user.setEmail(email);
					user.setPassword(password1);
					user.setRole("user");
					userDao.addUser(user);
					response.sendRedirect(request.getContextPath() + "/login");
				}
			}

		} else if (requestedUrl.equals("/addalbum"))

		{
			String albumName = request.getParameter("albumName");
			boolean shared = request.getParameter("shared") != null;

			user = (User) session.getAttribute("user");
			album.setUser(user);
			album.setAlbumName(albumName);
			album.setShared(shared);
			albumDao.addAlbum(album);
			request.setAttribute("albums", albumDao.findAll());
			response.sendRedirect(request.getContextPath() + "/index");

		} else if (requestedUrl.equals("/updatealbum")) {
			Album al = albumDao.findAlbumById(albumId);
			if (al != null) {
				String albumName = request.getParameter("albumName");
				boolean shared = request.getParameter("shared") != null;
				user = (User) session.getAttribute("user");
				al.setUser(user);
				al.setAlbumName(albumName);
				al.setShared(shared);
				albumDao.updateAlbum(al);
			}

			request.setAttribute("albums", albumDao.findAll());
			response.sendRedirect(request.getContextPath() + "/user-album");

		} else if (requestedUrl.equals("/update") || requestedUrl.equals("/addPhoto")) {

			// Date object
			Date date = new Date();
			long time = date.getTime();
			Timestamp dat = new Timestamp(time);

			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int heigth = Integer.parseInt(request.getParameter("heigth"));
			int width = Integer.parseInt(request.getParameter("width"));
			int albumId = Integer.parseInt(request.getParameter("album"));
			album = albumDao.findAlbumById(albumId);
			user = (User) session.getAttribute("user");
			Part part = request.getPart("photo");
			String imageName = getimageName(part);
			String path = imageName;
			// Si on a bien un fichier
			if (imageName != null && !imageName.isEmpty()) {
				// Corrige un bug du fonctionnement d'Internet Explorer
				imageName = imageName.substring(imageName.lastIndexOf('/') + 1)
						.substring(imageName.lastIndexOf('\\') + 1);
				// On écrit définitivement le fichier sur le disque
				ecrireFichier(part, imageName, CHEMIN_FICHIERS);
			}

			if (requestedUrl.equals("/update")) {
				image = imageDao.findImageById(imgId);

				if (image != null) {
					String pathCach = image.getImagePath();
					System.out.println(pathCach);
					image.setTitle(title);
					image.setDescription(description);
					image.setHeigth(heigth);
					image.setWidth(width);
					image.setUser(user);
					image.setAlbum(album);
					image.setModified(dat);
					imageDao.updateImage(image);
					response.sendRedirect(request.getContextPath() + "/image-detail");
				}
			} else {
				image.setTitle(title);
				image.setDescription(description);
				image.setHeigth(heigth);
				image.setWidth(width);
				image.setUser(user);
				image.setAlbum(album);
				image.setImagePath(path);
				image.setCreated(dat);
				imageDao.addImage(image);
				request.setAttribute("images", imageDao.getAll());
				request.setAttribute("albums", albumDao.findAll());
				response.sendRedirect(request.getContextPath() + "/index");
			}

		}
	}

	private void ecrireFichier(Part part, String imageName, String chemin) throws IOException {
		BufferedInputStream entree = null;
		BufferedOutputStream sortie = null;
		try {
			entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
			sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + imageName)), TAILLE_TAMPON);

			byte[] tampon = new byte[TAILLE_TAMPON];
			int longueur;
			while ((longueur = entree.read(tampon)) > 0) {
				sortie.write(tampon, 0, longueur);
			}
		} finally {
			try {
				sortie.close();
			} catch (IOException ignore) {
			}
			try {
				entree.close();
			} catch (IOException ignore) {
			}
		}
	}

	private static String getimageName(Part part) {
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
			if (contentDisposition.trim().startsWith("filename")) {
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
