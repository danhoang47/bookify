/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.BookingDAO;
import app.dao.TransactDAO;
import app.dto.BookingDTO;
import app.dto.TransactDTO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author toten
 */
public class BookingService {
    private BookingDAO bookingdao;
    private TransactDAO transactDAO;
    
    
    public BookingService() {
        bookingdao = new BookingDAO();
        transactDAO = new TransactDAO();
    }
    
    public List<BookingDTO> getUserBookingHistory(String userId) throws SQLException {
        return bookingdao.getUserBookingHistory(userId);
    }
    
    public List<BookingDTO> getUserBookingHistoryFilter(String userId, String condition) throws SQLException {
        return bookingdao.getUserBookingHistoryFilter(userId, condition);
    }
    
    public List<TransactDTO> getAllTransactOfUser(String userid, int month) throws SQLException {
        return transactDAO.getAllTransactOfUser(userid, month);
    }
    
    public int getAmountWallet(String userid) throws SQLException {
        return transactDAO.getAmountWallet(userid);
    }
    
    public TransactDTO getAllTransactOfUserByDays(String userid, int month) throws SQLException {
        return transactDAO.getAllTransactOfUserByDays(userid, month);
    }
    
    public List<String> getHotelByCondition(String district, int numberOfGuest) throws SQLException {
        return bookingdao.getHotelByCondition(district, numberOfGuest);
    }
    
    
    public static void main(String[] args) throws SQLException {
         List<TransactDTO> listTrans = new BookingService().getAllTransactOfUser("deaa34d5-2c36-4c1c-b97b-8dbf3e1b18c3", 11);
         
         System.out.println(listTrans);
    }
}
