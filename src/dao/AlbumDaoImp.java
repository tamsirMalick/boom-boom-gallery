package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Album;

@Stateless
public class AlbumDaoImp implements IAlbumDao {
	
	@PersistenceContext
	private EntityManager em;
	TypedQuery<Album> query;

	@Override
	public void addAlbum(Album album) {
		em.persist(album);
		
	}

	@Override
	public void updateAlbum(Album album) {
		em.merge(album);
		
	}

	@Override
	public void deleteAlbum(Album album) {
		em.remove(em.merge(album));
		
	}

	@Override
	public Album getAlbumById(int albumId) {
		return em.find(Album.class, albumId);
	}

	@Override
	public List<Album> getAll() {
		query = em.createNamedQuery("Album.findAll", Album.class);
		return query.getResultList();
	}

	@Override
	public List<Album> getAllPhotoByAlbumName(String albumName) {
		return em.createNamedQuery("Album.getAllPhotoByAlbumName", Album.class)
				.setParameter("albumName", albumName)
				.getResultList();

	}

}
