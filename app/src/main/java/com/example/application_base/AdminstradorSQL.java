package com.example.application_base;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AdminstradorSQL {
    private Connection objConnection;
    private String url = "jdbc:mysql://35.232.112.230/db_products";
    private String user = "root";
    private String passw = "12345";
    private String table = "products";
    private String instructionSQL;

    public boolean connectSQL() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            objConnection = DriverManager.getConnection(url, user, passw);
            Log.i("MyTag", "Conexión exitosa");
            return true;
        }
        catch (Exception ex) {
            Log.e("MyTag", ex.toString());
            return false;
        }
    }

    public void closeSQL(){
        try {
            objConnection.close();
        }
        catch (Exception ex) {
            Log.e("MyTag", "Error en la conexión");
        }

    }
    
    public boolean insertar_producto(String name, int id, int cantidad, double precio) {
        try {
            instructionSQL = "INSERT INTO " + table + " VALUES('"+name+"', "+id+", "+cantidad+", "+precio+")";
            objConnection.prepareStatement(instructionSQL).execute();
            return true;
        }
        catch(Exception ex) {
            Log.e("MyTag", ex.toString());
            return false;
        }

    }

    public ArrayList<String> consultar_tabla (){
        try{
            instructionSQL = "SELECT * FROM " + table;
            ArrayList<String> arrayProductos = new ArrayList<>();
            ResultSet objResultado = objConnection.prepareStatement(instructionSQL).executeQuery();
            while (objResultado.next()) {
                String producto = objResultado.getString("name") + " " + objResultado.getInt("id")
                        + " " + objResultado.getInt("cantidad") + " " + objResultado.getDouble("precio");
                arrayProductos.add(producto);
                Log.i("MyTag", producto);
            }
            return arrayProductos;
        }
        catch (Exception ex) {
            Log.e("MyTag", ex.toString());
            return null;
        }
    }

    public ArrayList consultar_nombres() {
        try {
            instructionSQL = "SELECT name FROM " + table;
            ResultSet objResultado = objConnection.prepareStatement(instructionSQL).executeQuery();
            ArrayList<String> arrayNombres = new ArrayList<>();
            while(objResultado.next()) {
                arrayNombres.add(objResultado.getString("name"));
            }
            return  arrayNombres;
        }
        catch (Exception ex) {
            Log.e("MyTag", ex.toString());
            return null;
        }
    }

    public ArrayList<String> productado_filtrado (String nombre){
        try {
            instructionSQL = "SELECT * FROM " + table + " WHERE name='"+nombre+"'";
            ResultSet objResultado = objConnection.prepareStatement(instructionSQL).executeQuery();
            ArrayList<String> arrayDatosProducto = new ArrayList<>();
            objResultado.next();
            arrayDatosProducto.add(objResultado.getString("name"));
            arrayDatosProducto.add(String.valueOf(objResultado.getInt("id")));
            arrayDatosProducto.add(String.valueOf(objResultado.getInt("cantidad")));
            arrayDatosProducto.add(String.valueOf(objResultado.getDouble("precio")));
            return arrayDatosProducto;
        }
        catch (Exception ex) {
            Log.e("MyTag", ex.toString());
            return null;
        }
    }

    public boolean actualizar_registro(String nameSelected, String name, int id, int cantidad, double precio) {
        try {
            instructionSQL = "UPDATE " + table + " SET name='"+name+"', id="+id+", cantidad="+cantidad+", precio="+precio+" WHERE name='"+nameSelected+"'";
            objConnection.prepareStatement(instructionSQL).execute();
            return true;
        }
        catch (Exception ex) {
            Log.e("MyTag", ex.toString());
            return false;
        }
    }

}
