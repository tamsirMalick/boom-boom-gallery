package servlets;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
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
		"/addalbum", "/addPhoto" })
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

	private static final int TAILLE_TAMPON = 10240;
	private static final String CHEMIN_FICHIERS = "C:\\JavaEE\\java_ee_project\\Boomboom_gallery\\WebContent\\images\\";

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
		String alb = request.getParameter("album");
		request.setAttribute("albumName", alb);;
		

		if (requestedUrl.equals("/gallery") || requestedUrl.equals("/index") || requestedUrl.equals("")) {
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());		
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
		}else if (requestedUrl.equals("/login")) {
			getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);

		} else if (requestedUrl.equals("/createAcount")) {
			getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

		} else if (requestedUrl.equals("/accueil")) {
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("users", userDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_ACCUEIL).forward(request, response);
		} else if (requestedUrl.equals("/addalbum")) {
			getServletContext().getRequestDispatcher(PAGE_AJOUT_ALBUM).forward(request, response);
		} else if (requestedUrl.equals("/addPhoto")) {
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_AJOUT_PHOTO).forward(request, response);
		} else if (requestedUrl.equals("/deconnection")) {
			HttpSession session = request.getSession();
			session.invalidate();
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
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
		Image image =new Image();
		HttpSession session = request.getSession();
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

			if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
				session.setAttribute("username", username);
				session.setAttribute("user", user);
				request.setAttribute("albums", albumDao.findAll());
				getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
			} else {
				getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
			}
		} else if (requestedUrl.equals("/createAcount")) {
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

				getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
			} else {
				System.out.println("Les mots de passes ne correspondent pas ");
			}
		} else if (requestedUrl.equals("/addalbum")) {
			String albumName = request.getParameter("albumName");
			boolean shared = request.getParameter("shared") != null;

			user = (User) session.getAttribute("user");
			album.setUser(user);
			album.setAlbumName(albumName);
			album.setShared(shared);
			albumDao.addAlbum(album);
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);

		} else if (requestedUrl.equals("/addPhoto")) {
			
			//Date object
			 Date date= new Date();
			 long time = date.getTime();
			 Timestamp created = new Timestamp(time);
			
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int heigth = Integer.parseInt(request.getParameter("heigth"));
			int width = Integer.parseInt(request.getParameter("width"));
			int albumId = Integer.parseInt(request.getParameter("album"));
			album = albumDao.findAlbumById(albumId);
			user = (User)session.getAttribute("user");
			System.out.println(album);

			Part part = request.getPart("photo");
			String imageName = getimageName(part);
			String path = imageName;
			// Si on a bien un fichier
			if (imageName != null && !imageName.isEmpty()) {
				//String nomChamp = part.getName();
				
				// Corrige un bug du fonctionnement d'Internet Explorer
				imageName = imageName.substring(imageName.lastIndexOf('/') + 1)
						.substring(imageName.lastIndexOf('\\') + 1);
				// On écrit définitivement le fichier sur le disque
				ecrireFichier(part, imageName, CHEMIN_FICHIERS);

				//request.setAttribute(nomChamp, imageName);
			}
			
			image.setTitle(title);
			image.setDescription(description);
			image.setHeigth(heigth);
			image.setWidth(width);
			image.setCreated(created);
			image.setUser(user);
			image.setAlbum(album);
			image.setImagePath(path);
			
			imageDao.addImage(image);
			request.setAttribute("images", imageDao.getAll());
			List<Image> imgs = imageDao.getAll();
			for(Image img: imgs) {
				System.out.println(img.toString());
			}
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
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
