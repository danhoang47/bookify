/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author toten
 */
//private final String serverName = "LEDUC\\MAYAO";
//    private final String dbName = "bookify";
//    private final String portNumber = "1433";
//    private final String userID = "sa";
//    private final String password = "13072002";

public class DBContext {

    
    
    
    public Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber +";databaseName=" + dbName;
	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
	return DriverManager.getConnection(url, userID, password); 
    }
    
    private final String serverName = "LEDUC\\MAYAO";
    private final String dbName = "bookify";
    private final String portNumber = "1433";
    private final String userID = "sa";
    private final String password = "13072002";
    
    
    public static void main(String[] args) {
        try {
            Connection connection = new DBContext().getConnection();
            
            if(connection!=null) {
                   System.out.println("Connect successfully to dtb asdasdasd");
            } else {
                System.out.println("Connect failed");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
