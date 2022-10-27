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
import model.dto.AvaiableAmenity;

/**
 *
 * @author toten
 */
public class AvaiableAmenityDAO {
     static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;
    
    public static List<AvaiableAmenity> listAll() {
        try {
            String query = "select * from Amenity";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<AvaiableAmenity> listAmenity = new ArrayList<>();

            while (rs.next()) {

                listAmenity.add(new AvaiableAmenity(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                
                
            }

            return listAmenity;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void main(String[] args) {
        List<AvaiableAmenity> listAmenity = new AvaiableAmenityDAO().listAll();
        System.out.println(listAmenity.size());
    }
}
