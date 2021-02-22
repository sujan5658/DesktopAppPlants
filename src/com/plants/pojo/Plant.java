
package com.plants.pojo;

public class Plant {
    private String commonName;
    private String genus;
    private String species;
    private byte[] photo;
    private String steam;
    private String leaf;
    private String photoName;
    private String location;

    public Plant() {
        this.commonName = "";
        this.genus = "";
        this.species = "";
        this.photo = null;
        this.steam = "";
        this.leaf = "";
        this.photoName = "";
        this.location = "";
    }
    
    public Plant(String commonName, String genus, String species, byte[] photo, String steam, String leaf,String photoName,String location) {
        this.commonName = commonName;
        this.genus = genus;
        this.species = species;
        this.photo = photo;
        this.steam = steam;
        this.leaf = leaf;
        this.photoName = photoName;
        this.location = location;
    }
    
    public Plant(Plant plant) {
        this.commonName = plant.commonName;
        this.genus = plant.genus;
        this.species = plant.species;
        this.photo = plant.photo;
        this.steam = plant.steam;
        this.leaf = plant.leaf;
        this.photoName = plant.photoName;
        this.location = plant.location;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setSteam(String steam) {
        this.steam = steam;
    }

    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }
    
    public String getCommonName() {
        return commonName;
    }

    public String getGenus() {
        return genus;
    }

    public String getSpecies() {
        return species;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public String getSteam() {
        return steam;
    }

    public String getLeaf() {
        return leaf;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Plant{" + "commonName=" + commonName + ", genus=" + genus + ", species=" + species + ", photo=" + photo + ", steam=" + steam + ", leaf=" + leaf + ", photoName=" + photoName + ", location=" + location + '}';
    }
}
