/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class RoomTypeDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public String addNewRoomType(int price, String bedType, int bedNumber, String bathType, int bathNumber, boolean isPrivateBath, 
            String guests_id, int numberOfGuest, int numberOfRoom) {
        try {
            String query = "insert into RoomType values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            
            UUID uuid = UUID.randomUUID();
            ps.setString(1, uuid.toString());
            ps.setInt(2, price);
            ps.setString(3, bedType);
            ps.setInt(4, bedNumber);
            ps.setString(5, bathType);
            ps.setInt(6, bathNumber);
            ps.setInt(7, isPrivateBath==false?0:1);
            ps.setString(8, guests_id);
            ps.setInt(9, numberOfGuest);
            ps.setInt(10, numberOfRoom);

            int x = ps.executeUpdate();
            if(x==1) {
                return uuid.toString();
            } else {
                return null;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        RoomTypeDAO rtd = new RoomTypeDAO();
       String a = rtd.addNewRoomType(123, "123", 123, "123", 123, true, "123", 3, 2);
        System.out.println(a);
    }
}
