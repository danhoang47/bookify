/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class HotelType {
    private String hoteltypeId;
    private String hotelType;
    private String icon;

    public HotelType(String hoteltypeId, String hotelType, String icon) {
        this.hoteltypeId = hoteltypeId;
        this.hotelType = hotelType;
        this.icon = icon;
    }
    
    public HotelType() {
        
    }

    public String getHoteltypeId() {
        return hoteltypeId;
    }

    public void setHoteltypeId(String hoteltypeId) {
        this.hoteltypeId = hoteltypeId;
    }

    public String getHotelType() {
        return hotelType;
    }

    public void setHotelType(String hotelType) {
        this.hotelType = hotelType;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "HotelType{" + "hoteltypeId=" + hoteltypeId + ", hotelType=" + hotelType + ", icon=" + icon + '}';
    }
    
    
}
