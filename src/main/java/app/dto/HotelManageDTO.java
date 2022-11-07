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
public class HotelManageDTO {
    private int bookingNumber;
    private int accessNumber;
    private int paymentNumber;
    private List<String> review;
    private int userRegisNumber;
    private List<Integer> bookingDays;
    private List<Integer> totalBookingPerDay;
    private List<Integer> incomeDays;
    private List<Integer> totalIncomePerDays;
    private List<Integer> accessDays;
    private List<Integer> totalAccessPerDays;
    private int month;

    public HotelManageDTO(int bookingNumber, int accessNumber, int paymentNumber, List<String> review, int userRegisNumber, List<Integer> bookingDays, List<Integer> totalBookingPerDay, List<Integer> incomeDays, List<Integer> totalIncomePerDays, List<Integer> accessDays, List<Integer> totalAccessPerDays, int month) {
        this.bookingNumber = bookingNumber;
        this.accessNumber = accessNumber;
        this.paymentNumber = paymentNumber;
        this.review = review;
        this.userRegisNumber = userRegisNumber;
        this.bookingDays = bookingDays;
        this.totalBookingPerDay = totalBookingPerDay;
        this.incomeDays = incomeDays;
        this.totalIncomePerDays = totalIncomePerDays;
        this.accessDays = accessDays;
        this.totalAccessPerDays = totalAccessPerDays;
        this.month = month;
    }

    public HotelManageDTO() {
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

    public List<String> getReview() {
        return review;
    }

    public void setReview(List<String> review) {
        this.review = review;
    }

    public int getUserRegisNumber() {
        return userRegisNumber;
    }

    public void setUserRegisNumber(int userRegisNumber) {
        this.userRegisNumber = userRegisNumber;
    }

    public List<Integer> getBookingDays() {
        return bookingDays;
    }

    public void setBookingDays(List<Integer> bookingDays) {
        this.bookingDays = bookingDays;
    }

    public List<Integer> getTotalBookingPerDay() {
        return totalBookingPerDay;
    }

    public void setTotalBookingPerDay(List<Integer> totalBookingPerDay) {
        this.totalBookingPerDay = totalBookingPerDay;
    }

    public List<Integer> getIncomeDays() {
        return incomeDays;
    }

    public void setIncomeDays(List<Integer> incomeDays) {
        this.incomeDays = incomeDays;
    }

    public List<Integer> getTotalIncomePerDays() {
        return totalIncomePerDays;
    }

    public void setTotalIncomePerDays(List<Integer> totalIncomePerDays) {
        this.totalIncomePerDays = totalIncomePerDays;
    }

    public List<Integer> getAccessDays() {
        return accessDays;
    }

    public void setAccessDays(List<Integer> accessDays) {
        this.accessDays = accessDays;
    }

    public List<Integer> getTotalAccessPerDays() {
        return totalAccessPerDays;
    }

    public void setTotalAccessPerDays(List<Integer> totalAccessPerDays) {
        this.totalAccessPerDays = totalAccessPerDays;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    @Override
    public String toString() {
        return "HotelManageDTO{" + "bookingNumber=" + bookingNumber + ", accessNumber=" + accessNumber + ", paymentNumber=" + paymentNumber + ", review=" + review + ", userRegisNumber=" + userRegisNumber + ", bookingDays=" + bookingDays + ", totalBookingPerDay=" + totalBookingPerDay + ", incomeDays=" + incomeDays + ", totalIncomePerDays=" + totalIncomePerDays + ", accessDays=" + accessDays + ", totalAccessPerDays=" + totalAccessPerDays + ", month=" + month + '}';
    }

    
    
    
}
