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


public class DetailsRecetteActivity extends AppCompatActivity {

    private String numRecette, libelleRecette, description, image, numType;
    private Button boutonModif, boutonSup, btn_retour;
    private RecetteDAO uneRecetteDAO;
    private Recette uneRecette;
    private EditText et_numRecette, et_libelleRecette, et_description, et_image, et_numType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_recette);

        et_numRecette = findViewById(R.id.numRecette);
        et_libelleRecette = findViewById(R.id.libelleRecette);
        et_description = findViewById(R.id.description);
        et_image = findViewById(R.id.image);
        et_numType = findViewById(R.id.numType);

        boutonModif = findViewById(R.id.btn_modifier);
        boutonSup = findViewById(R.id.btn_supprimer);
        btn_retour = findViewById(R.id.btn_retour);

        uneRecetteDAO = new RecetteDAO(this);
        Intent intent = getIntent();
        numRecette = intent.getStringExtra("numRecette");
        libelleRecette = intent.getStringExtra("libelleRecette");
        description = intent.getStringExtra("description");
        image = intent.getStringExtra("image");
        numType = intent.getStringExtra("numType");

        uneRecette = new Recette(numRecette, libelleRecette, description, image, numType);

        et_numRecette.setText(uneRecette.getNumRecette());
        et_libelleRecette.setText(uneRecette.getLibelleRecette());
        et_description.setText(uneRecette.getDescription());
        et_image.setText(uneRecette.getImage());
        et_numType.setText(uneRecette.getNumType());
        //Log.d(et_numRecette.setText(uneRecette.getNumRecette()),et_libelleRecette.setText(uneRecette.getLibelleRecette()));
        boutonModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifier();
                Intent intent = new Intent(DetailsRecetteActivity.this, RecetteActivity.class);
                startActivity(intent);
            }
        });

        boutonSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer();
                Intent intent = new Intent(DetailsRecetteActivity.this, RecetteActivity.class);
                startActivity(intent);

            }
        });

        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsRecetteActivity.this, RecetteActivity.class);
                startActivity(intent);

            }
        });
    }

    private void modifier(){
        Recette nvRecette = new Recette(
                et_numRecette.getText().toString(),
                et_libelleRecette.getText().toString(),
                et_description.getText().toString(),
                et_image.getText().toString(),
                et_numType.getText().toString()
        );

        uneRecetteDAO.modifierRecette(nvRecette,uneRecette);
    }

    private void supprimer(){
        if (uneRecetteDAO.supprimerRecette(uneRecette)) {
            Toast.makeText(this, "Suppression éffectuer", Toast.LENGTH_SHORT).show();
        }
    }
}