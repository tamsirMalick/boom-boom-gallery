package servlets;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

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
    private static final String PAGE_LOGIN = "/WEB-INF/login.jsp";
    private static final String PAGE_INDEX = "/WEB-INF/index.jsp";
    private static final String PAGE_ACOUNT = "/WEB-INF/createAcount.jsp";
    private static final String PAGE_AJOUT_ALBUM = "/WEB-INF/addAlbum.jsp";
    private static final String PAGE_AJOUT_PHOTO = "/WEB-INF/addPhoto.jsp";
    private static final String PAGE_IMAGE_DETAIL = "/WEB-INF/image-detail.jsp";
    private static final String PAGE_USERS_LIST = "/WEB-INF/userslist.jsp";
    private static final String PAGE_USER_ALBUM = "/WEB-INF/user-album.jsp";

    private static final int TAILLE_TAMPON = 10240;
    private static final String CHEMIN_FICHIERS = "C:\\JavaEE\\java_ee_project\\Boomboom_gallery\\WebContent\\images\\"; // A
															 // changer

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

	/**
	 * Si l'utilisateur demande la page gallery ou index ou ne saisi rien, je
	 * récupère tous les images via la methode imageDao.getAll() et tous les albums
	 * via la méthode albumDao.findAll() qui sont ajoutés et partagés par les autres
	 * utilisateurs puis je le redirige au niveau de la page gallery
	 */
	if (requestedUrl.equals("/gallery") || requestedUrl.equals("/gallery") || requestedUrl.equals("/index")
		|| requestedUrl.equals("")) {
	    request.setAttribute("images", imageDao.getAll());
	    request.setAttribute("albums", albumDao.findAll());
	    getServletContext().getRequestDispatcher(PAGE_INDEX).forward(request, response);
	} else if (requestedUrl.equals("/updateuser")) {
	    /**
	     * Si l'utilisateur demande à modifier ses informations, je récupere ses
	     * informations au niveau de la BD userDao.findUserById(userId) via son
	     * identfiant puis je le drige vers la page de modification
	     */
	    userId = Integer.parseInt(request.getParameter("userId"));
	    request.setAttribute("use", userDao.findUserById(userId));
	    getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

	} else if (requestedUrl.equals("/deleteuser")) {
	    /**
	     * Si l'administrateur veut à supprimer un utilisateur, je récupere les
	     * informations au niveau de la BD via l'identfiant de l'utilisateur
	     * userDao.findUserById(userId) puis j'appelle la methode de suppression
	     * userDao.deleteUser(user) qui supprime enfin définitivement l'utilisateur
	     */
	    int userid = Integer.parseInt(request.getParameter("userId"));
	    userDao.deleteUser(userDao.findUserById(userid));
	    request.setAttribute("users", userDao.findAll());
	    response.sendRedirect(request.getContextPath() + "/userslist");

	} else if (requestedUrl.equals("/userslist")) {
	    /**
	     * Si l'administrateur demande la liste des utilisateurs, je recupere la liste
	     * au niveau de la base de donnees via userDa.findAll()
	     */
	    request.setAttribute("users", userDao.findAll());
	    getServletContext().getRequestDispatcher(PAGE_USERS_LIST).forward(request, response);

	} else if (requestedUrl.equals("/login")) {
	    /**
	     * Si l'utilisateur veut se logger direction -> page login
	     */
	    getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);

	} else if (requestedUrl.equals("/createAcount")) {
	    getServletContext().getRequestDispatcher(PAGE_ACOUNT).forward(request, response);

	} else if (requestedUrl.equals("/delete")) {
	    /**
	     * Si l'utilisateur veut supprimer une de ses images, je récupere les
	     * informations de l'image au niveau de la BD via l'identfiant de l'image
	     * imageDao.findImageById(imageId) puis j'appelle la methode de suppression
	     * imageDao.deleteImage(image) qui supprime enfin définitivement l'image. Une
	     * fois l'image supprimé direction -> page gallery
	     */
	    int imageId = Integer.parseInt(request.getParameter("imageId"));
	    imageDao.deleteImage(imageDao.findImageById(imageId));

	    request.setAttribute("images", imageDao.getAll());
	    request.setAttribute("albums", albumDao.findAll());
	    response.sendRedirect(request.getContextPath() + "/index");
	} else if (requestedUrl.equals("/addalbum")) {
	    /**
	     * Si l'utilisateur veut ajouter un nouveau album direction -> page_ajout_album
	     */
	    getServletContext().getRequestDispatcher(PAGE_AJOUT_ALBUM).forward(request, response);
	} else if (requestedUrl.equals("/deletealbum")) {
	    /**
	     * Si l'utilisateur veut supprimer une de ses albums, je récupere les
	     * informations de l'album au niveau de la BD via l'identfiant de l'album
	     * albumDao.findAlbumById(albumId) puis j'appelle la methode de suppression
	     * imageDao.deleteImage(image) qui supprime enfin définitivement l'album. Une
	     * fois l'album supprimé direction -> page_album_user
	     */
	    int id = Integer.parseInt(request.getParameter("albumId"));
	    albumDao.deleteAlbum(albumDao.findAlbumById(id));
	    response.sendRedirect(request.getContextPath() + "/user-album");
	} else if (requestedUrl.equals("/updatealbum")) {
	    /**
	     * Si l'utilisateur ou l'administrateur veut modifier un album -> Récuperation
	     * de l'identifiant de l'album albumId -> Rechercher l'album dans la BD via l'id
	     * albumDao.findAlbumById(albumId) -> Envoi les informations au formulaire
	     */
	    if (request.getParameter("albumId") != null) {
		albumId = Integer.parseInt(request.getParameter("albumId"));
		Album album = albumDao.findAlbumById(albumId);
		request.setAttribute("album", album);
	    }
	    getServletContext().getRequestDispatcher(PAGE_AJOUT_ALBUM).forward(request, response);
	} else if (requestedUrl.equals("/user-album")) {
	    /**
	     * Si l'utilisateur ou l'administrateur demande à consulter ses albums Je
	     * recupere tous les album puis je fait le filtre au niveau de la page jsp
	     */
	    request.setAttribute("albums", albumDao.findAll());
	    getServletContext().getRequestDispatcher(PAGE_USER_ALBUM).forward(request, response);
	} else if (requestedUrl.equals("/update")) {
	    /**
	     * Si l'utilisateur veut modifier une de ses photos, meme principe avec la
	     * modification d'un album
	     */
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
	    /**
	     * Si l'utilisateur veux ajouter une photo, je recupere d'abord tous les albums
	     * qu'il a creer puis direction -> page_ajout_photo
	     */
	    user = (User) session.getAttribute("user");
	    request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
	    getServletContext().getRequestDispatcher(PAGE_AJOUT_PHOTO).forward(request, response);
	} else if (requestedUrl.equals("/image-detail")) {
	    /**
	     * Si l'utilisateur ou l'administrateur demande à consulter les details de sa
	     * photo Je recupere l'image concerné via son id au niveau de la BD
	     */
	    if (request.getParameter("imageId") != null) {
		int imageId = Integer.parseInt(request.getParameter("imageId"));
		request.setAttribute("image", imageDao.findImageById(imageId));
	    }
	    getServletContext().getRequestDispatcher(PAGE_IMAGE_DETAIL).forward(request, response);
	} else if (requestedUrl.equals("/deconnection")) {
	    /**
	     * Si l'utilisateur click sur le bouton deconnecter, je supprimer sa session par
	     * la methode session.invalidate() puis direction -> page index
	     */
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

	if (requestedUrl.equals("/login")) {
	    /**
	     * Recuperation du username et du mot de passe pour identifier l'utilisateur
	     */
	    String username = request.getParameter("username").trim();
	    String password = request.getParameter("password").trim();
	    /**
	     * Recherche de l'utilisateur via son username et son mot de passe
	     */
	    user = userDao.findUserByUsernammeAndPassword(username, password);

	    if (user != null && user.getPassword().equals(password)) {
		/**
		 * Je lui affecte une session via session.setAttribute pour pouvoir l'identifier
		 * tout au long de sa visite
		 */
		session.setAttribute("username", username);
		session.setAttribute("user", user);
		user = (User) session.getAttribute("user");
		/**
		 * Je recupereet affiches tous ses albums et ses images et tous les autres qui
		 * sont partagés
		 */
		request.setAttribute("albumsUser", albumDao.getAllbumUserByUser(user));
		request.setAttribute("images", imageDao.getAll());
		request.setAttribute("albums", albumDao.findAll());
		/**
		 * Enfin direction -> page gallery
		 */
		response.sendRedirect(request.getContextPath() + "/index");

	    } else {
		/**
		 * Si son username ou son mot de passe est incorrecte je lui affiche une message
		 * d'erreur au niveau de la vue pour qu'il les resaisis
		 */
		request.setAttribute("error", "error");
		response.sendRedirect(request.getContextPath() + "/login");
	    }
	} else if (requestedUrl.equals("/createAcount") || requestedUrl.equals("/updateuser")) {
	    /**
	     * Recuperation des informations et creation d'un nouveau utilisateur
	     */
	    String username = request.getParameter("username").trim();
	    String password1 = request.getParameter("password1").trim();
	    String password2 = request.getParameter("password2").trim();
	    String email = request.getParameter("email").trim();

	    if (requestedUrl.equals("/updateuser")) {
		/**
		 * Si c'est une requete de modification, je recupere l'utilisateur via son ID
		 * puis je met a jour ses informations via la methode userDao.updtaeUser(user)
		 */
		if (password1.equals(password2)) {
		    user = userDao.findUserById(userId);
		    String role = request.getParameter("role");
		    if (user != null) {
			user.setUsername(username);
			user.setEmail(email);
			user.setPassword(password1);
			user.setRole(role);
			userDao.updateUser(user);
			request.setAttribute("users", userDao.findAll());
			response.sendRedirect(request.getContextPath() + "/userslist");
		    }
		}
	    } else {
		/**
		 * Sinon si c'est une requete de d'ajout, je recupere les informations au niveau
		 * du formulaire et j'applle la methode d'ajout userDao.addUser(user)
		 */
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
	    /**
	     * Sinon si c'est une requete de d'ajout d'un album, je recupere les
	     * informations au niveau du formulaire et j'applle la methode d'ajout
	     * albumDao.addAlbum(album)
	     */
	    String albumName = request.getParameter("albumName").trim();
	    boolean shared = request.getParameter("shared") != null;

	    user = (User) session.getAttribute("user");
	    album.setUser(user);
	    album.setAlbumName(albumName);
	    album.setShared(shared);
	    albumDao.addAlbum(album);
	    request.setAttribute("albums", albumDao.findAll());
	    response.sendRedirect(request.getContextPath() + "/index");

	} else if (requestedUrl.equals("/updatealbum")) {
	    /**
	     * Si c'est une requete de modification d'un album, je recupere l'album via son
	     * ID puis je met a jour ses informations via la methode
	     * albumDao.updtaAlbum(album)
	     */
	    Album al = albumDao.findAlbumById(albumId);
	    if (al != null) {
		String albumName = request.getParameter("albumName").trim();
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
	    /**
	     * Ces instructions permet l'ajout ou la modification d'une photo
	     */
	    // Date object
	    Date date = new Date();
	    long time = date.getTime();
	    Timestamp dat = new Timestamp(time);

	    String title = request.getParameter("title").trim();
	    String description = request.getParameter("description").trim();
	    int heigth = Integer.parseInt(request.getParameter("heigth"));
	    int width = Integer.parseInt(request.getParameter("width"));
	    int albumId = Integer.parseInt(request.getParameter("album"));
	    album = albumDao.findAlbumById(albumId);
	    user = (User) session.getAttribute("user");

	    // Recuperer le fichier selection par l'utilisateur
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
		/**
		 * Si c'est une modofication, meme priciper avec un album
		 */
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
		/**
		 * Si c'est un ajout, meme principe avec un album
		 */
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

    /**
     * Cette methode permet de d'enregistrer l'image dans le disque
     * 
     * @param part
     * @param imageName
     * @param chemin
     * @throws IOException
     */
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

    /**
     * Cette methode permet d'extraire et de recuperer le nom de l'image
     * 
     * @param part
     * @return
     */
    private static String getimageName(Part part) {
	for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
	    if (contentDisposition.trim().startsWith("filename")) {
		return contentDisposition.substring(contentDisposition.indexOf('=') + 1).trim().replace("\"", "");
	    }
	}
	return null;
    }
}
