/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

import java.util.List;

/**
 *
 * @author toten
 */
public class Hotel {
//    17

    private String hotelId;
    private String userId;
    private String hotelTypeId;
    private String hotelName;
    private String backgroundImg;
    private boolean isVerified;
    private String description;
    private String country;
    private String district;
    private String city;
    private String address;
    private boolean isAllowPet;
    private boolean isHasCamera;
    private String checkin;
    private String checkout;
    private String closing;
    private String opening;
    private List<HotelAmenityDTO> hotelAmenities;
    private List<ImageDTO> images;

    public Hotel() {

    }

    public Hotel(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, String description, String country, 
            String district, String city, String address, boolean isHasCamera, boolean isAllowPet, String checkin, String checkout, String closing, String opening) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.isHasCamera = isHasCamera;
        this.isAllowPet = isAllowPet;
        this.checkin = checkin;
        this.checkout = checkout;
        this.closing = closing;
        this.opening = opening;
    }
    
    public Hotel(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, String description, String country, 
            String district, String city, String address, boolean isHasCamera, boolean isAllowPet, String checkin, String checkout, String closing, String opening,
            List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.isHasCamera = isHasCamera;
        this.isAllowPet = isAllowPet;
        this.checkin = checkin;
        this.checkout = checkout;
        this.closing = closing;
        this.opening = opening;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
    }
    
    public Hotel(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, 
            boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address, 
            List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
    }

    public Hotel(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, String description, String country, String district, String city, String address) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
    }

    public Hotel(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isIsAllowPet() {
        return isAllowPet;
    }

    public void setIsAllowPet(boolean isAllowPet) {
        this.isAllowPet = isAllowPet;
    }

    public boolean isIsHasCamera() {
        return isHasCamera;
    }

    public void setIsHasCamera(boolean isHasCamera) {
        this.isHasCamera = isHasCamera;
    }

    public List<HotelAmenityDTO> getHotelAmenities() {
        return hotelAmenities;
    }

    public void setHotelAmenities(List<HotelAmenityDTO> hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    
    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    @Override
    public String toString() {
        return "Hotel{" + "hotelId=" + hotelId + ", userId=" + userId + ", hotelTypeId=" + hotelTypeId + ", hotelName=" + hotelName + ", backgroundImg=" + backgroundImg + ", isVerified=" + isVerified + ", description=" + description + ", country=" + country + ", district=" + district + ", city=" + city + ", address=" + address + ", isAllowPet=" + isAllowPet + ", isHasCamera=" + isHasCamera + ", checkin=" + checkin + ", checkout=" + checkout + ", closing=" + closing + ", opening=" + opening + ", hotelAmenities=" + hotelAmenities + ", images=" + images + '}';
    }

    

}
