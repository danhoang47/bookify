/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.ImageDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class ImageDAO {

    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public List<ImageDTO> get(String hotelId) throws SQLException {

        String sql = "select * from Image where hotel_id = ?";
        List<ImageDTO> imageDtos = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, hotelId);
            rs = ps.executeQuery();
            imageDtos = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("image_id");
                String src = rs.getString("image");
                int type = rs.getInt("type");

                ImageDTO imageDto = new ImageDTO(id, hotelId, src, type);
                imageDtos.add(imageDto);
            }

        } catch (Exception ex) {
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    
    public void add(ImageDTO image) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "insert into Image values(?, ?, ?, ?)";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, image.getId());
            ps.setString(2, image.getHotelId());
            ps.setString(3, image.getSrc());
            ps.setInt(4, image.getType());

            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }

    public List<ImageDTO> getRandomImage(String hotelId) throws SQLException {

        String sql = "SELECT TOP 2 * from Image\n"
                + "where hotel_id=?\n"
                + "ORDER BY NEWID() ";
        List<ImageDTO> imageDtos = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, hotelId);
            rs = ps.executeQuery();
            imageDtos = new ArrayList<>();

            while (rs.next()) {
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
    
    public void delete(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "delete from Image where image_id = ?";

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


    public boolean addImage(String hotel_id, List<String> images, int type) {
        
        System.out.println("images list: " + images);

        List<Integer> check = new ArrayList<>();
        try {
            String query = "insert into Image values (?, ?, ?, ?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < images.size(); i++) {
                System.out.println("Add image : " + i);
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                ps.setString(1, uuid.toString());
                ps.setString(2, hotel_id);
                ps.setString(3, images.get(i));
                ps.setInt(4, type);

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
            Logger.getLogger(ImageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public static void main(String[] args) throws SQLException {
        ImageDAO dao = new ImageDAO();
        System.out.println(dao.getRandomImage("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9").size());
    }
}
