/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 *
 * @author ADMIN
 */
@Path("/hotel")
public class HotelController {
    
    @GET
    public Response get() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println("Get from hotel");
        
        return Response.accepted(gson.toJson("OK")).build();
    }
}
