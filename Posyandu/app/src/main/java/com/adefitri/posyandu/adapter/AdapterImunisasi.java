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
import com.adefitri.posyandu.activity.kader.FormImunisasi;
import com.adefitri.posyandu.activity.kader.Imunisasi;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterImunisasi extends RecyclerView.Adapter<AdapterImunisasi.HolderData> {
    private Context ctx;
    private List<DataModel> listImunisasi;
    private int IdImunisasi;

    public AdapterImunisasi(Context ctx, List<DataModel> listImunisasi){
        this.ctx = ctx;
        this.listImunisasi = listImunisasi;
    }

    @NonNull
    @Override
    public AdapterImunisasi.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_imunisasi,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterImunisasi.HolderData holder, int position) {
        DataModel dm = listImunisasi.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.id_imunisasi.setText(String.valueOf(dm.getId_imunisasi()));
        holder.tgl_imunisasi.setText(dm.getTanggal_imunisasi());
        holder.tanggal.setText(dm.getTanggal());
        holder.jenis_imunisasi.setText(dm.getJenis_imunisasi());
        holder.usia_pemberian.setText(dm.getUsia_pemberian());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listImunisasi.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, id_imunisasi, tgl_imunisasi, tanggal, jenis_imunisasi, usia_pemberian;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi = v.findViewById(R.id.tv_id_bayi);
            id_imunisasi= v.findViewById(R.id.tv_id_imunisasi_bayi);
            tgl_imunisasi = v.findViewById(R.id.tv_tgl_imunisasi);
            tanggal = v.findViewById(R.id.tv_tanggal);
            jenis_imunisasi = v.findViewById(R.id.tv_jenis_vaksin);
            usia_pemberian = v.findViewById(R.id.tv_usia_pemberian_vaksin);
            ic_delete = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, FormImunisasi.class);
                goInput.putExtra("id_bayi",dm.getId_bayi());
                goInput.putExtra("id_imunisasi",dm.getId_imunisasi());
                goInput.putExtra("tanggal_imunisasi",dm.getTanggal_imunisasi());
                goInput.putExtra("tanggal",dm.getTanggal());
                goInput.putExtra("jenis_imunisasi", dm.getJenis_imunisasi());
                goInput.putExtra("usia_pemberian", dm.getUsia_pemberian());

                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus?");

                dialogPesan.setCancelable(true);

                IdImunisasi = Integer.parseInt(id_imunisasi.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((Imunisasi)ctx).semuaImunisasi();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((Imunisasi)ctx).semuaImunisasi();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteImunisasi(IdImunisasi);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((Imunisasi)ctx).semuaImunisasi();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}