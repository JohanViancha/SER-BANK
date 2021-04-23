package com.example.ser_bank.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ser_bank.Models.SaldoUsuario;
import com.example.ser_bank.R;

import java.util.List;

public class ListAdapterUsuarios extends RecyclerView.Adapter<ListAdapterUsuarios.ViewHolder> {

    private List<SaldoUsuario> usuarios;
    private LayoutInflater inflater;
    Context context;

    public ListAdapterUsuarios(List<SaldoUsuario> usuarios, Context context) {
        this.usuarios = usuarios;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public ListAdapterUsuarios.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_usuarios,null);

        return new ListAdapterUsuarios.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapterUsuarios.ViewHolder holder, int position) {
        holder.mostrarusuarios(this.usuarios.get(position));
    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView nombres, saldo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.txtpara_listra);
            saldo = itemView.findViewById(R.id.txtde_listra);
        }

        public void mostrarusuarios(final SaldoUsuario list_usurio){

            nombres.setText(list_usurio.getNombre() + ' ' + list_usurio.getApellido());
            saldo.setText("$ " + String.valueOf(list_usurio.getSaldo()));
        }
    }
}
