/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;
import Context.DBContext;
import app.dto.HotelDTO;
import app.dto.ImageDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ImageDAO {
    
    public List<ImageDTO> get(String hotelId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Image where hotel_id = ?";
        List<ImageDTO> imageDtos = null;
        
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, hotelId);
            rs = ps.executeQuery();
            imageDtos = new ArrayList<>();
            
            while(rs.next()) {
                String id = rs.getString("image_id");
                String src = rs.getString("image");
                int type = rs.getInt("type");
                
                ImageDTO imageDto = new ImageDTO(id, hotelId, src, type);
                imageDtos.add(imageDto);
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
        
        return imageDtos;
    }
    
    public static void main(String[] args) throws SQLException {
        ImageDAO dao = new ImageDAO();
        System.out.println(dao.get("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9").size());
    }
}