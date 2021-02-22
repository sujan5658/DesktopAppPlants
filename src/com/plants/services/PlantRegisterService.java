
package com.plants.services;

import com.plants.connections.DBConnection;
import com.plants.pojo.Plant;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlantRegisterService {
    private Plant plant,tmpPlant;
    private DBConnection db;
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    
    public PlantRegisterService(){
        this.tmpPlant = new Plant();
        this.db = new DBConnection();
    }
    public void setPlant(Plant plant){
        this.plant = plant;
        System.out.println(this.plant);
    }
    
    public String registerPlant(){
        String msg = "";
        if(this.db.DBConnect()){
            this.conn = this.db.getConnectionVariable();
            String SQL = "INSERT INTO tbl_plants(common_name,genus,species,steam,leaf,photo,photo_name,location) VALUE(?,?,?,?,?,?,?,?)";
            try {
                System.out.println(this.plant);
                this.pst = this.conn.prepareStatement(SQL);
                this.pst.setString(1,this.plant.getCommonName());
                this.pst.setString(2,this.plant.getGenus());
                this.pst.setString(3, this.plant.getSpecies());
                this.pst.setString(4, this.plant.getSteam());
                this.pst.setString(5, this.plant.getLeaf());
                this.pst.setBytes(6, this.plant.getPhoto());
                this.pst.setString(7, this.plant.getPhotoName());
                this.pst.setString(8,this.plant.getLocation());
                this.pst.execute();
                msg = "success";
                this.db.DBClose();
            } catch (SQLException ex) {
                msg = ex.getMessage();
                ex.printStackTrace();
            }
        }
        else{
            msg = "Please Start Database Server";
        }
        return msg;
    }
    
    public ArrayList<Plant> getRegisteredPlants(){
        ArrayList<Plant> plants = new ArrayList<Plant>();
        this.db.DBConnect();
        this.conn = this.db.getConnectionVariable();
        String SQL = "SELECT * FROM tbl_plants";
        try {
            this.st = this.conn.createStatement();
            this.rs = this.st.executeQuery(SQL);
            System.out.println("Query executed");
            while(rs.next()){
                this.tmpPlant = new Plant();
                this.tmpPlant.setCommonName(rs.getString(1));
                this.tmpPlant.setGenus(rs.getString(2));
                this.tmpPlant.setSpecies(rs.getString(3));
                this.tmpPlant.setSteam(rs.getString(4));
                this.tmpPlant.setLeaf(rs.getString(5));
                this.tmpPlant.setPhoto(rs.getString(6).getBytes());
                this.tmpPlant.setPhotoName(rs.getString(7));
                this.tmpPlant.setLocation(rs.getString(8));
                plants.add(this.tmpPlant);
            }
            System.out.println("result taken");
            this.db.DBClose();
        } catch (SQLException ex) {
            plants = null;
            ex.printStackTrace();
        }
        return plants;
    }
}
