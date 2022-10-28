/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class Rating {
    private String ratingId;
    private String hotelId;
    private String userId;
    private int communication_point;
    private int accuracy_point;
    private int location_point;
    private int value_point;

    public Rating(String ratingId, String hotelId, String userId, int communication_point, int accuracy_point, int location_point, int value_point) {
        this.ratingId = ratingId;
        this.hotelId = hotelId;
        this.userId = userId;
        this.communication_point = communication_point;
        this.accuracy_point = accuracy_point;
        this.location_point = location_point;
        this.value_point = value_point;
    }
    
    public Rating(int communication_point, int accuracy_point, int location_point, int value_point) {
        this.communication_point = communication_point;
        this.accuracy_point = accuracy_point;
        this.location_point = location_point;
        this.value_point = value_point;
    }
    
    public Rating() {
        
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCommunication_point() {
        return communication_point;
    }

    public void setCommunication_point(int communication_point) {
        this.communication_point = communication_point;
    }

    public int getAccuracy_point() {
        return accuracy_point;
    }

    public void setAccuracy_point(int accuracy_point) {
        this.accuracy_point = accuracy_point;
    }

    public int getLocation_point() {
        return location_point;
    }

    public void setLocation_point(int location_point) {
        this.location_point = location_point;
    }

    public int getValue_point() {
        return value_point;
    }

    public void setValue_point(int value_point) {
        this.value_point = value_point;
    }

    @Override
    public String toString() {
        return "Rating{" + "ratingId=" + ratingId + ", hotelId=" + hotelId + ", userId=" + userId + ", communication_point=" + communication_point + ", accuracy_point=" + accuracy_point + ", location_point=" + location_point + ", value_point=" + value_point + '}';
    }

    
    
    
}
