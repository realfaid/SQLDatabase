package com.example.sqlprojekt;

public class Recept {
    private String id,nazev,suroviny,postup;

    public Recept() {
    }

    public Recept(String id, String nazev, String suroviny, String postup) {
        this.id = id;
        this.nazev = nazev;
        this.suroviny = suroviny;
        this.postup = postup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getSuroviny() {
        return suroviny;
    }

    public void setSuroviny(String suroviny) {
        this.suroviny = suroviny;
    }

    public String getPostup() {
        return postup;
    }

    public void setPostup(String postup) {
        this.postup = postup;
    }
}
