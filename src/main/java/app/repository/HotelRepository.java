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

    public void add() {

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
}
