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

public class AdapterJadwal2 extends RecyclerView.Adapter<AdapterJadwal2.HolderData> {

    private Context ctx;
    private List<DataModel> listJadwal2;

    public AdapterJadwal2(Context ctx, List<DataModel> listJadwal2){
        this.ctx = ctx;
        this.listJadwal2 = listJadwal2;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jadwal2,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listJadwal2.get(position);
        holder.id_jadwal.setText(String.valueOf(dm.getId_jadwal()));
        holder.tgl.setText(dm.getTgl());
        holder.bln.setText(dm.getBln());
        holder.waktu_jadwal.setText(dm.getWaktu_jadwal());
        holder.acara_jadwal.setText(dm.getAcara_jadwal());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listJadwal2.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_jadwal, tgl, bln, waktu_jadwal, acara_jadwal;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_jadwal = v.findViewById(R.id.tv_id_jadwal);
            tgl = v.findViewById(R.id.tv_tgl);
            bln = v.findViewById(R.id.tv_bln);
            waktu_jadwal = v.findViewById(R.id.tv_waktu_jadwal);
            acara_jadwal = v.findViewById(R.id.tv_acara_jadwal);
        }
    }
}
