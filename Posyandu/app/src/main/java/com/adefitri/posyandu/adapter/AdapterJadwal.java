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
import com.adefitri.posyandu.activity.kader.FormJadwalPosyandu;
import com.adefitri.posyandu.activity.kader.JadwalPosyandu;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterJadwal extends RecyclerView.Adapter<AdapterJadwal.HolderData> {

    private Context ctx;
    private List<DataModel> listJadwal;
    private int IdJadwal;

    public AdapterJadwal(Context ctx, List<DataModel> listJadwal){
        this.ctx = ctx;
        this.listJadwal = listJadwal;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_jadwal,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listJadwal.get(position);
        holder.id_jadwal.setText(String.valueOf(dm.getId_jadwal()));
        holder.tgl.setText(dm.getTgl());
        holder.bln.setText(dm.getBln());
        holder.waktu_jadwal.setText(dm.getWaktu_jadwal());
        holder.acara_jadwal.setText(dm.getAcara_jadwal());
        holder.tanggal.setText(dm.getTanggal());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listJadwal.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_jadwal, tgl, bln, waktu_jadwal, acara_jadwal, tanggal;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_jadwal       = v.findViewById(R.id.tv_id_jadwal);
            tgl             = v.findViewById(R.id.tv_tgl);
            bln             = v.findViewById(R.id.tv_bln);
            waktu_jadwal    = v.findViewById(R.id.tv_waktu_jadwal);
            acara_jadwal    = v.findViewById(R.id.tv_acara_jadwal);
            tanggal         = v.findViewById(R.id.tv_tanggal);
            ic_delete       = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, FormJadwalPosyandu.class);
                goInput.putExtra("id_jadwal",dm.getId_jadwal());
                goInput.putExtra("waktu_jadwal", dm.getWaktu_jadwal());
                goInput.putExtra("acara_jadwal", dm.getAcara_jadwal());
                goInput.putExtra("tanggal", dm.getTanggal());
                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus?");
                //dialogPesan.setIcon(R.mipmap.ic_launcher_round);
                dialogPesan.setCancelable(true);

                IdJadwal = Integer.parseInt(id_jadwal.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((JadwalPosyandu)ctx).semuaJadwalPosyandu();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((JadwalPosyandu)ctx).semuaJadwalPosyandu();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteJP(IdJadwal);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((JadwalPosyandu)ctx).semuaJadwalPosyandu();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
