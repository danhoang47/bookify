/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import app.dto.HotelDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author ADMIN
 */
@Path("/hotel")
public class HotelController {
    
    @POST
    public Response get(@FormDataParam("amenityId") String amenityId) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object response = gson.fromJson(amenityId, Object.class);
        System.out.println(response == null);
        
        return Response.accepted(gson.toJson("OK")).build();
    }
}
