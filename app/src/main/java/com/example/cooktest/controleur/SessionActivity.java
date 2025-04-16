package com.example.cooktest.controleur;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cooktest.R;
import com.example.cooktest.modele.Recette;
import com.example.cooktest.modele.RecetteDAO;
import com.example.cooktest.modele.Session;
import com.example.cooktest.modele.SessionDAO;

import java.util.ArrayList;

public class SessionActivity extends AppCompatActivity {
    private SessionDAO uneSessionDAO;
    private Session uneSession;
    private ListView lV_session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session); // Assure-toi que ce layout existe bien !

        uneSessionDAO = new SessionDAO(this);
        lV_session = findViewById(R.id.listSessions); // Assure-toi que l'id est correct dans activity_session.xml
        ArrayList<Session> collecSession = uneSessionDAO.recupSessions();

        ArrayAdapter<Session> monAdapteur = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, collecSession);
        lV_session.setAdapter(monAdapteur);

        lV_session.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SessionActivity.this, DetailsSessionActivity.class);
                uneSession = (Session) parent.getItemAtPosition(position);

                intent.putExtra("numSession", uneSession.getNumSession());
                intent.putExtra("nomSession", uneSession.getNomSession());
                intent.putExtra("dateSession", uneSession.getDateSession());
                intent.putExtra("heureDebut", uneSession.getHeureDebut());
                intent.putExtra("heureFin", uneSession.getHeureFin());
                intent.putExtra("prix", uneSession.getPrix());
                intent.putExtra("nbPlaceMax", uneSession.getNbPlaceMax());
                intent.putExtra("nbPlacePrise", uneSession.getNbPlacePrise());

                Log.d("libelle dans lV_session", uneSession.getNomSession());

                startActivity(intent);
            }
        });

        Button btn_ajoutSession = findViewById(R.id.btn_ajoutSession);

        btn_ajoutSession.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, AjoutSessionActivity.class);
                startActivity(intent);
            }
        });

        Button btn_retour = findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(SessionActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}