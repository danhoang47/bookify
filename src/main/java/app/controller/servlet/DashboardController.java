package app.controller.servlet;


import app.services.AmenityService;
import app.services.DashboardService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    
    
}
