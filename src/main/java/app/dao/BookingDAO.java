/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.BookingDTO;
import app.dto.HotelDTO;
import app.dto.RoomTypeDTO;
import app.dto.UserDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
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
public class BookingDAO {

    public void add(BookingDTO booking) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = "proc_bookingRoom @bookingId = ?, @userId = ?, @checkin = ?, @checkout = ?, @adult = ?, @child = ?, @infants = ?, @pets = ?, @roomId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, booking.getBookingId());
            cs.setString(2, booking.getUser().getUser_id());
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

    public List<BookingDTO> getAllTodayPendingBooking(String hotelId) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = " proc_getAllTodayPendingBooking @hotelId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String bookingId = rs.getString("booking_id");
                String checkin = rs.getString("check_in");
                String checkout = rs.getString("check_out");
                String roomId = rs.getString("room_id");
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infant = rs.getInt("infants");
                int pets = rs.getInt("pets");
                int status = 0;
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                Date bookAt = rs.getDate("bookAt");
                RoomTypeDTO roomType = new RoomTypeDTO();
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setBathroomType(rs.getString("bathroom_type"));
                UserDTO user = new UserDTO();
                user.setUser_id(userId);
                user.setAvatar(avatar);
                user.setUsername(username);
                BookingDTO booking = new BookingDTO(user, 
                        roomId, hotelId, 0, bookingId, checkin, 
                        checkout, adult, child, infant, pets,
                        status, bookAt, roomType
                );
                list.add(booking);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }

    public List<BookingDTO> getAllTodayCheckoutBooking(String hotelId) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getAllTodayCheckout @hotelId = ?";

         try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String bookingId = rs.getString("booking_id");
                String checkin = rs.getString("check_in");
                String checkout = rs.getString("check_out");
                String roomId = rs.getString("room_id");
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infant = rs.getInt("infants");
                int pets = rs.getInt("pets");
                int status = 2;
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                Date bookAt = rs.getDate("bookAt");
                RoomTypeDTO roomType = new RoomTypeDTO();
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setBathroomType(rs.getString("bathroom_type"));
                UserDTO user = new UserDTO();
                user.setUser_id(userId);
                user.setAvatar(avatar);
                user.setUsername(username);
                BookingDTO booking = new BookingDTO(user, 
                        roomId, hotelId, 0, bookingId, checkin, 
                        checkout, adult, child, infant, pets,
                        status, bookAt, roomType
                );
                list.add(booking);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }

    public List<BookingDTO> getAllTodayBookedBooking(String hotelId) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = " proc_getAllTodayBookedBooking @hotelId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String bookingId = rs.getString("booking_id");
                String checkin = rs.getString("check_in");
                String checkout = rs.getString("check_out");
                String roomId = rs.getString("room_id");
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infant = rs.getInt("infants");
                int pets = rs.getInt("pets");
                int status = 1;
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                Date bookAt = rs.getDate("bookAt");
                RoomTypeDTO roomType = new RoomTypeDTO();
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setBathroomType(rs.getString("bathroom_type"));
                UserDTO user = new UserDTO();
                user.setUser_id(userId);
                user.setAvatar(avatar);
                user.setUsername(username);
                BookingDTO booking = new BookingDTO(user, 
                        roomId, hotelId, 0, bookingId, checkin, 
                        checkout, adult, child, infant, pets,
                        status, bookAt, roomType
                );
                list.add(booking);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }

    public void acceptBooking(String bookingId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update Booking set status = 1 where booking_id = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bookingId);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public void rejectBooking(String bookingId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "update Booking set status = 2 where booking_id = ?";

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, bookingId);
            ps.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        BookingDAO dao = new BookingDAO();
        System.out.println(dao.getAllIncomingBooking("ae257b6b-43d4-4621-91f1-b331c6d4dea9"));
    }

    public List<BookingDTO> getAllPendingBooking(String hotelId) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getAllPendingBooking @hotelId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String bookingId = rs.getString("booking_id");
                String checkin = rs.getString("check_in");
                String checkout = rs.getString("check_out");
                String roomId = rs.getString("room_id");
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infant = rs.getInt("infants");
                int pets = rs.getInt("pets");
                int status = 1;
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                Date bookAt = rs.getDate("bookAt");
                RoomTypeDTO roomType = new RoomTypeDTO();
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setBathroomType(rs.getString("bathroom_type"));
                UserDTO user = new UserDTO();
                user.setUser_id(userId);
                user.setAvatar(avatar);
                user.setUsername(username);
                BookingDTO booking = new BookingDTO(user, 
                        roomId, hotelId, 0, bookingId, checkin, 
                        checkout, adult, child, infant, pets,
                        status, bookAt, roomType
                );
                list.add(booking);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }

    public List<BookingDTO> getAllCheckoutBooking(String hotelId) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getAllCheckout @hotelId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String bookingId = rs.getString("booking_id");
                String checkin = rs.getString("check_in");
                String checkout = rs.getString("check_out");
                String roomId = rs.getString("room_id");
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infant = rs.getInt("infants");
                int pets = rs.getInt("pets");
                int status = 1;
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                Date bookAt = rs.getDate("bookAt");
                RoomTypeDTO roomType = new RoomTypeDTO();
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setBathroomType(rs.getString("bathroom_type"));
                UserDTO user = new UserDTO();
                user.setUser_id(userId);
                user.setAvatar(avatar);
                user.setUsername(username);
                BookingDTO booking = new BookingDTO(user, 
                        roomId, hotelId, 0, bookingId, checkin, 
                        checkout, adult, child, infant, pets,
                        status, bookAt, roomType
                );
                list.add(booking);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }

   public List<BookingDTO> getAllIncomingBooking(String hotelId) throws SQLException {
        List<BookingDTO> list = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getAllIncomingBooking @hotelId = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotelId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                String userId = rs.getString("user_id");
                String bookingId = rs.getString("booking_id");
                String checkin = rs.getString("check_in");
                String checkout = rs.getString("check_out");
                String roomId = rs.getString("room_id");
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infant = rs.getInt("infants");
                int pets = rs.getInt("pets");
                int status = 1;
                String username = rs.getString("username");
                String avatar = rs.getString("avatar");
                Date bookAt = rs.getDate("bookAt");
                RoomTypeDTO roomType = new RoomTypeDTO();
                roomType.setBedType(rs.getString("bed_type"));
                roomType.setBathroomType(rs.getString("bathroom_type"));
                UserDTO user = new UserDTO();
                user.setUser_id(userId);
                user.setAvatar(avatar);
                user.setUsername(username);
                BookingDTO booking = new BookingDTO(user, 
                        roomId, hotelId, 0, bookingId, checkin, 
                        checkout, adult, child, infant, pets,
                        status, bookAt, roomType
                );
                list.add(booking);
            }
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
        return list;
    }
}
