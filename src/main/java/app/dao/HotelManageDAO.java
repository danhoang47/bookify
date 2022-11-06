/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.HotelManageDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class HotelManageDAO {

    private Connection conn;
    private PreparedStatement ps;
    private CallableStatement cs;
    private ResultSet rs;

    public HotelManageDTO listHotelData(String hotel_id, int month) {

        try {
            String query = "select day(bk.check_out) as days, sum(rt.price) as totalIncome from Booking as bk, room as rm, hotel as ht, RoomType as rt where bk.room_id=rm.room_id \n"
                    + "and rm.hotel_id=ht.hotel_id and rm.room_type_id=rt.id and ht.hotel_id=?\n"
                    + "and MONTH(bk.check_out)=? and bk.status=1\n"
                    + "group by bk.check_out";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);
            ps.setInt(2, month);

            rs = ps.executeQuery();

            HotelManageDTO listReview = new HotelManageDTO();
            List<Integer> days = new ArrayList<>();
            List<Integer> totalIncome = new ArrayList<>();

            while (rs.next()) {
                days.add(rs.getInt("days"));
                totalIncome.add(rs.getInt("totalIncome"));

            }

            listReview.setIncomeDays(days);
            listReview.setTotalIncomePerDays(totalIncome);

            return listReview;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public HotelManageDTO getNumberOfAllBooking(String hotel_id, int month) {

        try {
            String query = "select day(bk.bookAt) as days, count(*) as numberPerDay from Booking as bk, room as rm, hotel as ht, RoomType as rt where bk.room_id=rm.room_id\n"
                    + "and rm.hotel_id=ht.hotel_id and rm.room_type_id=rt.id and ht.hotel_id=?\n"
                    + "and MONTH(bk.check_out)=?\n"
                    + "group by day(bk.bookAt)";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, hotel_id);
            ps.setInt(2, month);

            rs = ps.executeQuery();

            HotelManageDTO listReview = new HotelManageDTO();
            List<Integer> days = new ArrayList<>();
            List<Integer> totalIncome = new ArrayList<>();

            while (rs.next()) {
                days.add(rs.getInt("days"));
                totalIncome.add(rs.getInt("numberPerDay"));

            }

            listReview.setBookingDays(days);
            listReview.setTotalBookingPerDay(totalIncome);

            return listReview;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelManageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void main(String[] args) {
        HotelManageDTO dto = new HotelManageDAO().getNumberOfAllBooking("f98320c3-235a-4cb7-a0a8-eda132b0e545", 11);
        System.out.println(dto);
    }
}
