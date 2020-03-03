package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;




/**
 * The persistent class for the image database table.
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name="Image.findAll", query="SELECT i FROM Image i"),
		@NamedQuery(name="findImageById", query="SELECT i FROM Image i WHERE i.imageID=:id")
		})
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int imageID;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Date created;

	private String description;

	private int heigth;

	@Lob
	@Column(name="image_path")
	private String imagePath;

	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	private String title;

	private int width;

	//bi-directional many-to-one association to Album
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="album_id")
	private Album album;

	//bi-directional many-to-one association to Category
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;

	//bi-directional many-to-one association to User
	@XmlTransient
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Image() {
	}

	public int getImageID() {
		return this.imageID;
	}

	public void setImageID(int imageID) {
		this.imageID = imageID;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getHeigth() {
		return this.heigth;
	}

	public void setHeigth(int heigth) {
		this.heigth = heigth;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}


	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getWidth() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Album getAlbum() {
		return this.album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Image [imageID=" + imageID + ", created=" + created + ", description=" + description + ", heigth="
				+ heigth + ", imagePath=" + imagePath + ", modified=" + modified + ", title=" + title + ", width="
				+ width + ", album=" + album + ", user=" + user + "]";
	}

	
	
	

}