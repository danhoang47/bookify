/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.repository;

import app.dao.UserDAO;

/**
 *
 * @author toten
 */
public class UserRepository {
    private UserDAO userDAO;
    
    public UserRepository() {
        userDAO = new UserDAO();
    }
    
    public boolean makeHosting(String userId) {
        boolean check = userDAO.makeHosting(userId);
        return check;
    }
}
