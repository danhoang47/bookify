/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

/**
 *
 * @author ADMIN
 */
public class AmenityTypeDTO {
    private String amenityTypeId;
    private String amenityTypeName;

    public AmenityTypeDTO() {
    }

    public AmenityTypeDTO(String amenityTypeId, String amenityTypeName) {
        this.amenityTypeId = amenityTypeId;
        this.amenityTypeName = amenityTypeName;
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
}
