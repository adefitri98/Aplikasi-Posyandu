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
import com.adefitri.posyandu.activity.kader.Bayi;
import com.adefitri.posyandu.activity.kader.DetailBayi;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterBayi extends RecyclerView.Adapter<AdapterBayi.HolderData> {

    private Context ctx;
    private List<DataModel> listBayi;
    private int IdBayi;

    public AdapterBayi(Context ctx, List<DataModel> listBayi){
        this.ctx = ctx;
        this.listBayi = listBayi;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bayi,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listBayi.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.nama_bayi.setText(dm.getNama_bayi());
        holder.tgl_lahir.setText(dm.getTgl_lahir());
        holder.jk_bayi.setText(dm.getJk_bayi());
        holder.nama_ibu.setText(dm.getNama_ibu());
        holder.nama_ayah.setText(dm.getNama_ayah());
        holder.kk_bayi.setText(dm.getKk_bayi());
        holder.alamat_bayi.setText(dm.getAlamat_bayi());
        holder.anak_ke.setText(dm.getAnak_ke());
        holder.berat_lahir.setText(dm.getBerat_lahir());
        holder.sasaran.setText(dm.getSasaran());
        holder.tanggal.setText(dm.getTanggal());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listBayi.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, nama_bayi, tgl_lahir, jk_bayi, nama_ibu, nama_ayah, kk_bayi, alamat_bayi,
                anak_ke, berat_lahir, sasaran, tanggal;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi     = v.findViewById(R.id.tv_id_bayi);
            nama_bayi   = v.findViewById(R.id.tv_nama_bayi);
            tgl_lahir   = v.findViewById(R.id.tv_tgl_lahir);
            jk_bayi     = v.findViewById(R.id.tv_jk_bayi);
            nama_ibu    = v.findViewById(R.id.tv_nama_ibu);
            nama_ayah   = v.findViewById(R.id.tv_nama_ayah);
            kk_bayi     = v.findViewById(R.id.tv_kk_bayi);
            alamat_bayi = v.findViewById(R.id.tv_alamat_bayi);
            anak_ke     = v.findViewById(R.id.tv_anak_ke);
            berat_lahir = v.findViewById(R.id.tv_berat_lahir);
            sasaran     = v.findViewById(R.id.tv_sasaran);
            tanggal     = v.findViewById(R.id.tv_tanggal);
            ic_delete   = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, DetailBayi.class);
                goInput.putExtra("id_bayi",dm.getId_bayi());
                goInput.putExtra("nama_bayi", dm.getNama_bayi());
                goInput.putExtra("tgl_lahir", dm.getTgl_lahir());
                goInput.putExtra("tanggal", dm.getTanggal());
                goInput.putExtra("jk_bayi", dm.getJk_bayi());
                goInput.putExtra("nama_ibu", dm.getNama_ibu());
                goInput.putExtra("nama_ayah", dm.getNama_ayah());
                goInput.putExtra("kk_bayi", dm.getKk_bayi());
                goInput.putExtra("alamat_bayi", dm.getAlamat_bayi());
                goInput.putExtra("anak_ke", dm.getAnak_ke());
                goInput.putExtra("berat_lahir", dm.getBerat_lahir());
                goInput.putExtra("sasaran", dm.getSasaran());
                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus ?");
                //dialogPesan.setIcon(R.mipmap.ic_launcher_round);
                dialogPesan.setCancelable(true);

                IdBayi = Integer.parseInt(id_bayi.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((Bayi)ctx).semuaBayi();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((Bayi)ctx).semuaBayi();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteBayi(IdBayi);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((Bayi)ctx).semuaBayi();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}