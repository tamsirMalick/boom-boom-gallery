package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Album;
import entities.User;

@Local
public interface IAlbumDao {
	void addAlbum(Album album);
	void updateAlbum(Album album);
	void deleteAlbum(Album album);
	Album getAlbumById(int albumId);
	List<Album> getAll();
	List<Album> getAllPhotoByAlbumName(String albumName);
	List<Album> getAllbumUserByUser(User user);
}
