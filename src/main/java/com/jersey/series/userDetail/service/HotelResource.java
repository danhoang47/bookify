/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jersey.series.userDetail.service;

import com.google.gson.Gson;
import com.service.Interface.HotelInterface;
import dao.AmenityDAO;
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
import model.dto.Amenity;
import model.dto.Hotel;
import model.dto.HotelType;
import model.dto.Image;
import model.dto.Rating;
import model.dto.Review;
import model.dto.UserDetail;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import service.uploadImage.UploadImage;

/**
 *
 * @author toten
 */
@Path("/hotel")
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
    @Override
    @GET
    @Path("/{hotel_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response viewSpecificHotel(@PathParam("hotel_id") String hotel_id) {

        JSONObject obj = new JSONObject();

//        dao
        HotelDAO hd = new HotelDAO();
        ImageDAO img = new ImageDAO();
        RatingDAO rt = new RatingDAO();
        ReviewDAO rev = new ReviewDAO();
        AmenityDAO amd = new AmenityDAO();
        RoomDAO rd = new RoomDAO();
        UserDetailDAO udd = new UserDetailDAO();

//        data
        Hotel ht = hd.viewHotel(hotel_id);
        List<Image> hotelImage = img.listAll(hotel_id);
        Rating rating = rt.getRatingPoint(hotel_id);
        List<Review> listReview = rev.listReview(hotel_id);
        List<Amenity> listAmenity = amd.hotelAmenities(hotel_id);
        double averagePrice = rd.averagePrice(hotel_id);
        UserDetail owner = udd.getOwner(ht.getUserId());

        obj.put("hotel", ht);
        obj.put("images", hotelImage);
        obj.put("ratingPoint", rating);
        obj.put("reviews", listReview);
        obj.put("amenities", listAmenity);
        obj.put("averagePrice", averagePrice);
        obj.put("owner", owner);

        if (ht != null) {
            return Response.ok(new Gson().toJson(obj)).build();
        } else {
            return Response.status(404).entity("Hotel not found").build();
        }

    }

    //   ------------------------------------------------------ Get data like amenities, hotel types,.... for form creating (Sign new hotel form) -------------------------------------------------------------------------
    @Override
    @GET
    @Path("/signhotel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response signNewHotelFormData() {
        JSONObject obj = new JSONObject();

        AmenityDAO amd = new AmenityDAO();
        HotelTypeDAO htd = new HotelTypeDAO();

        List<Amenity> listAmenity = amd.listAllAmenties();
        List<HotelType> listHotelType = htd.hotelTypes();

        obj.put("amenities", listAmenity);
        obj.put("types", listHotelType);

        return Response.ok(new Gson().toJson(obj)).build();
    }

//    ------------------------------------------------------ Sign new hotel ------------------------------------------------------------------------
    @POST
    @Path("/signhotel")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signNewHotel(@FormDataParam("hotelType") String hotelTypeId,
            @FormDataParam("hotelName") String hotelName,
            @FormDataParam("backgroundImage") InputStream fileInputStreamBG,
            @FormDataParam("backgroundImage") FormDataContentDisposition fileFormDataContentDispositionBG,
            @FormDataParam("description") String description,
            @FormDataParam("country") String country,
            @FormDataParam("district") String district,
            @FormDataParam("city") String city,
            @FormDataParam("streetName") String streetName,
            @FormDataParam("streetNumber") String streetNumber,
            @FormDataParam("amenities") List<String> amenities,
            @FormDataParam("uploadFile") FormDataBodyPart body,
            @FormDataParam("userId") String userId) throws IOException {


        UploadImage uploadhotel = new UploadImage();
        HotelDAO hotelDAO = new HotelDAO();
        ImageDAO imageDAO = new ImageDAO();
        AmenityDAO amentyDAO = new AmenityDAO();

        List<String> listHotelImagesPath = null;
        UUID uuid = UUID.randomUUID();
        JSONObject obj = new JSONObject();

//        Upload hotel images
        if (body != null) {
            listHotelImagesPath = uploadhotel.uploadMultipleFile(body);
        }

//        Upload background
        String backgroundImagePath = null;
        if (fileInputStreamBG != null && fileFormDataContentDispositionBG != null) {
            backgroundImagePath = uploadhotel.uploadSingleFile(fileInputStreamBG, fileFormDataContentDispositionBG);
        } 

        Hotel hotel = new Hotel(uuid.toString(), userId, hotelTypeId, hotelName, null, backgroundImagePath, false, description, country, district, city, streetName, streetNumber);

        
        
//        Upload hotel info
        boolean signHotel = hotelDAO.addNewHotel(hotel);
        System.out.println("herer " + signHotel);
        
        //        Upload hotel images
        boolean addImage = imageDAO.addImage(uuid.toString(), listHotelImagesPath);
        System.out.println("Image " + addImage);

//        Upload hotel amenties
        boolean addAmenties = amentyDAO.addHotelAmenties(amenities, uuid.toString());

        if (signHotel == true && addImage == true && addAmenties == true) {
            obj.put("message", "Sign up new hotel successfully, please wait for permission");
            return Response.ok(new Gson().toJson(obj)).build();
        } else {
            obj.put("message", "Sign up new hotel failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }
    }
 
    
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
            @FormDataParam("streetName") String streetName,
            @FormDataParam("streetNumber") String streetNumber,
            @FormDataParam("amenities") List<String> amenities,
            @FormDataParam("uploadFile") FormDataBodyPart body,
            @FormDataParam("userId") String userId) throws IOException {


        UploadImage uploadhotel = new UploadImage();
        HotelDAO hotelDAO = new HotelDAO();
        ImageDAO imageDAO = new ImageDAO();
        AmenityDAO amentyDAO = new AmenityDAO();

        List<String> listHotelImagesPath = null;
        UUID uuid = UUID.randomUUID();
        JSONObject obj = new JSONObject();

//        Upload hotel images
        if (body != null) {
            listHotelImagesPath = uploadhotel.uploadMultipleFile(body);
        }

//        Upload background
        String backgroundImagePath = null;
        if (fileInputStreamBG != null && fileFormDataContentDispositionBG != null) {
            backgroundImagePath = uploadhotel.uploadSingleFile(fileInputStreamBG, fileFormDataContentDispositionBG);
        } 

        Hotel hotel = new Hotel(uuid.toString(), userId, hotelTypeId, hotelName, null, backgroundImagePath, false, description, country, district, city, streetName, streetNumber);

        
        
//        Upload hotel info
        boolean signHotel = hotelDAO.addNewHotel(hotel);
        System.out.println("herer " + signHotel);
        
        //        Upload hotel images
        boolean addImage = imageDAO.addImage(uuid.toString(), listHotelImagesPath);
        System.out.println("Image " + addImage);

//        Upload hotel amenties
        boolean addAmenties = amentyDAO.addHotelAmenties(amenities, uuid.toString());

        if (signHotel == true && addImage == true && addAmenties == true) {
            obj.put("message", "Sign up new hotel successfully, please wait for permission");
            return Response.ok(new Gson().toJson(obj)).build();
        } else {
            obj.put("message", "Sign up new hotel failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }
    }

    public static void main(String[] args) {

    }

}
