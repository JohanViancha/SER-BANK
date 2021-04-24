package com.example.ser_bank.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ser_bank.Models.Transaccion;
import com.example.ser_bank.R;

import java.util.List;

public class ListAdapterTransaccion extends RecyclerView.Adapter<ListAdapterTransaccion.ViewHolder> {

    private List<Transaccion> transacciones;
    private LayoutInflater inflater;
    Context context;

    public ListAdapterTransaccion(List<Transaccion> transacciones, Context context) {
        this.transacciones = transacciones;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapterTransaccion.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_transacciones,null);

        return new ListAdapterTransaccion.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterTransaccion.ViewHolder holder, int position) {
        holder.mostrarTransacciones(this.transacciones.get(position));
    }

    @Override
    public int getItemCount() {
        return transacciones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView de, para, tipo,monto,fecha,medio,quien;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            de = itemView.findViewById(R.id.txtde_listra);
            para = itemView.findViewById(R.id.txtpara_listra);
            tipo = itemView.findViewById(R.id.txttipo_listra);
            monto = itemView.findViewById(R.id.txtmonto_listra);
            fecha = itemView.findViewById(R.id.txtfecha_listra);
            medio = itemView.findViewById(R.id.txtmedio_his);
            quien = itemView.findViewById(R.id.txtquien_his);


        }

        public void mostrarTransacciones(final Transaccion transaccion){
            de.setText(transaccion.getNombre_emi());
            para.setText(transaccion.getNombre_rec());
            tipo.setText(transaccion.getTipo());
            monto.setText(String.valueOf(transaccion.getMonto()));
            fecha.setText(transaccion.getFecha());
        }
    }
}
