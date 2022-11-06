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
import java.sql.Date;
import java.sql.SQLException;
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
}
