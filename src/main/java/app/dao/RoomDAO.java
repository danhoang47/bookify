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
        dao.RoomDAO rd = new dao.RoomDAO();
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
    
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
                UUID uuid = UUID.randomUUID();
                System.out.println(uuid.toString());
        }
    }
}
