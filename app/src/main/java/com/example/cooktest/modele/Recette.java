package com.example.cooktest.modele;

public class Recette {
    private String libelleRecette, description, image, numRecette, numType;

    public Recette(String numRecette, String libelleRecette, String description, String image,String numType) {
        this.numRecette = numRecette;
        this.libelleRecette = libelleRecette;
        this.description = description;
        this.image = image;
        this.numType = numType;
    }

    public String getNumRecette() {
        return numRecette;
    }

    public String getNumType() {
        return numType;
    }

    public String getLibelleRecette() {
        return libelleRecette;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return numRecette + " " + libelleRecette;
    }

}