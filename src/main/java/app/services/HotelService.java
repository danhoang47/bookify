package app.services;

import app.dao.BookingDAO;
import app.dao.ReportDAO;
import app.dto.BookingDTO;
import app.dto.HotelAmenityDTO;
import app.dto.HotelDTO;
import app.dto.RoomTypeDTO;
import app.dto.UserDTO;
import app.repository.HotelRepository;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import app.utils.UploadImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author ADMIN
 */
public class HotelService {

    final private HotelRepository hotelRepo;
    final private ReportDAO reportDao;

    public HotelService() {
        hotelRepo = new HotelRepository();
        reportDao = new ReportDAO();
    }

    public HotelDTO get(String hotelId, String userId) throws SQLException, ClassNotFoundException {
        
        return hotelRepo.get(hotelId, userId);
    }

    public void update(
            HotelDTO hotel,
            HotelDTO extraInforDto,
            List<HotelAmenityDTO> amenities,
            RoomTypeDTO roomTypeDto,
            InputStream fileInputStreamBG,
            FormDataContentDisposition fileFormDataContentDispositionBG,
            FormDataBodyPart viewImages,
            FormDataBodyPart roomImages,
            List<String> deletedImageIdList,
            String realPath
    ) throws IOException, SQLException {
        String typeUpload = "hotels";
        // merge hotel and extraInfor
        hotel.setIsHasCamera(extraInforDto.isIsHasCamera());
        hotel.setIsAllowPet(extraInforDto.isIsAllowPet());
        hotel.setClosing(extraInforDto.getClosing());
        hotel.setOpening(extraInforDto.getOpening());
        hotel.setCheckout(extraInforDto.getCheckout());
        hotel.setCheckin(extraInforDto.getCheckin());

        // get images src
        if (fileFormDataContentDispositionBG.getFileName() != null) {
            String backgroundImageSrc = UploadImage.uploadSingleFile(fileInputStreamBG, fileFormDataContentDispositionBG, typeUpload, realPath);
            hotel.setBackgroundImg(backgroundImageSrc);
            System.out.println(hotel.getBackgroundImg());
        }
        List<String> viewImageList = UploadImage.uploadMultipleFile(viewImages, typeUpload, realPath);
        for (String src : viewImageList) {
            System.out.println(src);
        }

        List<String> roomImageList = UploadImage.uploadMultipleFile(roomImages, typeUpload, realPath);
        for (String src : roomImageList) {
            System.out.println(src);
        }

        hotelRepo.update(hotel, amenities, roomTypeDto, viewImageList, roomImageList, deletedImageIdList);
    }

    public boolean addNewHotel(HotelDTO hotel) {
        return hotelRepo.addNewHotel(hotel);
    }

    public List<HotelDTO> getAllHotelBasicInfo(String userId) throws SQLException, ClassNotFoundException {
        return hotelRepo.getAllHotel(userId);
    }

    public List<HotelDTO> getFilterHotel(String type, String userId, String id) throws SQLException, ClassNotFoundException {
        return hotelRepo.getFilterHotels(type, userId, id);
    }

    public List<HotelDTO> getFilterHotelAdvance(String userId, String houseType, List<String> amenitiesPicked, int rooms, int numberOfBed, int numberOfBathroom, int min, int max) throws SQLException {
        return hotelRepo.getFilterHotelsAdvance(userId, houseType, amenitiesPicked, rooms, numberOfBed, numberOfBathroom, min, max);
    }

    public List<HotelDTO> getAllHotelDashboard() throws SQLException, ClassNotFoundException {
        List<HotelDTO> listHotelDashboard = hotelRepo.getAllHotelsDashboard();

        return listHotelDashboard;
    }

    public List<HotelDTO> getAllBookmarkedHotel(String userId) throws SQLException {
        return hotelRepo.getAllBookmarkedHotel(userId);

    }
    
    public boolean addReport(String hotelId, String userId, String title, String content) throws SQLException {
        return reportDao.addReport(hotelId, userId, title, content);
    }
    
    public int getUserBookingTimes(String hotelId, String userId) throws SQLException {
        return reportDao.checkNumberBookingTime(hotelId, userId);
    }

    public HotelDTO getBasicHotelInfo(String userId) throws SQLException {
        return hotelRepo.getBasicHotelInfo(userId);
    }

    public String bookingRoom(
            String hotelId,
            String checkin,
            String checkout,
            int adult,
            int child,
            int pet,
            int infant,
            String userId
    ) throws SQLException, ParseException {
        DateRangeService dateRangeService = new DateRangeService();
        List<String> availableRooms = dateRangeService.getFreeRooms(checkin, checkout, hotelId);
        String firstRoomId = availableRooms.get(0);
        BookingDAO bookingDao = new BookingDAO();
        UserDTO user = new UserDTO();
        String id = UUID.randomUUID().toString();
        user.setUser_id(userId);

        BookingDTO bookingDto = new BookingDTO(
                checkin,
                checkout,
                adult, child, pet, infant,
                user, firstRoomId, id
        );
        bookingDao.add(bookingDto);
        
        return id;
    }

    public HotelDTO getByUserId(String userId) throws SQLException, ClassNotFoundException {
        return hotelRepo.getByUserId(userId);

    }
    
    public List<BookingDTO> getAllTodayTypeBooking(String hotelId, String type) throws SQLException {
        switch(type) {
            case "pending": return hotelRepo.getAllTodayPendingBooking(hotelId);
            case "booked": return hotelRepo.getAllTodayBookedBooking(hotelId);
            case "checkout": return hotelRepo.getAllTodayCheckoutBooking(hotelId);
            default: return null;
        }
    }
    
    public void handleBooking(String bookingId, String type) throws SQLException {
        switch(type) {
            case "accept": 
                hotelRepo.acceptBooking(bookingId);  
                return; 
            case "reject": 
                hotelRepo.rejectBooking(bookingId);
                 return; 
            default: return;
        }
    }

    public List<BookingDTO> getAllTypeBooking(String hotelId, String type) throws SQLException {
        switch(type) {
            case "pending": return hotelRepo.getAllPendingBooking(hotelId);
            case "incoming": return hotelRepo.getAllIncomingBooking(hotelId);
            case "booked": return hotelRepo.getAllTodayBookedBooking(hotelId);
            case "checkout": return hotelRepo.getAllCheckoutBooking(hotelId);
            default: return null;
        }
    }
}
