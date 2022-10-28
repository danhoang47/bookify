/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import static dao.ImageDAO.conn;
import static dao.ImageDAO.ps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Amenity;
import model.dto.Image;

/**
 *
 * @author toten
 */
public class AmenityDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public static List<Amenity> hotelAmenities(String hotel_id) {

        try {
            String query = "select ha.hotel_amenities_id, a.amenity_name, a.icon from HotelAmenities as ha, Amenity as a where ha.amenity_id=a.amenity_id and "
                    + "ha.hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();

            List<Amenity> listHotelAmenities = new ArrayList<>();

            while (rs.next()) {

                listHotelAmenities.add(new Amenity(rs.getString(1), rs.getString(2), rs.getString(3)));

            }

            return listHotelAmenities;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean addHotelAmenties(List<String> amenties, String hotel_id) {
//        insert into HotelAmenities values (?, ?, 1, ?)
        List<Integer> check = new ArrayList<>();
        try {
            String query = "insert into HotelAmenities values (?, ?, 1, ?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < amenties.size(); i++) {
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                ps.setString(1, uuid.toString());
                ps.setString(2, amenties.get(i));
                ps.setString(3, hotel_id);
                
                 int x = ps.executeUpdate();
                 if(x!=0) {
                    check.add(x);
                }
            }

           if(check.size()>0) {
                return true;
            } else {
               return false;
           }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static List<Amenity> listAllAmenties() {

        try {
            String query = "select * from Amenity";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<Amenity> listAmenties = new ArrayList<>();

            while (rs.next()) {

                listAmenties.add(new Amenity(rs.getString(1), rs.getString(2), rs.getString(3)));

            }

            return listAmenties;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        

    }
}
