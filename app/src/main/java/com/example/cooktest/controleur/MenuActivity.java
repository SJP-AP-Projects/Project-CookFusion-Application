package com.example.cooktest.controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.cooktest.R;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    private Button btn_recette;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*Button btn_recette = findViewById(R.id.btn_recette);

        btn_recette.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, RecetteActivity.class);
                startActivity(intent);
            }
        });

        Button btn_type = findViewById(R.id.btn_type);

        btn_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, TypeActivity.class);
                startActivity(intent);
            }
        });*/
    }
}
