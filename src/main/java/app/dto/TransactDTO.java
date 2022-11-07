/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

import java.util.Date;
import java.util.List;

/**
 *
 * @author toten
 */
public class TransactDTO {
    private String userId;
    private Date createAt;
    private int ammount;
    private int type;
    private String spectDate;
    private int wallet_amount;
    private List<Integer> listDays;
    private List<Integer> totalPaymentPerDay;
    private int month;
    private String hotelName;
    private BookingDTO booking;
    
    public TransactDTO(String userId, Date createAt, int ammount, int type, String spectDate, int wallet_amount, int month) {
        this.userId = userId;
        this.createAt = createAt;
        this.ammount = ammount;
        this.type = type;
        this.spectDate = spectDate;
        this.wallet_amount = wallet_amount;
        this.month = month;
    }

    public TransactDTO(String userId, Date createAt, int ammount, int type, String spectDate, int wallet_amount) {
        this.userId = userId;
        this.createAt = createAt;
        this.ammount = ammount;
        this.type = type;
        this.spectDate = spectDate;
        this.wallet_amount = wallet_amount;
    }
    
    

    public TransactDTO(String userId, Date createAt, int ammount, int type) {
        this.userId = userId;
        this.createAt = createAt;
        this.ammount = ammount;
        this.type = type;
    }

    public TransactDTO(String userId, Date createAt, int ammount, int type, String spectDate) {
        this.userId = userId;
        this.createAt = createAt;
        this.ammount = ammount;
        this.type = type;
        this.spectDate = spectDate;
    }

    public TransactDTO(String userId, Date createAt, int ammount, int type, String spectDate, int wallet_amount, List<Integer> listDays, List<Integer> totalPaymentPerDay, int month) {
        this.userId = userId;
        this.createAt = createAt;
        this.ammount = ammount;
        this.type = type;
        this.spectDate = spectDate;
        this.wallet_amount = wallet_amount;
        this.listDays = listDays;
        this.totalPaymentPerDay = totalPaymentPerDay;
        this.month = month;
    }

    public TransactDTO(String userId, Date createAt, int ammount, int type, String spectDate, int wallet_amount, String hotelName, BookingDTO booking) {
        this.userId = userId;
        this.createAt = createAt;
        this.ammount = ammount;
        this.type = type;
        this.spectDate = spectDate;
        this.wallet_amount = wallet_amount;
        this.hotelName = hotelName;
        this.booking = booking;
    }

    

    

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }
    
    

    public List<Integer> getListDays() {
        return listDays;
    }

    public void setListDays(List<Integer> listDays) {
        this.listDays = listDays;
    }

    public List<Integer> getTotalPaymentPerDay() {
        return totalPaymentPerDay;
    }

    public void setTotalPaymentPerDay(List<Integer> totalPaymentPerDay) {
        this.totalPaymentPerDay = totalPaymentPerDay;
    }
    
    public String getSpectDate() {
        return spectDate;
    }

    public void setSpectDate(String spectDate) {
        this.spectDate = spectDate;
    }

    public int getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(int wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }
    
    

    public TransactDTO() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TransactDTO{" + "userId=" + userId + ", createAt=" + createAt + ", ammount=" + ammount + ", type=" + type + ", spectDate=" + spectDate + ", wallet_amount=" + wallet_amount + ", month=" + month + '}';
    }

    

    
}
