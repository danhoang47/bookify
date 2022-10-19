package com.jersey.series.userDetail.service;

import com.google.gson.Gson;
import com.service.Interface.UserDetailInterface;
import dao.UserDetailDAO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.io.IOException;
import java.io.InputStream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
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
import service.uploadImage.UploadImage;

@Path("/user_detail")
public class UserResource implements UserDetailInterface {

    @POST
    @Path("/login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormDataParam("username") String username, @FormDataParam("password") String password) {

        UserDetailDAO dao = new UserDetailDAO();
        UserDetail user = new UserDetail(username, password);
        JWTconvert userJwt = new JWTconvert();
        JSONObject obj = new JSONObject();
        System.out.println(user);

//         Check if username is true or not
        Boolean checkUsername = dao.getUsername(user.getUsername());

        if (checkUsername) {
            UserDetail userLogin = dao.login(username, password);

            if (userLogin != null) {
                String jwtCode = userJwt.encodeToJWT(userLogin.getUser_id(), userLogin.getRole());
                obj.put("user", userLogin);
                obj.put("token", jwtCode);

                return Response.ok(new Gson().toJson(obj)).build();
            } else {
                obj.put("message", "Wrong password");
                return Response.status(401).entity("Wrong password").build();
            }

        } else {
            obj.put("message", "Wrong username or username does not exist");
            return Response.status(401).entity(new Gson().toJson(obj)).build();
        }

    }

    @GET
    @Path("/verifyjwt")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDataByJWT(@HeaderParam("jwt") String jwtString) {
        
        
        System.out.println(jwtString);
        UserDetailDAO dao = new UserDetailDAO();
//        UserDetail user = new UserDetail(username, password);
        JWTconvert userJwt = new JWTconvert();
        JSONObject obj = new JSONObject();
        Jws<Claims> res = userJwt.decodeToJWT(jwtString, "ZGF5bGFwYXNzd29yZHNpZXVkYWlzaWV1dG9zaWV1a2hvbmdsbw0K");
        
        String userId =(String) res.getBody().get("user_id");
        
        UserDetail userLogin = dao.getUser(userId);
        userLogin.setUser_password(null);
        userLogin.setGgid(null);
        userLogin.setSalt(null);
       

        if (userLogin!=null) {
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
            obj.put("message", "Sign up successfully");
            return Response.status(401).entity(new Gson().toJson(obj)).build();
        }

    }

    @POST
    @Path("/update/{user_id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("user_id") String user_id, @FormDataParam("phone") String phone,
            @FormDataParam("name") String name, @FormDataParam("selfdescription") String selfDes,
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {

        UploadImage uploaduser = new UploadImage();
        JSONObject obj = new JSONObject();
        UserDetailDAO dao = new UserDetailDAO();
        // Get specific user
        UserDetail ud = dao.getUser(user_id);

        // local variables
        String fileName = null;
        String uploadFilePath = null;

        try {
            fileName = fileFormDataContentDisposition.getFileName();
            uploadFilePath = uploaduser.writeToFileServer("users", fileInputStream, fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            // release resources, if any
        }

        ud.setPhone(phone);
        ud.setName(name);
        ud.setSelf_description(selfDes);
        ud.setAvatar(uploadFilePath);

        System.out.println(ud);

//        Check update status
        Boolean checkUpdate = dao.update(ud);

        if (checkUpdate) {
            obj.put("message", "Successfully update");
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

}
