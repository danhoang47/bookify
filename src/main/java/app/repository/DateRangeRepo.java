/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.repository;

import app.dao.DateRangeDAO;
import app.dao.RoomDAO;
import app.dto.DateRangeDTO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author toten
 */
public class DateRangeRepo {
    RoomDAO roomDao;
    DateRangeDAO daterRangeDao;

    public DateRangeRepo() {
        daterRangeDao = new DateRangeDAO();
        roomDao = new RoomDAO();
    }

    public boolean checkDate(String checkin, String checkout, String hotelId) throws SQLException, ParseException {
        List<DateRangeDTO> list = daterRangeDao.getAll(checkin, checkout, hotelId);
        
        List<String> roomsId = roomDao.getHotelNumberOfRoom(hotelId);

        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date s1 = s.parse(checkin);
        Date e1 = s.parse(checkout);
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            if (s1.compareTo(list.get(i).getCheck_in())<=0 && e1.compareTo(list.get(i).getCheck_in()) >=0
                    || s1.compareTo(list.get(i).getCheck_out()) <=0 && e1.compareTo(list.get(i).getCheck_out()) >=0
                    || s1.compareTo(list.get(i).getCheck_in()) <=0 && e1.compareTo(list.get(i).getCheck_out()) >=0
                    || s1.compareTo(list.get(i).getCheck_in()) >=0 && e1.compareTo(list.get(i).getCheck_out())<=0) {
                
                
                result++;

                
            } 
        }
        System.out.println(result);

        
        if(result<roomsId.size()) {
            return true;
        } else {
            return false;
        }

    }
    
    public HashSet<String> getFreeRooms(String checkin, String checkout, String hotelId) throws SQLException, ParseException {
        List<DateRangeDTO> list = daterRangeDao.getAll(checkin, checkout, hotelId);
        
        List<String> roomsId = roomDao.getHotelNumberOfRoom(hotelId);
        HashSet<String> listFreeRoom = addToSet(roomsId);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date s1 = s.parse(checkin);
        Date e1 = s.parse(checkout);

        for (int i = 0; i < list.size(); i++) {
            if (s1.compareTo(list.get(i).getCheck_in())<=0 && e1.compareTo(list.get(i).getCheck_in()) >=0
                    || s1.compareTo(list.get(i).getCheck_out()) <=0 && e1.compareTo(list.get(i).getCheck_out()) >=0
                    || s1.compareTo(list.get(i).getCheck_in()) <=0 && e1.compareTo(list.get(i).getCheck_out()) >=0
                    || s1.compareTo(list.get(i).getCheck_in()) >=0 && e1.compareTo(list.get(i).getCheck_out())<=0) {

                listFreeRoom.remove(list.get(i).getRoom_id());
                
            } 
        }
        
        return listFreeRoom;

    }
    
    public static HashSet<String> addToSet(List<String> roomsId) {
        HashSet<String> listFreeRoom = new HashSet<>();
        for(String id : roomsId) {
            listFreeRoom.add(id);
        }
        return listFreeRoom;
    }
    
    public static void main(String[] args) throws SQLException, ParseException {

         boolean check = new  DateRangeRepo().checkDate("2022-11-7", "2022-11-10", "2f5923a5-64d0-4bdc-bf0b-fabfa76f0404");
         HashSet<String> freeRooms = new  DateRangeRepo().getFreeRooms("2022-11-24", "2022-11-29", "f98320c3-235a-4cb7-a0a8-eda132b0e545");
         System.out.println(check);
         
    }
}
