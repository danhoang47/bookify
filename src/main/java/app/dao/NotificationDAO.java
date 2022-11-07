/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.BookingDTO;
import app.dto.NotificationDTO;
import app.dto.RoomTypeDTO;
import app.dto.UserDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class NotificationDAO {
    public List<NotificationDTO> getAllNotification(String userId) throws SQLException {
        List<NotificationDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getAllNotification @userId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, userId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                int id = rs.getInt("notify_id");
                String sourceId = rs.getString("source_id");
                String hotelId = rs.getString("hotel_id");
                int notifyTYpe = rs.getInt("notify_type");
                String notifyDate = rs.getString("notify_date");
                boolean isRead = rs.getBoolean("is_read");
                String hotelName = rs.getString("hotel_name");
                String actorName = rs.getString("actorName");
                String actorAvatar = rs.getString("actorAvatar");
                NotificationDTO notif = new NotificationDTO(id, userId, hotelId, 
                        sourceId, notifyTYpe, notifyDate, hotelName, actorName, isRead, actorAvatar);
                list.add(notif);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }
    
    public static void main(String[] args) throws SQLException {
        NotificationDAO notifDao = new NotificationDAO();
        System.out.println(notifDao.getAllNotification("ca8c99e4-a955-4439-baaf-dc02c6aacf5e"));
    }

    public void markAsRead(int notifId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update Notification set is_read = 1 where notify_id = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, notifId);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
}
