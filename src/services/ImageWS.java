package services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

import dao.IImageDao;
import entities.Image;

@WebService()
@Stateless
public class ImageWS {
	
	@EJB
	private IImageDao dao;
	
	@WebMethod(operationName="addImage")
	@Oneway
	public void addImage(Image image) {
		dao.addImage(image);
	}
	
	@WebMethod(operationName="updateImage")
	@Oneway
	public void updateImage(Image image) {
		dao.updateImage(image);
	}
	
	@WebMethod(operationName="deleteImage")
	@Oneway
	public void deleteImage(Image image) {
		dao.deleteImage(image);
	}
	
	@WebMethod(operationName="findImageById")
	public void findUserById(int imageId) {
		dao.findImageById(imageId);
	}
	
	@WebMethod(operationName="findAll")
	public List<Image> findAll() {
		return dao.findAll(); 
	}
	
}