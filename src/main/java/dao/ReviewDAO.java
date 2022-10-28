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
            String query = "select rv.review_id, rv.hotel_id, rv.user_id, ud.name, ud.avatar, rv.content, rv.source_id, rv.create_at from "
                    + "Review as rv, userDetail as ud where rv.user_id=ud.user_id and  rv.hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();

            List<Review> listReview = new ArrayList<>();

            while (rs.next()) {
                listReview.add(new Review(rs.getString(1), null, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getDate(8)));
            }

            return listReview;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;


    }
    
    public static void main(String[] args) {
        ReviewDAO rd = new ReviewDAO();
        
        List<Review> listRreview =  rd.listReview("043a69b8-738f-4205-9a12-f96e1486f1e6");
        
        System.out.println(listRreview);
    }
}
