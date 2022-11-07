/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.HotelManageDAO;
import app.dao.ReviewDAO;
import app.dao.ViewDAO;
import app.dto.HotelManageDTO;
import app.dto.ReviewDTO;
import java.util.List;

/**
 *
 * @author toten
 */
public class HotelManageService {

    private static HotelManageDAO hotelManageDao;
    private static ReviewDAO reviewDao;
    private static ViewDAO viewDao;

    public HotelManageService() {
        hotelManageDao = new HotelManageDAO();
        reviewDao = new ReviewDAO();
        viewDao = new ViewDAO();
    }

    public HotelManageDTO listHotelBookingData(String hotel_id, int month) {
        return hotelManageDao.listHotelData(hotel_id, month);
    }

    public HotelManageDTO listHotelBookingDataAll(String hotel_id, int month) {
        String[] months = {"Jan","Feb","Mar", "Apr","May","Jun", "Jul","Aug","Sep","Oct","Nov","Dec"};
        HotelManageDTO hoteldto = hotelManageDao.getNumberOfAllBooking(hotel_id, month);
        hoteldto.setAccessNumber(viewDao.getViewByMonth(hotel_id, months[month-1]));
        return hoteldto;
    }

    public List<ReviewDTO> listHotelRatingData(String hotel_id) {
        List<ReviewDTO> list = reviewDao.listReview(hotel_id);
        return list;
    }

    public List<ReviewDTO> listHotelRatingWithPoint(String hotel_id, int point) {
        List<ReviewDTO> list = reviewDao.listReviewWithPoint(hotel_id, point);
        return list;
    }

    public static void main(String[] args) {
        HotelManageService sv = new HotelManageService();
        List<ReviewDTO> lis = sv.listHotelRatingWithPoint("cfcd8503-dda9-41d8-8368-759b018557d7", 4);
        System.out.println(lis);
    }
}
