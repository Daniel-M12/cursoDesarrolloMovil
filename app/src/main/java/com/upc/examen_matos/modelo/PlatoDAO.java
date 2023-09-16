package com.upc.examen_matos.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.upc.examen_matos.entidades.Plato;
import com.upc.examen_matos.util.PlatoDB;

import java.util.ArrayList;
import java.util.List;

public class PlatoDAO {
    SQLiteDatabase db;
    Context context;
    PlatoDB platoDB;

    public PlatoDAO(Context context) {
        this.platoDB = new PlatoDB(context);
        this.context = context;
    }
    public void permisoDB(){
        this.db = this.platoDB.getWritableDatabase();
    }

    public String registrarPlato(Plato plato){
        String respuesta = "";

        try {
            ContentValues values = new ContentValues();
            values.put("nombre",plato.getNombre());
            values.put("categoria",plato.getCategoria());
            values.put("observaciones",plato.getObservaciones());
            values.put("precio",plato.getPrecio());
            values.put("cantidad",plato.getCantidad());
            values.put("fechaPedido", plato.getFechaPedido());

            long r = db.insert("platos", null, values);

            if (r == -1){
                respuesta = "Error al registrar plato";
            } else {
                respuesta = "Se registró correctamente";
            }

        } catch (Exception e) {
            respuesta = "Ocurrió un error al registrar";
        }

        return respuesta;
    }

    public List<Plato> listarPlatos(){
        List<Plato> platos = new ArrayList<>();

        try {
            Cursor c = db.rawQuery("SELECT * FROM platos;", null);
            while (c.moveToNext()){
                platos.add(new Plato(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getDouble(4),
                        c.getInt(5),
                        c.getString(6)
                ));
            }
        } catch (Exception e) {
            Log.d("==>", e.toString());
        }

        return platos;
    }

    public String editarPlato(Plato plato){
        String respuesta = "";

        try {
            ContentValues values = new ContentValues();
            values.put("nombre",plato.getNombre());
            values.put("categoria",plato.getCategoria());
            values.put("observaciones",plato.getObservaciones());
            values.put("precio",plato.getPrecio());
            values.put("cantidad",plato.getCantidad());
            values.put("fechaPedido", plato.getFechaPedido());

            long r = db.update("platos", values, "id=" + plato.getId(), null);

            if (r == -1){
                respuesta = "Error al modificar plato";
            } else {
                respuesta = "Se modificó correctamente";
            }

        } catch (Exception e) {
            respuesta = "Ocurrió un error al modificar: " + e.getMessage();
        }

        return respuesta;
    }

    public String eliminarPlato(int id){
        String respuesta= "";

        try {

            long r = db.delete("platos", "id=" + id, null);

            if (r == -1){
                respuesta = "Error al eliminar plato";
            } else {
                respuesta = "Se eliminó correctamente";
            }

        } catch (Exception e) {
            respuesta = "Ocurrió un error al eliminar: " + e.getMessage();
        }

        return respuesta;
    }
}
