/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import app.dao.BookmarkDAO;
import app.dao.UserDAO;
import app.dto.UserDTO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dao.UserDetailDAO;
import app.services.UserService;
import com.google.gson.GsonBuilder;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.dto.UserDetail;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import secure.JWTconvert;
import service.UploadImage;

/**
 *
 * @author toten
 */
@Path("/user")
public class UserController {
    private UserService service = new UserService();

    @POST
    @Path("/login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormDataParam("username") String username, @FormDataParam("password") String password) {

        UserDAO dao = new UserDAO();
        JWTconvert userJwt = new JWTconvert();
        JSONObject obj = new JSONObject();

//         Check if username is true or not
        Boolean checkUsername = dao.getUsername(username);

        if (checkUsername) {
            UserDTO userLogin = dao.login(username, password);

            if (userLogin != null) {
                String jwtCode = userJwt.encodeToJWT(userLogin.getUser_id(), userLogin.getRole());
                obj.put("user", userLogin);
                obj.put("token", jwtCode);

                return Response.ok(new Gson().toJson(obj)).build();
            } else {
                obj.put("error", "Wrong password");
                return Response.status(401).entity(new Gson().toJson(obj)).build();
            }

        } else {
            obj.put("error", "Wrong username or username does not exist");
            return Response.status(401).entity(new Gson().toJson(obj)).build();
        }

    }

