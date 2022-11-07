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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class RoomDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public boolean addNewRoom(String hotel_id, int numberOfRoom, String roomTypeId) {
        List<Integer> check = new ArrayList<>();

        try {
            String query = "insert into Room values (?,?,?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < numberOfRoom; i++) {
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                ps.setString(1, uuid.toString());
                ps.setString(2, hotel_id);
                ps.setString(3, roomTypeId);
                int x = ps.executeUpdate();

                check.add(x);
            }

            if (check.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<String> getHotelNumberOfRoom(String hotel_id) {
        List<String> listRoomId = new ArrayList<>();
        try {
            String query = "select room_id from Room where hotel_id=?";
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);
            rs = ps.executeQuery();
            
            while (rs.next()) {
               listRoomId.add(rs.getString("room_id"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoomId;
    }
    
    public List<String> getAllRooms() {
        List<String> listRoomId = new ArrayList<>();
        try {
            String query = "select room_id from Room";
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            
            while (rs.next()) {
               listRoomId.add(rs.getString("room_id"));
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listRoomId;
    }


    public static void main(String[] args) {
       List<String> a = new RoomDAO().getAllRooms();
        System.out.println(a.size());
    }
}
