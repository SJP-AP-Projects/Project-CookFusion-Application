package com.example.cooktest.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cooktest.R;
import com.example.cooktest.modele.Session;
import com.example.cooktest.modele.SessionDAO;

public class RecetteProposerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recette_proposer);


        Button btn_retour = (Button) findViewById(R.id.btn_retour);
        btn_retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecetteProposerActivity.this, SessionActivity.class);
                startActivity(intent);
            }
        });
    }
}