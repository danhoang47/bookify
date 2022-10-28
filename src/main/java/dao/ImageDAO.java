/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import static dao.UserDetailDAO.ps;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.ImageDTO;


/**
 *
 * @author toten
 */
public class ImageDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public static List<ImageDTO> listAll(String hotel_id) {

        try {
            String query = "select * from image where hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();

            List<ImageDTO> listImageHotel = new ArrayList<>();

            while (rs.next()) {

                listImageHotel.add(new ImageDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));

            }

            return listImageHotel;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean addImage(String hotel_id, List<String> images, int type) {
        
        List<Integer> check = new ArrayList<>();
        try {
            String query = "insert into Image values (?, ?, ?, ?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < images.size(); i++) {
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                ps.setString(1, uuid.toString());
                ps.setString(2, hotel_id);
                ps.setString(3, images.get(i));                
                ps.setInt(4, type);

                
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

    public static void main(String[] args) {
        ImageDAO img = new ImageDAO();
        List<String> images = new ArrayList<>();
        images.add("image1");
        images.add("image2");
        images.add("image3");
        
        boolean x = img.addImage("043a69b8-738f-4205-9a12-f96e1486f1e6", images, 1);

        System.out.println(x);
    }
}
