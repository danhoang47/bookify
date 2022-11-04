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
    private String userId;
    private String title;
    private String content;
    private Date reportDate;

    public ReportDTO() {
    }

    public ReportDTO(String reportId, String hotelId, String userId, String title, String content, Date reportDate) {
        this.reportId = reportId;
        this.hotelId = hotelId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.reportDate = reportDate;
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
        return "ReportDTO{" + "reportId=" + reportId + ", hotelId=" + hotelId + ", userId=" + userId + ", title=" + title + ", content=" + content + ", reportDate=" + reportDate + '}';
    }
    
    
}
