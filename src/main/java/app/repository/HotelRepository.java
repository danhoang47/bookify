/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.repository;

import app.dao.BookingDAO;
import app.dto.ImageDTO;
import app.dto.HotelDTO;
import app.dto.HotelAmenityDTO;
import app.dao.HotelDAO;
import app.dao.ImageDAO;
import app.dao.HotelAmenityDAO;
import app.dao.ReviewDAO;
import app.dao.RoomTypeDAO;
import app.dao.UserDAO;
import app.dto.BookingDTO;
import app.dto.ReviewDTO;
import app.dto.RoomTypeDTO;
import app.dto.UserDTO;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HotelRepository {

    private HotelDAO hotelDao;
    private ImageDAO imageDao;
    private HotelAmenityDAO hotelAmenityDao;
    private RoomTypeDAO roomTypeDao;
    private ReviewDAO reviewDao;
    private UserDAO userDAO;
    private BookingDAO bookingDao;

    public HotelRepository() {
        hotelDao = new HotelDAO();
        imageDao = new ImageDAO();
        hotelAmenityDao = new HotelAmenityDAO();
        roomTypeDao = new RoomTypeDAO();
        reviewDao = new ReviewDAO();
        userDAO = new UserDAO();
        bookingDao = new BookingDAO();
    }

    public boolean addNewHotel(HotelDTO hotel) {
        return hotelDao.addNewHotel(hotel);
    }

    public HotelDTO get(String id, String userId) throws SQLException, ClassNotFoundException {

        HotelDTO hotelDto = hotelDao.get(id);
        if (hotelDto != null) {
            List<ImageDTO> imageDtos = imageDao.get(hotelDto.getHotelId());
            List<HotelAmenityDTO> hotelAmenityDtos = hotelAmenityDao.get(hotelDto.getHotelId());
            List<ReviewDTO> listReviews = reviewDao.listReview(id);
            RoomTypeDTO roomType = roomTypeDao.get(hotelDto.getHotelId());
            UserDTO owner = userDAO.getOwner(hotelDto.getUserId());

            hotelDto.setIsBookmarked(hotelDao.isHotelBookmarked(id, userId));
            hotelDto.setImages(imageDtos);
            hotelDto.setHotelAmenities(hotelAmenityDtos);
            hotelDto.setRoomType(roomType);
            hotelDto.setReviews(listReviews);
            hotelDto.setHotelOwner(owner);
        }

        return hotelDto;
    }

    public void update(
            HotelDTO hotel,
            List<HotelAmenityDTO> amenities,
            RoomTypeDTO roomTypeDto,
            List<String> viewImageList,
            List<String> roomImageList,
            List<String> deletedImageIdList
    ) throws SQLException {
        int viewImageType = 0;
        int roomImageType = 1;
        hotelDao.update(hotel);
        roomTypeDao.update(roomTypeDto);
        for (HotelAmenityDTO dto : amenities) {
            dto.setHotelId(hotel.getHotelId());
            if (dto.getAmenityId() == null && !dto.getId().contains("new")) {
                dto.setAmenityId(dto.getId());
                dto.setId(UUID.randomUUID().toString());
            }
        }
        hotelAmenityDao.update(amenities);
        for (String id : deletedImageIdList) {
            imageDao.delete(id);
        }
        for (String src : viewImageList) {
            String id = UUID.randomUUID().toString();
            imageDao.add(new ImageDTO(id, hotel.getHotelId(), src, viewImageType));
        }
        for (String src : roomImageList) {
            String id = UUID.randomUUID().toString();
            imageDao.add(new ImageDTO(id, hotel.getHotelId(), src, roomImageType));
        }
    }

    public List<HotelDTO> getAllHotel(String userId) throws SQLException, ClassNotFoundException {
        List<HotelDTO> listHotelBasic = hotelDao.getAllHotelBasicInfo(userId);
        for (int i = 0; i < listHotelBasic.size(); i++) {
            List<ImageDTO> listImage = imageDao.getRandomImage(listHotelBasic.get(i).getHotelId());
            listHotelBasic.get(i).setImages(listImage);
        }
        return listHotelBasic;
    }

    public List<HotelDTO> getAllHotelsDashboard() throws SQLException, ClassNotFoundException {
        List<HotelDTO> listHotelDashboard = hotelDao.getAllHotelsDashboard();

        return listHotelDashboard;
    }

    public List<HotelDTO> getFilterHotels(String type, String userid, String id) throws SQLException, ClassNotFoundException {
        List<HotelDTO> listHotelFilter = hotelDao.getFilterHotel(type, userid, id);
        for (int i = 0; i < listHotelFilter.size(); i++) {
            List<ImageDTO> listImage = imageDao.getRandomImage(listHotelFilter.get(i).getHotelId());
            listHotelFilter.get(i).setImages(listImage);
        }
        return listHotelFilter;
    }

    public List<HotelDTO> getFilterHotelsAdvance(String userId, String houseType, List<String> amenitiesPicked, int rooms, int numberOfBed, int numberOfBathroom, int min, int max) throws SQLException {
        List<HotelDTO> listHotel = hotelDao.getFilterAdvancedHotel(userId, houseType, amenitiesPicked, rooms, numberOfBed, numberOfBathroom, min, max);
        for (int i = 0; i < listHotel.size(); i++) {
            List<ImageDTO> listImage = imageDao.getRandomImage(listHotel.get(i).getHotelId());
            listHotel.get(i).setImages(listImage);
        }
        return listHotel;
    }

    public List<HotelDTO> getAllBookmarkedHotel(String userId) throws SQLException {
        List<HotelDTO> bookmarkedHotels = hotelDao.getAllBookmarkedHotel(userId);
        for (HotelDTO hotel : bookmarkedHotels) {
            hotel.setRoomType(roomTypeDao.get(hotel.getHotelId()));
        }

        return bookmarkedHotels;
    }

    public HotelDTO getByUserId(String userId) throws SQLException, ClassNotFoundException {
        HotelDTO hotel = hotelDao.get(userDAO.getOwnedHotelId(userId));

        return hotel;
    }
    
    public List<BookingDTO> getAllTodayPendingBooking(String hotelId) throws SQLException {
        return bookingDao.getAllTodayPendingBooking(hotelId);
    }
    
     public List<BookingDTO> getAllTodayBookedBooking(String hotelId) throws SQLException {
        return bookingDao.getAllTodayBookedBooking(hotelId);
    }
     
      public List<BookingDTO> getAllTodayCheckoutBooking(String hotelId) throws SQLException {
        return bookingDao.getAllTodayCheckoutBooking(hotelId);
    }
      
      public void acceptBooking(String bookingId) throws SQLException{
          bookingDao.acceptBooking(bookingId);
      }
      
      public void rejectBooking(String bookingId) throws SQLException {
          bookingDao.rejectBooking(bookingId);
      }

    public List<BookingDTO> getAllPendingBooking(String hotelId) throws SQLException {
        return bookingDao.getAllPendingBooking(hotelId);
    }

    public List<BookingDTO> getAllCheckoutBooking(String hotelId) throws SQLException {
        return bookingDao.getAllCheckoutBooking(hotelId);
    }

    public List<BookingDTO> getAllIncomingBooking(String hotelId) throws SQLException {
        return bookingDao.getAllIncomingBooking(hotelId);
    }
}
