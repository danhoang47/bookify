/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import static service.UploadImage.UPLOAD_FILE_SERVER;
import static service.UploadImage.writeToFileServer;

public class UploadImage {
    
    public static String uploadSingleFile(InputStream fileInputStream, ContentDisposition content, String typeUpload, String realPath) throws IOException {
        String qualifiedUploadFilePath = writeToFileServer(typeUpload, fileInputStream, content.getFileName(), realPath);

        return qualifiedUploadFilePath;
    }
    
   public static List<String> uploadMultipleFile(FormDataBodyPart body, String typeUpload, String realPath) throws IOException {
        
        List<String> listPath = new ArrayList<>();

        if ( body != null ) {
            for (BodyPart part : body.getParent().getBodyParts()) {
                InputStream is = part.getEntityAs(InputStream.class);
                ContentDisposition meta = part.getContentDisposition();

                if (meta.getFileName() != null) {
                    String path = writeToFileServer(typeUpload, is, meta.getFileName(), realPath);
                    if(!listPath.contains(path)) {
                        listPath.add(path);
                    }
                }
            }
        }
        
        System.out.println(listPath);

        return listPath;
    }
    
    public static String writeToFileServer(String typeUpload, InputStream inputStream, String fileName, String realPath) throws IOException {

        OutputStream outputStream = null;
        String qualifiedUploadFilePath =  "images" + File.separator + typeUpload + File.separator + fileName;

        try {
            File file = new File(realPath + File.separator + qualifiedUploadFilePath);
            outputStream = new FileOutputStream(file);
            
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            outputStream.close();
        }
        return "http://localhost:8080/bookify/images/" + typeUpload + "/" + fileName;
    }

}
