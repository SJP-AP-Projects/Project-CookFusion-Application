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
import com.example.cooktest.modele.Type;
import com.example.cooktest.modele.TypeDAO;

import java.util.ArrayList;

public class TypeActivity extends AppCompatActivity {
    private TypeDAO unTypeDAO;
    private Type unType;
    private ListView lV_type;
    private Button btn_retour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        unTypeDAO = new TypeDAO(this);
        lV_type = findViewById(R.id.listView);
        ArrayList<Type> collecType = unTypeDAO.recupTypes();
        ArrayAdapter<String> monAdapteur = new ArrayAdapter(this,android.R.layout.simple_list_item_1, collecType);
        lV_type.setAdapter(monAdapteur);

        lV_type.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(TypeActivity.this,DetailsTypeActivity.class);
                unType = (Type) parent.getItemAtPosition(position);
                intent.putExtra("numType",unType.getNumType());
                intent.putExtra("libelleType",unType.getLibelleType());

                Log.d("libelle dans lV_type", unType.getLibelleType());

                startActivity(intent);
            }
        });

        Button btn_AjoutType = findViewById(R.id.btn_ajouttype);

        btn_AjoutType.setOnClickListener(new View.OnClickListener(){

            public void onClick (View v){
                Intent intent = new Intent(TypeActivity.this, AjoutTypeActivity.class);
                startActivity(intent);
            }
        });

        Button btn_retour = findViewById(R.id.btn_retour);

        btn_retour.setOnClickListener(new View.OnClickListener(){

            public void onClick (View v){
                Intent intent = new Intent(TypeActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
    }
}