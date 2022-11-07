/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.ReviewDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Review;

/**
 *
 * @author toten
 */
public class ReviewDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<ReviewDTO> listReview(String hotel_id) {

        try {
//            String query = "select rv.*, (ud.subname + ' ' + ud.name) as displayName, ud.avatar\n"
//                    + "from Review as rv, userDetail as ud \n"
//                    + "where rv.user_id=ud.user_id and rv.hotel_id=?";

            String query = "select rv.*, CONVERT(VARCHAR(5),rv.create_at,108) as minutes, (ud.subname + ' ' + ud.name) as displayName, ud.username, ud.avatar from Review as rv, userDetail as ud \n"
                    + "where rv.user_id=ud.user_id and rv.hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();

            List<ReviewDTO> listReview = new ArrayList<>();

            while (rs.next()) {
                listReview.add(new ReviewDTO(rs.getString("review_id"), rs.getString("hotel_id"), rs.getString("user_id"), rs.getString("content"), rs.getInt("source_id"), rs.getInt("communication_point"), rs.getInt("accuracy_point"), rs.getInt("location_point"), rs.getInt("value_point"), rs.getDate("create_at"), rs.getString("displayName"), rs.getString("avatar"), rs.getString("username"), rs.getString("minutes")));
            }

            return listReview;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ReviewDTO> listReviewWithPoint(String hotel_id, int point) {

        try {
            String query = "select rv.*, CONVERT(VARCHAR(5),rv.create_at,108) as minutes, (ud.subname + ' ' + ud.name) as displayName, ud.username, ud.avatar \n"
                    + "from Review as rv, userDetail as ud where rv.user_id=ud.user_id and rv.hotel_id=?\n"
                    + "and (rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);
            ps.setInt(2, point);

            rs = ps.executeQuery();

            List<ReviewDTO> listReview = new ArrayList<>();

            while (rs.next()) {
                listReview.add(new ReviewDTO(rs.getString("review_id"), rs.getString("hotel_id"), rs.getString("user_id"), rs.getString("content"), rs.getInt("source_id"), rs.getInt("communication_point"), rs.getInt("accuracy_point"), rs.getInt("location_point"), rs.getInt("value_point"), rs.getDate("create_at"), rs.getString("displayName"), rs.getString("avatar"), rs.getString("username"), rs.getString("minutes")));
            }

            return listReview;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getNumberOfReview(int month) {

        try {
            String query = "select count(*) as reviewNumber from Review where month(create_at)=? ";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setInt(1, month);

            rs = ps.executeQuery();

            int res = 0;

            while (rs.next()) {
                res = rs.getInt("reviewNumber");
            }

            return res;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public boolean addNewReview(String hotelId, String userId, String content, int communicationPoint, int accuracyPoint, int locationPoint, int valuePoint) {

        try {
            String query = "insert into Review values(?, ?, ?, ?, 0, ?, ?, ?, ?, GETDATE())";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            UUID uuid = UUID.randomUUID();
            ps.setString(1, uuid.toString());
            ps.setString(2, hotelId);
            ps.setString(3, userId);
            ps.setString(4, content);
            ps.setInt(5, communicationPoint);
            ps.setInt(6, accuracyPoint);
            ps.setInt(7, locationPoint);
            ps.setInt(8, valuePoint);

            int res = ps.executeUpdate();

            if (res == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        List<ReviewDTO> listReviewDTO = new ReviewDAO().listReview("0e496299-ba26-4270-8ba9-f642c6843a62");
        System.out.println(new ReviewDAO().getNumberOfReview(11));
        System.out.println(listReviewDTO);
    }
}
