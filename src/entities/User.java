package entities;

import java.io.Serializable;
import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name="User.findAll", query="SELECT u FROM User u"),
	@NamedQuery(name="User.findById", query="SELECT u FROM User u WHERE u.userid=:id"),
	@NamedQuery(name="User.findUserByUsernammeAndPassword", query="SELECT u FROM User u WHERE u.username=:username AND u.password=:password")
})
public class User implements Serializable {
//	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userid;

	private String email;

	private String password;

	private String photoUrl;
	

	private String role;

	private String username;

	//bi-directional many-to-one association to Album
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Album> albums;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="user")
	private List<Image> images;

	public User() {
	}
	
	

	public User(String username, String password) {
		super();
		this.password = password;
		this.username = username;
	}



	public int getUserid() {
		return this.userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Album> getAlbums() {
		return this.albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}

	public Album addAlbum(Album album) {
		getAlbums().add(album);
		album.setUser(this);

		return album;
	}

	public Album removeAlbum(Album album) {
		getAlbums().remove(album);
		album.setUser(null);

		return album;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setUser(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setUser(null);

		return image;
	}

	@Override
	public String toString() {
		return "User [userid=" + userid + ", email=" + email + ", password=" + password + ", photo="+", role=" + role + ", username=" + username + ", albums=" + albums
				+ ", images=" + images + "]";
	}
	
	

}