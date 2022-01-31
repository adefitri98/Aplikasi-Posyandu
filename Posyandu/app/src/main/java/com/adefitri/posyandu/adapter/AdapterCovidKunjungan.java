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

public class AdapterCovidKunjungan extends RecyclerView.Adapter<AdapterCovidKunjungan.HolderData> {

    private Context ctx;
    private List<DataModel> listCovidKunjungan;

    public AdapterCovidKunjungan(Context ctx, List<DataModel> listCovidKunjungan){
        this.ctx = ctx;
        this.listCovidKunjungan = listCovidKunjungan;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_covid_pengunjung,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listCovidKunjungan.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.id_covid.setText(String.valueOf(dm.getId_covid()));
        holder.tgl_covid.setText(dm.getTanggal_covid());
        holder.masker.setText(dm.getMasker());
        holder.cuci_tangan.setText(dm.getCuci_tangan());
        holder.jaga_jarak.setText(dm.getJaga_jarak());
        holder.demam.setText(dm.getDemam());
        holder.batuk.setText(dm.getBatuk());
        holder.sesak_napas.setText(dm.getSesak_napas());
        holder.sakit_tenggorokan.setText(dm.getSakit_tenggorokan());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listCovidKunjungan.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, id_covid, tgl_covid, masker, cuci_tangan, jaga_jarak, demam, batuk,
                sesak_napas, sakit_tenggorokan;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi = v.findViewById(R.id.tv_id_bayi);
            id_covid = v.findViewById(R.id.tv_id_covid);
            tgl_covid = v.findViewById(R.id.tv_tgl_covid);
            masker = v.findViewById(R.id.tv_masker);
            cuci_tangan = v.findViewById(R.id.tv_cuci_tangan);
            jaga_jarak = v.findViewById(R.id.tv_jaga_jarak);
            demam = v.findViewById(R.id.tv_demam);
            batuk = v.findViewById(R.id.tv_batuk);
            sesak_napas = v.findViewById(R.id.tv_sesak_napas);
            sakit_tenggorokan = v.findViewById(R.id.tv_sakit_tenggorokan);

        }
    }
}