package com.service.Interface;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author toten
 */

 
import java.io.InputStream;
 
import javax.ws.rs.core.Response;
 
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
 
public interface UserDetailInterface {

    public Response login(String username, String password);
    public Response signUp(String username, String password, String email);
    public Response update(String user_id, String phone, String name, String selfDes, InputStream fileInputStream, FormDataContentDisposition fileFormDataContentDisposition);
}