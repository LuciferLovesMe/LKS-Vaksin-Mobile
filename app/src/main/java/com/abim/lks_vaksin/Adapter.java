package com.abim.lks_vaksin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Vaksin> vaksins;
    Context ctx;

    public Adapter(List<Vaksin> vaksins, Context ctx) {
        this.vaksins = vaksins;
        this.ctx = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_data, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder( Adapter.ViewHolder holder, int position) {
        String[] strings = vaksins.get(position).getTgl().split("T");
        holder.tv_periode.setText("Periode "+String.valueOf(vaksins.get(position).getPeriode()));
        holder.tv_jenis.setText(String.valueOf(vaksins.get(position).getJenis_vaksin()));
        holder.tv_tgl.setText(String.valueOf(strings[0]));
        holder.tv_dr.setText(String.valueOf(vaksins.get(position).getNama_dokter()));
    }

    @Override
    public int getItemCount() {
        return vaksins.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_periode, tv_jenis, tv_tgl, tv_dr;
        public ViewHolder( View v) {
            super(v);

            tv_dr = v.findViewById(R.id.tv_dr);
            tv_tgl = v.findViewById(R.id.tv_tgl);
            tv_jenis = v.findViewById(R.id.tv_jenis);
            tv_periode = v.findViewById(R.id.tv_periode);
        }
    }
}
