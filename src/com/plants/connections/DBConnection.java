
package com.plants.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection conn;
    private String url;
    private String userName;
    private String userPass;
    private String driver;
    private String dbName;
    
    public DBConnection(){
        this.driver = "com.mysql.jdbc.Driver";
        this.userName = "root";
        this.userPass = "";
        this.dbName = "db_plant";
        this.url = "jdbc:mysql://localhost:3306/"+this.dbName;
    }
    
    public boolean DBConnect(){
        boolean connectionStatus = true;
        try{
            Class.forName(this.driver);
            this.conn = DriverManager.getConnection(this.url, this.userName,this.userPass);
            System.out.println("Connected to database");
        }
        catch(Exception er){
            connectionStatus = false;
            System.out.println("Error : "+er.getMessage());
        }
        return connectionStatus;
    }
    public Connection getConnectionVariable(){
        return this.conn;
    }
    public void DBClose(){
        try {
            this.conn.close();
            System.out.println("Database closed");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
