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
public class DateRangeDTO {
    private String room_id;
    private Date check_in;
    private Date check_out;
    private String hotel_id;

    public DateRangeDTO(String room_id, Date check_in, Date check_out) {
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
    }

    public DateRangeDTO(String hotel_id, Date check_in, Date check_out, String room_id) {
        this.room_id = room_id;
        this.check_in = check_in;
        this.check_out = check_out;
        this.hotel_id = hotel_id;
    }


    public DateRangeDTO() {
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
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

    @Override
    public String toString() {
        return "DateRangeDTO{" + "room_id=" + room_id + ", check_in=" + check_in + ", check_out=" + check_out + '}';
    }

    
    
    
}
