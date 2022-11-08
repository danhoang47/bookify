/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.HotelDAO;
import app.dao.ImageDAO;
import app.dto.HotelDTO;
import app.dto.ImageDTO;
import app.repository.DateRangeRepo;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author toten
 */
public class SearchService {

    private DateRangeRepo dateRangeRepo;
    private BookingService bookingService;
    private HotelDAO hotelDAO;
    private ImageDAO imageDao;

    public SearchService() {
        dateRangeRepo = new DateRangeRepo();
        bookingService = new BookingService();
        hotelDAO = new HotelDAO();
        imageDao = new ImageDAO();
    }

    public  List<String> getHotelByCondition(String place, String checkin, String checkout, int guest) throws SQLException, ParseException {
        if (place == null) {
            place = "";
        }
        if (checkin == null) {
            checkin = "";
        }
        if (checkout == null) {
            checkout = "";
        }

        List<String> hotelId = new ArrayList<>();
        HashSet<String> setHotel = null;
        if ((place.length() == 0 || place == null) && (checkin == null || checkin.length() == 0) && (checkout == null || checkout.length() == 0) && guest == 0) {
            hotelId = bookingService.getHotelByCondition(place, guest);
        }

        if ((checkin == null || checkin.length() == 0) && (checkout == null || checkout.length() == 0)) {
            hotelId = bookingService.getHotelByCondition(place, guest);
            return hotelId;
        } else {
            hotelId = bookingService.getHotelByCondition(place, guest);
            setHotel = dateRangeRepo.getFreeRoomsAllWithHotel(checkin, checkout, hotelId);
            List<String> resultArr = new ArrayList<>();
            resultArr.addAll(setHotel);
            return resultArr;
        }

    }

    public List<String> getHotelByRoomId(List<String> roomId) throws SQLException {
        HashSet<String> listhotels = new HashSet<>();
        for (int i = 0; i < roomId.size(); i++) {
            if(hotelDAO.getHotelByRoomId(roomId.get(i))==null) {
                continue;
            }
            listhotels.add(hotelDAO.getHotelByRoomId(roomId.get(i)).getHotelId() );
        }
        List<String> hotelsRes = new ArrayList<>();
        hotelsRes.addAll(listhotels);
        return hotelsRes;
    }
    
    public List<HotelDTO> getHotelsAdvanceSearch(String place, String checkin, String checkout, int guest) throws SQLException, ParseException, ClassNotFoundException {
        List<String> listHotelId = new SearchService().getHotelByCondition(place, checkin, checkout, guest);
        List<String> listHotels = new SearchService().getHotelByRoomId(listHotelId);
        List<HotelDTO> listHotelArray = new ArrayList<>();
        for(int i=0; i<listHotels.size(); i++) {
            HotelDTO htd = hotelDAO.getVerifiedHotel(listHotels.get(i));
            if(htd.isIsVerified()==true) {
                listHotelArray.add(hotelDAO.getVerifiedHotel(listHotels.get(i)));
            }
            
        }
        for(int i=0; i<listHotelArray.size(); i++) {
            listHotelArray.get(i).setImages(imageDao.get(listHotels.get(i)));
        }
        return listHotelArray;
        
    }
    
   

    public static void main(String[] args) throws SQLException, ParseException, ClassNotFoundException {
        SearchService service = new SearchService();

        List<HotelDTO> listht = service.getHotelsAdvanceSearch("Thành phố Đà Nẵng", "2022-11-07", "2022-11-13", 4);

        System.out.println(listht);
    }
}
