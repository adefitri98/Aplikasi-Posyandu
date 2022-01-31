package com.adefitri.posyandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.pengunjung.DetailKjBayiPengunjung;
import com.adefitri.posyandu.model.DataModel;
import java.util.List;

public class AdapterKJBayiPengunjung extends RecyclerView.Adapter<AdapterKJBayiPengunjung.HolderData> {
    private Context ctx;
    private List<DataModel> listKJBayiPengunjung;

    public AdapterKJBayiPengunjung(Context ctx, List<DataModel> listKJBayiPengunjung){
        this.ctx = ctx;
        this.listKJBayiPengunjung = listKJBayiPengunjung;
    }

    @NonNull
    @Override
    public AdapterKJBayiPengunjung.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kj_bayi_pengunjung,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKJBayiPengunjung.HolderData holder, int position) {
        DataModel dm = listKJBayiPengunjung.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.id_kunjungan_bayi.setText(String.valueOf(dm.getId_kunjungan_bayi()));
        holder.tgl_kunjungan_bayi.setText(dm.getTanggal_kunjungan_bayi());
        holder.berat_badan.setText(dm.getBerat_badan());
        holder.tinggi_badan.setText(dm.getTinggi_badan());
        holder.lingkar_kepala.setText(dm.getLingkar_kepala());
        holder.vit_a.setText(dm.getVitamin_a());
        holder.oralit.setText(dm.getOralit());
        holder.umur_sekarang.setText(dm.getUmur_sekarang());
        holder.status_gizi.setText(dm.getStatus_gizi());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listKJBayiPengunjung.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, id_kunjungan_bayi, berat_badan, tinggi_badan, lingkar_kepala, vit_a,
                oralit, umur_sekarang, status_gizi, tgl_kunjungan_bayi;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi = v.findViewById(R.id.tv_id_bayi);
            id_kunjungan_bayi = v.findViewById(R.id.tv_id_kunjungan_bayi);
            tgl_kunjungan_bayi = v.findViewById(R.id.tv_tgl_kunjungan_bayi);
            umur_sekarang = v.findViewById(R.id.tv_umur_sekarang);
            status_gizi = v.findViewById(R.id.tv_status_gizi);
            berat_badan = v.findViewById(R.id.tv_berat_badan);
            tinggi_badan = v.findViewById(R.id.tv_tinggi_badan);
            lingkar_kepala = v.findViewById(R.id.tv_lingkar_kepala);
            vit_a = v.findViewById(R.id.tv_vit_a);
            oralit = v.findViewById(R.id.tv_oralit);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, DetailKjBayiPengunjung.class);
                goInput.putExtra("id_bayi",dm.getId_bayi());
                goInput.putExtra("id_kunjungan_bayi",dm.getId_kunjungan_bayi());
                goInput.putExtra("tanggal_kunjungan_bayi",dm.getTanggal_kunjungan_bayi());
                goInput.putExtra("umur_sekarang", dm.getUmur_sekarang());
                goInput.putExtra("status_gizi", dm.getStatus_gizi());
                goInput.putExtra("berat_badan", dm.getBerat_badan());
                goInput.putExtra("tinggi_badan", dm.getTinggi_badan());
                goInput.putExtra("lingkar_kepala", dm.getLingkar_kepala());
                goInput.putExtra("vitamin_a", dm.getVitamin_a());
                goInput.putExtra("oralit", dm.getOralit());
                ctx.startActivity(goInput);
            });

        }
    }
}