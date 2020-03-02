package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Album;

@Local
public interface IAlbumDao {
	void addAlbum(Album album);
	void updateAlbum(Album album);
	void deleteAlbum(Album album);
	Album getAlbumById(int albumId);
	List<Album> getAll();
}
