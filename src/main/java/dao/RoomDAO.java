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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class RoomDAO {
    
    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;
    
     public static double averagePrice(String hotel_id) {

        try {
            String query = "select avg(room_price) from room where hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();

            double result=0;

            while (rs.next()) {

                result = rs.getDouble(1);

            }

            return result;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
     public static void main(String[] args) {
        double averagePrice = new RoomDAO().averagePrice("043a69b8-738f-4205-9a12-f96e1486f1e6");
         System.out.println(averagePrice);
    }
            
}
