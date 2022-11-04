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
import java.util.Date;
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
        int numberOfRooms = roomDao.getHotelNumberOfRoom(hotelId);
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        Date s1 = s.parse(checkin);
        Date e1 = s.parse(checkout);
        int result = 0;
        for (int i = 0; i < list.size(); i++) {
            if (s1.before(list.get(i).getCheck_in()) && e1.after(list.get(i).getCheck_in())
                    || s1.before(list.get(i).getCheck_out()) && e1.after(list.get(i).getCheck_out())
                    || s1.before(list.get(i).getCheck_in()) && e1.after(list.get(i).getCheck_out())
                    || s1.after(list.get(i).getCheck_in()) && e1.before(list.get(i).getCheck_out())) {
                
                result++;
                
            }
        }
        
        
        if(result<numberOfRooms) {
            return true;
        } else {
            return false;
        }

    }
    
    public static void main(String[] args) throws SQLException, ParseException {
         
         boolean check = new  DateRangeRepo().checkDate("2022-11-12", "2022-11-17", "f98320c3-235a-4cb7-a0a8-eda132b0e545");
         System.out.println(check);
    
    }
}
