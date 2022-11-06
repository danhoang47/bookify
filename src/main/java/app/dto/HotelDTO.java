package app.dto;

import java.util.List;
import app.dto.HotelAmenityDTO;
import app.dto.RoomTypeDTO;
import java.util.Date;

public class HotelDTO {
//    13

    private String hotelId;
    private String userId;
    private String hotelTypeId;
    private String hotelName;
    private String backgroundImg;
    private boolean isVerified;
    private boolean isAllowPet;
    private boolean isHasCamera;
    private String description;
    private String country;
    private String district;
    private String city;
    private String address;
    private String closing;
    private String opening;
    private String checkin;
    private String checkout;
    private int averagePirce;
    private int rating;
    private List<HotelAmenityDTO> hotelAmenities;
    private List<ImageDTO> images;
    private List<ReviewDTO> reviews;
    private RoomTypeDTO roomType;
    private boolean isBookmarked;
    private UserDTO hotelOwner;
    private Date signAt;

    public HotelDTO() {

    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg,
            boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district,
            String city, String address, String closing, String opening, String checkin, String checkout, List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.closing = closing;
        this.opening = opening;
        this.checkin = checkin;
        this.checkout = checkout;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg,
            boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district,

            String city, String address, String closing, String opening, String checkin, String checkout, List<HotelAmenityDTO> hotelAmenities, 
            List<ImageDTO> images, int rating, Date signAt) {

        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.closing = closing;
        this.opening = opening;
        this.checkin = checkin;
        this.checkout = checkout;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
        this.rating = rating;
        this.signAt = signAt;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address, String closing, String opening, String checkin, String checkout, int averagePirce, int rating, List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images, List<ReviewDTO> reviews, RoomTypeDTO roomType, boolean isBookmarked, UserDTO hotelOwner, Date signAt) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.closing = closing;
        this.opening = opening;
        this.checkin = checkin;
        this.checkout = checkout;
        this.averagePirce = averagePirce;
        this.rating = rating;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
        this.reviews = reviews;
        this.roomType = roomType;
        this.isBookmarked = isBookmarked;
        this.hotelOwner = hotelOwner;
        this.signAt = signAt;
    }
    
    

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg,
            boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country,
            String district, String city, String address, List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, String description, String country, String district, String city, String address) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg,
            boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, String description, String country,
            String district, String city, String address, boolean isHasCamera, boolean isAllowPet, String checkin, String checkout, String closing, String opening) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.closing = closing;
        this.opening = opening;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg,
            boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country,
            String district, String city, String address, String closing, String opening, String checkin, String checkout,
            List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images, RoomTypeDTO roomType) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.closing = closing;
        this.opening = opening;
        this.checkin = checkin;
        this.checkout = checkout;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
        this.roomType = roomType;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address, String closing, String opening, String checkin, String checkout, int averagePirce, int rating, List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images, List<ReviewDTO> reviews, RoomTypeDTO roomType, boolean isBookmarked) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.closing = closing;
        this.opening = opening;
        this.checkin = checkin;
        this.checkout = checkout;
        this.averagePirce = averagePirce;
        this.rating = rating;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
        this.reviews = reviews;
        this.roomType = roomType;
        this.isBookmarked = isBookmarked;
    }

    public HotelDTO(String hotelId, String userId, String hotelTypeId, String hotelName, String backgroundImg, boolean isVerified, boolean isAllowPet, boolean isHasCamera, String description, String country, String district, String city, String address, String closing, String opening, String checkin, String checkout, int averagePirce, int rating, List<HotelAmenityDTO> hotelAmenities, List<ImageDTO> images, List<ReviewDTO> reviews, RoomTypeDTO roomType, boolean isBookmarked, UserDTO hotelOwner) {
        this.hotelId = hotelId;
        this.userId = userId;
        this.hotelTypeId = hotelTypeId;
        this.hotelName = hotelName;
        this.backgroundImg = backgroundImg;
        this.isVerified = isVerified;
        this.isAllowPet = isAllowPet;
        this.isHasCamera = isHasCamera;
        this.description = description;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.closing = closing;
        this.opening = opening;
        this.checkin = checkin;
        this.checkout = checkout;
        this.averagePirce = averagePirce;
        this.rating = rating;
        this.hotelAmenities = hotelAmenities;
        this.images = images;
        this.reviews = reviews;
        this.roomType = roomType;
        this.isBookmarked = isBookmarked;
        this.hotelOwner = hotelOwner;
    }

    public HotelDTO(String hotelId, String hotelName, Date signAt, boolean isVerified, UserDTO hotelOwner) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.isVerified = isVerified;
        this.hotelOwner = hotelOwner;
        this.signAt = signAt;
  
    }

   
    

    public HotelDTO(String hotelId, String hotelName, String hotelTypeId, String backgroundImg, String country, String district, String city, String address, int averagePirce, int rating, boolean isBookmarked) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.hotelTypeId = hotelTypeId;
        this.backgroundImg = backgroundImg;
        this.country = country;
        this.district = district;
        this.city = city;
        this.address = address;
        this.averagePirce = averagePirce;
        this.rating = rating;
        this.isBookmarked = isBookmarked;
    }

    public Date getSignAt() {
        return signAt;
    }

    public void setSignAt(Date signAt) {
        this.signAt = signAt;
    }
    
    
    

    public List<ReviewDTO> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDTO> reviews) {
        this.reviews = reviews;
    }

    public boolean isIsBookmarked() {
        return isBookmarked;
    }

    public void setIsBookmarked(boolean isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    public void setRoomType(RoomTypeDTO roomType) {
        this.roomType = roomType;
    }

    public RoomTypeDTO getRoomType() {
        return roomType;
    }

    public String getClosing() {
        return closing;
    }

    public String getOpening() {
        return opening;
    }

    public String getCheckin() {
        return checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public void setImages(List<ImageDTO> images) {
        this.images = images;
    }

    public List<ImageDTO> getImages() {
        return images;
    }

    public boolean isIsAllowPet() {
        return isAllowPet;
    }

    public boolean isIsHasCamera() {
        return isHasCamera;
    }

    public void setIsAllowPet(boolean isAllowPet) {
        this.isAllowPet = isAllowPet;
    }

    public void setIsHasCamera(boolean isHasCamera) {
        this.isHasCamera = isHasCamera;
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

    public String getHotelTypeId() {
        return hotelTypeId;
    }

    public void setHotelTypeId(String hotelTypeId) {
        this.hotelTypeId = hotelTypeId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getBackgroundImg() {
        return backgroundImg;
    }

    public void setBackgroundImg(String backgroundImg) {
        this.backgroundImg = backgroundImg;
    }

    public boolean isIsVerified() {
        return isVerified;
    }

    public void setIsVerified(boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHotelAmenities(List<HotelAmenityDTO> hotelAmenities) {
        this.hotelAmenities = hotelAmenities;
    }

    public List<HotelAmenityDTO> getHotelAmenities() {
        return hotelAmenities;
    }

    public int getAveragePirce() {
        return averagePirce;
    }

    public void setAveragePirce(int averagePirce) {
        this.averagePirce = averagePirce;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public UserDTO getHotelOwner() {
        return hotelOwner;
    }

    public void setHotelOwner(UserDTO hotelOwner) {
        this.hotelOwner = hotelOwner;
    }

    @Override
    public String toString() {

        return "HotelDTO{" + "hotelId=" + hotelId + ", userId=" + userId
                + ", hotelTypeId=" + hotelTypeId + ", hotelName=" + hotelName
                + ", backgroundImg=" + backgroundImg + ", isVerified=" + isVerified
                + ", isAllowPet=" + isAllowPet + ", isHasCamera=" + isHasCamera
                + ", description=" + description + ", country=" + country + ", district="
                + district + ", city=" + city + ", address=" + address + ", closing=" + closing
                + ", opening=" + opening + ", checkin=" + checkin + ", checkout="
                + checkout + ", averagePirce=" + averagePirce + ", rating=" + rating
                + ", hotelAmenities=" + hotelAmenities + ", images=" + images + ", reviews="
                + reviews + ", roomType=" + roomType + ", isBookmarked=" + isBookmarked + ", hotelOwner=" + hotelOwner + '}';
    }
}