/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
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
    
    public HotelAmenityDTO(String amenityId, String amenityName, String icon, String amenityTypeId) {
        this.amenityId = amenityId;
        this.icon = icon;
        this.amenityName = amenityName;
        this.amenityTypeId = amenityTypeId;
    }
    
     public HotelAmenityDTO(String amenityTypeId, String amenityTypeName ) {
        this.amenityTypeId = amenityTypeId;
         this.amenityName = amenityTypeName;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmenityId() {
        return amenityId;
    }

    public void setAmenityId(String amenityId) {
        this.amenityId = amenityId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getAmenityName() {
        return amenityName;
    }

    public void setAmenityName(String amenityName) {
        this.amenityName = amenityName;
    }

    public String getAmenityTypeName() {
        return amenityTypeName;
    }

    public void setAmenityTypeName(String amenityTypeName) {
        this.amenityTypeName = amenityTypeName;
    }

    public String getAmenityTypeId() {
        return amenityTypeId;
    }

    public void setAmenityTypeId(String amenityTypeId) {
        this.amenityTypeId = amenityTypeId;
    }

    @Override
    public String toString() {
        return "HotelAmenities{" + "id=" + id + ", amenityId=" + amenityId + ", hotelId=" + hotelId + ", icon=" + icon + ", amenityName=" + amenityName + ", amenityTypeName=" + amenityTypeName + ", amenityTypeId=" + amenityTypeId + '}';
    }
    
    
}
