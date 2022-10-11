/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class Hotel {
//    13
    private String hotelId;
    private String userId;
    private String hotelTypeId;
    private String hotelName;
    private String ratingId;
    private String backgroundImg;
    private boolean isVerified;
    private String description;
    private String country;
    private String district;
    private String city;
    private String streetName;
    private String streetNum;
    
    public Hotel() {
        
    }

    public Hotel(String hotelId, String userId, String hotelTypeId, String hotelName, String ratingId, String backgroundImg, boolean isVerified, String description, String country, String district, String city, String streetName, String streetNum) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.ratingId = ratingId;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.streetName = streetName;
        this.streetNum = streetNum;
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

    public String getHotelTypeId() {
        return hotelTypeId;
    }

    public void setHotelTypeId(String hotelTypeId) {
        this.hotelTypeId = hotelTypeId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRatingId() {
        return ratingId;
    }

    public void setRatingId(String ratingId) {
        this.ratingId = ratingId;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    @Override
    public String toString() {
        return "Hotel{" + "hotelId=" + hotelId + ", userId=" + userId + ", hotelTypeId=" + hotelTypeId + ", hotelName=" + hotelName + ", ratingId=" + ratingId + ", backgroundImg=" + backgroundImg + ", isVerified=" + isVerified + ", description=" + description + ", country=" + country + ", district=" + district + ", city=" + city + ", streetName=" + streetName + ", streetNum=" + streetNum + '}';
    }

    
}
