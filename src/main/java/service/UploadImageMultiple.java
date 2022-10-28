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
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.ContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

/**
 *
 * @author toten
 */
public class UploadImageMultiple {
     public static final String UPLOAD_FILE_SERVER = "D:/netbeanJavaWeb/testUpload/src/main/webapp/images/";

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
        return qualifiedUploadFilePath;
    }

    public static List<String> uploadMultipleFile(FormDataBodyPart body) throws IOException {
        
        List<String> listPath = new ArrayList<>();

        for (BodyPart part : body.getParent().getBodyParts()) {
            System.out.println("Lan 1");
            InputStream is = part.getEntityAs(InputStream.class);
            ContentDisposition meta = part.getContentDisposition();

            if (meta.getFileName() != null) {
                String path = writeToFileServer("hotels", is, meta.getFileName());
                if(!listPath.contains(path)) {
                    listPath.add(path);
                }
                
            }

        }
        
        System.out.println(listPath);

        return listPath;
    }



    
}
