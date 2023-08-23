package com.example.application_base;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtId;
    private EditText txtCantidad;
    private EditText txtPrecio;
    private Button btnAgregar;
    private ModelProductos objProducto;
    private AdminstradorSQL objBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        inicializar();

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txtNombre.getText().toString();
                String id = txtId.getText().toString();
                String cantidad = txtCantidad.getText().toString();
                String precio = txtPrecio.getText().toString();
                boolean confirmacion_conectar = validar_campos(nombre, id, cantidad, precio);
                if (confirmacion_conectar) {
                    establecer_objeto(nombre, Integer.parseInt(id), Integer.parseInt(cantidad), Double.parseDouble(precio));
                    objBase.connectSQL();
                    boolean confirmation_registrar = objBase.insertar_producto(objProducto.getName(), objProducto.getId(),
                            objProducto.getCantidad(), objProducto.getPrecio());
                    if(confirmation_registrar){
                        Toast.makeText(AgregarActivity.this, "Producto registrado", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(AgregarActivity.this, "Error registrando Producto", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void inicializar() {
        txtNombre = findViewById(R.id.txtNombre);
        txtId = findViewById(R.id.txtId);
        txtCantidad = findViewById(R.id.txtCantidad);
        txtPrecio = findViewById(R.id.txtPrecio);
        btnAgregar = findViewById(R.id.btnAgregar);
        objProducto = new ModelProductos();
        objBase = new AdminstradorSQL();
    }

    private boolean validar_campos(String nombre, String id, String cantidad, String precio) {
        boolean confirmation = true;

        if (nombre.equals("")) {
            confirmation = false;
            Toast.makeText(this, "Debe agregar un nombre", Toast.LENGTH_SHORT).show();
        }

        if (id.equals("")) {
            confirmation = false;
            Toast.makeText(this, "Debe agregar un id", Toast.LENGTH_SHORT).show();
        }

        if (cantidad.equals("")) {
            confirmation = false;
            Toast.makeText(this, "Debe agregar una cantidad", Toast.LENGTH_SHORT).show();
        }

        if (precio.equals("")) {
            confirmation = false;
            Toast.makeText(this, "Debe agregar un precio", Toast.LENGTH_SHORT).show();
        }
        return confirmation;
    }

    private void establecer_objeto(String nombre, int id, int cantidad, double precio){
        objProducto.setName(nombre);
        objProducto.setId(id);
        objProducto.setCantidad(cantidad);
        objProducto.setPrecio(precio);
    }
}