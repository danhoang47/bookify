/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dto;

import java.util.Date;

/**
 *
 * @author toten
 */
public class UserDetail {
//    13

    private String user_id;
    private String username;
    private String user_password;
    private String email;
    private String phone;
    private String name;
    private String avatar;
    private int role;
    private String ggid;
    private String wishlist_id;
    private String self_description;
    private String salt;
    private double wallet_amount;

    public UserDetail() {
        super();
    }

    public UserDetail(String user_id, String username, String user_password, String email, String phone, String name, String avatar, int role, 
            String ggid, String wishlist_id,
            String self_description,  String salt, double wallet_amount) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
        this.role = role;
        this.ggid = ggid;
        this.wishlist_id = wishlist_id;
        this.self_description = self_description;
        this.salt = salt;
        this.wallet_amount = wallet_amount;
    }

    public UserDetail(String user_id, String username, String user_password, String email, String phone, String name, String avatar, int role, String ggid, String wishlist_id,
            String self_description) {
        this.user_id = user_id;
        this.username = username;
        this.user_password = user_password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
        this.role = role;
        this.ggid = ggid;
        this.wishlist_id = wishlist_id;
        this.self_description = self_description;

    }

    public UserDetail(String username, String user_password, String email, String phone, String name, String avatar, int role, String ggid, String wishlist_id,
            String self_description) {

        this.username = username;
        this.user_password = user_password;
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
        this.role = role;
        this.ggid = ggid;
        this.wishlist_id = wishlist_id;
        this.self_description = self_description;

    }

    public UserDetail(String email, String phone, String name, String self_description, String avatar) {
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.avatar = avatar;
        this.self_description = self_description;
    }

    public UserDetail(String username, String password) {
        this.username = username;
        this.user_password = password;
    }

    public UserDetail(String username, String password, String email) {
        this.username = username;
        this.user_password = password;
        this.email = email;
    }



    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getEmail() {
        return email;
    }

    public double getWallet_amount() {
        return wallet_amount;
    }

    public void setWallet_amount(double wallet_amount) {
        this.wallet_amount = wallet_amount;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getGgid() {
        return ggid;
    }

    public void setGgid(String ggid) {
        this.ggid = ggid;
    }

    public String getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(String wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public String getSelf_description() {
        return self_description;
    }

    public void setSelf_description(String self_description) {
        this.self_description = self_description;
    }

    @Override
    public String toString() {
        return "UserDetail{" + "user_id=" + user_id + ", username=" + username + ", user_password=" + user_password + ", email=" + email + ", phone=" + phone + ", name=" + name + ", avatar=" + avatar + ", role=" + role + ", ggid=" + ggid + ", wishlist_id=" + wishlist_id + ", self_description=" + self_description  +'}';
    }

}
