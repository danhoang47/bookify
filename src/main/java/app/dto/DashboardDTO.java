/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

import java.util.List;

/**
 *
 * @author toten
 */
public class DashboardDTO {
    private int bookingNumber;
    private int accessNumber;
    private int paymentNumber;
    private int reviewNumber;
    private int userRegisNumber;
    private List<String> bookingHotelType;
    private List<Integer> bookingHotelTypeNumber;
    private List<Integer> bookingDate;
    private List<Integer> bookingDateNumber;
    private List<ReportDTO> listReport;
    private int month;

    public DashboardDTO(int bookingNumber, int accessNumber, int paymentNumber, int reviewNumber, int userRegisNumber, List<String> bookingHotelType, List<Integer> bookingHotelTypeNumber, List<Integer> bookingDate, List<Integer> bookingDateNumber, List<ReportDTO> listReport, int month) {
        this.bookingNumber = bookingNumber;
        this.accessNumber = accessNumber;
        this.paymentNumber = paymentNumber;
        this.reviewNumber = reviewNumber;
        this.userRegisNumber = userRegisNumber;
        this.bookingHotelType = bookingHotelType;
        this.bookingHotelTypeNumber = bookingHotelTypeNumber;
        this.bookingDate = bookingDate;
        this.bookingDateNumber = bookingDateNumber;
        this.listReport = listReport;
        this.month = month;
    }

    public DashboardDTO() {
    }

    public int getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(int bookingNumber) {
        this.bookingNumber = bookingNumber;
    }

    public int getAccessNumber() {
        return accessNumber;
    }

    public void setAccessNumber(int accessNumber) {
        this.accessNumber = accessNumber;
    }

    public int getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(int paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public int getReviewNumber() {
        return reviewNumber;
    }

    public void setReviewNumber(int reviewNumber) {
        this.reviewNumber = reviewNumber;
    }

    public int getUserRegisNumber() {
        return userRegisNumber;
    }

    public void setUserRegisNumber(int userRegisNumber) {
        this.userRegisNumber = userRegisNumber;
    }

    public List<String> getBookingHotelType() {
        return bookingHotelType;
    }

    public void setBookingHotelType(List<String> bookingHotelType) {
        this.bookingHotelType = bookingHotelType;
    }

    public List<Integer> getBookingHotelTypeNumber() {
        return bookingHotelTypeNumber;
    }

    public void setBookingHotelTypeNumber(List<Integer> bookingHotelTypeNumber) {
        this.bookingHotelTypeNumber = bookingHotelTypeNumber;
    }

    public List<Integer> getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(List<Integer> bookingDate) {
        this.bookingDate = bookingDate;
    }

    public List<Integer> getBookingDateNumber() {
        return bookingDateNumber;
    }

    public void setBookingDateNumber(List<Integer> bookingDateNumber) {
        this.bookingDateNumber = bookingDateNumber;
    }

    public List<ReportDTO> getListReport() {
        return listReport;
    }

    public void setListReport(List<ReportDTO> listReport) {
        this.listReport = listReport;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "DashboardDTO{" + "bookingNumber=" + bookingNumber + ", accessNumber=" + accessNumber + ", paymentNumber=" + paymentNumber + ", reviewNumber=" + reviewNumber + ", userRegisNumber=" + userRegisNumber + ", bookingHotelType=" + bookingHotelType + ", bookingHotelTypeNumber=" + bookingHotelTypeNumber + ", bookingDate=" + bookingDate + ", bookingDateNumber=" + bookingDateNumber + ", listReport=" + listReport + ", month=" + month + '}';
    }
    
    
}
