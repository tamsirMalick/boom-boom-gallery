package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Image;

@Local
public interface IImageDao {
	void addImage(Image image);
	void updateImage(Image image);
	void deleteImage(Image image);
	Image findImageById(int imageId);
	List<Image> findAll();
}
