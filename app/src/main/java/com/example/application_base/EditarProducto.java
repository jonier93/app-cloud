package com.example.application_base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class EditarProducto extends AppCompatActivity {
    private Spinner listProductos;
    private EditText txtNombre;
    private EditText txtId;
    private EditText txtCantidad;
    private EditText txtPrecio;
    private Button btnConsultar;
    private Button btnActualizar;
    private AdminstradorSQL objBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_producto);
        inicializar();
        objBase.connectSQL();
        ArrayList<String> arrayNombres = objBase.consultar_nombres();
        imprimir_nombres(arrayNombres);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayDatosProducto = objBase.productado_filtrado(
                        listProductos.getSelectedItem().toString());
                desplegar_datos(arrayDatosProducto);
                activar_botones();
            }
        });

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objBase.actualizar_registro(listProductos.getSelectedItem().toString(), txtNombre.getText().toString(), Integer.parseInt(txtId.getText().toString()),
                        Integer.parseInt(txtCantidad.getText().toString()), Double.parseDouble(txtPrecio.getText().toString()));
                Toast.makeText(EditarProducto.this, "Registro actualizado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void inicializar(){
        listProductos = findViewById(R.id.spinnerNombres);
        txtNombre = findViewById(R.id.txtNombreEditar);
        txtId = findViewById(R.id.txtIdEditar);
        txtCantidad = findViewById(R.id.txtCantidadEditar);
        txtPrecio = findViewById(R.id.txtPrecioEditar);
        btnConsultar = findViewById(R.id.btnConsultarEditar);
        btnActualizar = findViewById(R.id.btnActualizar);
        objBase = new AdminstradorSQL();
    }

    private void imprimir_nombres(ArrayList<String> arrayNombres) {
        ArrayAdapter<String> adapterNombres = new ArrayAdapter<>(EditarProducto.this,
                android.R.layout.simple_spinner_item, arrayNombres);
        listProductos.setAdapter(adapterNombres);
    }

    private void desplegar_datos(ArrayList<String> arrayDatosProducto) {
        txtNombre.setText(arrayDatosProducto.get(0));
        txtId.setText(arrayDatosProducto.get(1));
        txtCantidad.setText(arrayDatosProducto.get(2));
        txtPrecio.setText(arrayDatosProducto.get(3));
    }

    private void activar_botones() {
        txtNombre.setEnabled(true);
        txtId.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtPrecio.setEnabled(true);
        btnActualizar.setEnabled(true);
    }
}