/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.BookingDTO;
import app.dto.HotelDTO;
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
 * @author ADMIN
 */
public class BookingDAO {

    public void add(BookingDTO booking) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = " proc_bookingRoom @bookingId = ?, @userId = ?, @checkin = ?, @checkout = ?, @adult = ?, @child = ?, @infants = ?, @pets = ?, @roomId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, booking.getBookingId());
            cs.setString(2, booking.getUserId());
            cs.setString(3, booking.getCheckin());
            cs.setString(4, booking.getCheckout());
            cs.setInt(5, booking.getAdult());
            cs.setInt(6, booking.getChild());
            cs.setInt(7, booking.getInfant());
            cs.setInt(8, booking.getPet());
            cs.setString(9, booking.getRoomId());
            cs.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    public List<BookingDTO> getUserBookingHistory(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select bk.*, ht.hotel_name, rt.price,(ht.address + ' - ' + ht.city + ' - ' + ht.district) as hotel_address \n"
                + "from Booking bk, hotel ht, Room rm ,RoomType rt where bk.room_id=rm.room_id and rm.hotel_id=ht.hotel_id and rm.room_type_id = rt.id\n"
                + "and bk.user_id=? order by bk.check_out desc";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            BookingDTO booking = null;
            ps.setString(1, userId);
            rs = ps.executeQuery();

            List<BookingDTO> listRes = new ArrayList<>();

            while (rs.next()) {
                String userIdData = rs.getString("user_id");
                String checkIn = rs.getDate("check_in").toString();
                String checkOut = rs.getDate("check_out").toString();
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infants = rs.getInt("infants");
                int pets = rs.getInt("pets");
                String price = rs.getInt("price") + "";
                String roomId = rs.getString("room_id");
                int status = rs.getInt("status");
                Date bookAt = rs.getDate("bookAt");
                String hotelName = rs.getString("hotel_name");
                String address = rs.getString("hotel_address");
                listRes.add(new BookingDTO(userIdData, checkIn, checkOut, adult, child, infants, pets, price, roomId, status, bookAt, hotelName, address));
            }

            return listRes;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }

    public List<BookingDTO> getUserBookingHistoryFilter(String userId, String condition) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String type = (!condition.equals("1") && !condition.equals("0")) ? "and bk.check_in" : "and bk.status";

        String sql = "select bk.*, ht.hotel_name, rt.price,(ht.address + ' - ' + ht.city + ' - ' + ht.district) as hotel_address \n"
                + "from Booking bk, hotel ht, Room rm ,RoomType rt where bk.room_id=rm.room_id and rm.hotel_id=ht.hotel_id \n"
                + "and rm.room_type_id = rt.id and bk.user_id=? " + type + "=? order by bk.check_out desc ";

        System.out.println(sql);
        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            BookingDTO booking = null;
            ps.setString(1, userId);
            if (type.equals("and bk.status")) {
                ps.setInt(2, Integer.parseInt(condition));
            } else if (type.equals("and bk.check_in")) {
                ps.setString(2, condition);
            }

            rs = ps.executeQuery();

            List<BookingDTO> listRes = new ArrayList<>();

            while (rs.next()) {
                String userIdData = rs.getString("user_id");
                String checkIn = rs.getDate("check_in").toString();
                String checkOut = rs.getDate("check_out").toString();
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infants = rs.getInt("infants");
                int pets = rs.getInt("pets");
                String price = rs.getInt("price") + "";
                String roomId = rs.getString("room_id");
                int status = rs.getInt("status");
                Date bookAt = rs.getDate("bookAt");
                String hotelName = rs.getString("hotel_name");
                String address = rs.getString("hotel_address");
                listRes.add(new BookingDTO(userIdData, checkIn, checkOut, adult, child, infants, pets, price, roomId, status, bookAt, hotelName, address));
            }

            return listRes;

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
        List<BookingDTO> list = new BookingDAO().getUserBookingHistoryFilter("deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3", "1");
        System.out.println(list);
    }
}
