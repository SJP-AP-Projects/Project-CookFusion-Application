package com.example.cooktest.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;


public class TypeDAO {
    private static String base = "BDDCuisine";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;

    public TypeDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public ArrayList<Type> recupTypes() {
        SQLiteDatabase bd = accesBD.getReadableDatabase();
        ArrayList<Type> lesTypes = new ArrayList<Type>();
        String req = "SELECT * FROM Type";
        Log.d("log", req);

        // Exécution de la requête
        Cursor cursor = bd.rawQuery(req, null);

        // Parcours des résultats de la requête
        while (cursor.moveToNext()) {
            // Récupération des données de chaque ligne
            String numType = cursor.getString(0);
            String lebelleType = cursor.getString(1);

            // Création d'un objet Visiteur et ajout à la liste
            Type type = new Type(numType, lebelleType);
            lesTypes.add(type);
        }
        return lesTypes;
    }

    public int ajouterType(Type unType) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "INSERT INTO Type " +
                "values('" + unType.getNumType() + "','" +
                unType.getLibelleType() + "')";
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

    public int modifierType(Type nvType, Type ancType) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "UPDATE Type " +
                "SET numType = '" + nvType.getNumType() +
                "', libelleType = '" + nvType.getLibelleType() +
                "' WHERE numType ='" + ancType.getNumType() + "'";

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

    public Boolean supprimerType(Type unType){
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "DELETE FROM Type " +
                " WHERE numType ='" + unType.getNumType() + "'";

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
