/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

import java.util.Date;





/**
 *
 * @author toten
 */
public class ReviewDTO {
     private String reviewId;
    private String hotelId;
    private String userId;
    private String content;
    private int sourceId;
    private int communication_point;
    private int accuracy_point;
    private int location_point;
    private int value_point;
    private Date createdAt;
    private String username;
    private String avatar;
    private String usernameAcount;
    private String minute;

    public ReviewDTO(String reviewId, String hotelId, String userId, String content, int sourceId, int communication_point, int accuracy_point, int location_point, int value_point, Date createdAt, String username, String avatar) {
        this.reviewId = reviewId;
        this.hotelId = hotelId;
        this.userId = userId;
        this.content = content;
        this.sourceId = sourceId;
        this.communication_point = communication_point;
        this.accuracy_point = accuracy_point;
        this.location_point = location_point;
        this.value_point = value_point;
        this.createdAt = createdAt;
        this.username = username;
        this.avatar = avatar;
    }

    public ReviewDTO(String reviewId, String hotelId, String userId, String content, int sourceId, int communication_point, int accuracy_point, int location_point, int value_point, Date createdAt, String username, String avatar, String usernameAcount, String minute) {
        this.reviewId = reviewId;
        this.hotelId = hotelId;
        this.userId = userId;
        this.content = content;
        this.sourceId = sourceId;
        this.communication_point = communication_point;
        this.accuracy_point = accuracy_point;
        this.location_point = location_point;
        this.value_point = value_point;
        this.createdAt = createdAt;
        this.username = username;
        this.avatar = avatar;
        this.usernameAcount = usernameAcount;
        this.minute = minute;
    }
    
    

    public ReviewDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsernameAcount() {
        return usernameAcount;
    }

    public void setUsernameAcount(String usernameAcount) {
        this.usernameAcount = usernameAcount;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    


    
    

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
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

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public int getCommunication_point() {
        return communication_point;
    }

    public void setCommunication_point(int communication_point) {
        this.communication_point = communication_point;
    }

    public int getAccuracy_point() {
        return accuracy_point;
    }

    public void setAccuracy_point(int accuracy_point) {
        this.accuracy_point = accuracy_point;
    }

    public int getLocation_point() {
        return location_point;
    }

    public void setLocation_point(int location_point) {
        this.location_point = location_point;
    }

    public int getValue_point() {
        return value_point;
    }

    public void setValue_point(int value_point) {
        this.value_point = value_point;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" + "reviewId=" + reviewId + ", hotelId=" + hotelId + ", userId=" + userId + ", content=" + content + ", sourceId=" + sourceId + ", communication_point=" + communication_point + ", accuracy_point=" + accuracy_point + ", location_point=" + location_point + ", value_point=" + value_point + ", createdAt=" + createdAt + ", username=" + username + ", avatar=" + avatar + '}';
    }
    
    

    
    
    
}
