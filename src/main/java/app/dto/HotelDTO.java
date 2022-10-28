/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;
import java.util.List;
import app.dto.HotelAmenityDTO;

/**
 *
 * @author toten
 */
public class HotelDTO {
//    13
    private String hotelId;
    private String userId;
    private String hotelTypeId;
    private String hotelName;
    private String backgroundImg;
    private boolean isVerified;
    private boolean isAllowPet;
    private boolean isHasCamera;
    private String description;
    private String country;
    private String district;
    private String city;
    private String address;
    private List<HotelAmenityDTO> hotelAmenities;
    private List<ImageDTO> images;

    
    public HotelDTO() {
        
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address, List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images) {
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

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, String description, String country, String district, String city, String address) {
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

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address) {
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

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public boolean isIsAllowPet() {
        return isAllowPet;
    }

    public boolean isIsHasCamera() {
        return isHasCamera;
    }

    public void setIsAllowPet(boolean isAllowPet) {
        this.isAllowPet = isAllowPet;
    }

    public void setIsHasCamera(boolean isHasCamera) {
        this.isHasCamera = isHasCamera;
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

    public void setHotelAmenities(List<HotelAmenityDTO> hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public List<HotelAmenityDTO> getHotelAmenities() {
        return hotelAmenities;
    }

    @Override
    public String toString() {
        return "Hotel{" + "hotelId=" + hotelId + ", userId=" + userId + ", hotelTypeId=" + hotelTypeId + ", hotelName=" + hotelName + ", backgroundImg=" + backgroundImg + ", isVerified=" + isVerified + ", description=" + description + ", country=" + country + ", district=" + district + ", city=" + city + ", address=" + address + '}';
    }

    
    
    
}
