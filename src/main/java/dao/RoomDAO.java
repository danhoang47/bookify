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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.AvaiableAmenity;
import model.dto.HotelType;

/**
 *
 * @author toten
 */
public class RoomDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public static double averagePrice(String hotel_id) {

        try {
            String query = "select avg(room_price) from room where hotel_id=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);

            rs = ps.executeQuery();

            double result = 0;

            while (rs.next()) {

                result = rs.getDouble(1);

            }

            return result;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void addRoomService(String bedType, int bedNumber, String bathType, int bathNumber, String roomId, boolean isPrivateBath) {
        try {
            String query = "insert into RoomService values (?, ?, ?, ?, ?, ?, ?)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            
            UUID uuid = UUID.randomUUID();
            ps.setString(1, uuid.toString());
            ps.setString(2, bedType);
            ps.setInt(3, bedNumber);
            ps.setString(4, bathType);
            ps.setInt(5, bathNumber);
            ps.setString(6, roomId);
            ps.setInt(7, isPrivateBath==false?0:1);

            ps.executeUpdate();
            

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean addNewRoom(String hotel_id, int roomPrice, int type, int maxGuest, int roomNum, 
            int bathNum, int bedNum, boolean isbathPrivate) {
        List<Integer> check = new ArrayList<>();
        RoomDAO rd = new RoomDAO();
        try {
            String query = "insert into Room values (?,?,?,?,?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < roomNum; i++) {
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                ps.setString(1, uuid.toString());
                ps.setString(2, hotel_id);
                ps.setInt(3, roomPrice);
                ps.setInt(4, type);
                ps.setInt(5, maxGuest);
                int x = ps.executeUpdate();
                
                rd.addRoomService("Normal bedd", bedNum, "Normal Bathroomm", bathNum, uuid.toString(), isbathPrivate);
                
                check.add(x);
            }

            if (check.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RoomDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
//        AvaiableAmenityDAO amd = new AvaiableAmenityDAO();
//        HotelTypeDAO htd = new HotelTypeDAO();
//
//        List<AvaiableAmenity> listAmenity = amd.listAll();
//        List<HotelType> listHotelType = htd.hotelTypes();
//
//        System.out.println(listAmenity);
//        System.out.println(listHotelType);s

        RoomDAO rd = new RoomDAO();
        
        boolean check = rd.addNewRoom("fe1f3fd7-6b6f-4450-b8c5-9f1ccee123a9", 100, 0, 5, 2, 1, 1, true);
        
        System.out.println(check);

    }

}
