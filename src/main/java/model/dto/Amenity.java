/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class Amenity {
    private String amenity_id;
    private String name;
    private String icon;

    public Amenity(String amenity_id, String name, String icon) {
        this.amenity_id = amenity_id;
        this.name = name;
        this.icon = icon;
    }
    
    public Amenity() {
        
    }

    public String getAmenity_id() {
        return amenity_id;
    }

    public void setAmenity_id(String amenity_id) {
        this.amenity_id = amenity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Amenity{" + "amenity_id=" + amenity_id + ", name=" + name + ", icon=" + icon + '}';
    }
    
    
    
}
