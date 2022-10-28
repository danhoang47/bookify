/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class AmenityType {
    private String amenity_type_id;
    private String amenity_type_name;
    private int amenity_type_number;

    public AmenityType(String amenity_type_id, String amenity_type_name, int amenity_type_number) {
        this.amenity_type_id = amenity_type_id;
        this.amenity_type_name = amenity_type_name;
        this.amenity_type_number = amenity_type_number;
    }

    public AmenityType() {
    }

    
    
    public String getAmenity_type_id() {
        return amenity_type_id;
    }

    public void setAmenity_type_id(String amenity_type_id) {
        this.amenity_type_id = amenity_type_id;
    }

    public String getAmenity_type_name() {
        return amenity_type_name;
    }

    public void setAmenity_type_name(String amenity_type_name) {
        this.amenity_type_name = amenity_type_name;
    }

    public int getAmenity_type_number() {
        return amenity_type_number;
    }

    public void setAmenity_type_number(int amenity_type_number) {
        this.amenity_type_number = amenity_type_number;
    }

    @Override
    public String toString() {
        return "AmenityType{" + "amenity_type_id=" + amenity_type_id + ", amenity_type_name=" + amenity_type_name + ", amenity_type_number=" + amenity_type_number + '}';
    }
    
    
}
