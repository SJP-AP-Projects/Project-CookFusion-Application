package com.example.cooktest.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class RecetteDAO {
    private static String base = "BDDCuisine";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;
    public RecetteDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public ArrayList<Recette> recupRecettes() {
        SQLiteDatabase bd = accesBD.getReadableDatabase();
        ArrayList<Recette> lesRecettes = new ArrayList<Recette>();
        String req = "SELECT * FROM Recette";
        Log.d("log", req);

        Cursor cursor = bd.rawQuery(req, null);

        while (cursor.moveToNext()) {
            String numRecette = cursor.getString(0);
            String libelleRecette = cursor.getString(1);
            String description = cursor.getString(2);
            String image = cursor.getString(3);
            String numType = cursor.getString(4);

            Recette recette = new Recette(numRecette, libelleRecette, description, image, numType);
            lesRecettes.add(recette);
        }
        return lesRecettes;
    }

    public int ajouterRecette(Recette uneRecette) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "INSERT INTO Recette " +
                "values('" + uneRecette.getNumRecette() + "','" +
                uneRecette.getLibelleRecette() + "','" +
                uneRecette.getDescription() + "','" +
                uneRecette.getImage() + "','" +
                uneRecette.getNumType() + "')";
        Log.d("log", req);
        try {
            bd.execSQL(req);
            bd.close();
            return 1;
        } catch (Exception e) {
            Log.e("log", "Erreur SQL : " + e.getMessage());
            bd.close();
            return 0;
        }
    }

    public int modifierRecette(Recette nvRecette, Recette ancRecette) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "UPDATE Recette " +
                "SET numRecette = '" + nvRecette.getNumRecette() +
                "', libelleRecette = '" + nvRecette.getLibelleRecette() +
                "', description = '" + nvRecette.getDescription() +
                "', image = '" + nvRecette.getImage() +
                "', numType = '" + nvRecette.getNumType() +
                "' WHERE numRecette ='" + ancRecette.getNumRecette() + "'";

        Log.d("log", req);

        try {
            bd.execSQL(req);
            bd.close();
            return 1;
        } catch (Exception e) {
            Log.e("log", "Erreur SQL : " + e.getMessage());
            bd.close();
            return 0;
        }
    }

    public Boolean supprimerRecette(Recette uneRecette){
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "DELETE FROM Recette " +
                " WHERE numRecette ='" + uneRecette.getNumRecette() + "'";

        Log.d("log", req);

        try {
            bd.execSQL(req);
            bd.close();
            return true;
        } catch (Exception e) {
            Log.e("log", "Erreur SQL : " + e.getMessage());
            bd.close();
            return false;
        }
    }
}
