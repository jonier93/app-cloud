package com.example.application_base;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnAgregar;
    private Button btnConsultar;
    private Button btnEditar;
    private ImageView imgPortada;
    private Animation objAnimacion;
    private Intent objIntent;
    private AdminstradorSQL objBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();

        objAnimacion = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animacion);
        imgPortada.startAnimation(objAnimacion);

        boolean confirmacion = objBase.connectSQL();
        if(confirmacion){
            btnAgregar.setEnabled(true);
            btnConsultar.setEnabled(true);
        }

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objBase.closeSQL();
                objIntent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivity(objIntent);
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objIntent = new Intent(MainActivity.this, ConsultarActivity.class);
                startActivity(objIntent);
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objIntent = new Intent(MainActivity.this, EditarProducto.class);
                startActivity(objIntent);
            }
        });

    }

    private void inicializar() {
        btnAgregar = findViewById(R.id.btnAgregarMain);
        btnConsultar = findViewById(R.id.btnConsultarMain);
        btnEditar = findViewById(R.id.btnEditar);
        imgPortada = findViewById(R.id.imgPortada);
        objBase = new AdminstradorSQL();
    }

}