/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class BookmarkDAO {
    
    public boolean add(String hotelId, String userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
         try {
            String sql = "insert into Bookmark values (?,?,?, getdate())";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, UUID.randomUUID().toString());
            ps.setString(2, userId);
            ps.setString(3, hotelId);
            result = ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return result == 1 ? true : false;
    }
    
    public boolean delete(String hotelId, String userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        int result = 0;
         try {
            String sql = "delete from Bookmark where user_id = ? and hotel_id = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setString(2, hotelId);
            result = ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return result == 1 ? true : false;
    }
    
    public static void main(String[] args) {
        System.out.println(UUID.randomUUID().toString());
    }
}
