package dao;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface IUserDao {
	void addUser(User user);
	void updatUser(User user);
	void deleteUser(User user);
	User findUserById(int userId);
	public List<User> findAll();
	User findUserByUsernammeAndPassword(String username, String password);

}
