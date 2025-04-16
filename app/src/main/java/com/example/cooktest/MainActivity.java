package com.example.cooktest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cooktest.controleur.MenuActivity;
import com.example.cooktest.modele.Adherent;
import com.example.cooktest.modele.AdherentDAO;

public class MainActivity extends AppCompatActivity {
    private Button btn_connexion;
    private EditText et_loginAdherent,et_mdpAdherent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_loginAdherent = findViewById(R.id.et_loginAdherent);
        et_mdpAdherent = findViewById(R.id.et_mdpAdherent);

        AdherentDAO unAdherentDAO = new AdherentDAO(this);

        Button btn_connexion = findViewById(R.id.btn_connexion);

        btn_connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("authentification",et_loginAdherent.getText().toString() + et_loginAdherent.getText().toString());
                if(unAdherentDAO.seConnecter(et_loginAdherent.getText().toString(),et_loginAdherent.getText().toString())){
                    Log.d("authentification2","je rentre");
                    Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(v.getContext(), "Erreur authentification", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}