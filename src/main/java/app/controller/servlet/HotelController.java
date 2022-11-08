/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import app.dao.ViewDAO;
import app.dto.HotelAmenityDTO;
import app.dto.RoomTypeDTO;
import app.dto.HotelDTO;
import app.services.AmenityService;
import app.services.DateRangeService;
import app.services.HotelManageService;
import app.services.HotelService;
import app.services.ImageService;
import app.services.ReviewService;
import app.services.RoomService;
import app.services.SearchService;
import app.services.UserService;
import app.utils.UploadImage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;

// http://localhost:8080/bookify/images/hotels/uthappizza.png
@Path("/hotel")
public class HotelController {

    private final static HotelService service = new HotelService();
    private final static UserService userService = new UserService();
    private final static SearchService searchService = new SearchService();
    private final static ReviewService reviewService = new ReviewService();
    private final static HotelManageService manageService = new HotelManageService();
    private final static DateRangeService dateRangeService = new DateRangeService();
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    ServletContext context;

    @POST
    @Path("/booking/{hotelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookingRoom(
            @PathParam("hotelId") String hotelId,
            @QueryParam("checkin") String checkin,
            @QueryParam("checkout") String checkout,
            @QueryParam("adult") int adult,
            @QueryParam("child") int child,
            @QueryParam("pet") int pet,
            @QueryParam("infant") int infant,
            @QueryParam("userId") String userId
    ) throws SQLException, ParseException {
        JsonObject response = new JsonObject();

        String bookingId = service.bookingRoom(
                hotelId,
                checkin,
                checkout,
                adult, child, pet, infant, userId
        );

        response.addProperty("status", "ok");
        response.addProperty("bookingId", bookingId);

        return Response.ok(gson.toJson(response)).build();
    }

