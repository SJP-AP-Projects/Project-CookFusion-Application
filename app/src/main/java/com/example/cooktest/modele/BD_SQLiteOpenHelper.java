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

            private String Utilisateur = "CREATE TABLE IF NOT EXISTS Utilisateur (\n" +
            "    id text PRIMARY KEY NOT NULL,\n" +
            "    nom text DEFAULT NULL,\n" +
            "    prenom text DEFAULT NULL,\n" +
            "    login text DEFAULT NULL,\n" +
            "    mdp text DEFAULT NULL,\n" +
            "    mail text DEFAULT NULL,\n" +
            "    role text DEFAULT NULL\n" +
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
            "    FOREIGN KEY(id) REFERENCES Utilisateur(id)\n" +
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
        db.execSQL(Utilisateur);
        db.execSQL(Proposer);
        db.execSQL(Participer);

        db.execSQL("INSERT INTO Type (numType, libelleType) VALUES \n" +
                "    ('1', 'Desert'),\n" +
                "    ('2', 'Plat italiano');");
        db.execSQL("INSERT INTO Recette (numRecette, libelleRecette, description, image, numType) VALUES \n" +
                "    ('1', 'Tarte aux pommes', 'Une délicieuse tarte aux pommes caramélisées.', 'tarte_pommes.jpg', '1'),\n" +
                "    ('2', 'Spaghetti Carbonara', 'Un plat italien avec des pâtes, des lardons et une sauce crémeuse.', 'carbonara.jpg', '2'),\n" +
                "    ('3', 'Mousse au chocolat', 'Un dessert gourmand à base de chocolat noir et d’œufs.', 'mousse_chocolat.jpg', '1');");



        Log.d("log","base de test cree");
    }
}
