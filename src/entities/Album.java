package entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the album database table.
 * 
 */
@Entity
@NamedQuery(name="Album.findAll", query="SELECT a FROM Album a")
public class Album implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="album_id")
	private int albumId;

	@Column(name="album_name")
	private String albumName;
	
	private boolean shared;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	//bi-directional many-to-one association to Image
	@OneToMany(mappedBy="album", fetch=FetchType.LAZY)
	private List<Image> images;

	public Album() {
	}

	public int getAlbumId() {
		return this.albumId;
	}

	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}

	public String getAlbumName() {
		return this.albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}
	
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Image> getImages() {
		return this.images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public Image addImage(Image image) {
		getImages().add(image);
		image.setAlbum(this);

		return image;
	}

	public Image removeImage(Image image) {
		getImages().remove(image);
		image.setAlbum(null);

		return image;
	}

}