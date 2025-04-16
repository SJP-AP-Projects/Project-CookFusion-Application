package com.example.cooktest.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cooktest.R;
import com.example.cooktest.modele.Recette;
import com.example.cooktest.modele.RecetteDAO;
import com.example.cooktest.modele.Session;
import com.example.cooktest.modele.SessionDAO;

public class DetailsSessionActivity extends AppCompatActivity {

    private String numSession, nomSession, dateSession, heureDebut, heureFin, prix, nbPlaceMax, nbPlacePrise;
    private Button btn_recettes,boutonModif, boutonSup, btn_retour;
    private SessionDAO uneSessionDAO;
    private Session uneSession;
    private EditText et_numSession, et_nomSession, et_dateSession, et_heureDebut, et_heureFin, et_prix, et_nbPlaceMax, et_nbPlacePrise;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_session);

        et_numSession = findViewById(R.id.numSession);
        et_nomSession = findViewById(R.id.nomSession);
        et_dateSession = findViewById(R.id.dateSession);
        et_heureDebut = findViewById(R.id.heureDebut);
        et_heureFin = findViewById(R.id.heureFin);
        et_prix = findViewById(R.id.prix);
        et_nbPlaceMax = findViewById(R.id.nbPlaceMax);
        et_nbPlacePrise = findViewById(R.id.nbPlacePrise);

        btn_recettes = findViewById(R.id.voirRecettes);
        boutonModif = findViewById(R.id.btn_modifier);
        boutonSup = findViewById(R.id.btn_supprimer);
        btn_retour = findViewById(R.id.btn_retour);

        uneSessionDAO = new SessionDAO(this);
        Intent intent = getIntent();
        numSession = intent.getStringExtra("numSession");
        nomSession = intent.getStringExtra("nomSession");
        dateSession = intent.getStringExtra("dateSession");
        heureDebut = intent.getStringExtra("heureDebut");
        heureFin = intent.getStringExtra("heureFin");
        prix = intent.getStringExtra("prix");
        nbPlaceMax = intent.getStringExtra("nbPlaceMax");
        nbPlacePrise = intent.getStringExtra("nbPlacePrise");

        uneSession = new Session(numSession, nomSession, dateSession, heureDebut, heureFin, prix, nbPlaceMax, nbPlacePrise);

        et_numSession.setText(uneSession.getNumSession());
        et_nomSession.setText(uneSession.getNomSession());
        et_dateSession.setText(uneSession.getDateSession());
        et_heureDebut.setText(uneSession.getHeureDebut());
        et_heureFin.setText(uneSession.getHeureFin());
        et_prix.setText(uneSession.getPrix());
        et_nbPlaceMax.setText(uneSession.getNbPlaceMax());
        et_nbPlacePrise.setText(uneSession.getNbPlacePrise());


        btn_recettes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsSessionActivity.this, RecetteProposerActivity.class);
                intent.putExtra("numSession", uneSession.getNumSession());
                startActivity(intent);
            }
        });

        boutonModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifier();
                Intent intent = new Intent(DetailsSessionActivity.this, SessionActivity.class);
                startActivity(intent);
            }
        });

        boutonSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer();
                Intent intent = new Intent(DetailsSessionActivity.this, SessionActivity.class);
                startActivity(intent);

            }
        });

        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsSessionActivity.this, SessionActivity.class);
                startActivity(intent);

            }
        });
    }

    private void modifier(){
        Session nvSession = new Session(
                et_numSession.getText().toString(),
                et_nomSession.getText().toString(),
                et_dateSession.getText().toString(),
                et_heureDebut.getText().toString(),
                et_heureFin.getText().toString(),
                et_prix.getText().toString(),
                et_nbPlaceMax.getText().toString(),
                et_nbPlacePrise.getText().toString()
        );

        uneSessionDAO.modifierSession(nvSession,uneSession);
    }

    private void supprimer(){
        if (uneSessionDAO.supprimerSession(uneSession)) {
            Toast.makeText(this, "Suppression éffectuer", Toast.LENGTH_SHORT).show();
        }
    }
}