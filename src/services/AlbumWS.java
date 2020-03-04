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
	public void addAlbum(Album Album) {
		dao.addAlbum(Album);
	}
	
	@WebMethod(operationName="updateAlbum")
	@Oneway
	public void updateAlbum(Album Album) {
		dao.updateAlbum(Album);
	}
	
	@WebMethod(operationName="deleteAlbum")
	@Oneway
	public void deleteAlbum(Album Album) {
		dao.deleteAlbum(Album);
	}
	
	@WebMethod(operationName="findAlbumById")
	public Album findAlbumById(int AlbumId) {
		return dao.getAlbumById(AlbumId);
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
