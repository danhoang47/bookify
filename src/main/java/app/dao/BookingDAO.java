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
                int price = rs.getInt("price");
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
                int price = rs.getInt("price");
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

            while (rs.next()) {
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

            while (rs.next()) {
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

            while (rs.next()) {
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

            while (rs.next()) {
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

            while (rs.next()) {
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

            while (rs.next()) {
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

    public List<String> getHotelByCondition(String district, int numberOfGuest) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        System.out.println(district);

        String sql = "select * from SearchAdvance ";

        if ((district.length() > 0 || district != null) && numberOfGuest == 0) {
            sql = sql + "where district=?";
        } else if ((district.length() == 0 || district == null) && numberOfGuest != 0) {
            sql = sql + "where number_of_guest >=?";
        } else if ((district.length() > 0 || district != null) && numberOfGuest != 0) {
            sql = sql + "where district=? and number_of_guest >=? ";
        }

        System.out.println(sql);

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);

            if ((district.length() > 0 || district != null) && numberOfGuest == 0) {
                ps.setString(1, district);
            } else if ((district.length() == 0 || district == null) && numberOfGuest != 0) {
                ps.setInt(1, numberOfGuest);
            } else if ((district.length() > 0 || district != null) && numberOfGuest != 0) {
                ps.setString(1, district);
                ps.setInt(2, numberOfGuest);
            }

            rs = ps.executeQuery();

            List<String> listRes = new ArrayList<>();

            while (rs.next()) {
                listRes.add(rs.getString("hotel_id"));
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
        BookingDAO dao = new BookingDAO();
        List<String> list = dao.getHotelByCondition("Thành phố Đà Nẵng", 5);
        System.out.println(list);
    }
}
