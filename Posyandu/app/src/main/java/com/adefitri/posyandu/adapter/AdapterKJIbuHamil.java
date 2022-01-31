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
import com.adefitri.posyandu.activity.kader.FormKjIbuHamil;
import com.adefitri.posyandu.activity.kader.KunjunganIbuHamil;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterKJIbuHamil extends RecyclerView.Adapter<AdapterKJIbuHamil.HolderData> {
    private Context ctx;
    private List<DataModel> listKJIbuHamil;
    private int IdKunjunganIbuHamil;

    public AdapterKJIbuHamil(Context ctx, List<DataModel> listKJIbuHamil){
        this.ctx = ctx;
        this.listKJIbuHamil = listKJIbuHamil;
    }

    @NonNull
    @Override
    public AdapterKJIbuHamil.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_kj_ibu_hamil,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKJIbuHamil.HolderData holder, int position) {
        DataModel dm = listKJIbuHamil.get(position);
        holder.id_ibu_hamil.setText(String.valueOf(dm.getId_ibu_hamil()));
        holder.id_kunjungan_ibu_hamil.setText(String.valueOf(dm.getId_kunjungan_ibu_hamil()));
        holder.tgl_kunjungan.setText(dm.getTanggal_kunjungan());
        holder.hasil_penimbangan.setText(dm.getHasil_penimbangan());
        holder.pmt_pemulihan.setText(dm.getPmt_pemulihan());
        holder.tanggal.setText(dm.getTanggal());
        holder.umur_kehamilan.setText(dm.getUmur_kehamilan());
        holder.lingkar_lengan.setText(dm.getLingkar_lengan());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listKJIbuHamil.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_ibu_hamil, id_kunjungan_ibu_hamil, hasil_penimbangan, pmt_pemulihan,
                tgl_kunjungan, tanggal, umur_kehamilan, lingkar_lengan;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_ibu_hamil            = v.findViewById(R.id.tv_id_ibu_hamil);
            id_kunjungan_ibu_hamil  = v.findViewById(R.id.tv_id_kunjungan_ibu_hamil);
            tgl_kunjungan           = v.findViewById(R.id.tv_tgl_kunjungan_ibu_hamil);
            tanggal                 = v.findViewById(R.id.tv_tanggal);
            hasil_penimbangan       = v.findViewById(R.id.tv_hasil_penimbangan);
            pmt_pemulihan           = v.findViewById(R.id.tv_pmt_pemulihan);
            umur_kehamilan          = v.findViewById(R.id.tv_umur_kehamilan);
            lingkar_lengan          = v.findViewById(R.id.tv_lingkar_lengan);
            ic_delete               = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, FormKjIbuHamil.class);
                goInput.putExtra("id_kunjungan_ibu_hamil",dm.getId_kunjungan_ibu_hamil());
                goInput.putExtra("id_ibu_hamil",dm.getId_ibu_hamil());
                goInput.putExtra("hasil_penimbangan", dm.getHasil_penimbangan());
                goInput.putExtra("pmt_pemulihan", dm.getPmt_pemulihan());
                goInput.putExtra("umur_kehamilan", dm.getUmur_kehamilan());
                goInput.putExtra("lingkar_lengan", dm.getLingkar_lengan());
                goInput.putExtra("tanggal", dm.getTanggal());
                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus?");
                //dialogPesan.setIcon(R.mipmap.ic_launcher_round);
                dialogPesan.setCancelable(true);

                IdKunjunganIbuHamil = Integer.parseInt(id_kunjungan_ibu_hamil.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((KunjunganIbuHamil)ctx).semuaKunjunganIbuHamil();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((KunjunganIbuHamil)ctx).semuaKunjunganIbuHamil();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteIBKJ(IdKunjunganIbuHamil);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((KunjunganIbuHamil)ctx).semuaKunjunganIbuHamil();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
