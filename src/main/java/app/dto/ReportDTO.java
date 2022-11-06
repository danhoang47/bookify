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
public class ReportDTO {
    private String reportId;
    private String hotelId;
    private String hotelName;
    private String userId;
    private String username;
    private String avatar;
    private String title;
    private String content;
    private Date reportDate;

    public ReportDTO() {
    }

    public ReportDTO(String reportId, String hotelId, String userId, String username, String avatar, String title, String content, Date reportDate) {
        this.reportId = reportId;
        this.hotelId = hotelId;
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
        this.title = title;
        this.content = content;
        this.reportDate = reportDate;
    }

    public ReportDTO(String reportId, String hotelId, String hotelName, String userId, String username, String avatar, String title, String content, Date reportDate) {
        this.reportId = reportId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
        this.title = title;
        this.content = content;
        this.reportDate = reportDate;
    }
    
    

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    @Override
    public String toString() {
        return "ReportDTO{" + "reportId=" + reportId + ", hotelId=" + hotelId + ", userId=" + userId + ", username=" + username + ", avatar=" + avatar + ", title=" + title + ", content=" + content + ", reportDate=" + reportDate + '}';
    }

    
    
    
}