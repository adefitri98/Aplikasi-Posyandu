package com.adefitri.posyandu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.model.DataModel;
import java.util.List;

public class AdapterImunisasiPengunjung extends RecyclerView.Adapter<AdapterImunisasiPengunjung.HolderData> {
    private Context ctx;
    private List<DataModel> listImunisasiPengunjung;

    public AdapterImunisasiPengunjung(Context ctx, List<DataModel> listImunisasiPengunjung){
        this.ctx = ctx;
        this.listImunisasiPengunjung = listImunisasiPengunjung;
    }

    @NonNull
    @Override
    public AdapterImunisasiPengunjung.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_imunisasi_pengunjung,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterImunisasiPengunjung.HolderData holder, int position) {
        DataModel dm = listImunisasiPengunjung.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.id_imunisasi.setText(String.valueOf(dm.getId_imunisasi()));
        holder.tgl_imunisasi.setText(dm.getTanggal_imunisasi());
        holder.jenis_imunisasi.setText(dm.getJenis_imunisasi());
        holder.usia_pemberian.setText(dm.getUsia_pemberian());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listImunisasiPengunjung.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, id_imunisasi, tgl_imunisasi, jenis_imunisasi, usia_pemberian;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi = v.findViewById(R.id.tv_id_bayi);
            id_imunisasi = v.findViewById(R.id.tv_id_imunisasi_bayi);
            tgl_imunisasi = v.findViewById(R.id.tv_tgl_imunisasi);
            jenis_imunisasi = v.findViewById(R.id.tv_jenis_vaksin);
            usia_pemberian = v.findViewById(R.id.tv_usia_pemberian_vaksin);
        }
    }
}