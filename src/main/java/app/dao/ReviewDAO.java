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
            String query = "select rv.*, (ud.subname + ' ' + ud.name) as displayName, ud.avatar\n"
                    + "from Review as rv, userDetail as ud \n"
                    + "where rv.user_id=ud.user_id and rv.hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();

            List<ReviewDTO> listReview = new ArrayList<>();

            while (rs.next()) {
                listReview.add(new ReviewDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getDate(10), rs.getString(11), rs.getString(12)));
            }

            return listReview;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ReviewDTO> listReviewWithPoint(String hotel_id, int point) {

        try {
            String query = "select rv.*, (ud.subname + ' ' + ud.name) as displayName, ud.avatar \n"
                    + "from Review as rv, userDetail as ud where rv.user_id=ud.user_id and rv.hotel_id=?\n"
                    + "and (rv.communication_point + rv.accuracy_point + rv.location_point + rv.value_point)/4=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);
            ps.setInt(2, point);

            rs = ps.executeQuery();

            List<ReviewDTO> listReview = new ArrayList<>();

            while (rs.next()) {
                listReview.add(new ReviewDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getDate(10), rs.getString(11), rs.getString(12)));
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

    public static void main(String[] args) {
        List<ReviewDTO> listReviewDTO = new ReviewDAO().listReview("0e496299-ba26-4270-8ba9-f642c6843a62");
        System.out.println(new ReviewDAO().getNumberOfReview(11));
        System.out.println(listReviewDTO);
    }
}
