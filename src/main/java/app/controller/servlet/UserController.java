/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.controller.servlet;

import com.google.gson.Gson;
import dao.UserDetailDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
    @POST
    @Path("/login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormDataParam("username") String username, @FormDataParam("password") String password) {

        UserDetailDAO dao = new UserDetailDAO();
        JWTconvert userJwt = new JWTconvert();
        JSONObject obj = new JSONObject();
        System.out.println(username + " " + password);

//         Check if username is true or not
        Boolean checkUsername = dao.getUsername(username);

        if (checkUsername) {
            UserDetail userLogin = dao.login(username, password);

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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataByJWT(String jwtString) {
        Gson gson = new Gson();

        String jwtToken = gson.fromJson(jwtString, String.class).toString();

        UserDetailDAO dao = new UserDetailDAO();
//        UserDetail user = new UserDetail(username, password);
        JWTconvert userJwt = new JWTconvert();
        JSONObject obj = new JSONObject();
        Jws<Claims> res = userJwt.decodeToJWT(jwtToken, "ZGF5bGFwYXNzd29yZHNpZXVkYWlzaWV1dG9zaWV1a2hvbmdsbw0K");

        String userId = (String) res.getBody().get("user_id");

        UserDetail userLogin = dao.getUser(userId);
        userLogin.setUser_password(null);
        userLogin.setGgid(null);
        userLogin.setSalt(null);
        
        System.out.println(userLogin);

        if (userLogin != null) {
            obj.put("user", userLogin);

            return Response.ok(new Gson().toJson(obj)).build();

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

        UserDetailDAO dao = new UserDetailDAO();
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
        UserDetailDAO dao = new UserDetailDAO();
        // Get specific user
        UserDetail ud = dao.getUser(user_id);

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

        UserDetailDAO dao = new UserDetailDAO();
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

        UserDetailDAO dao = new UserDetailDAO();
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
}
