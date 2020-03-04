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
		"/addalbum", "/addPhoto", "/image-detail", "/delete", "/update", "/userslist", "/deleteuser", "/updateuser" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50, location = "C:\\fichierstemp")
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

	private static final int TAILLE_TAMPON = 10240;
	private static final String CHEMIN_FICHIERS = "C:\\JavaEE\\java_ee_project\\Boomboom_gallery\\WebContent\\images\\";
	
	private HttpSession session;

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
		HttpSession session = request.getSession();
		String albumName = request.getParameter("album");
		request.setAttribute("albumName", albumName);

		if (requestedUrl.equals("/gallery") || requestedUrl.equals("/index") || requestedUrl.equals("")) 
		{
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());		
			getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
		}
		else if (requestedUrl.equals("/updateuser")) 
		{
			int userid = Integer.parseInt(request.getParameter("userId"));
			request.setAttribute("use", userDao.findUserById(userid));
			getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

		}
		else if (requestedUrl.equals("/deleteuser")) 
		{
			int userid = Integer.parseInt(request.getParameter("userId"));
			userDao.deleteUser(userDao.findUserById(userid));
			request.setAttribute("users", userDao.findAll());
			response.sendRedirect(request.getContextPath() + "/userslist");

		}
		else if (requestedUrl.equals("/userslist")) 
		{
			request.setAttribute("users", userDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_USERS_LIST).forward(request, response);

		}
		else if (requestedUrl.equals("/login")) 
		{
			getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);

		}
		else if (requestedUrl.equals("/createAcount")) 
		{
			getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

		} 
		else if (requestedUrl.equals("/accueil")) 
		{
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("users", userDao.findAll());
			getServletContext().getRequestDispatcher(PAGE_ACCUEIL).forward(request, response);
		} 
		else if (requestedUrl.equals("/delete")) 
		{
			int imageId = Integer.parseInt(request.getParameter("imageId"));
			imageDao.deleteImage(imageDao.findImageById(imageId));
			
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			response.sendRedirect(request.getContextPath() + "/index");
		} 
		else if (requestedUrl.equals("/addalbum")) 
		{
			getServletContext().getRequestDispatcher(PAGE_AJOUT_ALBUM).forward(request, response);
		}
		else if (requestedUrl.equals("/update")) {
			if(request.getParameter("imageId") != null) 
			{
				int imageId = Integer.parseInt(request.getParameter("imageId"));
				Image img = imageDao.findImageById(imageId);
				if(img != null) 
				{
					request.setAttribute("img", img);
				}
			}
			User user = (User) session.getAttribute("user");					
			request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
			getServletContext().getRequestDispatcher(PAGE_AJOUT_PHOTO).forward(request, response);
		} 
		else if (requestedUrl.equals("/addPhoto")) {
			User user = (User) session.getAttribute("user");					
			request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
			getServletContext().getRequestDispatcher(PAGE_AJOUT_PHOTO).forward(request, response);
		}
		else if (requestedUrl.equals("/image-detail")) 
		{
			if(request.getParameter("imageId") != null) 
			{
				int imageId = Integer.parseInt(request.getParameter("imageId"));
				request.setAttribute("image", imageDao.findImageById(imageId));
			}	
			getServletContext().getRequestDispatcher(PAGE_IMAGE_DETAIL).forward(request, response);
		} 
		else if (requestedUrl.equals("/deconnection")) 
		{
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
		Image image =new Image();
		session = request.getSession();
			
		if (requestedUrl.equals("/accueil")) 
		{
			response.setContentType("text/html;charset=UTF-8");

			user.setUsername(request.getParameter("username"));
			user.setEmail(request.getParameter("email"));
			user.setPassword(request.getParameter("password"));
			user.setRole(request.getParameter("role"));

			if (request.getParameter("username") != null && !request.getParameter("username").trim().isEmpty()) 
			{
				userDao.addUser(user);
				getServletContext().getRequestDispatcher(PAGE_GALLERY).forward(request, response);
			} 
			else 
			{
				response.getWriter().print("Vous devez renseigner le nom du participant");
			}
		} 
		else if (requestedUrl.equals("/login")) 
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			user = userDao.findUserByUsernammeAndPassword(username, password);

			if (username.equals(user.getUsername()) && password.equals(user.getPassword()))
			{
				session.setAttribute("username", username);
				session.setAttribute("user", user);
				user = (User) session.getAttribute("user");
				request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
				request.setAttribute("images", imageDao.getAll());
				request.setAttribute("albums", albumDao.findAll());
				response.sendRedirect(request.getContextPath() + "/index");
			} 
			else 
			{
				getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
			}
		} 
		else if (requestedUrl.equals("/createAcount")) 
		{
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");

			if (password1.equals(password2)) 
			{
				user.setUsername(username);
				user.setEmail(email);
				user.setPassword(password1);
				user.setRole("user");
				session.setAttribute("username", username);
				userDao.addUser(user);

				getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
			} 
			else 
			{
				System.out.println("Les mots de passes ne correspondent pas ");
			}
		} 
		else if (requestedUrl.equals("/updateuser")) 
		{
			String username = request.getParameter("username");
			String password1 = request.getParameter("password1");
			String password2 = request.getParameter("password2");
			String email = request.getParameter("email");

			if (password1.equals(password2)) 
			{
				user.setUsername(username);
				user.setEmail(email);
				user.setPassword(password1);
				user.setRole("user");
				session.setAttribute("username", username);
				
				int userId= Integer.parseInt(request.getParameter("userId"));
				
				for(User u: userDao.findAll()) {
					if(u.getUserid() == userId) {
						userDao.updateUser(user);
					}
				}

				getServletContext().getRequestDispatcher(PAGE_USERS_LIST).forward(request, response);
			} 
			else 
			{
				System.out.println("Les mots de passes ne correspondent pas ");
			}
		} 
		else if (requestedUrl.equals("/addalbum")) 
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

		} 
		else if (requestedUrl.equals("/update") || requestedUrl.equals("/addPhoto")) 
		{
			
			//Date object
			 Date date= new Date();
			 long time = date.getTime();
			 Timestamp dat = new Timestamp(time);

			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int heigth = Integer.parseInt(request.getParameter("heigth"));
			int width = Integer.parseInt(request.getParameter("width"));
			int albumId = Integer.parseInt(request.getParameter("album"));
			album = albumDao.findAlbumById(albumId);
			user = (User)session.getAttribute("user");
			Part part = request.getPart("photo");
			String imageName = getimageName(part);
			String path = imageName;
			// Si on a bien un fichier
			if (imageName != null && !imageName.isEmpty()) 
			{
				// Corrige un bug du fonctionnement d'Internet Explorer
				imageName = imageName.substring(imageName.lastIndexOf('/') + 1)
						.substring(imageName.lastIndexOf('\\') + 1);
				// On écrit définitivement le fichier sur le disque
				ecrireFichier(part, imageName, CHEMIN_FICHIERS);
			}
			image.setTitle(title);
			image.setDescription(description);
			image.setHeigth(heigth);
			image.setWidth(width);
			image.setUser(user);
			image.setAlbum(album);
			image.setImagePath(path);
			
			if(request.getParameter("imageId") != null)
			{
				int imageId = Integer.parseInt(request.getParameter("imageId"));
				System.out.println(imageId);
				for(Image img: imageDao.getAll()) 
				{
					if(img.getImageID() == imageId)
					{
						img.setModified(dat);
						imageDao.updateImage(image);
					}
				}			
			}
			else 
			{
				image.setCreated(dat);
				imageDao.addImage(image);
			}
						
			request.setAttribute("images", imageDao.getAll());
			request.setAttribute("albums", albumDao.findAll());
			response.sendRedirect(request.getContextPath() + "/index");
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
		for (String contentDisposition : part.getHeader("content-disposition").split(";")) 
		{
			if (contentDisposition.trim().startsWith("filename")) 
			{
				return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

}
