/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.DashboardDTO;
import app.dto.ExchangeDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class DashboardDAO {

    private Connection conn;
    private PreparedStatement ps;
    private CallableStatement cs;
    private ResultSet rs;

    public Map<Integer, Integer> getBookingData(int month) throws SQLException {

        String sql = "select DAY(bk.bookAt) as days,  \n"
                + "count(*) as numberBooking from booking as bk, Room as rm, hotel as ht  where MONTH(bookAt)=? and (DAY(bookAt)% 2)<>0 \n"
                + "and bk.room_id=rm.room_id and rm.hotel_id=ht.hotel_id  \n"
                + "group by bookAt\n"
                + "order by bookAt ";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();

            Map<Integer, Integer> dashBoard
                    = new HashMap<Integer, Integer>();
            while (rs.next()) {
                dashBoard.put(rs.getInt("days"), rs.getInt("numberBooking"));

            }

            return dashBoard;

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

        return null;
    }

    public Map<String, Integer> bookingTrend(int month) throws SQLException {

        String sql = "select htt.hoteltype, count(*) as number from Booking as bk, room as rm, hotel as ht, hotelType as htt \n"
                + "where bk.room_id=rm.room_id and rm.hotel_id=ht.hotel_id and ht.hoteltype_id=htt.hoteltype_id and MONTH(bk.bookAt)=?\n"
                + "group by htt.hoteltype ";

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, month);
            rs = ps.executeQuery();

            Map<String, Integer> dashBoardTrend
                    = new HashMap<String, Integer>();
            while (rs.next()) {
                dashBoardTrend.put(rs.getString("hoteltype"), rs.getInt("number"));
            }

            return dashBoardTrend;

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

        return null;
    }

    public int getNumberOfBooking(int month) {

        try {
            String query = "select count(*) as numberBooking from Booking where month(bookAt)=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setInt(1, month);

            rs = ps.executeQuery();

            int res = 0;

            while (rs.next()) {
                res = rs.getInt("numberBooking");
            }

            return res;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getNumberOfPayment(int month) {

        try {
            String query = "select count(*) as numberBooking from Booking where status=1 and month(bookAt)=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setInt(1, month);

            rs = ps.executeQuery();

            int res = 0;

            while (rs.next()) {
                res = rs.getInt("numberBooking");
            }

            return res;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getRegisNumber(int month) {

        try {
            String query = "select count(*) as regisNumber from userDetail where month(signAt)=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setInt(1, month);

            rs = ps.executeQuery();

            int res = 0;

            while (rs.next()) {
                res = rs.getInt("regisNumber");
            }

            return res;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int getRatingNumber(int month) {

        try {
            String query = "select count(*) as regisNumber from userDetail where month(signAt)=?";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setInt(1, month);

            rs = ps.executeQuery();

            int res = 0;

            while (rs.next()) {
                res = rs.getInt("regisNumber");
            }

            return res;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public List<ExchangeDTO> getExchangeData() {

        try {
            String query = "select bk.*, (us.subname + ' ' + us.name ) as userfullname, us.username, rt.price \n"
                    + "from booking as bk, room as rm, userDetail as us, RoomType as rt \n"
                    + "where bk.room_id=rm.room_id and rm.room_type_id=rt.id and us.user_id=bk.user_id and status=1";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<ExchangeDTO> excDto = new ArrayList<>();

            while (rs.next()) {
                excDto.add(new ExchangeDTO(rs.getString("booking_id"), rs.getString("user_id"), rs.getDate("check_in"), rs.getDate("check_out"), rs.getInt("adult"), rs.getInt("child"), rs.getInt("infants"), rs.getInt("pets"), rs.getString("room_id"), rs.getDate("bookAt"), rs.getString("userfullname"), rs.getString("username"), rs.getInt("price")));
            }

            return excDto;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ReviewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) throws SQLException {
//        Map<String, Integer> dto = new DashboardDAO().bookingTrend(11);
//        System.out.println(new DashboardDAO().getRegisNumber(11));

        List<ExchangeDTO> exc = new DashboardDAO().getExchangeData();
        System.out.println(exc);
    }
}
