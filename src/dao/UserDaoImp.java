package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.User;

@Stateless
public class UserDaoImp {
	
	@PersistenceContext(name="Boomboom_gallery")
	private EntityManager em;
	private TypedQuery<User> query; 


	public void addUser(User user) {
		em.persist(user);
	}


	public User updatUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteUser(int userId) {
		// TODO Auto-generated method stub
		
	}


	public User findUserById(int userId) {
		return null;
	}


	public List<User> findAll() {
		query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

}
