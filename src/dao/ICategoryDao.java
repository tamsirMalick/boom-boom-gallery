package dao;

import java.util.List;

import entities.Category;

public interface ICategoryDao {
	public Category addCategory(Category cat);
	public Category deleteCategory(int catId);
	public List<Category> findAll();

}
