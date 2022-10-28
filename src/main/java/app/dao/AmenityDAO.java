/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.AmenityDTO;
import app.dto.AmenityTypeDTO;
import app.dto.HotelAmenityDTO;
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

    public static void main(String[] args) throws SQLException {
        AmenityDAO dao = new AmenityDAO();
        System.out.println(dao.getTypes());
    }
}
