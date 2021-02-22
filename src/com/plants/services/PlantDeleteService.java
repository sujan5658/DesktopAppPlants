
package com.plants.services;

import com.plants.connections.DBConnection;
import com.plants.pojo.Plant;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class PlantDeleteService {
    private Plant plant;
    private DBConnection db;
    private Connection conn;
    private PreparedStatement pst;
   
    public PlantDeleteService(){
        this.plant = new Plant();
        this.db = new DBConnection();
    }
    
    public String deletePlant(Plant plant){
        String msg = "";
        try{
            if(this.db.DBConnect()){
                this.conn = this.db.getConnectionVariable();
                String SQL = "DELETE FROM tbl_plants WHERE common_name=?";
                this.pst = this.conn.prepareStatement(SQL);
                this.pst.setString(1,plant.getCommonName());
                this.pst.executeUpdate();
                this.db.DBClose();
                msg = "success";
                
                File file = new File("uploads"+File.separator+plant.getPhotoName());
                if(file.exists()){
                    file.delete();
                }
            }
            else{
                msg = "Please Start Database Server";
            }
        }catch(Exception er){
            er.printStackTrace();
            msg = "Internal Error..!!";
        }
        return msg;
    }
}
