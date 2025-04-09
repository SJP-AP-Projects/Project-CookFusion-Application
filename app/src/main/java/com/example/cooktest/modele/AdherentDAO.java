package com.example.cooktest.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class AdherentDAO {
    private static String base = "BDDCuisine";
    private static int version = 1;
    private BD_SQLiteOpenHelper accesBD;

    public AdherentDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    public Boolean seConnecter(String loginAdherent, String mdpAdherent){
        SQLiteDatabase bd = accesBD.getReadableDatabase();
        String req = "SELECT * FROM Adherent WHERE roleAdherent = 'Admin'";
        Boolean resultat = false;
        Log.d("log", req);

        Cursor cursor = bd.rawQuery(req, null);

        while (cursor.moveToNext()) {
            int indexLogin = cursor.getColumnIndexOrThrow("loginAdherent");
            int indexMdp = cursor.getColumnIndexOrThrow("mdpAdherent");


            String loginAdherentEnreg = cursor.getString(indexLogin);
            String mdpAdherentEnreg = cursor.getString(indexMdp);

            if (loginAdherent.equals(loginAdherentEnreg) && mdpAdherent.equals(mdpAdherentEnreg)) {
                resultat = true;
            }
        }
        return resultat;
    }
}
