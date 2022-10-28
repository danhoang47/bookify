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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.AvaiableAmenity;
import model.dto.HotelType;

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
        AvaiableAmenityDAO amd = new AvaiableAmenityDAO();
        HotelTypeDAO htd = new HotelTypeDAO();

        List<AvaiableAmenity> listAmenity = amd.listAll();
        List<HotelType> listHotelType = htd.hotelTypes();
        
         System.out.println(listAmenity);         
         System.out.println(listHotelType);

    }
            
}
