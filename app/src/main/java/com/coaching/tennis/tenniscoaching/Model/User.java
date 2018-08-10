package com.coaching.tennis.tenniscoaching.Model;

/**
 * Created by kaoutherbouguerra on 30/06/2018.
 */

public class User {

    private String id_client;
    private String login;
    private String password;
    private String nom;
    private String prenom;
    private String adresse;
    private String genre;
    private String date_naissance;
    private String activite_sportif;
    private String nom_pere;
    private String prenom_pere;
    private String email_pere;
    private String travail_pere;
    private String tel_pere;
    private String nom_mere;
    private String prenom_mere;
    private String email_mere;
    private String travail_mere;
    private String tel_mere;
    private String profile_img;

    public String getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }

    public User(String id_client, String login, String password, String nom, String prenom, String adresse, String genre, String date_naissance, String activite_sportif, String nom_pere, String prenom_pere, String email_pere, String travail_pere, String tel_pere, String nom_mere, String prenom_mere, String email_mere, String travail_mere, String tel_mere, String profile_img) {
        this.id_client = id_client;
        this.login = login;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.genre = genre;
        this.date_naissance = date_naissance;
        this.activite_sportif = activite_sportif;
        this.nom_pere = nom_pere;
        this.prenom_pere = prenom_pere;
        this.email_pere = email_pere;
        this.travail_pere = travail_pere;
        this.tel_pere = tel_pere;
        this.nom_mere = nom_mere;
        this.prenom_mere = prenom_mere;
        this.email_mere = email_mere;
        this.travail_mere = travail_mere;
        this.tel_mere = tel_mere;
        this.profile_img = profile_img;
    }


    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getActivite_sportif() {
        return activite_sportif;
    }

    public void setActivite_sportif(String activite_sportif) {
        this.activite_sportif = activite_sportif;
    }

    public String getNom_pere() {
        return nom_pere;
    }

    public void setNom_pere(String nom_pere) {
        this.nom_pere = nom_pere;
    }

    public String getPrenom_pere() {
        return prenom_pere;
    }

    public void setPrenom_pere(String prenom_pere) {
        this.prenom_pere = prenom_pere;
    }

    public String getEmail_pere() {
        return email_pere;
    }

    public void setEmail_pere(String email_pere) {
        this.email_pere = email_pere;
    }

    public String getTravail_pere() {
        return travail_pere;
    }

    public void setTravail_pere(String travail_pere) {
        this.travail_pere = travail_pere;
    }

    public String getTel_pere() {
        return tel_pere;
    }

    public void setTel_pere(String tel_pere) {
        this.tel_pere = tel_pere;
    }

    public String getNom_mere() {
        return nom_mere;
    }

    public void setNom_mere(String nom_mere) {
        this.nom_mere = nom_mere;
    }

    public String getPrenom_mere() {
        return prenom_mere;
    }

    public void setPrenom_mere(String prenom_mere) {
        this.prenom_mere = prenom_mere;
    }

    public String getEmail_mere() {
        return email_mere;
    }

    public void setEmail_mere(String email_mere) {
        this.email_mere = email_mere;
    }

    public String getTravail_mere() {
        return travail_mere;
    }

    public void setTravail_mere(String travail_mere) {
        this.travail_mere = travail_mere;
    }

    public String getTel_mere() {
        return tel_mere;
    }

    public void setTel_mere(String tel_mere) {
        this.tel_mere = tel_mere;
    }



    @Override
    public String toString() {
        return "User{" +
                "id_client='" + id_client + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", genre='" + genre + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                ", activite_sportif='" + activite_sportif + '\'' +
                ", nom_pere='" + nom_pere + '\'' +
                ", prenom_pere='" + prenom_pere + '\'' +
                ", email_pere='" + email_pere + '\'' +
                ", travail_pere='" + travail_pere + '\'' +
                ", tel_pere='" + tel_pere + '\'' +
                ", nom_mere='" + nom_mere + '\'' +
                ", prenom_mere='" + prenom_mere + '\'' +
                ", email_mere='" + email_mere + '\'' +
                ", travail_mere='" + travail_mere + '\'' +
                ", tel_mere='" + tel_mere + '\'' +
                ", profile_img='" + profile_img + '\'' +
                '}';
    }
}
