package dao;

import java.util.List;
import javax.ejb.Remote;
import entities.User;

@Remote
public interface IUserDao {
	public void addUser(User user);
	public User updatUser(User user);
	public void deleteUser(int userId);
	public User findUserById(int userId);
	public List<User> findAll(); 

}
