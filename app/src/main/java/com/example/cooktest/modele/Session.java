package com.example.cooktest.modele;

public class Session {
    private String numSession, nomSession, dateSession, heureDebut, heureFin, prix, nbPlaceMax, nbPlacePrise;

    public Session(String numSession, String nomSession, String dateSession, String heureDebut, String heureFin, String prix, String nbPlaceMax, String nbPlacePrise) {
        this.numSession = numSession;
        this.nomSession = nomSession;
        this.dateSession = dateSession;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.prix = prix;
        this.nbPlaceMax = nbPlaceMax;
        this.nbPlacePrise = nbPlacePrise;
    }

    public String getNumSession() {
        return numSession;
    }

    public String getNomSession() {
        return nomSession;
    }

    public String getDateSession() {
        return dateSession;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public String getPrix() {
        return prix;
    }

    public String getNbPlaceMax() {
        return nbPlaceMax;
    }

    public String getNbPlacePrise() {
        return nbPlacePrise;
    }

    @Override
    public String toString() {
        return  numSession + " " + nomSession ;
    }
}
