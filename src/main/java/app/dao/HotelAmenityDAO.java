/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.AmenityDTO;
import java.util.List;
import java.util.ArrayList;
import app.dto.HotelAmenityDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HotelAmenityDAO {
    
     private Connection conn;
     private PreparedStatement ps;
     private CallableStatement cs;
     private ResultSet rs;
    
     public List<HotelAmenityDTO> get(String hotelId) throws SQLException {
        String sql = "proc_getHotelAmenities @hotelId = ?";
        List<HotelAmenityDTO> hotelAmenityDtos = null;

        try {
            conn = new DBContext().getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            hotelAmenityDtos = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("hotel_amenities_id");
                String amenityId = rs.getString("amenity_id");
                String amenityName = rs.getString("amenity_name");
                String amenityTypeId = rs.getString("amenity_type_id");
                String amenityTypeName = rs.getString("amenity_type_name");
                String icon = rs.getString("icon");

                HotelAmenityDTO hotelAmenityDto
                        = new HotelAmenityDTO(id, amenityId, hotelId, icon,
                                amenityName, amenityTypeName,
                                amenityTypeId);

                hotelAmenityDtos.add(hotelAmenityDto);
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

        return hotelAmenityDtos;
    }

    public void add(HotelAmenityDTO hotelAmenityDto) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into HotelAmenities values(?, ?, ?)";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hotelAmenityDto.getId());
            ps.setString(2, hotelAmenityDto.getAmenityId());
            ps.setString(3, hotelAmenityDto.getHotelId());
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void update(List<HotelAmenityDTO> amenities) throws SQLException {
        AmenityDAO amenityDao = new AmenityDAO();
        List<AmenityDTO> newAmenities = new ArrayList<>();

        if (!amenities.isEmpty()) {
            // delete all amenities
            this.deleteAll(amenities.get(0).getHotelId());

            for (HotelAmenityDTO dto : amenities) {
                if (dto.getId().contains("new")) {
                    String id = UUID.randomUUID().toString();
                    newAmenities.add(new AmenityDTO(id, dto.getName(), dto.getIcon(), dto.getAmenityTypeId()));
                    dto.setAmenityId(id);
                    dto.setId(UUID.randomUUID().toString());
                }
            }

            for (AmenityDTO dto : newAmenities) {
                amenityDao.add(dto);
            }

            for (HotelAmenityDTO dto : amenities) {
                this.add(dto);
            }
        }
    }

    public void deleteAll(String hotelId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from HotelAmenities where hotel_id = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hotelId);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void delete(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from HotelAmenities where hotel_amenities_id = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

     
     
    public boolean addHotelAmenties(List<String> amenties, String hotel_id) {
        List<Integer> check = new ArrayList<>();

        try {
            String query = "insert into HotelAmenities values (?, ?,  ?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < amenties.size(); i++) {
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                
                ps.setString(1, uuid.toString());
                ps.setString(2, amenties.get(i));
                ps.setString(3, hotel_id);
                int x = ps.executeUpdate();

                if (x != 0) {
                    check.add(x);
                }
            }

            if (check.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.HotelAmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException {
        HotelAmenityDAO dao = new HotelAmenityDAO();
        System.out.println(dao.get("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9").size());
    }
}
