/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.dao;

import Context.DBContext;
import app.dto.UserDTO;
import dao.UserDetailDAO;
import java.sql.CallableStatement;
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
public class UserDAO {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    //    ---------------------------------------------------------Sign up ---------------------------------------------------------
    public String signUp(String username, String password, String email) {
        UUID uuid = UUID.randomUUID();
//         SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
//         Date date = new Date();  
        PassEncrypt passEncrypt = new PassEncrypt();
        String saltvalue = getSaltvalue(30);

        String encrypPassword = passEncrypt.generateSecurePassword(password, saltvalue);

        try {
            String query = "INSERT INTO userDetail VALUES (?, ?, ?, ?, null, null, null, 1, null, null, null, ?, null, null, null, GETDATE(), null)";
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
    public List<UserDTO> listAll() {
        try {
            String query = "select * from userDetail";

            conn = new DBContext().getConnection();

            ps = conn.prepareStatement(query);

            rs = ps.executeQuery();

            List<UserDTO> listUser = new ArrayList<>();

            while (rs.next()) {
                listUser.add(new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getDate(14), rs.getDate(15), rs.getString(16)));
            }

            return listUser;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //    ---------------------------------------------------------Log in ---------------------------------------------------------
    public UserDTO login(String username, String password) {
        PassEncrypt passEncrypt = new PassEncrypt();
//        String saltvalue = getSaltvalue(30);
        try {
            String query = "select * from userDetail where username=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, username);

            rs = ps.executeQuery();

            UserDTO ud = null;
            while (rs.next()) {
                ud = (new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getDate(14), rs.getDate(15), rs.getString(16)));

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
    public UserDTO getUser(String id) {
        try {
            String query = "select * from userDetail where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            UserDTO ud = null;
            while (rs.next()) {
                ud = (new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getInt(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getDate(14), rs.getDate(15), rs.getString(16)));

                return ud;
            }

            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean getUsername(String username) {
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
    public Boolean update(UserDTO userDetail) {

        try {
            String query = "update userDetail set phone=?,  avatar=?,  self_description=?, name=?, "
                    + "subname=?, dob=? where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);

            ps.setString(1, userDetail.getPhone());
            ps.setString(2, userDetail.getAvatar());
            ps.setString(3, userDetail.getSelf_description());
            ps.setString(4, userDetail.getName());

            ps.setString(5, userDetail.getSubname());
            ps.setString(6, userDetail.getDob().toString());

          
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
    public Boolean changePassword(String newPassword, String user_id) {
        PassEncrypt passEncrypt = new PassEncrypt();
        String saltvalue = getSaltvalue(30);

        String encrypPassword = passEncrypt.generateSecurePassword(newPassword, saltvalue);

        try {
            String query = "update userDetail set user_password=?, salt=? where user_id=?";
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
    
        //    --------------------------------------------------------- Current pasword -------------------------------------------------------
    public Boolean compareCurrentPassword(String userId, String currPassword) {
        PassEncrypt passEncrypt = new PassEncrypt();
        try {
            String query = "select * from userDetail where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, userId);
            rs = ps.executeQuery();
            

            String password = null;
            String salt = null;
            while (rs.next()) {
                password = rs.getString(3);
                salt = rs.getString(12);

                break;

            }
            
            System.out.println("password: " + password);
            System.out.println("salt: " + salt);
            
            Boolean status = passEncrypt.verifyUserPassword(currPassword, password, salt);
            
          
            

            return status;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

//    ----------------------------------------------------- DELETE USER -----------------------------------------------
    public Boolean delete(String id) {

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

//    ---------------------------- Get hotel owner -------------------------------------------
    public UserDTO getOwner(String id) {
        try {
            String query = "select * from userDetail where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();

            UserDTO ud = null;
            while (rs.next()) {
                ud = (new UserDTO(rs.getString("user_id"), rs.getString("username"), null, rs.getString("email"), rs.getString("phone"), rs.getString("name"),
                        rs.getString("avatar"), rs.getInt("role"), null, null, rs.getString("self_description"), null, null, null, null));
                ud.setSubname(rs.getString("subname"));
                ud.setSignAt(rs.getDate("signAt"));
                return ud;
            }

            return null;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
 //    ---------------------------- make host-------------------------------------------
    public boolean makeHosting(String id) {
        try {
            String query = "update userDetail set role=2 where user_id=?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            int a = ps.executeUpdate();

            if(a==1) {
                return true;
            } else {
                return false;
            }
            

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public String getOwnedHotelId(String ownerId) {
        CallableStatement cs = null;
        ResultSet rs = null;
        String hotelId = null;
        try {
            String query = "proc_getHotelIdFromOwnerId @ownerId = ?";
            conn = new DBContext().getConnection();
            cs = conn.prepareCall(query);
            cs.setString(1, ownerId);
            rs = cs.executeQuery();
            
            while(rs.next()) {
                hotelId = rs.getString("hotel_id");
            }
            
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hotelId;
    }
    
    
    public boolean updateBankingAccountNumber(String userId, String bankingAccountNumber) {
        try {
            String query = "select * from BankingAccount where bank_number = ?";
            conn = new DBContext().getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, bankingAccountNumber);
            rs = ps.executeQuery();

            if (rs.next()) {
                String bankId = rs.getString("bank_number");
                String updateQuery = "update userDetail set banking_account_id = ? where user_id = ?";
                PreparedStatement pss = conn.prepareStatement(updateQuery);
                pss.setString(1, bankId);
                pss.setString(2, userId);
                
                return true;
            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDetailDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static void main(String[] args) {

        boolean ud = new UserDAO().updateBankingAccountNumber(
                "asdasdasd",
                "asdc-dawd-2312d-dasd"
        ); 
//        List<UserDetail> list = new UserDetailDAO().listAll();
//        System.out.println(list);
//        for (int i = 1; i <= 10; i++) {
//            UUID uuid = UUID.randomUUID();
//
//            System.out.println(uuid.toString());
//        }

//    Boolean test = new UserDetailDAO().compareCurrentPassword("b955c796-027c-4e11-92ff-1bb942a102c8", "12345678");
//        System.out.println(test);

    }
}
