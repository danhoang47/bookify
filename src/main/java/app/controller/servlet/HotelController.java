/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import app.dto.HotelAmenityDTO;
import app.dto.HotelDTO;
import app.dto.RoomTypeDTO;
import app.services.HotelService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletContext;
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
import org.apache.commons.beanutils.BeanUtils;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


// http://localhost:8080/bookify/images/hotels/uthappizza.png
@Path("/hotel")
public class HotelController {
    private final static HotelService service = new HotelService();
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context ServletContext context;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotel(@QueryParam("id") String hotelId) throws SQLException, ClassNotFoundException {
        
        return Response.ok(gson.toJson(service.get(hotelId))).build();
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
        Type hotelAmenityListType = new TypeToken<List<HotelAmenityDTO>>() {}.getType();
        Type deletedIdListType = new TypeToken<List<String>>() {}.getType();
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
}
