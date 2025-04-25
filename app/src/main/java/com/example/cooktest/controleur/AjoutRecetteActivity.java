package com.example.cooktest.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cooktest.R;
import com.example.cooktest.modele.Recette;
import com.example.cooktest.modele.RecetteDAO;

public class AjoutRecetteActivity extends AppCompatActivity {
    private EditText et_numRecette,et_libelleRecette, et_description, et_image, et_numType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_recette);

        Button btn_ajouter = (Button) findViewById(R.id.btn_ajoutRecette);

        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouter();
                Intent intent = new Intent(AjoutRecetteActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        Button btn_retour = (Button) findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AjoutRecetteActivity.this, RecetteActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ajouter(){
        RecetteDAO uneRecetteDAO = new RecetteDAO(this);

        et_numRecette = findViewById(R.id.numRecette);
        et_libelleRecette = findViewById(R.id.libelleRecette);
        et_description = findViewById(R.id.description);
        et_image = findViewById(R.id.image);
        et_numType = findViewById(R.id.numType);

        Recette uneRecette = new Recette(
                et_numRecette.getText().toString(),
                et_libelleRecette.getText().toString(),
                et_description.getText().toString(),
                et_image.getText().toString(),
                et_numType.getText().toString()
        );

        if (uneRecetteDAO.ajouterRecette(uneRecette)==1){
            Toast.makeText(this,"Ajout éffectuer", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Erreur ajout (vérifier le numRecette)", Toast.LENGTH_SHORT).show();
        }

    }
}