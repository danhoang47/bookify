/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.ReportDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class ReportDAO {

    public List<ReportDTO> getAll(int month) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select rp.*, ht.hotel_name, (ud.subname + ud.name) as username, ud.avatar from Report as rp, userDetail as ud, hotel as ht \n"
                + "where rp.user_id=ud.user_id and rp.hotel_id=ht.hotel_id and month(rp.report_date)=?";
        List<ReportDTO> reports = null;

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();
            reports = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("report_id");
                String hotelid = rs.getString("hotel_id");
                String userId = rs.getString("user_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                Date reportDate = rs.getDate("report_date");
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                String hotelName = rs.getString("hotel_name");

                reports.add(new ReportDTO(id, hotelid, hotelName, userId, username, avatar, title, content, reportDate));
            }

        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return reports;
    }

    public boolean addReport(String hotelId, String userId, String title, String content) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "insert into Report values (?, ?, ?, ?, ?, GETDATE())";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            UUID uuid = UUID.randomUUID();
            ps.setString(1, uuid.toString());
            ps.setString(2, hotelId);
            ps.setString(3, userId);
            ps.setString(4, title);
            ps.setString(5, content);
            int a = ps.executeUpdate();

            if (a == 1) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return false;
    }

    public int checkNumberBookingTime(String hotelId, String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select count(*) as bookingTimes from Booking bk, room rm, hotel ht where bk.room_id=rm.room_id and rm.hotel_id=ht.hotel_id\n"
                + "and ht.hotel_id=? and bk.user_id=?\n"
                + "and bk.check_out >= GETDATE()";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            UUID uuid = UUID.randomUUID();
            ps.setString(1, hotelId);
            ps.setString(2, userId);
            
            rs = ps.executeQuery();
            int result = 0;
            
            while(rs.next()) {
                result = rs.getInt("bookingTimes");
                return result;
            }
            
            return result;

        } catch (Exception ex) {
            Logger.getLogger(ReportDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return 0;
    }

    public static void main(String[] args) throws SQLException {
        List<ReportDTO> listReport = new ReportDAO().getAll(11);
        System.out.println(listReport);
    }
}
