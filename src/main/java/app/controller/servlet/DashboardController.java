package app.controller.servlet;


import app.services.AmenityService;
import app.services.DashboardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/dashboard")
public class DashboardController {

    private final static DashboardService service = new DashboardService();
    private final static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllData(@QueryParam("month") int month) throws SQLException {

        return Response.ok(gson.toJson(service.getDashboardData(month))).build();
    }
    
    @GET
    @Path("/exchange")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExchangeData() throws SQLException {

        return Response.ok(gson.toJson(service.getExchangeData())).build();
    }
    
    @PUT
    @Path("/verified/{hotelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response verifiedHotel(@PathParam("hotelId") String hotelId) throws SQLException {
        service.verifiedHotel(hotelId);
        JsonObject response = new JsonObject();
        response.addProperty("success", "ok");
        return Response.ok(gson.toJson(response)).build();
    }
    
    @PUT
    @Path("/disabled/{hotelId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response disabledHotel(@PathParam("hotelId") String hotelId) throws SQLException {
        service.disabledHotel(hotelId);
        JsonObject response = new JsonObject();
        response.addProperty("success", "ok");
        return Response.ok(gson.toJson(response)).build();
    }
}
