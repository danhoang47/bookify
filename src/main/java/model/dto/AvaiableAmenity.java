/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class AvaiableAmenity {
    private String amenity_id;
    private String amenity_name;
    private String icon;
    private String type;

    public AvaiableAmenity(String amenity_id, String amenity_name, String icon, String type) {
        this.amenity_id = amenity_id;
        this.amenity_name = amenity_name;
        this.icon = icon;
        this.type = type;
    }

    public AvaiableAmenity() {
    }

    public String getAmenity_id() {
        return amenity_id;
    }

    public void setAmenity_id(String amenity_id) {
        this.amenity_id = amenity_id;
    }

    public String getAmenity_name() {
        return amenity_name;
    }

    public void setAmenity_name(String amenity_name) {
        this.amenity_name = amenity_name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AvaiableAmenity{" + "amenity_id=" + amenity_id + ", amenity_name=" + amenity_name + ", icon=" + icon + ", type=" + type + '}';
    }
    
    
}
