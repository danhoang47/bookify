/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dto.HotelAmenityDTO;

/**
 *
 * @author toten
 */
public class HotelAmenityDAO {

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

    public static List<HotelAmenityDTO> listAllTypes() {

        try {
            String query = "select * from Amenity_type";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<HotelAmenityDTO> listTypes = new ArrayList<>();

            while (rs.next()) {

                listTypes.add(new HotelAmenityDTO(rs.getString(1), rs.getString(2)));

            }

            return listTypes;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelAmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<HotelAmenityDTO> listAllAmenity() {

        try {
            String query = "select * from amenity";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<HotelAmenityDTO> listTypes = new ArrayList<>();

            while (rs.next()) {

                listTypes.add(new HotelAmenityDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));

            }

            return listTypes;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelAmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static List<String> listAllAmenityId() {

        try {
            String query = "select amenity_id from amenity";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<String> listAmentiesId = new ArrayList<>();

            while (rs.next()) {

                listAmentiesId.add(rs.getString(1));

            }

            return listAmentiesId;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelAmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void addAmenity(String amenityId, String amenityName, String amenityType) {
        try {
            String query = "insert into amenity values (?, ?, ?, ?)";
            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);
            ps.setString(1, amenityId);
            ps.setString(2, amenityName);
            ps.setString(3, "faPencil");
            ps.setString(4, amenityType);

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelAmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean addHotelAmenitiesAll(List<String> amenitiesId, List<String> amenitiesName, List<String> amenitiesTypes, String hotel_id) {
        HotelAmenityDAO had = new HotelAmenityDAO();
        List<String> currentAmenities = had.listAllAmenityId();
        List<String> newAmenitiesId = had.stringToArray(amenitiesId.get(0));
        List<String> newAmenitiesName = had.stringToArray(amenitiesName.get(0));
        List<String> newAmenitiesTypes = had.stringToArray(amenitiesTypes.get(0));
        


        for (int i = 0; i < newAmenitiesId.size(); i++) {
            if (!currentAmenities.contains(newAmenitiesId.get(i))) {
                had.addAmenity(newAmenitiesId.get(i), newAmenitiesName.get(i), newAmenitiesTypes.get(i));
            }
        }
        boolean check = had.addHotelAmenties(newAmenitiesId, hotel_id);
        if (check) {
            return true;
        }
        return false;
    }

    public static List<String> stringToArray(String str) {
        List<String> items = Arrays.asList(str.split("\\s*,\\s*"));
        return items;
    }

    public static boolean addHotelAmenties(List<String> amenties, String hotel_id) {
        List<Integer> check = new ArrayList<>();

        try {
            String query = "insert into HotelAmenities values (?, ?,  ?)";
            conn = new DBContext().getConnection();

            for (int i = 0; i < amenties.size(); i++) {
                UUID uuid = UUID.randomUUID();
                ps = conn.prepareStatement(query);
                
                ps.setString(1, uuid.toString());
                ps.setString(2, amenties.get(i));
                ps.setString(3, hotel_id);
                int x = ps.executeUpdate();

                if (x != 0) {
                    check.add(x);
                }
            }

            if (check.size() > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(HotelAmenityDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {

        HotelAmenityDAO had = new HotelAmenityDAO();

        List<String> hotelSignAmenities = new ArrayList<>();
        hotelSignAmenities.add("01d8e6d4-bd0e-49d4-b21a-660cd2809184");
        hotelSignAmenities.add("0d9fd954-78d7-49ae-a465-043be9c1d07c");
        hotelSignAmenities.add("anuihd1982dh19dh");

        List<String> hotelSignAmenitiesName = new ArrayList<>();
        hotelSignAmenitiesName.add("Pool");
        hotelSignAmenitiesName.add("Wifi");
        hotelSignAmenitiesName.add("Dich vu moi");

        List<String> hotelSignAmenitiesType = new ArrayList<>();
        hotelSignAmenitiesType.add("230198c8-317c-4f60-8a60-78e6ab002963");
        hotelSignAmenitiesType.add("230198c8-317c-4f60-8a60-78e6ab002963");
        hotelSignAmenitiesType.add("f7bedf8c-7c2c-43e8-a8ec-fc327eb0f520");

        Boolean check = had.addHotelAmenitiesAll(hotelSignAmenities, hotelSignAmenitiesName,
                hotelSignAmenitiesType, "0768f7ee-5df8-47e4-9185-af0e767e9f4d");

        System.out.println(check);

//        List<String> listTypes = new HotelAmenityDAO().listAllAmenityId();
//
//        System.out.println(listTypes);
    }
}
