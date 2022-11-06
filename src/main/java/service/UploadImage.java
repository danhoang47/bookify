/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author toten
 */
public class UploadImage {

    public static final String UPLOAD_FILE_SERVER = "D:/Project/Bookify/src/main/webapp/images/";
//    public static final String UPLOAD_FILE_SERVER = "D:/Project/bookify-login-feature/public/photo/";

    public static String writeToFileServer(String typeUpload, InputStream inputStream, String fileName) throws IOException {

        OutputStream outputStream = null;
        String qualifiedUploadFilePath = UPLOAD_FILE_SERVER + typeUpload + "/" + fileName;

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
        return "http://localhost:8080/bookify/images/"+typeUpload + "/" + fileName;
    }

    public static List<String> uploadMultipleFile(FormDataBodyPart body) throws IOException {

        List<String> listPath = new ArrayList<>();

        for (BodyPart part : body.getParent().getBodyParts()) {
            InputStream is = part.getEntityAs(InputStream.class);
            ContentDisposition meta = part.getContentDisposition();

            if (meta.getFileName() != null) {
                String path = writeToFileServer("hotels", is, meta.getFileName());
                System.out.println("Path: " + path);
                if (!listPath.contains(path)) {
                    listPath.add(path);
                }

            }

        }


        return listPath;
    }
    
     public static List<String> uploadMultipleFile2(List<FormDataBodyPart> body) throws IOException {

        List<String> listPath = new ArrayList<>();
        
        if(body!=null) {
            for(int i =0; i<body.size(); i++) {
                BodyPartEntity bodyPartEntity = (BodyPartEntity) body.get(i).getEntity();
                String fileName = body.get(i).getContentDisposition().getFileName();
                if(fileName!=null) {
                    String path = writeToFileServer("hotels", bodyPartEntity.getInputStream(), fileName);
                    System.out.println(path);
                    listPath.add(path);
                }
            }
        }

        return listPath;
    }

//    public static String uploadSingle(FormDataBodyPart body) throws IOException {
//        String path = "";
//        for (BodyPart part : body.getParent().getBodyParts()) {
//            InputStream is = part.getEntityAs(InputStream.class);
//            ContentDisposition meta = part.getContentDisposition();
//
//            path = writeToFileServer("users", is, meta.getFileName());
//
//        }
//        return path;
//    }
    public static String uploadSingleFile(InputStream fileInputStream,
            FormDataContentDisposition fileFormDataContentDisposition, String role) {

        if (fileInputStream == null || fileFormDataContentDisposition == null) {
            return null;
        }

        // local variables
        String fileName = null;
        String uploadFilePath = null;

        try {
            UUID uuid = UUID.randomUUID();
            fileName = fileFormDataContentDisposition.getFileName();
//            users
            uploadFilePath = writeToFileServer(role, fileInputStream, fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            // release resources, if any
        }

        return uploadFilePath;
    }

    public static void main(String[] args) {
        
    }
}
