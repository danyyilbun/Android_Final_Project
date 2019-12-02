package com.example.final_android_project.model;

public class Chessplayer {


    private int id;
    private String firstName;
    private String lastName;
    private int eloRating;
    private String dateOfBirth;
    private String dateOfDeath;
    private String yearsChampion;
    private String country;
    private byte [] image;

    public Chessplayer(int id,String firstName, String lastName, int eloRating, String dateOfBirth, String dateOfDeath, String yearsChampion, String country,  byte[] image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eloRating = eloRating;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.yearsChampion = yearsChampion;
        this.country = country;
        this.id = id;
        this.image = image;
    }

    public Chessplayer(String firstName, String lastName, int eloRating, String dateOfBirth, String dateOfDeath, String yearsChampion, String country, byte[] image) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.eloRating = eloRating;
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
        this.yearsChampion = yearsChampion;
        this.country = country;
        this.image = image;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getEloRating() {
        return eloRating;
    }

    public void setEloRating(int eloRating) {
        this.eloRating = eloRating;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfDeath() {
        return dateOfDeath;
    }

    public void setDateOfDeath(String dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getYearsChampion() {
        return yearsChampion;
    }

    public void setYearsChampion(String yearsChampion) {
        this.yearsChampion = yearsChampion;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

}
