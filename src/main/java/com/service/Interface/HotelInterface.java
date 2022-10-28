/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.Interface;

import javax.ws.rs.core.Response;

/**
 *
 * @author toten
 */
public interface HotelInterface {
    
    public Response listAllHotel();

    public Response viewSpecificHotel(String hotel_id);
    
    public Response signNewHotelFormData();
}
