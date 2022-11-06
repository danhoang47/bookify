/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import app.dto.HotelDTO;
import Context.DBContext;
import app.dto.UserDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class HotelDAO {

    public HotelDTO get(String id) throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from getHotel where hotel_id=?";
        HotelDTO hotel = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, id);
            rs = ps.executeQuery();

            while (rs.next()) {
                String ownerId = rs.getString("user_id");
                String hotelTypeId = rs.getString("hoteltype_id");
                String name = rs.getString("hotel_name");
                String backgroundImage = rs.getString("background_image");
                String description = rs.getString("description");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                boolean isAllowPet = rs.getBoolean("isAllowPet");
                boolean isHasCamera = rs.getBoolean("isHasCamera");
                String checkin = rs.getString("checkin");
                String checkout = rs.getString("checkout");
                String closing = rs.getString("closing");
                String opening = rs.getString("opening");
                int rating = rs.getInt("rating");
                Date signAt = rs.getDate("signAt");
                hotel = new HotelDTO(id, ownerId,
                        hotelTypeId, name, backgroundImage,
                        isAllowPet, isAllowPet, isHasCamera,
                        description, country, district, city,
                        address, closing, opening, checkin, checkout, null, null, rating, signAt);

                System.out.println(hotel.getHotelName());
            }

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return hotel;
    }

    public HotelDTO getBasicHotelInfo(String userId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from hotel where user_id=?";
        HotelDTO hotel = null;

        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareCall(sql);
            ps.setString(1, userId);
            rs = ps.executeQuery();

            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelTypeId = rs.getString("hoteltype_id");
                String name = rs.getString("hotel_name");
                String backgroundImage = rs.getString("background_image");
                String description = rs.getString("description");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                boolean isAllowPet = rs.getBoolean("isAllowPet");
                boolean isHasCamera = rs.getBoolean("isHasCamera");
                String checkin = rs.getString("checkin");
                String checkout = rs.getString("checkout");
                String closing = rs.getString("closing");
                String opening = rs.getString("opening");

                Date signAt = rs.getDate("signAt");
                hotel = new HotelDTO(hotelId, userId,
                        hotelTypeId, name, backgroundImage,
                        isAllowPet, isHasCamera,
                        description, country, district, city,
                        address, closing, opening, checkin, checkout,  signAt);

                System.out.println(hotel.getHotelName());
            }

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return hotel;
    }

    public List<HotelDTO> getAllHotelsDashboard() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select ht.hotel_id, ht.hotel_name, ht.signAt, ht.is_verified, ud.user_id\n"
                + "from hotel as ht, userDetail as ud where ht.user_id = ud.user_id";
        HotelDTO hotel = null;
        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareCall(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                Date signAt = rs.getDate("signAt");
                boolean isVerified = rs.getInt("is_verified") == 1 ? true : false;
                String userId = rs.getString("user_id");
                UserDTO owner = new UserDAO().getOwner(userId);

                listHotel.add(new HotelDTO(hotelId, hotelName, signAt, isVerified, owner));

            }

            return listHotel;

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }

        return null;
    }

    public void update(HotelDTO hotel) throws SQLException {
        Connection conn = null;
        CallableStatement cs = null;
        String sql = "proc_updateHotel @hotelId = ?, "
                + "@hotelTypeId = ?, @hotelName = ?, @backgroundImage = ?, "
                + "@description = ?, @country = ?, @district = ?, @city = ?, "
                + "@address = ?, @isAllowPet = ?, @isHasCamera = ?, "
                + "@closing = ?, @opening = ?, @checkin = ?, @checkout = ?";

        try {
            conn = DBContext.getConnection();
            cs = conn.prepareCall(sql);
            cs.setString(1, hotel.getHotelId());
            cs.setString(2, hotel.getHotelTypeId());
            cs.setString(3, hotel.getHotelName());
            cs.setString(4, hotel.getBackgroundImg());
            cs.setString(5, hotel.getDescription());
            cs.setString(6, hotel.getCountry());
            cs.setString(7, hotel.getDistrict());
            cs.setString(8, hotel.getCity());
            cs.setString(9, hotel.getAddress());
            cs.setBoolean(10, hotel.isIsAllowPet());
            cs.setBoolean(11, hotel.isIsHasCamera());
            cs.setString(12, hotel.getClosing());
            cs.setString(13, hotel.getOpening());
            cs.setString(14, hotel.getCheckin());
            cs.setString(15, hotel.getCheckout());
            cs.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (cs != null) {
                cs.close();
            }
        }
    }

    public boolean addNewHotel(HotelDTO hotel) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO Hotel VALUES ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ?, ?,  ? , ? , ? , ? , GETDATE())";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, hotel.getHotelId());
            ps.setString(2, hotel.getUserId());
            ps.setString(3, hotel.getHotelTypeId());
            ps.setString(4, hotel.getHotelName());
            ps.setString(5, hotel.getBackgroundImg());
            ps.setInt(6, 0);
            ps.setString(7, hotel.getDescription());
            ps.setString(8, hotel.getCountry());
            ps.setString(9, hotel.getDistrict());
            ps.setString(10, hotel.getCity());
            ps.setString(11, hotel.getAddress());
            ps.setInt(12, hotel.isIsHasCamera() == false ? 0 : 1);
            ps.setInt(13, hotel.isIsAllowPet() == false ? 0 : 1);
            ps.setString(14, hotel.getCheckin());
            ps.setString(15, hotel.getCheckout());
            ps.setString(16, hotel.getClosing());
            ps.setString(17, hotel.getOpening());

            int a = ps.executeUpdate();

            if (a == 1) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean isHotelBookmarked(String hotelId, String userId) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int a = 0;
        try {
            String query = "select * from Bookmark where hotel_id=? and user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, hotelId);
            ps.setString(2, userId);

            rs = ps.executeQuery();

            while (rs.next()) {
                a++;
            }

            if (a > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(dao.HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<HotelDTO> getAllHotelBasicInfo(String userId) throws SQLException {
        String query = "proc_getAllHotelBasicInfor @userId=?";
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            cs = conn.prepareCall(query);
            cs.setString(1, userId);
            cs.executeQuery();
            rs = cs.getResultSet();

            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelTypeId = rs.getString("hotelType_id");
                String bgImage = rs.getString("background_image");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                int averagePrice = rs.getInt("average_price");
                int rating = rs.getInt("rating");
                int isBookmarked = rs.getInt("isBookmarked");
                HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelTypeId, bgImage, country, city, district, address, averagePrice, rating, isBookmarked == 1 ? true : false);

                listHotel.add(hotel);
            }

            return listHotel;

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
        }

        return null;
    }

    public List<HotelDTO> getAllBookmarkedHotel(String userId) throws SQLException {
        String query = "proc_getAllBookmarkedHotel @user_id = ?";
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            cs = conn.prepareCall(query);
            cs.setString(1, userId);
            cs.executeQuery();
            rs = cs.getResultSet();

            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelTypeId = rs.getString("hotelType_id");
                String bgImage = rs.getString("background_image");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                int averagePrice = 0;
                int rating = 0;
                HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelTypeId, bgImage, country, city, district, address, averagePrice, rating, true);

                listHotel.add(hotel);
            }

            return listHotel;

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
        }

        return null;
    }

    public List<HotelDTO> getFilterHotel(String type, String userId, String id) throws SQLException {
        String query = "";
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        if (type.equals("hotel")) {
            query = "proc_getAllHotelBasicInforByHotelType @userId=?, @hoteltypeId=?";
        } else if (type.equals("amenity")) {
            query = "proc_getAllHotelBasicInforByAmenityId @userId=?, @amenityId=?";
        }

        System.out.println(query);

        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            cs = conn.prepareCall(query);

            cs.setString(1, userId);
            cs.setString(2, id);
            cs.executeQuery();
            rs = cs.getResultSet();

            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelTypeId = rs.getString("hotelType_id");
                String bgImage = rs.getString("background_image");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                int averagePrice = rs.getInt("average_price");
                int rating = rs.getInt("rating");
                int isBookmarked = rs.getInt("isBookmarked");
                HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelTypeId, bgImage, country, city, district, address, averagePrice, rating, isBookmarked == 1 ? true : false);

                listHotel.add(hotel);
            }

            return listHotel;

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
        }

        return null;
    }

    public List<HotelDTO> getFilterAdvancedHotel(String userid, String houseType, List<String> amenitiesPicked, int rooms, int numberOfBed, int numberOfBathroom, int min, int max) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "select af.* , ( select count(*) from Bookmark as bm where bm.hotel_id=af.hotel_id and bm.user_id=?) as isBookmarked from AdvancedFilter as af where af.number_of_room>=? and af.bed_number>=? and af.bath_number>=? and af.average_price between ? and ?";

        if (houseType.length() > 0) {
            query += " and af.hoteltype_id=?";
        }
        if (amenitiesPicked.size() > 0 && !amenitiesPicked.get(0).equals("")) {
            System.out.println("size: " + amenitiesPicked.size());
            String listAmenities = "?";
            for (int i = 1; i < amenitiesPicked.size(); i++) {
                listAmenities += ",?";
            }
            query += " and af.hotel_id in (select hotel_id from HotelAmenities where amenity_id in (" + listAmenities + ") group by hotel_id having COUNT(*)=" + amenitiesPicked.size() + ")";
        }

        System.out.println(query);

        List<HotelDTO> listHotel = new ArrayList<>();
        try {
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, userid);
            ps.setInt(2, rooms);
            ps.setInt(3, numberOfBed);
            ps.setInt(4, numberOfBathroom);
            ps.setInt(5, min);
            ps.setInt(6, max == 0 ? 100000 : max);
            int nextIndex = 6;
            if (houseType.length() > 0) {
                nextIndex++;
                ps.setString(nextIndex, houseType);
            }

            if (amenitiesPicked.size() > 0 && !amenitiesPicked.get(0).equals("")) {
                nextIndex++;
                for (int i = 0; i < amenitiesPicked.size(); i++) {
                    ps.setString(nextIndex + i, amenitiesPicked.get(i));
                }

            }

            rs = ps.executeQuery();

            while (rs.next()) {
                String hotelId = rs.getString("hotel_id");
                String hotelName = rs.getString("hotel_name");
                String hotelTypeId = rs.getString("hotelType_id");
                String bgImage = rs.getString("background_image");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String district = rs.getString("district");
                String address = rs.getString("address");
                int averagePrice = rs.getInt("average_price");
                int rating = rs.getInt("rating");
                int isBookmarked = rs.getInt("isBookmarked");
                HotelDTO hotel = new HotelDTO(hotelId, hotelName, hotelTypeId, bgImage, country, city, district, address, averagePrice, rating, isBookmarked == 1 ? true : false);

                listHotel.add(hotel);
            }

            return listHotel;

        } catch (Exception ex) {
            Logger.getLogger(HotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
        }
        return null;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        HotelDAO dao = new HotelDAO();
//        System.out.println(dao.getAllHotelBasicInfo());
        System.out.println(dao.getBasicHotelInfo("8f850c39-086f-4c15-aede-ab7e00d26dd4"));
    }

}