    @POST
    @Path("/verifyjwt")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataByJWT(@FormDataParam("jwt") String jwtString) {
        Gson gson = new Gson();

        String jwtToken = gson.fromJson(jwtString, String.class).toString();

        UserDAO dao = new UserDAO();
//        UserDetail user = new UserDetail(username, password);
        JWTconvert userJwt = new JWTconvert();
        JSONObject obj = new JSONObject();
        Jws<Claims> res = userJwt.decodeToJWT(jwtToken, "ZGF5bGFwYXNzd29yZHNpZXVkYWlzaWV1dG9zaWV1a2hvbmdsbw0K");

        String userId = (String) res.getBody().get("user_id");

        UserDTO userLogin = dao.getUser(userId);
        userLogin.setUser_password(null);
        userLogin.setGgid(null);
        userLogin.setSalt(null);

        System.out.println(userLogin);

        if (userLogin != null) {

            return Response.ok(new Gson().toJson(userLogin)).build();

        } else {
            obj.put("error", "Login again");
            return Response.status(401).entity(new Gson().toJson(obj)).build();
        }

        
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response signUp(@FormDataParam("username") String username, @FormDataParam("password") String password, @FormDataParam("email") String email) {

        UserDAO dao = new UserDAO();
        JSONObject obj = new JSONObject();
        UserDetail user = new UserDetail(username, password, email);

//        Check if username existed
        Boolean checkUsername = dao.getUsername(user.getUsername());

        if (!checkUsername) {
            String id = dao.signUp(user.getUsername(), user.getUser_password(), user.getEmail());

            obj.put("message", "Sign up successfully");
            obj.put("user_id", id);
            return Response.ok(new Gson().toJson(obj)).build();

        } else {
            obj.put("error", "Username existed, please use another one");
            return Response.status(401).entity(new Gson().toJson(obj)).build();
        }

    }

    @POST
    @Path("/update/{user_id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("user_id") String user_id,
            @FormDataParam("subname") String subname,
            @FormDataParam("name") String name,
            @FormDataParam("phone") String phone,
            @FormDataParam("email") String email,
            @FormDataParam("dob") String dob,
            @FormDataParam("selfdescription") String selfDes,
            @FormDataParam("avatar") InputStream fileInputStream,
            @FormDataParam("avatar") FormDataContentDisposition fileFormDataContentDisposition) throws ParseException {

        LocalDate ld = LocalDate.parse(dob);
        Date userDob = java.sql.Date.valueOf(ld);
        UploadImage uploaduser = new UploadImage();
        JSONObject obj = new JSONObject();
        UserDAO dao = new UserDAO();
        // Get specific user
        UserDTO ud = dao.getUser(user_id);

        // local variables
        String fileName = null;
        String uploadFilePath = null;

        try {
            UUID uuid = UUID.randomUUID();
            fileName = fileFormDataContentDisposition.getFileName();
            uploadFilePath = uploaduser.writeToFileServer("users", fileInputStream, fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            // release resources, if any
        }

        ud.setEmail(email);
        ud.setPhone(phone);
        ud.setName(name);
        ud.setSubname(subname);
        ud.setDob(userDob);
        ud.setSelf_description(selfDes);
        ud.setAvatar(uploadFilePath);

        System.out.println(ud);
        System.out.println(ud.getDob());

//        Check update status
        Boolean checkUpdate = dao.update(ud);
//        System.out.println(checkUpdate);
//        Boolean checkUpdate = true;
        if (checkUpdate) {
            obj.put("message", "Successfully update");
//            Response.status(200).entity(new Gson().toJson(obj)).build();
            return Response.ok(new Gson().toJson(obj)).build();
        } else {
            obj.put("message", "Failed to update");
            return Response.status(400).entity(new Gson().toJson(obj)).build();
        }

    }

    @POST
    @Path("/changePassword/{user_id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response changePassword(@PathParam("user_id") String user_id, @FormDataParam("newPassword") String newPassword) {

        UserDAO dao = new UserDAO();
        JSONObject obj = new JSONObject();
//        Check if change password success or not
        Boolean checkChange = dao.changePassword(newPassword, user_id);

        if (checkChange) {
            obj.put("message", "Successfully change password");
            return Response.ok(new Gson().toJson(obj)).build();
        } else {
            return Response.status(401).entity(new Gson().toJson(obj)).build();
        }

    }

    @POST
    @Path("/compareCurrentPassword/{user_id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response compareCurrentPassword(@PathParam("user_id") String user_id, @FormDataParam("currentPassword") String currentPassword) {

        UserDAO dao = new UserDAO();
        JSONObject obj = new JSONObject();
//        Check if change password success or not
        Boolean checkChange = dao.compareCurrentPassword(user_id, currentPassword);

        if (checkChange) {
            obj.put("success", "Password matched");
            return Response.ok(new Gson().toJson(obj)).build();
        } else {
            obj.put("error", "Wrong password");
            return Response.status(401).entity(new Gson().toJson(obj)).build();
        }

    }
    
    @PUT
    @Path("/bookmark")
    @Produces(MediaType.APPLICATION_JSON)
    public Response bookmarkHotel(@QueryParam("hotelId") String hotelId, @QueryParam("userId") String userId) {
        BookmarkDAO bookmarkDao = new BookmarkDAO();
        boolean isAdded = bookmarkDao.add(hotelId, userId);
        JsonObject response = new JsonObject(); 
        
        if (isAdded) {
            response.addProperty("ok", "success");
            return Response.ok(new Gson().toJson(response)).build();
        } else {
            response.addProperty("error", "can not perform this action");
            return Response.ok(new Gson().toJson(response)).build();
        }
    }
    
    @DELETE
    @Path("/bookmark")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteHotelFromBookmark(@QueryParam("hotelId") String hotelId, @QueryParam("userId") String userId) {
        BookmarkDAO bookmarkDao = new BookmarkDAO();
        boolean isDeleted =  bookmarkDao.delete(hotelId, userId);
        JsonObject response = new JsonObject(); 
        
        if (isDeleted) {
            response.addProperty("ok", "success");
            return Response.ok(new Gson().toJson(response)).build();
        } else {
            response.addProperty("error", "can not perform this action");
            return Response.ok(new Gson().toJson(response)).build();
        }
    }
    
    @GET
    @Path("amount/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAmount(@PathParam("userId") String userId) throws SQLException {
        JsonObject response = new JsonObject();
        UserDetailDAO dao = new UserDetailDAO();
        int amount = dao.getAmount(userId);
        Gson gson = new Gson();
        response.addProperty("amount", amount);
        
        return Response.ok(gson.toJson(response)).build();
    }
    
    @GET
    @Path("notification")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotificaiton(
            @QueryParam("userId") String userId, 
            @QueryParam("type") String type,
            @QueryParam("sourceId") String sourceId
    ) throws SQLException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(userId + " " + type);
        if (type.equals("latest")) {
            return Response.ok(gson.toJson(service.getNotification(sourceId))).build();  
        } else {
            return Response.ok(gson.toJson(service.getNotification(userId, type))).build();  
        } 
    }
    
    @PUT
    @Path("notification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response markNotifAsRead(@PathParam("id") int notifId) throws SQLException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(notifId);
        service.markNotifAsRead(notifId);
        JsonObject response = new JsonObject();
        response.addProperty("status", "success");
        return Response.ok(gson.toJson(response)).build();   
    }
    
    @PUT
    @Path("notification/all/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response markAllNotifAsRead(@PathParam("id") String userId ) throws SQLException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        service.markAllNotifAsRead(userId);
        JsonObject response = new JsonObject();
        response.addProperty("status", "success");
        return Response.ok(gson.toJson(response)).build();   
    }
    
//    UPDATE BANKING NUMBER
    @POST
    @Path("bank")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBankingAccount(
            @FormDataParam("bankingAccountNumber") String bankingAccountNumber,
            @FormDataParam("userId") String userId
    ) throws SQLException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        boolean isExisted = service.updateBankingAccountNumber(userId, bankingAccountNumber);
        JsonObject response = new JsonObject();
        if (isExisted) {
            response.addProperty("success", "ok");
        } else {
            response.addProperty("fail", "not found");
        }
        return Response.ok(gson.toJson(response)).build();   
    }
}
