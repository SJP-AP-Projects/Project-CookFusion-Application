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

import java.util.ArrayList;

public class RecetteActivity extends AppCompatActivity {
    private RecetteDAO uneRecetteDAO;
    private Recette uneRecette;
    private ListView lV_recette;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette);

        uneRecetteDAO = new RecetteDAO(this);
        lV_recette = findViewById(R.id.listRecettes);
        ArrayList<Recette> collecRecette = uneRecetteDAO.recupRecettes();
        ArrayAdapter<Recette> monAdapteur = new ArrayAdapter(this,android.R.layout.simple_list_item_1, collecRecette);
        lV_recette.setAdapter(monAdapteur);

        lV_recette.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(RecetteActivity.this,DetailsRecetteActivity.class);
                uneRecette = (Recette) parent.getItemAtPosition(position);
                intent.putExtra("numRecette",uneRecette.getNumRecette());
                intent.putExtra("libelleRecette",uneRecette.getLibelleRecette());
                intent.putExtra("description",uneRecette.getDescription());
                intent.putExtra("image",uneRecette.getImage());
                intent.putExtra("numType",uneRecette.getNumType());

                Log.d("libelle dans lV_recette", uneRecette.getLibelleRecette());

                startActivity(intent);
            }
        });

        Button btn_ajoutRecette = findViewById(R.id.btn_ajoutRecette);

        btn_ajoutRecette.setOnClickListener(new View.OnClickListener(){

            public void onClick (View v){
                Intent intent = new Intent(RecetteActivity.this, AjoutRecetteActivity.class);
                startActivity(intent);
            }
        });

        Button btn_retour = findViewById(R.id.btn_retour);

        btn_retour.setOnClickListener(new View.OnClickListener(){

            public void onClick (View v){
                Intent intent = new Intent(com.example.cooktest.controleur.RecetteActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}