    @GET
    @Path("/bookmark/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookmarkedHotel(@PathParam("userId") String userId) throws SQLException {
        System.out.println("getBookmarkedHotel " + userId);
        if (userId == null) {
            return Response.noContent().build();
        } else {
            return Response.ok(gson.toJson(service.getAllBookmarkedHotel(userId))).build();
        }
    }
    
//    ----------------------- ACCEPT/REJECT BOOKING FROM USER
    @PUT
    @Path("/booking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleBooking(@QueryParam("id") String bookingId, @QueryParam("action") String action) throws SQLException {
        JsonObject response = new JsonObject();
        service.handleBooking(bookingId, action);
        response.addProperty("status", "ok");
        return Response.ok(gson.toJson(response)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotel(@QueryParam("id") String hotelId, @QueryParam("userid") String userid) throws SQLException, ClassNotFoundException {

        String newUserId = userid == null ? "" : userid;
        HotelDTO hotel = service.get(hotelId, newUserId);
        if (hotel != null) {
            return Response.ok(gson.toJson(hotel)).build();
        } else {
            JsonObject response = new JsonObject();
            response.addProperty("error", "not found");
            return Response.ok(response).build();
        }
    }

    @GET
    @Path("/owner/{userId}")
    public Response getHotelByUserId(@PathParam("userId") String userId) throws SQLException, ClassNotFoundException {
        if (userId == "null") {
            return Response.accepted().build();
        }
        HotelDTO hotel = service.getByUserId(userId);
        if (hotel != null) {
            return Response.ok(gson.toJson(hotel)).build();
        } else {
            JsonObject response = new JsonObject();
            response.addProperty("error", "not found");
            return Response.ok(response).build();
        }
    }

    @GET
    @Path("/manage/gethotel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotel(@QueryParam("userid") String userid) throws SQLException, ClassNotFoundException {

        String newUserId = userid == null ? "" : userid;
        return Response.ok(gson.toJson(service.getBasicHotelInfo(newUserId))).build();
    }

    @PUT
    @Path("/update/{hotelId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateHotel(
            @PathParam("hotelId") String hotelId,
            @FormDataParam("amenities") String amenities,
            @FormDataParam("updatedBasicHotelInfor") String basicHotelInfor,
            @FormDataParam("extraInforModified") String extraInfor,
            @FormDataParam("roomInfor") String roomInfor,
            @FormDataParam("deletedImages") String deletedImagesString,
            @FormDataParam("backgroundImage") String backgroundImage,
            @FormDataParam("backgroundImage") InputStream fileInputStreamBG,
            @FormDataParam("backgroundImage") FormDataContentDisposition fileFormDataContentDispositionBG,
            @FormDataParam("updatedViewImages") FormDataBodyPart viewImages,
            @FormDataParam("updatedRoomImages") FormDataBodyPart roomImages
    ) throws IllegalAccessException, InvocationTargetException, IOException, SQLException {
        Type hotelAmenityListType = new TypeToken<List<HotelAmenityDTO>>() {
        }.getType();
        Type deletedIdListType = new TypeToken<List<String>>() {
        }.getType();
        List<HotelAmenityDTO> amenityList = gson.fromJson(amenities, hotelAmenityListType);
        List<String> deletedImageIdList = gson.fromJson(deletedImagesString, deletedIdListType);
        HotelDTO hotel = gson.fromJson(basicHotelInfor, HotelDTO.class);
        HotelDTO extraInforDto = gson.fromJson(extraInfor, HotelDTO.class);
        RoomTypeDTO roomTypeDto = gson.fromJson(roomInfor, RoomTypeDTO.class);

        hotel.setHotelId(hotelId);
        hotel.setBackgroundImg(backgroundImage);

        service.update(
                hotel, extraInforDto, amenityList,
                roomTypeDto, fileInputStreamBG,
                fileFormDataContentDispositionBG,
                viewImages, roomImages, deletedImageIdList,
                context.getRealPath("")
        );

        return Response.noContent().build();
    }

    @GET
    @Path("/search/advance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAdvanceHotel(@QueryParam("place") String place, @QueryParam("check-in") String checkin, @QueryParam("check-out") String checkout, 
            @QueryParam("guest") int guest) throws SQLException, ParseException, ClassNotFoundException {
        String placeNew = (place==null || place.length()==0) ? "" : place;
        String checkinNew = (checkin==null || checkin.length()==0) ? "" : checkin;
        String checkoutNew = (checkout==null || checkout.length()==0) ? "" : checkout;
        
        return Response.ok(gson.toJson(searchService.getHotelsAdvanceSearch(place, checkin, checkout, guest))).build();
    }

    @POST
    @Path("/report")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReport(@FormDataParam("hotelid") String hotelid, @FormDataParam("userid") String userid, @FormDataParam("title") String title, @FormDataParam("content") String content) throws SQLException {
        JSONObject obj = new JSONObject();

        boolean check = service.addReport(hotelid, userid, title, content);

        if (check == true) {
            obj.put("success", "Report allow");
        } else if (check == false) {
            obj.put("error", "Report failed");
        }

        return Response.ok(gson.toJson(obj)).build();
    }
    
    @GET
    @Path("/report")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUser(@QueryParam("hotelid") String hotelid, @QueryParam("userid") String userid) throws SQLException {
        JSONObject obj = new JSONObject();
        int getTimeBooking = service.getUserBookingTimes(hotelid, userid);

        if (getTimeBooking > 0) {

            obj.put("success", "Report successfully");

        } else {
            obj.put("require", "Login require");
        }

        return Response.ok(gson.toJson(obj)).build();
    }
    
    @POST
    @Path("/review")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReview(
            @FormDataParam("hotelid") String hotelid, 
            @FormDataParam("userid") String userid, 
            @FormDataParam("content") String content, 
            @FormDataParam("communication_point") int communicationPoint,
            @FormDataParam("accuracy_point") int accuracyPoint,
            @FormDataParam("location_point") int locationPoint,
            @FormDataParam("value_point") int valuePoint
    ) throws SQLException {
        JSONObject obj = new JSONObject();

        boolean check = reviewService.addNewReview(hotelid, userid, content, communicationPoint, accuracyPoint, locationPoint, valuePoint);

        if (check == true) {
            obj.put("success", "Review allow");
        } else if (check == false) {
            obj.put("error", "Review failed");
        }

        return Response.ok(gson.toJson(obj)).build();
    }
    
    @GET
    @Path("/review")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkUserReview(@QueryParam("hotelid") String hotelid, @QueryParam("userid") String userid) throws SQLException {
        JSONObject obj = new JSONObject();
        int getTimeBooking = service.getUserBookingTimes(hotelid, userid);

        if (getTimeBooking > 0) {

            obj.put("success", "Review successfully");

        } else {
            obj.put("require", "Login require");
        }

        return Response.ok(gson.toJson(obj)).build();
    }

    

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotelAll(@QueryParam("userid") String userId) throws SQLException, ClassNotFoundException {
//        JSONObject obj = new JSONObject();
        System.out.println(userId);
        return Response.ok(gson.toJson(service.getAllHotelBasicInfo(userId))).build();
    }

    @GET
    @Path("/all/dashboard")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotelAll() throws SQLException, ClassNotFoundException {
//        JSONObject obj = new JSONObject();

        return Response.ok(gson.toJson(service.getAllHotelDashboard())).build();
    }

    @GET
    @Path("/manage/income")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHotelBookingData(@QueryParam("hotelid") String hotelId, @QueryParam("month") int month) throws SQLException, ClassNotFoundException {
//        JSONObject obj = new JSONObject();

        return Response.ok(gson.toJson(manageService.listHotelBookingData(hotelId, month))).build();
    }

    @GET
    @Path("/manage/views")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHotelBookingDataAll(@QueryParam("hotelid") String hotelId, @QueryParam("month") int month) throws SQLException, ClassNotFoundException {
//        JSONObject obj = new JSONObject();

        return Response.ok(gson.toJson(manageService.listHotelBookingDataAll(hotelId, month))).build();
    }

    @GET
    @Path("/manage/rating")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHotelRating(@QueryParam("hotelid") String hotelId) throws SQLException, ClassNotFoundException {
//        JSONObject obj = new JSONObject();

        return Response.ok(gson.toJson(manageService.listHotelRatingData(hotelId))).build();
    }

    @GET
    @Path("/manage/rating/point")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listHotelRating(@QueryParam("hotelid") String hotelId, @QueryParam("point") int point) throws SQLException, ClassNotFoundException {
//        JSONObject obj = new JSONObject();

        return Response.ok(gson.toJson(manageService.listHotelRatingWithPoint(hotelId, point))).build();
    }

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilter(@QueryParam("type") String type, @QueryParam("id") String id, @QueryParam("userid") String userId) throws SQLException, ClassNotFoundException {
//        JSONObject obj = new JSONObject();
        System.out.println("userid: " + userId);
        String newUserId = userId == null ? "" : userId;
        return Response.ok(gson.toJson(service.getFilterHotel(type, newUserId, id))).build();
    }

    @GET
    @Path("/checkrange")
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkDateRange(
            @QueryParam("checkin") String checkin,
            @QueryParam("checkout") String checkout,
            @QueryParam("hotelId") String hotelId
    ) throws SQLException, ClassNotFoundException, ParseException {
        JSONObject obj = new JSONObject();
        boolean check = dateRangeService.checkDateRange(checkin, checkout, hotelId);
        List<String> listFreeRooms = dateRangeService.getFreeRooms(checkin, checkout, hotelId);
        obj.put("check", check);
        obj.put("listFreeRooms", listFreeRooms);
        return Response.ok(gson.toJson(obj)).build();
    }

    @POST
    @Path("/filteradvanced")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFilterAdvanced(
            @QueryParam("userid") String userId,
            @FormDataParam("houseType") String houseType,
            @FormDataParam("amenitiesPicked") List<String> amenitiesPicked,
            @FormDataParam("rooms") int rooms,
            @FormDataParam("numberOfBed") int numberOfBed,
            @FormDataParam("numberOfBathroom") int numberOfBathroom,
            @FormDataParam("min") int min,
            @FormDataParam("max") int max
    ) throws SQLException, ClassNotFoundException {

        List<String> newAmenity = Arrays.asList(amenitiesPicked.get(0).trim().split(","));
        String newUserId = userId == null ? "" : userId;
        List<HotelDTO> listHotel = service.getFilterHotelAdvance(newUserId, houseType, newAmenity, rooms, numberOfBed, numberOfBathroom, min, max);

        return Response.ok(gson.toJson(listHotel)).build();
    }

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
            @FormDataParam("hotelImage") List<FormDataBodyPart> hotelImage,
            @FormDataParam("viewImage") List<FormDataBodyPart> viewImage,
            @FormDataParam("isCamera") boolean isCamera,
            @FormDataParam("isAnimalAccept") boolean isAnimalAccept,
            @FormDataParam("checkin") String checkin,
            @FormDataParam("checkout") String checkout,
            @FormDataParam("closing") String closing,
            @FormDataParam("opening") String opening,
            @FormDataParam("roomPrice") int roomPrice,
            @FormDataParam("maxGuest") int numberOfGuest,
            @FormDataParam("roomNum") int roomNum,
            @FormDataParam("bathNum") int bathNum,
            @FormDataParam("bedNum") int bedNum,
            @FormDataParam("bedroomNum") int bedroomNum,
            @FormDataParam("isbathPrivate") boolean isbathPrivate,
            @FormDataParam("userId") String userId) throws IOException {

        UploadImage uploadhotel = new UploadImage();

        System.out.println(isCamera);
        System.out.println(isAnimalAccept);

        HotelService hotelService = new HotelService();
        ImageService imgService = new ImageService();
        RoomService roomService = new RoomService();
        AmenityService amenityService = new AmenityService();
        ViewDAO view = new ViewDAO();
//
        List<String> listHotelImagesPath = null;
        List<String> listViewImagePath = null;
        UUID uuid = UUID.randomUUID();
        String realPath = context.getRealPath("");
        JSONObject obj = new JSONObject();

////        Upload background
        String backgroundImagePath = null;
        if (fileInputStreamBG != null && fileFormDataContentDispositionBG != null) {
            backgroundImagePath = UploadImage.uploadSingleFile(
                    fileInputStreamBG,
                    fileFormDataContentDispositionBG,
                    "hotels",
                    realPath
            );
        }

        System.out.println(listHotelImagesPath);
        HotelDTO hotel = new HotelDTO(uuid.toString(), userId, hotelTypeId, hotelName, backgroundImagePath, false,
                description, country, district, city, address, isCamera, isAnimalAccept, checkin, checkout, closing, opening);

//        Upload hotel info
        boolean signHotel = hotelService.addNewHotel(hotel);
        System.out.println("hotel Sign " + signHotel);
        if (signHotel == false) {
            obj.put("message", "Sign up new hotel failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

//        Upload room info
        boolean signNewRoom = roomService.addNewRoom(hotel.getHotelId(), roomNum, roomPrice, "Normal bedroom", bedNum, "Normal bathroom", bathNum, isbathPrivate,
                "Guest ID", numberOfGuest, bedroomNum);

        System.out.println("room sign " + signNewRoom);
        if (signNewRoom == false) {
            obj.put("message", "Sign up new room failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

        //        Upload hotel images
        if (hotelImage != null) {
            listHotelImagesPath = UploadImage.uploadMultipleFile2(hotelImage, "hotels", realPath);
        }
        boolean addImageHotel = imgService.addImage(hotel.getHotelId(), listHotelImagesPath, 1);
        System.out.println("Image " + addImageHotel);
        if (addImageHotel == false) {
            obj.put("message", "Sign up new image room failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

        //        Upload view images
        if (viewImage != null) {
            listViewImagePath = UploadImage.uploadMultipleFile2(viewImage, "hotels", realPath);
        }
        boolean addImageView = imgService.addImage(hotel.getHotelId(), listViewImagePath, 0);
        System.out.println("Image " + addImageView);
        if (addImageView == false) {
            obj.put("message", "Sign up new image view failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

//        Upload hotel amenties
        boolean addAmenties = amenityService.addHotelAmenitiesAll(amenitiesId, amenities, amenitiyType, hotel.getHotelId());
        if (addAmenties == false) {
            obj.put("message", "Sign up new hotel amenities failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

//        Update userRole
        boolean updateUser = userService.makeHosting(userId);
        if (updateUser == false) {
            obj.put("message", "Update user failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }
        boolean addNewView = view.addNewView(hotel.getHotelId());
        if (addNewView == false) {
            obj.put("message", "Update view failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

        obj.put("message", "Sign up new hotel successfully, please wait for permission");
        System.out.println("Hello");
        return Response.ok(new Gson().toJson(obj)).build();
    }
    
    @GET
    @Path("/today")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodayBooking(
            @QueryParam("id") String hotelId, 
            @QueryParam("type") String type
    ) throws SQLException {
        return Response.ok(gson.toJson(service.getAllTodayTypeBooking(hotelId, type))).build();
    }
    
    @GET
    @Path("/allbooking")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBooking(
            @QueryParam("id") String hotelId, 
            @QueryParam("type") String type
    ) throws SQLException {
        return Response.ok(gson.toJson(service.getAllTypeBooking(hotelId, type))).build();
    }
}
