package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

import dao.IUserDao;
import entities.User;

@WebService()
@Stateless
public class UserWS {
	
	@EJB
	private IUserDao dao;
	
	@WebMethod(operationName="addUser")
	@Oneway
	public void addUser(User user) {
		dao.addUser(user);
	}
	
	@WebMethod(operationName="updateUser")
	@Oneway
	public void updateUser(User user) {
		dao.updatUser(user);
	}
	
	@WebMethod(operationName="deleteUser")
	@Oneway
	public void deleteUser(User user) {
		dao.deleteUser(user);
	}
	
	@WebMethod(operationName="findUserById")
	public User findUserById(int userId) {
		return dao.findUserById(userId);
	}
	
	@WebMethod(operationName="findAll")
	public List<User> findAll() {
		return dao.findAll(); 
	}
	
	@WebMethod(operationName="findUserByUsernammeAndPassword")
	public User findUserByUsernammeAndPassword(String username, String password) {
		return dao.findUserByUsernammeAndPassword(username, password);
	}
}
