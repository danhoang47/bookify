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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.Amenity;
import model.dto.HotelType;

/**
 *
 * @author toten
 */
public class HotelTypeDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public static List<HotelType> hotelTypes() {

        try {
            String query = "select * from hotelType";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<HotelType> listHotelTypes = new ArrayList<>();

            while (rs.next()) {

                listHotelTypes.add(new HotelType(rs.getString(1), rs.getString(2), rs.getString(3)));

            }

            return listHotelTypes;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        List<HotelType> list = new HotelTypeDAO().hotelTypes();
        
        System.out.println(list);
    }
}
