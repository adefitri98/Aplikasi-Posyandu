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
import com.adefitri.posyandu.activity.kader.DetailIbuHamil;
import com.adefitri.posyandu.activity.kader.IbuHamil;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterIbuHamil extends RecyclerView.Adapter<AdapterIbuHamil.HolderData> {

    private Context ctx;
    private List<DataModel> listIbuHamil;
    private int IdIbuHamil;

    public AdapterIbuHamil(Context ctx, List<DataModel> listIbuHamil){
        this.ctx = ctx;
        this.listIbuHamil = listIbuHamil;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ibu_hamil,parent,false);
        return new HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listIbuHamil.get(position);
        holder.id_ibu_hamil.setText(String.valueOf(dm.getId_ibu_hamil()));
        holder.nama_ibu_hamil.setText(dm.getNama_ibu_hamil());
        holder.nama_suami.setText(dm.getNama_suami());
        holder.hamil_ke.setText(dm.getHamil_ke());
        holder.tanggal_pendaftaran.setText(dm.getTanggal_pendaftaran());
        holder.tanggal.setText(dm.getTanggal());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listIbuHamil.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_ibu_hamil, nama_ibu_hamil, nama_suami, hamil_ke, tanggal_pendaftaran, tanggal;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_ibu_hamil = v.findViewById(R.id.tv_id_ibu_hamil);
            nama_ibu_hamil = v.findViewById(R.id.tv_nama_ibu_hamil);
            nama_suami = v.findViewById(R.id.tv_nama_suami);
            tanggal_pendaftaran = v.findViewById(R.id.tv_tanggal_pendaftaran);
            tanggal = v.findViewById(R.id.tv_tanggal);
            hamil_ke = v.findViewById(R.id.tv_hamil_ke);
            ic_delete = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, DetailIbuHamil.class);
                goInput.putExtra("id_ibu_hamil",dm.getId_ibu_hamil());
                goInput.putExtra("nama_ibu_hamil", dm.getNama_ibu_hamil());
                goInput.putExtra("nama_suami", dm.getNama_suami());
                goInput.putExtra("tanggal_pendaftaran", dm.getTanggal_pendaftaran());
                goInput.putExtra("hamil_ke", dm.getHamil_ke());
                goInput.putExtra("tanggal", dm.getTanggal());
                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus ?");
                //dialogPesan.setIcon(R.mipmap.ic_launcher_round);
                dialogPesan.setCancelable(true);

                IdIbuHamil = Integer.parseInt(id_ibu_hamil.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((IbuHamil)ctx).semuaIbuHamil();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((IbuHamil)ctx).semuaIbuHamil();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteIB(IdIbuHamil);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((IbuHamil)ctx).semuaIbuHamil();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
