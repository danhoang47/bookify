
package app.dto;

import java.util.Date;

public class BookingDTO {
    private UserDTO user;
    private String roomId;
    private String hotelId;
    private int price;
    private String bookingId;
    private String checkin;
    private String checkout;
    private int adult;
    private int child;
    private int infant;
    private int pet;
    private int status;
    private Date bookAt;
    private RoomTypeDTO roomType;

    public BookingDTO() {
    }

    public BookingDTO(UserDTO user, String hotelId, int price, int adult, int child, int infant, int pet, int status, Date bookAt, RoomTypeDTO roomType) {
        this.user = user;
        this.hotelId = hotelId;
        this.price = price;
        this.adult = adult;
        this.child = child;
        this.infant = infant;
        this.pet = pet;
        this.status = status;
        this.bookAt = bookAt;
        this.roomType = roomType;
    }

    public BookingDTO(UserDTO user, String roomId, String hotelId, 
            int price, String bookingId, String checkin, 
            String checkout, int adult, int child, int infant, int pet, int status, 
            Date bookAt, RoomTypeDTO roomType
    ) {
        this.user= user;
        this.roomId = roomId;
        this.hotelId = hotelId;
        this.price = price;
        this.bookingId = bookingId;
        this.checkin = checkin;
        this.checkout = checkout;
        this.adult = adult;
        this.child = child;
        this.infant = infant;
        this.pet = pet;
        this.status = status;
        this.bookAt = bookAt;
        this.roomType = roomType;
    }
    
    public BookingDTO(
        String checkin,
        String checkout,
        int adult,
        int child,
        int pet,
        int infant,
        UserDTO user,
        String roomId,
        String bookingId
    ) {
        this.user = user;
        this.roomId = roomId;
        this.bookingId = bookingId;
        this.adult = adult;
        this.child = child;
        this.infant = infant;
        this.pet = pet;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
     
    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    
    public String getRoomId() {
        return this.roomId;
    }

    public UserDTO getUser() {
        return user;
    }

    public String getHotelId() {
        return hotelId;
    }

    public int getPrice() {
        return price;
    }

    public int getAdult() {
        return adult;
    }

    public int getChild() {
        return child;
    }

    public int getInfant() {
        return infant;
    }

    public int getPet() {
        return pet;
    }

    public int getStatus() {
        return status;
    }

    public Date getBookAt() {
        return bookAt;
    }

    public RoomTypeDTO getRoomType() {
        return roomType;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public void setInfant(int infant) {
        this.infant = infant;
    }

    public void setPet(int pet) {
        this.pet = pet;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setBookAt(Date bookAt) {
        this.bookAt = bookAt;
    }

    public void setRoomType(RoomTypeDTO roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "BookingDTO{" + "userId=" + user.getName() + ", hotelId=" + hotelId + ", price=" + price + ", adult=" + adult + ", child=" + child + ", infant=" + infant + ", pet=" + pet + ", status=" + status + ", bookAt=" + bookAt + ", roomType=" + roomType + '}';
    }
    
    
}
