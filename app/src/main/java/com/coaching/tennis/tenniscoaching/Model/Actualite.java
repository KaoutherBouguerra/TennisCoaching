package com.coaching.tennis.tenniscoaching.Model;

/**
 * Created by macbook on 24/07/2018.
 */

public class Actualite {

    private String id_actualite;
    private String titre;
    private String description;
    private String id_image;
    private String url_image;
    private String id_client;
    private String urlIVideo;

    public Actualite(String id_actualite, String titre, String description, String id_image, String url_image, String id_client) {
        this.id_actualite = id_actualite;
        this.titre = titre;
        this.description = description;
        this.id_image = id_image;
        this.url_image = url_image;
        this.id_client = id_client;
    }

    public String getId_actualite() {
        return id_actualite;
    }

    public void setId_actualite(String id_actualite) {
        this.id_actualite = id_actualite;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId_image() {
        return id_image;
    }

    public void setId_image(String id_image) {
        this.id_image = id_image;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getUrlIVideo() {
        return urlIVideo;
    }

    public void setUrlIVideo(String urlIVideo) {
        this.urlIVideo = urlIVideo;
    }
}
