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

public class AjoutTypeActivity extends AppCompatActivity {

    private EditText et_numType,et_libelleType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_type);

        Button btn_ajouter = (Button) findViewById(R.id.btn_ajouter);

        btn_ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                ajouter();
                Intent intent = new Intent(AjoutTypeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });

        Button btn_retour = (Button) findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(AjoutTypeActivity.this, TypeActivity.class);
                startActivity(intent);
            }
        });

    }

    private void ajouter(){
        TypeDAO unTypeDAO = new TypeDAO(this);

        et_numType = findViewById(R.id.numType);
        et_libelleType = findViewById(R.id.libelleType);

        Type unType = new Type(
                et_numType.getText().toString(),
                et_libelleType.getText().toString()
                );

        if (unTypeDAO.ajouterType(unType)==1){
            Toast.makeText(this,"Ajout éffectuer", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Erreur ajout (vérifier le numType)", Toast.LENGTH_SHORT).show();
        }

    }
}