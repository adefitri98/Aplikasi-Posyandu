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
import com.adefitri.posyandu.activity.kader.FormKjBayi;
import com.adefitri.posyandu.activity.kader.KunjunganBayi;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKJBayi extends RecyclerView.Adapter<AdapterKJBayi.HolderData> {
    private Context ctx;
    private List<DataModel> listKJBayi;
    private int IdKunjunganBayi;

    public AdapterKJBayi(Context ctx, List<DataModel> listKJBayi){
        this.ctx = ctx;
        this.listKJBayi = listKJBayi;
    }

    @NonNull
    @Override
    public AdapterKJBayi.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kj_bayi,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKJBayi.HolderData holder, int position) {
        DataModel dm = listKJBayi.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.id_kunjungan_bayi.setText(String.valueOf(dm.getId_kunjungan_bayi()));
        holder.tgl_kunjungan_bayi.setText(dm.getTanggal_kunjungan_bayi());
        holder.tanggal.setText(dm.getTanggal());
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
        return listKJBayi.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, id_kunjungan_bayi, berat_badan, tinggi_badan, lingkar_kepala, vit_a,
                oralit, umur_sekarang, status_gizi, tgl_kunjungan_bayi, tanggal;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi = v.findViewById(R.id.tv_id_bayi);
            id_kunjungan_bayi= v.findViewById(R.id.tv_id_kunjungan_bayi);
            tgl_kunjungan_bayi = v.findViewById(R.id.tv_tgl_kunjungan_bayi);
            tanggal = v.findViewById(R.id.tv_tanggal);
            umur_sekarang = v.findViewById(R.id.tv_umur_sekarang);
            status_gizi = v.findViewById(R.id.tv_status_gizi);
            berat_badan = v.findViewById(R.id.tv_berat_badan);
            tinggi_badan = v.findViewById(R.id.tv_tinggi_badan);
            lingkar_kepala = v.findViewById(R.id.tv_lingkar_kepala);
            vit_a = v.findViewById(R.id.tv_vit_a);
            oralit = v.findViewById(R.id.tv_oralit);
            ic_delete = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, FormKjBayi.class);
                goInput.putExtra("id_bayi",dm.getId_bayi());
                goInput.putExtra("id_kunjungan_bayi",dm.getId_kunjungan_bayi());
                goInput.putExtra("tanggal_kunjungan_bayi",dm.getTanggal_kunjungan_bayi());
                goInput.putExtra("tanggal",dm.getTanggal());
                goInput.putExtra("umur_sekarang", dm.getUmur_sekarang());
                goInput.putExtra("status_gizi", dm.getStatus_gizi());
                goInput.putExtra("berat_badan", dm.getBerat_badan());
                goInput.putExtra("tinggi_badan", dm.getTinggi_badan());
                goInput.putExtra("lingkar_kepala", dm.getLingkar_kepala());
                goInput.putExtra("vitamin_a", dm.getVitamin_a());
                goInput.putExtra("oralit", dm.getOralit());

                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus?");

                dialogPesan.setCancelable(true);

                IdKunjunganBayi = Integer.parseInt(id_kunjungan_bayi.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((KunjunganBayi)ctx).semuaKunjunganBayi();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((KunjunganBayi)ctx).semuaKunjunganBayi();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteBayiKJ(IdKunjunganBayi);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((KunjunganBayi)ctx).semuaKunjunganBayi();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
