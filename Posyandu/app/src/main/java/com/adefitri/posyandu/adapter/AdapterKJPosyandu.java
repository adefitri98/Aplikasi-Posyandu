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
import com.adefitri.posyandu.activity.gizi.KunjunganPosyanduTanggal;
import com.adefitri.posyandu.model.DataModel;
import java.util.List;

public class AdapterKJPosyandu extends RecyclerView.Adapter<AdapterKJPosyandu.HolderData> {
    private Context ctx;
    private List<DataModel> listKJBayiGizi;

    public AdapterKJPosyandu(Context ctx, List<DataModel> listKJBayiGizi){
        this.ctx = ctx;
        this.listKJBayiGizi = listKJBayiGizi;
    }

    @NonNull
    @Override
    public AdapterKJPosyandu.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kj_posyandu,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKJPosyandu.HolderData holder, int position) {
        DataModel dm = listKJBayiGizi.get(position);
        holder.tgl_kunjungan_bayi.setText(dm.getTanggal_kunjungan_bayi());
        holder.tanggal.setText(dm.getTanggal());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listKJBayiGizi.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView tgl_kunjungan_bayi, tanggal;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            tgl_kunjungan_bayi = v.findViewById(R.id.tv_tgl_kunjungan_bayi);
            tanggal = v.findViewById(R.id.tv_tanggal);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, KunjunganPosyanduTanggal.class);
                goInput.putExtra("tanggal",dm.getTanggal());
                ctx.startActivity(goInput);
            });
        }
    }
}