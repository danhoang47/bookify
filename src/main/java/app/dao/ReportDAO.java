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

    public static void main(String[] args) throws SQLException {
        List<ReportDTO> listReport = new ReportDAO().getAll(11);
        System.out.println(listReport);
    }
}
