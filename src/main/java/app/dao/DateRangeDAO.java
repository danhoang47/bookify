/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.DateRangeDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class DateRangeDAO {

    public List<DateRangeDTO> getAll(String checkin, String checkout, String hotelId) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs;
        String sql = "proc_getAllMergedDayBooking @check_in=?, @check_out=?, @hotelId=?";
        List<DateRangeDTO> listHotel = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, checkin);
            cs.setString(2, checkout);
            cs.setString(3, hotelId);

            cs.executeQuery();
            rs = cs.getResultSet();

            while (rs.next()) {
                String room_id = rs.getString("room_id");
                Date check_in = rs.getDate("check_in");
                Date check_out = rs.getDate("check_out");

                DateRangeDTO hotel = new DateRangeDTO(room_id, check_in, check_out);

                listHotel.add(hotel);
            }

            return listHotel;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DateRangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return null;

    }
    
    public List<DateRangeDTO> getAllBookedRoom() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql = "select * from dateRangeBookingMerged";
        List<DateRangeDTO> listHotel = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String room_id = rs.getString("room_id");
                Date check_in = rs.getDate("check_in");
                Date check_out = rs.getDate("check_out");
                String hotel_id = rs.getString("hotel_id");

                DateRangeDTO hotel = new DateRangeDTO(hotel_id, check_in, check_out, room_id);

                listHotel.add(hotel);
            }

            return listHotel;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DateRangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return null;

    }
    
    public List<DateRangeDTO> getAllBookedRoomWithHotel(String hotelId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql = "select * from dateRangeBookingMerged where hotel_id=?";
        List<DateRangeDTO> listHotel = new ArrayList<>();
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, hotelId);
            rs = ps.executeQuery();

            while (rs.next()) {
                String room_id = rs.getString("room_id");
                Date check_in = rs.getDate("check_in");
                Date check_out = rs.getDate("check_out");
                String hotel_id = rs.getString("hotel_id");

                DateRangeDTO hotel = new DateRangeDTO(hotel_id, check_in, check_out, room_id);

                listHotel.add(hotel);
            }

            return listHotel;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DateRangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return null;

    }
    
    public void test() throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = "proc_testProc";
        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.executeQuery();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DateRangeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        List<DateRangeDTO> list = new DateRangeDAO().getAll(
                "2022-11-05",
                "2022-11-06",
                "f98320c3-235a-4cb7-a0a8-eda132b0e545"
        );
        
        List<DateRangeDTO> list2 = new DateRangeDAO().getAllBookedRoom();
        System.out.println(list2.size());
    }
}
