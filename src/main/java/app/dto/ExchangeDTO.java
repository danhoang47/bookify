/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

import java.util.Date;

/**
 *
 * @author toten
 */
public class ExchangeDTO {
    private String booking_id;
    private String user_id;
    private Date check_in;
    private Date check_out;
    private int aldult;
    private int child;
    private int infants;
    private int pets;
    private String room_id;
    private Date bookAt;
    private String userFullName;
    private String username;
    private int price;

    public ExchangeDTO(String booking_id, String user_id, Date check_in, Date check_out, int aldult, int child, int infants, int pets, String room_id, Date bookAt, String userFullName, String username, int price) {
        this.booking_id = booking_id;
        this.user_id = user_id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.aldult = aldult;
        this.child = child;
        this.infants = infants;
        this.pets = pets;
        this.room_id = room_id;
        this.bookAt = bookAt;
        this.userFullName = userFullName;
        this.username = username;
        this.price = price;
    }

    public ExchangeDTO() {
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Date getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Date check_in) {
        this.check_in = check_in;
    }

    public Date getCheck_out() {
        return check_out;
    }

    public void setCheck_out(Date check_out) {
        this.check_out = check_out;
    }

    public int getAldult() {
        return aldult;
    }

    public void setAldult(int aldult) {
        this.aldult = aldult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public Date getBookAt() {
        return bookAt;
    }

    public void setBookAt(Date bookAt) {
        this.bookAt = bookAt;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ExchangeDTO{" + "booking_id=" + booking_id + ", user_id=" + user_id + ", check_in=" + check_in + ", check_out=" + check_out + ", aldult=" + aldult + ", child=" + child + ", infants=" + infants + ", pets=" + pets + ", room_id=" + room_id + ", bookAt=" + bookAt + ", userFullName=" + userFullName + ", username=" + username + ", price=" + price + '}';
    }
    
    
}
