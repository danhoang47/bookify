/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

/**
 *
 * @author ADMIN
 */
public class ImageDTO {
    private String id;
    private String hotelId;
    private String src;
    private int type;

    public ImageDTO() {
    }

    public ImageDTO(String id, String hotelId, String src, int type) {
        this.id = id;
        this.hotelId = hotelId;
        this.src = src;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getSrc() {
        return src;
    }

    public int getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public void setType(int type) {
        this.type = type;
    }
}
