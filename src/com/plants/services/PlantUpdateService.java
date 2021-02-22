/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.plants.services;

import com.plants.connections.DBConnection;
import com.plants.pojo.Plant;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PlantUpdateService {
    private Plant oldPlant,newPlant;
    private DBConnection db;
    private Connection conn;
    private PreparedStatement pst;
    private ResultSet rs;
    private Statement st;
    
    public PlantUpdateService(){
        this.oldPlant = new Plant();
        this.newPlant = new Plant();
        this.db = new DBConnection();
    }
    public void setPlant(Plant oldPlant,Plant newPlant){
        this.oldPlant = oldPlant;
        this.newPlant = newPlant;
    }
    public String updatePlant(){
        String msg = "";
        if(this.db.DBConnect()){
            this.conn = this.db.getConnectionVariable();
            String SQL = "UPDATE tbl_plants SET common_name=?,genus=?,species=?,steam=?,leaf=?,photo=?,photo_name=?,location=? WHERE common_name=?";
            try{
                this.pst = this.conn.prepareStatement(SQL);
                this.pst.setString(1,this.newPlant.getCommonName());
                this.pst.setString(2, this.newPlant.getGenus());
                this.pst.setString(3, this.newPlant.getSpecies());
                this.pst.setString(4, this.newPlant.getSteam());
                this.pst.setString(5, this.newPlant.getLeaf());
                this.pst.setBytes(6, this.newPlant.getPhoto());
                this.pst.setString(7, this.newPlant.getPhotoName());
                this.pst.setString(8,this.newPlant.getLocation());
                this.pst.setString(9,this.oldPlant.getCommonName());
                
                this.pst.executeUpdate();
                this.db.DBClose();
                msg = "success";
            }catch(Exception er){
                er.printStackTrace();
                msg = "Internal Error .!!!!!";
            }
        }
        else{
            msg = "Please Start Database Server";
        }
        return msg;
    }
}
