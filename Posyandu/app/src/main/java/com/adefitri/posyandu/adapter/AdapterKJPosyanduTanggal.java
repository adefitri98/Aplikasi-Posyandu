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
import com.adefitri.posyandu.activity.gizi.KunjunganPosyanduDetail;
import com.adefitri.posyandu.model.DataModel;
import java.util.List;

public class AdapterKJPosyanduTanggal extends RecyclerView.Adapter<AdapterKJPosyanduTanggal.HolderData> {
    private Context ctx;
    private List<DataModel> listKJPosyanduTanggal;

    public AdapterKJPosyanduTanggal(Context ctx, List<DataModel> listKJPosyanduTanggal){
        this.ctx = ctx;
        this.listKJPosyanduTanggal = listKJPosyanduTanggal;
    }

    @NonNull
    @Override
    public AdapterKJPosyanduTanggal.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kj_posyandu_tanggal,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKJPosyanduTanggal.HolderData holder, int position) {
        DataModel dm = listKJPosyanduTanggal.get(position);
        holder.tgl_kunjungan_bayi.setText(dm.getTanggal_kunjungan_bayi());
        holder.berat_badan.setText(dm.getBerat_badan());
        holder.nama_bayi.setText(dm.getNama_bayi());
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
        return listKJPosyanduTanggal.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView nama_bayi, berat_badan, tinggi_badan, lingkar_kepala, vit_a, oralit,
                umur_sekarang, status_gizi, tgl_kunjungan_bayi;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            tgl_kunjungan_bayi  = v.findViewById(R.id.tv_tgl_kunjungan_bayi);
            umur_sekarang       = v.findViewById(R.id.tv_umur_sekarang);
            nama_bayi           = v.findViewById(R.id.tv_nama_bayi);
            status_gizi         = v.findViewById(R.id.tv_status_gizi);
            berat_badan         = v.findViewById(R.id.tv_berat_badan);
            tinggi_badan        = v.findViewById(R.id.tv_tinggi_badan);
            lingkar_kepala      = v.findViewById(R.id.tv_lingkar_kepala);
            vit_a               = v.findViewById(R.id.tv_vit_a);
            oralit              = v.findViewById(R.id.tv_oralit);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, KunjunganPosyanduDetail.class);
                goInput.putExtra("tanggal_kunjungan_bayi",dm.getTanggal_kunjungan_bayi());
                goInput.putExtra("umur_sekarang", dm.getUmur_sekarang());
                goInput.putExtra("nama_bayi", dm.getNama_bayi());
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
