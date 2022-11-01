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
import app.dao.RoomTypeDAO;
import app.dto.RoomTypeDTO;
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

    public HotelRepository() {
        hotelDao = new HotelDAO();
        imageDao = new ImageDAO();
        hotelAmenityDao = new HotelAmenityDAO();
        roomTypeDao = new RoomTypeDAO();
    }

    public void add() {

    }

    public HotelDTO get(String id) throws SQLException, ClassNotFoundException {
        HotelDTO hotelDto = hotelDao.get(id);
        List<ImageDTO> imageDtos = imageDao.get(hotelDto.getHotelId());
        List<HotelAmenityDTO> hotelAmenityDtos = hotelAmenityDao.get(hotelDto.getHotelId());
        RoomTypeDTO roomType = roomTypeDao.get(hotelDto.getHotelId());

        hotelDto.setImages(imageDtos);
        hotelDto.setHotelAmenities(hotelAmenityDtos);
        hotelDto.setRoomType(roomType);

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
            if ( dto.getAmenityId() == null && !dto.getId().contains("new")) {
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
}
