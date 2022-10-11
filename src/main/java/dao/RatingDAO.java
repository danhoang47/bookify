/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Image;
import model.dto.Rating;

/**
 *
 * @author toten
 */
public class RatingDAO {
    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public static Rating getRatingPoint(String hotel_id) {

        try {
            String query = "select AVG(communication_point), AVG(accurary_point), AVG(loccation_point), AVG(value_point) from Rating where hotel_id=?";

           conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();
            
            while (rs.next()) {
                Rating ratingPoint = new Rating(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
                
                return ratingPoint;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        RatingDAO rating = new RatingDAO();
        
        Rating rt = rating.getRatingPoint("043a69b8-738f-4205-9a12-f96e1486f1e6");
        
        System.out.println(rt);
    }
}
