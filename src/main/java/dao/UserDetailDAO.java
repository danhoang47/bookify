/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.dto.UserDetail;
import Context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import secure.PassEncrypt;
import static secure.PassEncrypt.getSaltvalue;

/**
 *
 * @author toten
 */
public class UserDetailDAO {

    private static UserDetailDAO instance;

    public static UserDetailDAO getInstance() {
        if (instance == null) {
            instance = new UserDetailDAO();
        }
        return instance;
    }

    static Connection conn;
    static PreparedStatement ps;
    static ResultSet rs;

//    ---------------------------------------------------------Sign up ---------------------------------------------------------
    public String signUp(String username, String password, String email) {
        UUID uuid = UUID.randomUUID();
//         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
//         Date date = new Date();  
        PassEncrypt passEncrypt = new PassEncrypt();
        String saltvalue = getSaltvalue(30);

        String encrypPassword = passEncrypt.generateSecurePassword(password, saltvalue);

        try {
            String query = "INSERT INTO userDetail VALUES (?, ?, ?, ?, null, null, null, null, null, null, null, ?, null)";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, uuid.toString());
            ps.setString(2, username);
            ps.setString(3, encrypPassword);
            ps.setString(4, email);
            ps.setString(5, saltvalue);

            ps.executeUpdate();

            return uuid.toString();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    //    ---------------------------------------------------------Users ---------------------------------------------------------
    public static List<UserDetail> listAll() {
        try {
            String query = "select * from userDetail";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<UserDetail> listUser = new ArrayList<>();

            while (rs.next()) {
                listUser.add(new UserDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDouble(13)));
            }

            return listUser;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //    ---------------------------------------------------------Log in ---------------------------------------------------------
    public static UserDetail login(String username, String password) {
        PassEncrypt passEncrypt = new PassEncrypt();
//        String saltvalue = getSaltvalue(30);
        try {
            String query = "select * from userDetail where username=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            UserDetail ud = null;
            while (rs.next()) {
                ud = (new UserDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDouble(13)));

                break;

            }

            if (ud != null) {
                Boolean status = passEncrypt.verifyUserPassword(password, ud.getUser_password(), ud.getSalt());
                if (status == true) {
                    ud.setUser_password(null);
                    ud.setSalt(null);
                    return ud;
                }
            }

            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    --------------------------------------------------------- GET USER -----------------------------------------------------
    public static UserDetail get(String id) {
        try {
            String query = "select * from userDetail where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            UserDetail ud = null;
            while (rs.next()) {
                ud = (new UserDetail(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getDouble(13)));
                return ud;
            }

            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Boolean getUsername(String username) {
        try {
            String query = "select * from userDetail where username=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }

            return false;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    --------------------------------------------------------- Update -------------------------------------------------------
    public static Boolean update(UserDetail userDetail) {

        try {
            String query = "update userDetail set phone=?,  avatar=?,  ggid=?, whislist_id=?, self_description=?, name=? where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, userDetail.getPhone());
            ps.setString(2, userDetail.getAvatar());
            ps.setString(3, userDetail.getGgid());
            ps.setString(4, userDetail.getWishlist_id());
            ps.setString(5, userDetail.getSelf_description());
            ps.setString(6, userDetail.getName());
            ps.setString(7, userDetail.getUser_id());

            int a = ps.executeUpdate();

            if (a == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //    --------------------------------------------------------- Change pasword -------------------------------------------------------
    public static Boolean changePassword(String newPassword, String user_id) {
        PassEncrypt passEncrypt = new PassEncrypt();
        String saltvalue = getSaltvalue(30);

        String encrypPassword = passEncrypt.generateSecurePassword(newPassword, saltvalue);

        try {
            String query = "update userDetail set password=?, salt=? where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, encrypPassword);
            ps.setString(2, saltvalue);
            ps.setString(3, user_id);

            int a = ps.executeUpdate();

            if (a == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    ----------------------------------------------------- DELETE USER -----------------------------------------------
    public static Boolean delete(String id) {

        try {
            String query = "delete from userDetail where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, id);

            int a = ps.executeUpdate();

            if (a == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    public static void main(String[] args) {
//        String data = "{deletes: [a, b, c]}";
//        HashMap<String, Object> map = new Gson().fromJson(data, HashMap.class);
//        System.out.println(map.get("deletes").getClass().getName());
//    }
    public static void main(String[] args) {

//        UserDetail ud = new UserDetailDAO().login("duclq", "12345zxc");
//
//        System.out.println(new UserDetailDAO().getUsername("newAcc1"));
        for (int i = 1; i <= 2; i++) {
            UUID uuid = UUID.randomUUID();

            System.out.println(uuid.toString());
        }

    }

}
