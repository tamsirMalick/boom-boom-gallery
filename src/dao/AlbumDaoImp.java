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
		TypedQuery<Album> query = em.createNamedQuery("Album.findAll", Album.class);
		return query.getResultList();
	}

}
