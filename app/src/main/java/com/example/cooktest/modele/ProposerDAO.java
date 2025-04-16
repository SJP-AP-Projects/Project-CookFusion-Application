package com.example.cooktest.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class ProposerDAO {
    private static String base = "BDDCuisine";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;
    public ProposerDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public ArrayList<Recette> recupRecetteProposerSession(String numSession) {
        SQLiteDatabase bd = accesBD.getReadableDatabase();
        ArrayList<Recette> lesRecettesProposees = new ArrayList<>();

        // Requête SQL avec des paramètres liés pour éviter les injections SQL
        String req = "SELECT Recette.* FROM Recette " +
                "INNER JOIN Proposer ON Recette.numRecette = Proposer.numRecette " +
                "WHERE Proposer.numSession = ?";
        Log.d("log", req);

        // Exécution de la requête avec un paramètre lié
        Cursor cursor = bd.rawQuery(req, new String[]{numSession});

        // Parcours des résultats du curseur
        while (cursor.moveToNext()) {
            String numRecette = cursor.getString(cursor.getColumnIndexOrThrow("numRecette"));
            String libelleRecette = cursor.getString(cursor.getColumnIndexOrThrow("libelleRecette"));
            String description = cursor.getString(cursor.getColumnIndexOrThrow("description"));
            String image = cursor.getString(cursor.getColumnIndexOrThrow("image"));
            String numType = cursor.getString(cursor.getColumnIndexOrThrow("numType"));

            // Création de l'objet Recette
            Recette recette = new Recette(numRecette, libelleRecette, description, image, numType);
            lesRecettesProposees.add(recette);
        }

        // Fermeture du curseur pour libérer les ressources
        cursor.close();

        // Retourne la liste des recettes
        return lesRecettesProposees;
    }

    public int ajouterRecetteProposer(Recette uneRecette, String numSession) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "INSERT INTO Proposer " +
                "values('" + uneRecette.getNumRecette() + "','" +
                numSession + "')";
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

    public void supprimerRecetteProposer(String numRecette, String numSession) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        bd.delete("Proposer", "numRecette = ? AND numSession = ?", new String[]{String.valueOf(numRecette), numSession});
        bd.close();
    }

}
