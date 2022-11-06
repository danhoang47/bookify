/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.repository;

import app.dao.DashboardDAO;
import app.dao.ReportDAO;
import app.dao.ReviewDAO;
import app.dto.DashboardDTO;
import app.dto.ExchangeDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author toten
 */
public class DashboardRepository {

    private ReportDAO reportDAO;
    private DashboardDAO dashboardDAO;
    private ReviewDAO reviewDAO;

    public DashboardRepository() {
        reportDAO = new ReportDAO();
        dashboardDAO = new DashboardDAO();
        reviewDAO = new ReviewDAO();
    }

    public DashboardDTO getDashboardData(int month) throws SQLException {
        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setListReport(reportDAO.getAll(month));
        Map<Integer, Integer> listBooking = dashboardDAO.getBookingData(month);
        Map<String, Integer> listBookingHotelType = dashboardDAO.bookingTrend(month);
        List<Integer> listBookingDateNumber = new ArrayList<>();
        List<Integer> listBookingDate = new ArrayList<>();
        List<Integer> listBookingHotelTypeNumber = new ArrayList<>();
        for (Map.Entry<Integer, Integer> set
                : listBooking.entrySet()) {
            listBookingDate.add(set.getKey());
            listBookingDateNumber.add(set.getValue());

        }
        
        for (int i = 0; i < dashboard.getBookingHotelType().size(); i++) {
            if (listBookingHotelType.containsKey(dashboard.getBookingHotelType().get(i))) {
                listBookingHotelTypeNumber.add(listBookingHotelType.get(dashboard.getBookingHotelType().get(i)));
            } else {
                listBookingHotelTypeNumber.add(0);
            }
        }
        dashboard.setBookingDate(listBookingDate);
        dashboard.setBookingDateNumber(listBookingDateNumber);
        dashboard.setBookingHotelTypeNumber(listBookingHotelTypeNumber);
        dashboard.setReviewNumber(reviewDAO.getNumberOfReview(month));
        dashboard.setBookingNumber(dashboardDAO.getNumberOfBooking(month));
        dashboard.setPaymentNumber(dashboardDAO.getNumberOfPayment(month));
        dashboard.setUserRegisNumber(dashboardDAO.getRegisNumber(month));
        dashboard.setMonth(month);

        return dashboard;
    }
    
    public List<ExchangeDTO> getExchangeData() throws SQLException {
        List<ExchangeDTO> data = dashboardDAO.getExchangeData();

        return data;
    }


    public static void main(String[] args) throws SQLException {
        DashboardRepository repo = new DashboardRepository();
        DashboardDTO dashboard = repo.getDashboardData(11);
        System.out.println(dashboard);
    }
}
