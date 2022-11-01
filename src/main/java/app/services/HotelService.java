/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.AmenityDAO;
import app.dao.HotelDAO;
import app.dto.AmenityDTO;
import app.dto.HotelAmenityDTO;
import app.dto.HotelDTO;
import app.dto.RoomTypeDTO;
import app.repository.HotelRepository;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import app.utils.UploadImage;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author ADMIN
 */
public class HotelService {
    final private HotelRepository hotelRepo;

    public HotelService() {
        hotelRepo = new HotelRepository();
    }
    
    public HotelDTO get(String hotelId) throws SQLException, ClassNotFoundException {
        return hotelRepo.get(hotelId);
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
        }
        List<String> viewImageList = UploadImage.uploadMultipleFile(viewImages, typeUpload, realPath);
        for (String src : viewImageList) {
            System.out.println(src);
        }
        List<String> roomImageList = UploadImage.uploadMultipleFile(roomImages, typeUpload, realPath);
        
        hotelRepo.update(hotel, amenities, roomTypeDto, viewImageList, roomImageList, deletedImageIdList);
    }

    public boolean addNewHotel(HotelDTO hotel) {
        return hotelRepo.addNewHotel(hotel);
    }
    
    public List<HotelDTO> getAllHotelBasicInfo() throws SQLException, ClassNotFoundException {
        return hotelRepo.getAllHotel();
    }

    public List<HotelDTO> getFilterHotel(String type, String id) throws SQLException, ClassNotFoundException {
        return hotelRepo.getFilterHotels(type, id);
    }

    public List<HotelDTO> getFilterHotelAdvance(String houseType, List<String> amenitiesPicked, int rooms, int numberOfBed, int numberOfBathroom, int min, int max) throws SQLException {
        return hotelRepo.getFilterHotelsAdvance(houseType, amenitiesPicked, rooms, numberOfBed, numberOfBathroom, min, max);
    }
}
