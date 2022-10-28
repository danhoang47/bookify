/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

public class HotelAmenityDTO {
    private String id;
    private String amenityId;
    private String hotelId;
    private String icon;
    private String amenityName;
    private String amenityTypeName;
    private String amenityTypeId;
    
    public HotelAmenityDTO() {
        
    }

    public HotelAmenityDTO(String id, String amenityId, String hotelId, String icon, String amenityName, String amenityTypeName, String amenityTypeId) {
        this.id = id;
        this.amenityId = amenityId;
        this.hotelId = hotelId;
        this.icon = icon;
        this.amenityName = amenityName;
        this.amenityTypeName = amenityTypeName;
        this.amenityTypeId = amenityTypeId;
    }

    public String getAmenityTypeId() {
        return amenityTypeId;
    }

    public String getAmenityTypeName() {
        return amenityTypeName;
    }

    public void setAmenityTypeId(String amenityTypeId) {
        this.amenityTypeId = amenityTypeId;
    }

    public void setAmenityTypeName(String amenityTypeName) {
        this.amenityTypeName = amenityTypeName;
    }

    public String getId() {
        return id;
    }

    public String getAmenityId() {
        return amenityId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getAmenityName() {
        return amenityName;
    }
    
    public String getIcon() {
        return icon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmenityId(String amenityId) {
        this.amenityId = amenityId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }
}
