/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.BookingDTO;
import app.dto.TransactDTO;
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
public class TransactDAO {

    public List<TransactDTO> getAllTransactOfUser(String userId, int month) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        String sql = "proc_getTransactionDataHistory @userId=?, @month=?";
        List<TransactDTO> transactDTO = null;

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, userId);
            cs.setInt(2, month);
            cs.executeQuery();
            rs = cs.getResultSet();
            transactDTO = new ArrayList<>();

            while (rs.next()) {
                String userIdTrans = rs.getString("user_id");
                Date createAt = rs.getDate("createdAt");
                int amount = rs.getInt("amount");
                int type = rs.getInt("type");
                String specTime = rs.getString("specTime");
                int wallet = rs.getInt("wallet");
                String hotelName = rs.getString("hotel_name");
                int adult = rs.getInt("adult");
                int child = rs.getInt("child");
                int infants = rs.getInt("infants");
                int pets = rs.getInt("pets");

                transactDTO.add(new TransactDTO(userIdTrans, createAt, amount, type, specTime, wallet, hotelName, new BookingDTO(adult, child, infants, pets)));
            }

            return transactDTO;

        } catch (Exception ex) {
            Logger.getLogger(TransactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
        }

        return null;
    }

    public TransactDTO getAllTransactOfUserByDays(String userId, int month) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select day(createdAt) as days, sum(amount) as amountPerDay from Transact \n"
                + "where user_id=? and MONTH(createdAt)=?  group by day(createdAt)";
        TransactDTO transactDTO = new TransactDTO();

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            ps.setInt(2, month);
            ps.executeQuery();
            rs = ps.executeQuery();
            List<Integer> days = new ArrayList<>();
            List<Integer> dataByDays = new ArrayList<>();
            
            

            while (rs.next()) {
                days.add(rs.getInt("days"));
                dataByDays.add(rs.getInt("amountPerDay"));
            }
            
            transactDTO.setListDays(days);
            transactDTO.setTotalPaymentPerDay(dataByDays);

            return transactDTO;

        } catch (Exception ex) {
            Logger.getLogger(TransactDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public int getAmountWallet(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select ba.amount from userDetail ud, BankingAccount ba where ud.banking_account_id=ba.id and ud.user_id=?";
        List<TransactDTO> transactDTO = null;

        try {
            conn = DBContext.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            int result = 0;

            while (rs.next()) {
                result = rs.getInt("amount");
                return result;
            }

            return result;

        } catch (Exception ex) {
            Logger.getLogger(TransactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return 0;
    }

    public static void main(String[] args) throws SQLException {
        List<TransactDTO> listTrans = new TransactDAO().getAllTransactOfUser("deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3", 11);

        System.out.println(listTrans);
    }
}
