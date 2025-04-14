package com.example.cooktest.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class SessionDAO {
    private static String base = "BDDCuisine";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;
    public SessionDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public ArrayList<Session> recupSessions() {
        SQLiteDatabase bd = accesBD.getReadableDatabase();
        ArrayList<Session> lesSessions = new ArrayList<Session>();
        String req = "SELECT * FROM SessionCours";
        Log.d("log", req);

        Cursor cursor = bd.rawQuery(req, null);

        while (cursor.moveToNext()) {
            String numSession = cursor.getString(0);
            String nomSession = cursor.getString(1);
            String dateSession = cursor.getString(2);
            String heureDebut = cursor.getString(3);
            String heureFin = cursor.getString(4);
            String prix = cursor.getString(5);
            String nbPlaceMax = cursor.getString(6);
            String nbPlacePrise = cursor.getString(7);

            Session session = new Session(numSession, nomSession, dateSession, heureDebut, heureFin, prix, nbPlaceMax, nbPlacePrise);
            lesSessions.add(session);
            Log.d("logSession", session.toString());
        }

        return lesSessions;
    }

    public int ajouterSession(Session uneSession) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "INSERT INTO SessionCours " +
                "values('" + uneSession.getNumSession() + "','" +
                uneSession.getNomSession() + "','" +
                uneSession.getDateSession() + "','" +
                uneSession.getHeureDebut() + "','" +
                uneSession.getHeureFin() + "','" +
                uneSession.getPrix() + "','" +
                uneSession.getNbPlaceMax() + "','" +
                uneSession.getNbPlacePrise() + "')";
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

    public int modifierSession(Session nvSession, Session ancSession) {
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "UPDATE SessionCours " +
                "SET numSession = '" + nvSession.getNumSession() +
                "', nomSession = '" + nvSession.getNomSession() +
                "', dateSession = '" + nvSession.getDateSession() +
                "', heureDebut = '" + nvSession.getHeureDebut() +
                "', heureFin = '" + nvSession.getHeureFin() +
                "', prix = '" + nvSession.getPrix() +
                "', nbPlaceMax = '" + nvSession.getNbPlaceMax() +
                "', nbPlacePrise = '" + nvSession.getNbPlacePrise() +
                "' WHERE numSession ='" + ancSession.getNumSession() + "'";

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

    public Boolean supprimerSession(Session uneSession){
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        String req = "DELETE FROM SessionCours " +
                " WHERE numSession ='" + uneSession.getNumSession() + "'";

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