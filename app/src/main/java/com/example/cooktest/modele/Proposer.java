package com.example.cooktest.modele;

public class Proposer {
    private String numRecette, numSession;

    public Proposer(String numRecette, String numSession) {
        this.numRecette = numRecette;
        this.numSession = numSession;
    }

    public String getNumRecette() {
        return numRecette;
    }

    public String getNumSession() {
        return numSession;
    }
}
