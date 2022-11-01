/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.repository;

import app.dto.ImageDTO;
import app.dto.HotelDTO;
import app.dto.HotelAmenityDTO;
import app.dao.HotelDAO;
import app.dao.ImageDAO;
import app.dao.HotelAmenityDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HotelRepository {
    private HotelDAO hotelDao;
    private ImageDAO imageDao;
    private HotelAmenityDAO hotelAmenityDao;

    
    public HotelRepository() {
        hotelDao = new HotelDAO();
        imageDao = new ImageDAO();
        hotelAmenityDao = new HotelAmenityDAO();
    }

    public boolean addNewHotel(HotelDTO hotel) {
        return hotelDao.addNewHotel(hotel);
    }

    public HotelDTO get(String id) throws SQLException, ClassNotFoundException {
       
        HotelDTO hotelDto = hotelDao.get(id);
        List<ImageDTO> imageDtos = imageDao.get(hotelDto.getHotelId());
        List<HotelAmenityDTO> hotelAmenityDtos = hotelAmenityDao.get(hotelDto.getHotelId());

        hotelDto.setImages(imageDtos);
        System.out.println(imageDtos.size());
        hotelDto.setHotelAmenities(hotelAmenityDtos);

        return hotelDto;
    }

    public void update() {

    }
    
    public List<HotelDTO> getAllHotel() throws SQLException, ClassNotFoundException {
        List<HotelDTO> listHotelBasic = hotelDao.getAllHotelBasicInfo();
        for(int i =0; i<listHotelBasic.size(); i++) {
            List<ImageDTO> listImage = imageDao.getRandomImage(listHotelBasic.get(i).getHotelId());
            listHotelBasic.get(i).setImages(listImage);
        }
        return listHotelBasic;
    }
    
    public List<HotelDTO> getFilterHotels(String type, String id) throws SQLException, ClassNotFoundException {
        List<HotelDTO> listHotelFilter = hotelDao.getFilterHotel(type, id);
        for(int i =0; i<listHotelFilter.size(); i++) {
            List<ImageDTO> listImage = imageDao.getRandomImage(listHotelFilter.get(i).getHotelId());
            listHotelFilter.get(i).setImages(listImage);
        }
        return listHotelFilter;
    }

    public List<HotelDTO> getFilterHotelsAdvance(String houseType, List<String> amenitiesPicked, int rooms, int numberOfBed, int numberOfBathroom, int min, int max) throws SQLException {
        List<HotelDTO> listHotel = hotelDao.getFilterAdvancedHotel(houseType, amenitiesPicked, rooms, numberOfBed, numberOfBathroom, min, max);
        for(int i =0; i<listHotel.size(); i++) {
            List<ImageDTO> listImage = imageDao.getRandomImage(listHotel.get(i).getHotelId());
            listHotel.get(i).setImages(listImage);
        }
        return listHotel;
    }
    
}
