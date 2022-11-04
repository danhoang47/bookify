/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.AmenityDTO;
import app.dto.AmenityTypeDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class AmenityDAO {
    
//    static Connection conn;
//    static PreparedStatement ps;
//    static CallableStatement cs;
//    static ResultSet rs;

    public List<AmenityDTO> getAll() throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getDefaultAmenities";
        List<AmenityDTO> amenities = null;

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            rs = cs.executeQuery();
            amenities = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("amenity_id");
                String name = rs.getString("amenity_name");
                String icon = rs.getString("icon");
                String type = rs.getString("type_id");

                amenities.add(new AmenityDTO(id, name, icon, type));
            }

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
        }

        return amenities;
    }
    
    public List<AmenityTypeDTO> getTypes() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Amenity_type";
        List<AmenityTypeDTO> amenityTypes = null;

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            amenityTypes = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("amenity_type_id");
                String name = rs.getString("amenity_type_name");

                amenityTypes.add(new AmenityTypeDTO(id, name));
            }

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return amenityTypes;
    }
    
    public void add(AmenityDTO amenity) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into Amenity values(?, ?, ?, ?)";
        
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, amenity.getId());
            ps.setString(2, amenity.getName());
            ps.setString(3, amenity.getIcon());
            ps.setString(4, amenity.getType());
            ps.executeUpdate();
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }
    
    public static void addAmenity(String amenityId, String amenityName, String amenityType) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "insert into amenity values (?, ?, ?, ?)";
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, amenityId);
            ps.setString(2, amenityName);
            ps.setString(3, "faPencil");
            ps.setString(4, amenityType);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static List<String> listAllAmenityId() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "select amenity_id from amenity";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<String> listAmentiesId = new ArrayList<>();

            while (rs.next()) {

                listAmentiesId.add(rs.getString(1));

            }

            return listAmentiesId;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        List<AmenityDTO> listAmen = new AmenityDAO().getAll();
        
        AmenityDAO amd = new AmenityDAO();
        amd.addAmenity("new-3d18e756-ae0c-4bfb-a624-awjdaw8dy9", "Boi Loi", "230198c8-317c-4f60-8a60-78e6ab002963");
    }
}
