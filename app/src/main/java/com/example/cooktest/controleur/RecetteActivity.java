package com.example.cooktest.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.cooktest.R;
import com.example.cooktest.modele.Recette;
import com.example.cooktest.modele.RecetteDAO;

import java.util.ArrayList;

public class RecetteActivity extends AppCompatActivity {
        private RecetteDAO uneRecetteDAO;
        private ListView lV_recette;
        private Button btn_retour;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_recette);

            uneRecetteDAO = new RecetteDAO(this);
            lV_recette = findViewById(R.id.listRecettes);
            ArrayList<Recette> collecRecette = uneRecetteDAO.recupRecettes();
            ArrayAdapter<Recette> monAdapteur = new ArrayAdapter(this,android.R.layout.simple_list_item_1, collecRecette);
            lV_recette.setAdapter(monAdapteur);

            Button btn_retour = findViewById(R.id.btn_AjoutType);

            btn_retour.setOnClickListener(new View.OnClickListener(){

                public void onClick (View v){
                    Intent intent = new Intent(com.example.cooktest.controleur.RecetteActivity.this, MenuActivity.class);
                    startActivity(intent);
                }
            });
        }
    }