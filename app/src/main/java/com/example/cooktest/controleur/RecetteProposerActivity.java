package com.example.cooktest.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.cooktest.R;
import com.example.cooktest.modele.Proposer;
import com.example.cooktest.modele.ProposerDAO;
import com.example.cooktest.modele.Recette;
import com.example.cooktest.modele.RecetteDAO;
import com.example.cooktest.modele.Session;
import com.example.cooktest.modele.SessionDAO;

import java.util.ArrayList;

public class RecetteProposerActivity extends AppCompatActivity {
    private Spinner spinner_recette;
    private Button btn_retour, btn_valider, btn_ajouter;
    private ListView lV_recetteProposee;
    private RecetteDAO uneRecetteDAO;
    private Recette uneRecette;
    private Proposer uneProposition;
    private ProposerDAO unePropositionDAO;
    private String numSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette_proposer);

        uneRecetteDAO = new RecetteDAO(this);
        unePropositionDAO = new ProposerDAO(this);
        spinner_recette = findViewById(R.id.spinner_recette);
        ArrayList<Recette> collecRecette = uneRecetteDAO.recupRecettes();
        ArrayAdapter<String> monAdapteur = new ArrayAdapter(this,android.R.layout.simple_spinner_item, collecRecette);
        spinner_recette.setAdapter(monAdapteur);

        Intent intent = getIntent();
        numSession = intent.getStringExtra("numSession");

        spinner_recette.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                uneRecette = (Recette) spinner_recette.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        uneRecetteDAO = new RecetteDAO(this);
        lV_recetteProposee = findViewById(R.id.lV_recetteProposee);
        ArrayList<Recette> collecRecetteProposee = unePropositionDAO.recupRecetteProposerSession(numSession);
        ArrayAdapter<Recette> monAdapteur2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, collecRecetteProposee);
        lV_recetteProposee.setAdapter(monAdapteur2);

        lV_recetteProposee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Recette recetteASupprimer = (Recette) parent.getItemAtPosition(position);

                new android.app.AlertDialog.Builder(RecetteProposerActivity.this)
                        .setTitle("Suppression")
                        .setMessage("Voulez-vous supprimer cette recette proposée ?")
                        .setPositiveButton("Oui", (dialog, which) -> {
                            // Supprime la recette de la session
                            unePropositionDAO.supprimerRecetteProposer(recetteASupprimer.getNumRecette(), numSession);

                            // Met à jour la liste
                            modifier();

                            Toast.makeText(RecetteProposerActivity.this, "Recette supprimée", Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Non", null)
                        .show();
            }
        });


        Button btn_retour = (Button) findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecetteProposerActivity.this, SessionActivity.class);
                startActivity(intent);
            }
        });

        Button btn_ajouter = (Button) findViewById(R.id.btn_ajouter);
        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unePropositionDAO.ajouterRecetteProposer(uneRecette,numSession);
                modifier();
            }
        });
    }

    private void modifier(){
        ArrayList<Recette> collecRecetteProposee = unePropositionDAO.recupRecetteProposerSession(numSession);
        ArrayAdapter<Recette> monAdapteur2 = new ArrayAdapter(this,android.R.layout.simple_list_item_1, collecRecetteProposee);
        lV_recetteProposee.setAdapter(monAdapteur2);
    }
}