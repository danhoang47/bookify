package com.jersey.series.userDetail.service;

import com.google.gson.Gson;
import dao.UserDetailDAO;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.dto.UserDetail;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.json.simple.JSONObject;
import secure.JWTconvert;

@Path("/user_detail")
public class UserResource implements IFileService {

    public static final String UPLOAD_FILE_SERVER = "D:/netbeanJavaWeb/testUpload/src/main/webapp/images/users/";

    @POST
    @Path("/upload/images")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response uploadImageFile(
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {

        // local variables
        String fileName = null;
        String uploadFilePath = null;

        try {
            fileName = fileFormDataContentDisposition.getFileName();
            uploadFilePath = writeToFileServer(fileInputStream, fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            // release resources, if any
        }
        return Response.ok("File uploaded successfully at " + uploadFilePath).build();
    }

    private String writeToFileServer(InputStream inputStream, String fileName) throws IOException {

        OutputStream outputStream = null;
        String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + fileName;

        try {
            outputStream = new FileOutputStream(new File(qualifiedUploadFilePath));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            //release resource, if any
            outputStream.close();
        }
        return qualifiedUploadFilePath;
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
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
                String jwtCode = userJwt.encodeToJWT(username, userLogin.getRole());
                obj.put("user", userLogin);
                obj.put("token", jwtCode);
                return Response.ok(new Gson().toJson(obj)).build();
            } else {
                return Response.status(401).entity("Wrong password").build();
            }

        } else {
            return Response.status(401).entity("Wrong username or username does not exist").build();
        }

    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response signUp(@FormDataParam("username") String username, @FormDataParam("password") String password, @FormDataParam("email") String email) {

        UserDetailDAO dao = new UserDetailDAO();

        UserDetail user = new UserDetail(username, password, email);

//        Check if username existed
        Boolean checkUsername = dao.getUsername(user.getUsername());

        if (!checkUsername) {
            String id = dao.signUp(user.getUsername(), user.getUser_password(), user.getEmail());
            System.out.println(id);

            return Response.ok(id, MediaType.APPLICATION_JSON).build();

        } else {
            return Response.status(401).entity("Existed username").build();
        }

    }

    @POST
    @Path("/update/{user_id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response update(@PathParam("user_id") String user_id, @FormDataParam("phone") String phone,
            @FormDataParam("name") String name, @FormDataParam("selfdescription") String selfDes,
            @FormDataParam("uploadFile") InputStream fileInputStream,
            @FormDataParam("uploadFile") FormDataContentDisposition fileFormDataContentDisposition) {

        UserDetailDAO dao = new UserDetailDAO();
        // Get specific user
        UserDetail ud = dao.get(user_id);

        // local variables
        String fileName = null;
        String uploadFilePath = null;

        try {
            fileName = fileFormDataContentDisposition.getFileName();
            uploadFilePath = writeToFileServer(fileInputStream, fileName);
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
            return Response.ok("Successfully update").build();
        } else {
            return Response.status(400).entity("Failed to update").build();
        }

    }
    
    @POST
    @Path("/changePassword/{user_id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response changePassword(@PathParam("user_id") String user_id, @FormDataParam("newPassword") String newPassword) {

        UserDetailDAO dao = new UserDetailDAO();
        
//        Check if change password success or not
        Boolean checkChange = dao.changePassword(newPassword, user_id);

        if (checkChange) {
            return Response.ok("Successfully change password").build();
        } else {
            return Response.status(401).entity("Failed to change password").build();
        }

    }
}
