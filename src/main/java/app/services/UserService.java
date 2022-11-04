/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.services;

import app.repository.UserRepository;

/**
 *
 * @author toten
 */
public class UserService {
    private UserRepository userRepo;
    
    public UserService() {
        userRepo = new UserRepository();
    }
    
    public boolean makeHosting(String userId) {
    
        return userRepo.makeHosting(userId);
    }
}
