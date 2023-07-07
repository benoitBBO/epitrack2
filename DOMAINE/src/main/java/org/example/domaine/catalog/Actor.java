package org.example.domaine.catalog;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String photoUrl;
    private String tmdbRef;

    public Actor(String name, String photoUrl, String tmdbRef) {
        this.name = name;
        this.photoUrl = photoUrl;
        this.tmdbRef = tmdbRef;
    }

    public Actor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getTmdbRef() {
        return tmdbRef;
    }

    public void setTmdbRef(String tmdbRef) {
        this.tmdbRef = tmdbRef;
    }
}
