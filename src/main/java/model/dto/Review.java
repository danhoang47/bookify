/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

import java.util.Date;

/**
 *
 * @author toten
 */
public class Review {
//    8
    private String reviewId;
    private String hotelId;
    private String userId;
    private String userFullName;
    private String userAvatar;
    private String content;
    private String sourceId;
    private Date create_at;
    
    public Review() {
        
    }

    public Review(String reviewId, String hotelId, String userId, String userFullName, String userAvatar, String content, String sourceId, Date create_at) {
        this.reviewId = reviewId;
        this.hotelId = hotelId;
        this.userId = userId;
        this.userFullName = userFullName;
        this.userAvatar = userAvatar;
        this.content = content;
        this.sourceId = sourceId;
        this.create_at = create_at;
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

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public Date getCreate_at() {
        return create_at;
    }

    public void setCreate_at(Date create_at) {
        this.create_at = create_at;
    }

    @Override
    public String toString() {
        return "Review{" + "reviewId=" + reviewId + ", hotelId=" + hotelId + ", userId=" + userId + ", userFullName=" + userFullName + ", userAvatar=" + userAvatar + ", content=" + content + ", sourceId=" + sourceId + ", create_at=" + create_at + '}';
    }
    
    
    
        
}
