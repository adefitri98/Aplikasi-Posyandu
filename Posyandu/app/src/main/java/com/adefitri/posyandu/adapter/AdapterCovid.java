package com.adefitri.posyandu.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.kader.Covid19;
import com.adefitri.posyandu.activity.kader.FormCovid19;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterCovid extends RecyclerView.Adapter<AdapterCovid.HolderData> {

    private Context ctx;
    private List<DataModel> listCovid;
    private int IdCovid;

    public AdapterCovid(Context ctx, List<DataModel> listCovid){
        this.ctx = ctx;
        this.listCovid = listCovid;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_covid,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listCovid.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.id_covid.setText(String.valueOf(dm.getId_covid()));
        holder.tanggal.setText(dm.getTanggal());
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
        return listCovid.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, id_covid, tanggal, tgl_covid, masker, cuci_tangan, jaga_jarak, demam, batuk, sesak_napas, sakit_tenggorokan;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi             = v.findViewById(R.id.tv_id_bayi);
            id_covid            = v.findViewById(R.id.tv_id_covid);
            tanggal             = v.findViewById(R.id.tv_tanggal);
            tgl_covid           = v.findViewById(R.id.tv_tgl_covid);
            masker              = v.findViewById(R.id.tv_masker);
            cuci_tangan         = v.findViewById(R.id.tv_cuci_tangan);
            jaga_jarak          = v.findViewById(R.id.tv_jaga_jarak);
            demam               = v.findViewById(R.id.tv_demam);
            batuk               = v.findViewById(R.id.tv_batuk);
            sesak_napas         = v.findViewById(R.id.tv_sesak_napas);
            sakit_tenggorokan   = v.findViewById(R.id.tv_sakit_tenggorokan);
            ic_delete           = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, FormCovid19.class);
                goInput.putExtra("id_bayi",dm.getId_bayi());
                goInput.putExtra("id_covid", dm.getId_covid());
                goInput.putExtra("tanggal", dm.getTanggal());
                goInput.putExtra("masker", dm.getMasker());
                goInput.putExtra("cuci_tangan", dm.getCuci_tangan());
                goInput.putExtra("jaga_jarak", dm.getJaga_jarak());
                goInput.putExtra("demam", dm.getDemam());
                goInput.putExtra("batuk", dm.getBatuk());
                goInput.putExtra("sesak_napas", dm.getSesak_napas());
                goInput.putExtra("sakit_tenggorokan", dm.getSakit_tenggorokan());
                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus ?");
                //dialogPesan.setIcon(R.mipmap.ic_launcher_round);
                dialogPesan.setCancelable(true);

                IdCovid = Integer.parseInt(id_covid.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((Covid19)ctx).semuaCovid();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((Covid19)ctx).semuaCovid();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteCovid(IdCovid);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((Covid19)ctx).semuaCovid();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}