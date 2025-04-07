package com.example.cooktest.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cooktest.R;
import com.example.cooktest.modele.Type;
import com.example.cooktest.modele.TypeDAO;

public class DetailsTypeActivity extends AppCompatActivity {
    private String numType, libelleType;
    private Button boutonModif, boutonSup, btn_retour;
    private TypeDAO unTypeDAO;
    private Type unType;
    private EditText et_numType, et_libelleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_type);

        et_numType = findViewById(R.id.numType);
        et_libelleType = findViewById(R.id.libelleType);

        boutonModif = findViewById(R.id.btn_modifier);
        boutonSup = findViewById(R.id.btn_supprimer);
        btn_retour = findViewById(R.id.btn_retour);

        unTypeDAO = new TypeDAO(this);
        Intent intent = getIntent();
        numType = intent.getStringExtra("numType");
        libelleType = intent.getStringExtra("libelleType");

        unType = new Type(numType, libelleType);

        et_numType.setText(unType.getNumType());
        et_libelleType.setText(unType.getLibelleType());

        boutonModif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modifier();
                Intent intent = new Intent(DetailsTypeActivity.this, TypeActivity.class);
                startActivity(intent);
            }
        });

        boutonSup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supprimer();
                Intent intent = new Intent(DetailsTypeActivity.this, TypeActivity.class);
                startActivity(intent);

            }
        });

        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsTypeActivity.this, TypeActivity.class);
                startActivity(intent);

            }
        });
    }

    private void modifier(){
        Type nvType = new Type(
                et_numType.getText().toString(),
                et_libelleType.getText().toString()
        );

        unTypeDAO.modifierType(nvType,unType);
    }

    private void supprimer(){
        if (unTypeDAO.supprimerType(unType)) {
            Toast.makeText(this, "Suppression éffectuer", Toast.LENGTH_SHORT).show();
        }
    }
}