package com.example.application_base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ConsultarActivity extends AppCompatActivity {
    private Button btnConsultar;
    private ListView listProductos;
    private AdminstradorSQL objBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        inicializar();

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objBase.connectSQL();
                ArrayList<String> arrayProductos = objBase.consultar_tabla();
                visualizar_productos(arrayProductos);
                objBase.closeSQL();
            }
        });
    }

    private void inicializar(){
        btnConsultar = findViewById(R.id.btnConsultar);
        listProductos = findViewById(R.id.listProductos);
        objBase = new AdminstradorSQL();
    }

    private void visualizar_productos(ArrayList<String> arrayProductos){
        ArrayAdapter<String> adapterProductos = new ArrayAdapter<String>(ConsultarActivity.this,
                android.R.layout.simple_spinner_item, arrayProductos);
        listProductos.setAdapter(adapterProductos);
    }
}