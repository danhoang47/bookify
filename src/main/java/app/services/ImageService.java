/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.ImageDAO;
import java.util.List;

/**
 *
 * @author toten
 */
public class ImageService {
    private ImageDAO imgDAO;
    
    public ImageService() {
        imgDAO = new ImageDAO();
    }
    
    public boolean addImage(String hotel_id, List<String> images, int type) {
        boolean check = imgDAO.addImage(hotel_id, images, type);
        return check;
    }
}
