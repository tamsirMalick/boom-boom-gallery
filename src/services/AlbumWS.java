package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

import dao.IAlbumDao;
import entities.Album;
import entities.User;

@WebService()
@Stateless
public class AlbumWS {
	
	@EJB
	private IAlbumDao dao;
	
	@WebMethod(operationName="addAlbum")
	@Oneway
	public void addAlbum(Album album) {
		dao.addAlbum(album);
	}
	
	@WebMethod(operationName="updateAlbum")
	@Oneway
	public void updateAlbum(Album album) {
		dao.updateAlbum(album);
	}
	
	@WebMethod(operationName="deleteAlbum")
	@Oneway
	public void deleteAlbum(Album album) {
		dao.deleteAlbum(album);
	}
	
	@WebMethod(operationName="findAlbumById")
	public Album findAlbumById(int albumId) {
		return dao.getAlbumById(albumId);
	}
	
	@WebMethod(operationName="findAll")
	public List<Album> findAll() {
		return dao.getAll(); 
	}
	
	@WebMethod(operationName="getAllImageByAlbumName")
	public List<Album> getAllImageByAlbumName(String albumName) {
		return dao.getAllPhotoByAlbumName(albumName); 
	}
	
	@WebMethod(operationName="getAllbumUserByUser")
	public List<Album> getAllbumUserByUser(User user) {
		return dao.getAllbumUserByUser(user); 
	}
	
}
