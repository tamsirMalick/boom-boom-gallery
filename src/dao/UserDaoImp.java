package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entities.User;

@Stateless
public class UserDaoImp implements IUserDao {

    @PersistenceContext
    private EntityManager em;
    private TypedQuery<User> query;

    @Override
    public void addUser(User user) {
	em.persist(user);
    }

    @Override
    public void updatUser(User user) {
	em.merge(user);
    }

    @Override
    public void deleteUser(User user) {
	em.remove(em.merge(user));

    }

    @Override
    public User findUserById(int userId) {
	return em.find(User.class, userId);
    }

    @Override
    public List<User> findAll() {
	query = em.createNamedQuery("User.findAll", User.class);
	return query.getResultList();
    }

    @Override
    public User findUserByUsernammeAndPassword(String username, String password) {
	query = em.createNamedQuery("User.findUserByUsernammeAndPassword", User.class)
		.setParameter("username", username).setParameter("password", password);

	try {
	    return query.getSingleResult();
	} catch (NoResultException ex) {
	    System.out.println("User non trouvé!");
	}
	return null;
    }

}
