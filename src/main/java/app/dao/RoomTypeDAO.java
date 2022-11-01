
package app.dao;

import app.dto.RoomTypeDTO;
import java.sql.CallableStatement;
import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RoomTypeDAO {
    
    public RoomTypeDTO get(String hotelId) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getRoomType @hotelId = ?";
        RoomTypeDTO roomType = null;
        
        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                String id = rs.getString("id");
                int price = rs.getInt("price");
                String bedType = rs.getString("bed_type");
                int numberOfBed = rs.getInt("bed_number");
                String bathroomType = rs.getString("bathroom_type");
                int numberOfBathroom = rs.getInt("bathroom_number");
                int numberOfRoom = rs.getInt("number_of_room");
                int numberOfGuests = rs.getInt("number_of_guests");
                boolean isPrivateBathroom  = rs.getBoolean("is_private_bathroom");
                int rooms = rs.getInt("rooms");
                
                roomType = new RoomTypeDTO(id, price, bedType, 
                        numberOfBed, bathroomType, numberOfBathroom, 
                        numberOfRoom, numberOfGuests, isPrivateBathroom, hotelId, rooms);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            } 
            if (cs != null) {
                cs.close();
            }
        }
        
        return roomType;
    }
    
    public void update(RoomTypeDTO roomType) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = "proc_updateRoomType @id = ?, @price = ?, @numberOfBed = ?, @numberOfBathroom = ?, "
                + "@isPrivateBathroom = ?, @numberOfGuests = ?, @numberOfRoom = ?";
        
        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, roomType.getId());
            cs.setInt(2, roomType.getPrice());
            cs.setInt(3, roomType.getNumberOfBed());
            cs.setInt(4, roomType.getNumberOfBathroom());
            cs.setBoolean(5, roomType.isIsPrivateBathroom());
            cs.setInt(6, roomType.getNumberOfGuests());
            cs.setInt(7, roomType.getNumberOfRoom());
            cs.executeUpdate();
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    public String addNewRoomType(int price, String bedType, int bedNumber, String bathType, int bathNumber, boolean isPrivateBath, 
            String guests_id, int numberOfGuest, int numberOfRoom) {
        PreparedStatement ps = null;
        Connection conn = null;
        try {
            String query = "insert into RoomType values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            
            UUID uuid = UUID.randomUUID();
            ps.setString(1, uuid.toString());
            ps.setInt(2, price);
            ps.setString(3, bedType);
            ps.setInt(4, bedNumber);
            ps.setString(5, bathType);
            ps.setInt(6, bathNumber);
            ps.setInt(7, isPrivateBath==false?0:1);
            ps.setString(8, guests_id);
            ps.setInt(9, numberOfGuest);
            ps.setInt(10, numberOfRoom);

            int x = ps.executeUpdate();
            if(x==1) {
                return uuid.toString();
            } else {
                return null;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RoomTypeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
