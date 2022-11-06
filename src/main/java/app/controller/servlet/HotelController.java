/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import app.dto.HotelAmenityDTO;
import app.dto.RoomTypeDTO;
import app.dto.HotelDTO;
import app.services.AmenityService;
import app.services.DateRangeService;
import app.services.HotelService;
import app.services.ImageService;
import app.services.RoomService;
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
    private final static DateRangeService dateRangeService = new DateRangeService();
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context ServletContext context;

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
        service.bookingRoom(
                hotelId, 
                checkin, 
                checkout,
                adult, child, pet, infant, userId
        );

        response.addProperty("status", "ok");

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

    @POST
    @Path("/search/advance")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchAdvanceHotel(@FormDataParam("searchData") String searchData) {

        return Response.ok().build();
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
            @FormDataParam("hotelImage") FormDataBodyPart hotelImage,
            @FormDataParam("viewImage") FormDataBodyPart viewImage,
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
        System.out.println(isCamera);
        System.out.println(isAnimalAccept);
        
        HotelService hotelService = new HotelService();
        ImageService imgService = new ImageService();
        RoomService roomService = new RoomService();
        AmenityService amenityService = new AmenityService();
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
            listHotelImagesPath = UploadImage.uploadMultipleFile(hotelImage, "hotels", realPath);
        }
        boolean addImageHotel = imgService.addImage(hotel.getHotelId(), listHotelImagesPath, 1);
        System.out.println("Image " + addImageHotel);
        if (addImageHotel == false) {
            obj.put("message", "Sign up new image room failed, please try again");
            return Response.ok(new Gson().toJson(obj)).build();
        }

        //        Upload view images
        if (viewImage != null) {
            listViewImagePath = UploadImage.uploadMultipleFile(viewImage, "hotels", realPath);
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

        obj.put("message", "Sign up new hotel successfully, please wait for permission");
        System.out.println("Hello");
        return Response.ok(new Gson().toJson(obj)).build();
    }
    
    @GET
    @Path("today")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTodayBooking(
            @QueryParam("id") String hotelId, 
            @QueryParam("type") String type
    ) throws SQLException {
        return Response.ok(gson.toJson(service.getAllTodayTypeBooking(hotelId, type))).build();
    }
}
