package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.Image;

@Stateless
public class ImageDaoImp implements IImageDao{
	
	@PersistenceContext
	private EntityManager em;
	private TypedQuery<Image> query;

	@Override
	public void addImage(Image image) {
		em.persist(image);
		
	}

	@Override
	public void updateImage(Image image) {
		em.merge(image);
		
	}

	@Override
	public void deleteImage(Image image) {
		em.remove(em.merge(image));
		
	}

	@Override
	public List<Image> findAll() {
		query = em.createNamedQuery("image.findAll", Image.class);
		return query.getResultList();
	}

	@Override
	public Image findImageById(int imageId) {
		return em.find(Image.class, imageId);
	}

}
