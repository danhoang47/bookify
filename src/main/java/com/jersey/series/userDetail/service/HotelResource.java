/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jersey.series.userDetail.service;

import com.google.gson.Gson;
import com.service.Interface.HotelInterface;

import dao.AvaiableAmenityDAO;
import dao.HotelAmenityDAO;
import dao.HotelDAO;
import dao.HotelTypeDAO;
import dao.ImageDAO;
import dao.RatingDAO;
import dao.ReviewDAO;
import dao.RoomDAO;
import dao.UserDetailDAO;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.dto.AvaiableAmenity;
import model.dto.Hotel;
import model.dto.HotelAmenityDTO;
import model.dto.HotelType;
import model.dto.ImageDTO;
import model.dto.Rating;
import model.dto.Review;
import model.dto.UserDetail;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import service.UploadImage;
import service.UploadImageMultiple;

/**
 *
 * @author toten
 */
public class HotelResource implements HotelInterface {

    //   ------------------------------------------------------ List all hotels ( View all hotels) -------------------------------------------------------------------------
    @Override
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllHotel() {
        JSONObject obj = new JSONObject();

        HotelDAO hd = new HotelDAO();
        HotelTypeDAO htd = new HotelTypeDAO();

        List<Hotel> listHotel = hd.listAll();
        List<HotelType> listTypes = htd.hotelTypes();

        obj.put("hotels", listHotel);
        obj.put("types", listTypes);

        if (listHotel != null) {
            return Response.ok(new Gson().toJson(obj)).build();
        } else {
            return Response.status(401).entity("No hotel available").build();
        }
    }

//   ------------------------------------------------------ Get one specific hotel ( View one hotel) -------------------------------------------------------------------------
//    @Override
//    @GET
//    @Path("/{hotel_id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response viewSpecificHotel(@PathParam("hotel_id") String hotel_id) {
//
//        JSONObject obj = new JSONObject();
//
////        dao
//        HotelDAO hd = new HotelDAO();
//        ImageDAO img = new ImageDAO();
//        RatingDAO rt = new RatingDAO();
//        ReviewDAO rev = new ReviewDAO();
//        HotelAmenityDAO amd = new HotelAmenityDAO();
//        RoomDAO rd = new RoomDAO();
//        UserDetailDAO udd = new UserDetailDAO();
//
////        data
//        Hotel ht = hd.viewHotel(hotel_id);
//        List<ImageDTO> hotelImage = img.listAll(hotel_id);
//        Rating rating = rt.getRatingPoint(hotel_id);
//        List<Review> listReview = rev.listReview(hotel_id);
//        List<HotelAmenityDTO> listAmenity = amd.listAllAmenity(hotel_id);
//        double averagePrice = rd.averagePrice(hotel_id);
//        UserDetail owner = udd.getOwner(ht.getUserId());
//
//        obj.put("hotel", ht);
//        obj.put("images", hotelImage);
//        obj.put("ratingPoint", rating);
//        obj.put("reviews", listReview);
//        obj.put("amenities", listAmenity);
//        obj.put("averagePrice", averagePrice);
//        obj.put("owner", owner);
//
//        if (ht != null) {
//            return Response.ok(new Gson().toJson(obj)).build();
//        } else {
//            return Response.status(404).entity("Hotel not found").build();
//        }
//
//    }
    @Override
    public Response viewSpecificHotel(String hotel_id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    //   ------------------------------------------------------ Get data like amenities, hotel types,.... for form creating (Sign new hotel form) -------------------------------------------------------------------------
    @Override
    @GET
    @Path("/signhotel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signNewHotelFormData() {
        JSONObject obj = new JSONObject();

        AvaiableAmenityDAO amd = new AvaiableAmenityDAO();
        HotelTypeDAO htd = new HotelTypeDAO();
        HotelAmenityDAO atd = new HotelAmenityDAO();

        List<AvaiableAmenity> listAmenity = amd.listAll();
        List<HotelAmenityDTO> listAmenityType = atd.listAllTypes();
        List<HotelType> listHotelType = htd.hotelTypes();

        System.out.println(listAmenity);
        System.out.println(listHotelType);
        System.out.println(listAmenityType);

        obj.put("amenities", listAmenity);
        obj.put("amenitiesType", listAmenityType);
        obj.put("hotelTypes", listHotelType);

        return Response.ok(new Gson().toJson(obj)).build();
    }

//    ------------------------------------------------------ Sign new hotel ------------------------------------------------------------------------
@POST
    @Path("/signhotel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signNewHotel(
            @FormDataParam("hotelType") String hotelTypeId,
            @FormDataParam("hotelName") String hotelName,
            @FormDataParam("backgroundImage") InputStream fileInputStreamBG,
            @FormDataParam("backgroundImage") FormDataContentDisposition fileFormDataContentDispositionBG,
            @FormDataParam("description") String description,
            @FormDataParam("country") String country,
            @FormDataParam("district") String district,
            @FormDataParam("city") String city,
            @FormDataParam("address") String address,
            @FormDataParam("amenitiesId") List<String> amenitiesId,
            @FormDataParam("amenitiesName") List<String> amenities,
            @FormDataParam("amenitiesTypes") List<String> amenitiyType,
            @FormDataParam("hotelImage") FormDataBodyPart hotelImage,
            @FormDataParam("viewImage") FormDataBodyPart viewImage,
            @FormDataParam("isCamera") boolean isCamera,
            @FormDataParam("isAnimalAccept") boolean isAnimalAccept,
            @FormDataParam("checkin") String checkin,
            @FormDataParam("checkout") String checkout,
            @FormDataParam("closing") String closing,
            @FormDataParam("opening") String opening,
            @FormDataParam("roomPrice") int roomPrice,
            @FormDataParam("maxGuest") int maxGuest,
            @FormDataParam("roomNum") int roomNum,
            @FormDataParam("bathNum") int bathNum,
            @FormDataParam("bedNum") int bedNum,
            @FormDataParam("isbathPrivate") boolean isbathPrivate,
            @FormDataParam("userId") String userId) throws IOException {

        UploadImage uploadhotel = new UploadImage();

        HotelDAO hotelDAO = new HotelDAO();
        ImageDAO imageDAO = new ImageDAO();
        RoomDAO roomDAO = new RoomDAO();
        HotelAmenityDAO amentyDAO = new HotelAmenityDAO();

        List<String> listHotelImagesPath = null;
        List<String> listViewImagePath = null;
        UUID uuid = UUID.randomUUID();
        JSONObject obj = new JSONObject();

//        Upload hotel images
        if (hotelImage != null) {
            
            listHotelImagesPath = uploadhotel.uploadMultipleFile(hotelImage);
        }

        //        Upload view images
        if (viewImage != null) {
            listViewImagePath = uploadhotel.uploadMultipleFile(viewImage);
        }

//        Upload background
        String backgroundImagePath = null;
        if (fileInputStreamBG != null && fileFormDataContentDispositionBG != null) {
            backgroundImagePath = uploadhotel.uploadSingleFile(fileInputStreamBG, fileFormDataContentDispositionBG);
        }

        Hotel hotel = new Hotel(uuid.toString(), userId, hotelTypeId, hotelName, backgroundImagePath, false,
                description, country, district, city, address, isCamera, isAnimalAccept, checkin, checkout, closing, opening);

//        Upload hotel info
        boolean signHotel = hotelDAO.addNewHotel(hotel);
        System.out.println("hotel Sign " + signHotel);
        if (signHotel == false) {
            obj.put("message", "Sign up new hotel failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

//        Upload room info
        boolean signNewRoom = roomDAO.addNewRoom(hotel.getHotelId(), roomPrice, 0, maxGuest, roomNum, bathNum, bedNum, isbathPrivate);
        System.out.println("room sign " + signNewRoom);
        if (signNewRoom == false) {
            obj.put("message", "Sign up new room failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

        System.out.println("listImageHotel: " + listHotelImagesPath);
        System.out.println("listimageview: " + listViewImagePath);
        //        Upload hotel images
        boolean addImageHotel = imageDAO.addImage(uuid.toString(), listHotelImagesPath, 1);
        System.out.println("Image " + addImageHotel);
        if (addImageHotel == false) {
            obj.put("message", "Sign up new image room failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

        //        Upload hotel images
        boolean addImageView = imageDAO.addImage(uuid.toString(), listViewImagePath, 2);
        System.out.println("Image " + addImageView);
        if (addImageView == false) {
            obj.put("message", "Sign up new image view failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

//        Upload hotel amenties
        
    boolean addAmenties = amentyDAO.addHotelAmenitiesAll(amenitiesId, amenities, amenitiyType, hotel.getHotelId());
        if (addAmenties == false) {
            obj.put("message", "Sign up new hotel amenities failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

        obj.put("message", "Sign up new hotel successfully, please wait for permission");
        return Response.ok(new Gson().toJson(obj)).build();

    }
    
//    ----------------------------------------------------------------------------------------------------
    @POST
    @Path("/signhotel/{hotel_id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHotel(
            @PathParam("hotel_id") String hotelId,
            @FormDataParam("hotelType") String hotelTypeId,
            @FormDataParam("hotelName") String hotelName,
            @FormDataParam("backgroundImage") InputStream fileInputStreamBG,
            @FormDataParam("backgroundImage") FormDataContentDisposition fileFormDataContentDispositionBG,
            @FormDataParam("description") String description,
            @FormDataParam("country") String country,
            @FormDataParam("district") String district,
            @FormDataParam("city") String city,
            @FormDataParam("address") String address,
            @FormDataParam("amenities") List<String> amenities,
            @FormDataParam("uploadFile") FormDataBodyPart body,
            @FormDataParam("userId") String userId) throws IOException {

        return Response.ok("Ok").build();
    }

    public static void main(String[] args) {

    }

}
