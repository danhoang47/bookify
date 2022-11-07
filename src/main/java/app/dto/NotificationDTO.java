/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dto;

/**
 *
 * @author ADMIN
 */
public class NotificationDTO {
    private int id;
    private String userId;
    private String hotelId;
    private String sourceId;
    private int notifyType;
    private String notifyDate;
    private String hotelName;
    private String actorName;
    private boolean isRead;
    private String actorAvatar;
    private String actorId;

    public NotificationDTO(int id, String userId, String hotelId, String sourceId, 
            int notifyType, String notifyDate, String hotelName, 
            String actorName, boolean isRead, String actorAvatar) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.sourceId = sourceId;
        this.notifyType = notifyType;
        this.notifyDate = notifyDate;
        this.hotelName = hotelName;
        this.actorName = actorName;
        this.isRead = isRead;
        this.actorAvatar = actorAvatar;
    }

    public NotificationDTO(int id, String userId, String hotelId, String sourceId, 
            int notifyType, String notifyDate, String hotelName, String actorName,
            boolean isRead, String actorAvatar, String actorId) {
        this.id = id;
        this.userId = userId;
        this.hotelId = hotelId;
        this.sourceId = sourceId;
        this.notifyType = notifyType;
        this.notifyDate = notifyDate;
        this.hotelName = hotelName;
        this.actorName = actorName;
        this.isRead = isRead;
        this.actorAvatar = actorAvatar;
        this.actorId = actorId;
    }

    public String getActorId() {
        return actorId;
    }

    public void setActorId(String actorId) {
        this.actorId = actorId;
    }
  
    public void setActorAvatar(String actorAvatar) {
        this.actorAvatar = actorAvatar;
    }

    public String getActorAvatar() {
        return actorAvatar;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getHotelId() {
        return hotelId;
    }

    public String getSourceId() {
        return sourceId;
    }

    public int getNotifyType() {
        return notifyType;
    }

    public String getNotifyDate() {
        return notifyDate;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getActorName() {
        return actorName;
    }

    public boolean isIsRead() {
        return isRead;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public void setNotifyType(int notifyType) {
        this.notifyType = notifyType;
    }

    public void setNotifyDate(String notifyDate) {
        this.notifyDate = notifyDate;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public void setIsRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return "NotificationDTO{" + "id=" + id + ", userId=" + userId + ", hotelId=" + hotelId + ", sourceId=" + sourceId + ", notifyType=" + notifyType + ", notifyDate=" + notifyDate + ", hotelName=" + hotelName + ", actorName=" + actorName + ", isRead=" + isRead + '}';
    }

}
