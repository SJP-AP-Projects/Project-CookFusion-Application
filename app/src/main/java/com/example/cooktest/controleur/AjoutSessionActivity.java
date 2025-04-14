package com.example.cooktest.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cooktest.R;
import com.example.cooktest.modele.Session;
import com.example.cooktest.modele.SessionDAO;

public class AjoutSessionActivity extends AppCompatActivity {
    private EditText et_numSession, et_nomSession, et_dateSession, et_heureDebut, et_heureFin, et_prix, et_nbPlaceMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_session);

        Button btn_ajouter = findViewById(R.id.btn_ajoutSession);

        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouter();
                Intent intent = new Intent(AjoutSessionActivity.this, SessionActivity.class);
                startActivity(intent);
            }
        });

        Button btn_retour = (Button) findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(AjoutSessionActivity.this, SessionActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ajouter() {
        SessionDAO sessionDAO = new SessionDAO(this);

        et_numSession = findViewById(R.id.numSession);
        et_nomSession = findViewById(R.id.nomSession);
        et_dateSession = findViewById(R.id.dateSession);
        et_heureDebut = findViewById(R.id.heureDebut);
        et_heureFin = findViewById(R.id.heureFin);
        et_prix = findViewById(R.id.prix);
        et_nbPlaceMax = findViewById(R.id.nbPlaceMax);

        Session session = new Session(
                et_numSession.getText().toString(),
                et_nomSession.getText().toString(),
                et_dateSession.getText().toString(),
                et_heureDebut.getText().toString(),
                et_heureFin.getText().toString(),
                et_prix.getText().toString(),
                et_nbPlaceMax.getText().toString(),
                "0"
        );

        if (sessionDAO.ajouterSession(session) == 1) {
            Toast.makeText(this, "Ajout effectué", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erreur ajout (vérifier le numSession)", Toast.LENGTH_SHORT).show();
        }
    }
}