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
import model.dto.Review;

/**
 *
 * @author toten
 */
public class ReviewDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public List<Review> listReview(String hotel_id) {

//select * from Review where hotel_id=?
        try {
            String query = "select * from Review where hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<Review> listReview = new ArrayList<>();

            while (rs.next()) {
                listReview.add(new Review(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
            }

            return listReview;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;


    }

}
