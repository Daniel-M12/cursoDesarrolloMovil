package com.upc.examen_matos.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.upc.examen_matos.entidades.Plato;
import com.upc.examen_matos.util.PlatoDB;

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
}
