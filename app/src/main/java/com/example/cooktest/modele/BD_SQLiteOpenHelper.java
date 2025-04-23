package com.example.cooktest.modele;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BD_SQLiteOpenHelper extends SQLiteOpenHelper {
    private String SessionCours = "CREATE TABLE IF NOT EXISTS SessionCours (\n" +
            "    numSession text PRIMARY KEY NOT NULL,\n" +
            "    nomSession text DEFAULT NULL,\n" +
            "    dateSession text DEFAULT NULL,\n" +
            "    heureDebut text DEFAULT NULL,\n" +
            "    heureFin text DEFAULT NULL,\n" +
            "    prix text DEFAULT NULL,\n" +
            "    nbPlaceMax text DEFAULT NULL,\n" +
            "    nbPlacePrise text DEFAULT NULL\n" +
            ");";


            private String Type = "CREATE TABLE IF NOT EXISTS Type (\n" +
            "    numType text PRIMARY KEY NOT NULL,\n" +
            "    libelleType text DEFAULT NULL\n" +
            ");";

            private String Adherent = "CREATE TABLE IF NOT EXISTS Adherent (\n" +
            "     idAdherent  text PRIMARY KEY NOT NULL,\n" +
            "    nomAdherent text DEFAULT NULL,\n" +
            "    prenomAdherent text DEFAULT NULL,\n" +
            "    loginAdherent text DEFAULT NULL,\n" +
            "    mdpAdherent text DEFAULT NULL,\n" +
            "    mailAdherent text DEFAULT NULL,\n" +
            "    roleAdherent text DEFAULT NULL\n" +
            ");";

            private String Recette = "CREATE TABLE IF NOT EXISTS Recette (\n" +
            "    numRecette text PRIMARY KEY NOT NULL,\n" +
            "    libelleRecette text DEFAULT NULL,\n" +
            "    description text DEFAULT NULL,\n" +
            "    image text DEFAULT NULL,\n" +
            "    numType text NOT NULL,\n" +
            "    FOREIGN KEY(numType) REFERENCES Type(numType)\n" +
            ");";

            private String Proposer = "CREATE TABLE IF NOT EXISTS Proposer (\n" +
            "    numRecette text NOT NULL,\n" +
            "    numSession text NOT NULL,\n" +
            "    PRIMARY KEY(numRecette, numSession),\n" +
            "    FOREIGN KEY(numRecette) REFERENCES Recette(numRecette),\n" +
            "    FOREIGN KEY(numSession) REFERENCES SessionCours(numSession)\n" +
            ");";

            private String Participer = "CREATE TABLE IF NOT EXISTS Participer (\n" +
            "    numSession text NOT NULL,\n" +
            "    id text NOT NULL,\n" +
            "    Commentaire text DEFAULT NULL,\n" +
            "    PRIMARY KEY(numSession, id),\n" +
            "    FOREIGN KEY(numSession) REFERENCES SessionCours(numSession),\n" +
            "    FOREIGN KEY(id) REFERENCES Adherent(idAdherent)\n" +
            ");";

    public BD_SQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Type);
        db.execSQL(Recette);
        db.execSQL(SessionCours);
        db.execSQL(Adherent);
        db.execSQL(Proposer);
        db.execSQL(Participer);

        db.execSQL("INSERT INTO Type (numType, libelleType) VALUES \n" +
                "    ('1', 'Desert'),\n" +
                "    ('2', 'Plat italiano');");
        db.execSQL("INSERT INTO Recette (numRecette, libelleRecette, description, image, numType) VALUES \n" +
                "    ('1', 'Tarte aux pommes', 'Une délicieuse tarte aux pommes caramélisées.', 'tarte_pommes.jpg', '1'),\n" +
                "    ('2', 'Spaghetti Carbonara', 'Un plat italien avec des pâtes, des lardons et une sauce crémeuse.', 'carbonara.jpg', '2'),\n" +
                "    ('3', 'Mousse au chocolat', 'Un dessert gourmand à base de chocolat noir et d’œufs.', 'mousse_chocolat.jpg', '1');");
        db.execSQL("INSERT INTO SessionCours (numSession, nomSession, dateSession, heureDebut, heureFin, prix, nbPlaceMax, nbPlacePrise) VALUES \n" +
                "    ('1', 'Italien', '15/02', '18h00', '20h00', '200', '20', '17'),\n" +
                "    ('2', 'Français', '17/08', '19h00', '21h00', '250', '20', '14');");
        db.execSQL("INSERT INTO Proposer (numRecette, numSession) VALUES \n" +
                "    ('1', '2'),\n" +
                "    ('2', '1'),\n" +
                "    ('3', '2');");
        db.execSQL("INSERT INTO Adherent (idAdherent, nomAdherent, prenomAdherent, loginAdherent, mdpAdherent, mailAdherent, roleAdherent) VALUES \n" +
                "    ('1', 'Riviere', 'Fabio', 'Riviere', 'Gourmande', 'f.riviere@gmail.com', 'Admin'),\n" +
                "    ('2', 'test', 'test', 'test', 'test', 'test', 'Admin');");



        Log.d("log","base de test cree");
    }
}
