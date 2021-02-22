
package com.plants.services;

import com.plants.connections.DBConnection;
import com.plants.pojo.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlantSearchService {
    private Plant plant;
    private DBConnection db;
    private Connection conn;
    private ResultSet rs;
    private PreparedStatement pst;
    
    public PlantSearchService(){
        this.plant = new Plant();
        this.db = new DBConnection();
    }
    
    public Plant searchPlant(String commonName){
        try{
            this.db.DBConnect();
            this.conn = this.db.getConnectionVariable();
            String SQL = "SELECT * FROM tbl_plants WHERE common_name=?";
            this.pst = this.conn.prepareStatement(SQL);
            this.pst.setString(1, commonName);
            this.rs = this.pst.executeQuery();
            System.out.println(this.rs);
            if(this.rs!=null){
                System.out.println("Not Null");
                this.rs.last();
                if(this.rs.getRow()>0){
                    this.plant = new Plant();
                    this.plant.setCommonName(this.rs.getString(1));
                    this.plant.setGenus(this.rs.getString(2));
                    this.plant.setSpecies(this.rs.getString(3));
                    this.plant.setSteam(this.rs.getString(4));
                    this.plant.setLeaf(this.rs.getString(5));
                    this.plant.setPhoto(this.rs.getBytes(6));
                    this.plant.setPhotoName(this.rs.getString(7));
                    this.plant.setLocation(this.rs.getString(8));
                }
                else{
                    this.plant = new Plant();
                    this.plant.setGenus("true");
                }
            }
            else{
                this.plant = null;
            }
        }catch(Exception er){
            er.printStackTrace();
            this.plant = null;
        }
        this.db.DBClose();
        return this.plant;
    }
}
