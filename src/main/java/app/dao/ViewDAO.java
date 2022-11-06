/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
public class ViewDAO {

    private Connection conn;
    private PreparedStatement ps;
    private CallableStatement cs;
    private ResultSet rs;

    public boolean addNewView(String hotelId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO hotelView VALUES (?, default, default, default, default, default, default, default , default , default, default, default , default, default)\n"
                    + " ";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, hotelId);

            int a = ps.executeUpdate();

            if (a == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean increaseView(String hotelId, String month) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "update hotelView set " +  month + "=((select " + month +" from hotelView where hotel_id=?)+1) where hotel_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, hotelId);
            ps.setString(2, hotelId);

            int a = ps.executeUpdate();

            if (a == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int getViewByMonth(String hotelId, String month) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "select * from hotelView as hv where hotel_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, hotelId);

            rs= ps.executeQuery();
            
            int views = 0;

           while (rs.next()) {
                views = rs.getInt(month);
                return views;
            }
           
           return views;

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ViewDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        int a =new ViewDAO().getViewByMonth("cfcd8503-dda9-41d8-8368-759b018557d7", "Nov");
        System.out.println(a);
    }
}
