/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

/**
 *
 * @author toten
 */
public class Image {
    private String imageId;
    private String hotelId;
    private String image;
    private int type;


    
    public Image(String imageId, String image) {
        this.imageId = imageId;
        this.image = image;
    }
    
    
    public Image() {
        
    }

   
    

    public Image(String imageId, String hotelId, String image, int type) {
        this.imageId = imageId;
        this.hotelId = hotelId;
        this.image = image;
        this.type = type;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Image{" + "imageId=" + imageId + ", hotelId=" + hotelId + ", image=" + image + ", type=" + type + '}';
    }

    
}
