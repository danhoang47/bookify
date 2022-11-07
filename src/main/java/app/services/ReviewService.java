/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.ReviewDAO;
import java.sql.SQLException;

/**
 *
 * @author toten
 */
public class ReviewService {

    private ReviewDAO reviewDao;
    
    public ReviewService() {
        reviewDao = new ReviewDAO();
    }
    
    public boolean addNewReview(String hotelId, String userId, String content, int communicationPoint, int accuracyPoint, int locationPoint, int valuePoint) throws SQLException {
        
        return reviewDao.addNewReview(hotelId, userId, content, communicationPoint, accuracyPoint, locationPoint, valuePoint);
    }
}
