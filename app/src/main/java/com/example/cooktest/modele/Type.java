package com.example.cooktest.modele;

public class Type {
    private String numType;
    private String libelleType;

    public Type(String numType, String libelleType) {
        this.numType = numType;
        this.libelleType = libelleType;
    }

    public String getNumType() {
        return numType;
    }

    public String getLibelleType() {
        return libelleType;
    }

    @Override
    public String toString() {
        return numType + " " + libelleType ;
    }
}
