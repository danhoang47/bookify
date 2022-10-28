/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import app.services.HotelService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author ADMIN
 */
@Path("/hotel")
public class HotelController {
    
    @GET
    @Path("/{hotelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getHotel(@PathParam("hotelId") String hotelId) throws SQLException, ClassNotFoundException {
        HotelService service = new HotelService();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        
        return Response.ok(gson.toJson(service.get(hotelId))).build();
    }
    
    @POST
    @Path("/{hotelId}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response updateHotel(
        @PathParam("hotelId") String hotelId,
        @FormDataParam("viewImages") FormDataBodyPart  viewImagesBodyPart,
        @FormDataParam("backgroundImage") FormDataContentDisposition  backgroundImageBodyPart,
        @FormDataParam("roomImages") FormDataBodyPart  roomImagesBodyPart
    ) {
        System.out.println("updateHotel called");    
        for (BodyPart part : viewImagesBodyPart.getParent().getBodyParts()) {
            ContentDisposition meta = part.getContentDisposition();
            System.out.println(meta.getFileName());
        }
        
        return Response.ok(new Gson().toJson("hello")).build();
    }
}
