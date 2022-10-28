package app.controller.servlet;


import app.services.AmenityService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/amenity")
public class AmenityController {

    private final static AmenityService service = new AmenityService();
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDefaultAmenities() throws SQLException {

        return Response.ok(gson.toJson(service.getDefaultAmenities())).build();
    }
    
    @POST
    @Path("/test")
    public Response test() {
        return Response.accepted("Hello").build();
    }

    @GET
    @Path("/type")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDefaultAmenityTypes() throws SQLException {

        return Response.ok(gson.toJson(service.getDefaultAmenityTypes())).build();
    }
}
