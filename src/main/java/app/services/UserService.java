/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.dao.NotificationDAO;
import app.dto.NotificationDTO;
import app.repository.UserRepository;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author toten
 */
public class UserService {
    private UserRepository userRepo;
    private NotificationDAO notifDao;
    
    public UserService() {
        userRepo = new UserRepository();
        notifDao = new NotificationDAO();
    }
    
    public boolean makeHosting(String userId) {
    
        return userRepo.makeHosting(userId);
    }
    
    public List<NotificationDTO> getNotification(String userId, String type) throws SQLException {
        switch(type) {
            case "all": return notifDao.getAllNotification(userId);
            default: 
                return new ArrayList<>();
        }
    }

    public void markNotifAsRead(int notifId) throws SQLException {
        notifDao.markAsRead(notifId);
    }

    public boolean updateBankingAccountNumber(String userId, String bankingAccountNumber) throws SQLException {
        System.out.println(userId);
        return userRepo.updateBankingAccountNumber(userId, bankingAccountNumber);
    }

    public NotificationDTO getNotification(String sourceId) throws SQLException {
        return notifDao.getNotification(sourceId);
    }

    public void markAllNotifAsRead(String userId) throws SQLException {
        notifDao.markAllAsRead(userId);
    }
}
