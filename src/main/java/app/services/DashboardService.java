/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dto.DashboardDTO;
import app.dto.ExchangeDTO;
import app.repository.DashboardRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author toten
 */
public class DashboardService {
    private DashboardRepository dashboardRepo;
    
    public DashboardService() {
        dashboardRepo = new DashboardRepository();
    }
    
    public List<DashboardDTO> getDashboardData(int month) throws SQLException {
        List<DashboardDTO> listDashboardDTOs = new ArrayList<>();
        listDashboardDTOs.add(dashboardRepo.getDashboardData(month));
        listDashboardDTOs.add(dashboardRepo.getDashboardData(month-1));
                
        return listDashboardDTOs;
    }
    
    public List<ExchangeDTO> getExchangeData() throws SQLException {
        List<ExchangeDTO> data = dashboardRepo.getExchangeData();

        return data;
    }
}
