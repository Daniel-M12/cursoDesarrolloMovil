package com.upc.examen_matos;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.upc.examen_matos.entidades.Plato;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.MiViewHolder> {
    Context contexto;
    private List<Plato> platos = new ArrayList<>();

    public Adaptador(Context contexto, List<Plato> platos) {
        this.contexto = contexto;
        this.platos = platos;
    }

    @NonNull
    @Override
    public Adaptador.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View vista = inflater.inflate(R.layout.fila,parent,false);
        return new MiViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull Adaptador.MiViewHolder holder, int position) {
        holder.filaNombre.setText(platos.get(position).getNombre() + "");
        holder.filaCategoria.setText(platos.get(position).getCategoria() + "");
        holder.filaPrecio.setText(platos.get(position).getPrecio() + "");
        holder.filaCantidad.setText(platos.get(position).getCantidad() + "");
        holder.filafecha.setText(platos.get(position).getFechaPedido() + "");
        holder.filaObservacion.setText(platos.get(position).getObservaciones() + "");

        holder.btnEditar.setOnClickListener(view -> {
            mostrarMensaje("Editar presionado");
        });

        holder.btnEliminar.setOnClickListener(view -> {
            mostrarMensaje("Eliminar presionado");
        });
    }

    private void mostrarMensaje(String mensaje) {
        AlertDialog.Builder ventana = new AlertDialog.Builder(contexto);
        ventana.setTitle("Mensaje informativo");
        ventana.setMessage(mensaje);
        ventana.setPositiveButton("Aceptar", (dialogInterface, which) -> {
            Intent intent = new Intent(contexto, ListarActivity.class);
            contexto.startActivity(intent);
        });
        ventana.create().show();
    }

    @Override
    public int getItemCount() {
        return platos.size();
    }

    public class MiViewHolder extends RecyclerView.ViewHolder{
        TextView filaNombre, filaCategoria, filaPrecio, filaCantidad, filafecha, filaObservacion;
        ImageButton btnEditar, btnEliminar;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            filaNombre = itemView.findViewById(R.id.filaNombre);
            filaCategoria = itemView.findViewById(R.id.filaCategoria);
            filaPrecio = itemView.findViewById(R.id.filaPrecio);
            filaCantidad = itemView.findViewById(R.id.filaCantidad);
            filafecha = itemView.findViewById(R.id.filafecha);
            filaObservacion = itemView.findViewById(R.id.filaObservacion);

            btnEditar = itemView.findViewById(R.id.btnEditar);
            btnEliminar = itemView.findViewById(R.id.btnEliminar);
        }
    }
}
