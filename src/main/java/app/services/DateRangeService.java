/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.repository.DateRangeRepo;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author toten
 */
public class DateRangeService {
    final private DateRangeRepo dateRangeRepo;

    public DateRangeService() {
        dateRangeRepo = new DateRangeRepo();
    }
    
    public boolean checkDateRange(String checkin, String checkout, String hotelId) throws SQLException, ParseException {
        return dateRangeRepo.checkDate(checkin, checkout, hotelId);
    }
    
    public List<String> getFreeRooms(String checkin, String checkout, String hotelId) throws SQLException, ParseException {
        HashSet<String> listFreeRooms = dateRangeRepo.getFreeRooms(checkin, checkout, hotelId);
        List<String> listRooms = new ArrayList<>();
        Iterator<String> it = listFreeRooms.iterator();
        while (it.hasNext()) {
            listRooms.add(it.next());
        }
        
        return listRooms;
    }
}
