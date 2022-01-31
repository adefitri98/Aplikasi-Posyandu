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
import com.adefitri.posyandu.activity.kader.AkunPengunjung;
import com.adefitri.posyandu.activity.kader.FormAkunPengunjung;
import com.adefitri.posyandu.activity.kader.FormCovid19;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAkunPengunjung extends RecyclerView.Adapter<AdapterAkunPengunjung.HolderData> {
    private Context ctx;
    private List<DataModel> listAkun;
    private int IdLoginPengunjung;

    public AdapterAkunPengunjung(Context ctx, List<DataModel> listAkun){
        this.ctx = ctx;
        this.listAkun = listAkun;
    }

    @NonNull
    @Override
    public AdapterAkunPengunjung.HolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_akun_pengunjung,parent,false);
        return new AdapterAkunPengunjung.HolderData(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAkunPengunjung.HolderData holder, int position) {
        DataModel dm = listAkun.get(position);
        holder.id_bayi.setText(String.valueOf(dm.getId_bayi()));
        holder.id_login_pengunjung.setText(dm.getId_login_pengunjung());
        holder.username_pengunjung.setText(dm.getUsername_pengunjung());
        holder.password_pengunjung.setText(dm.getPassword_pengunjung());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return listAkun.size();
    }

    class HolderData extends RecyclerView.ViewHolder{
        TextView id_bayi, id_login_pengunjung, username_pengunjung, password_pengunjung;
        ImageView ic_delete;
        DataModel dm;

        public HolderData(@NonNull View v) {
            super(v);
            id_bayi             = v.findViewById(R.id.tv_id_bayi);
            id_login_pengunjung = v.findViewById(R.id.tv_id_login_pengunjung);
            username_pengunjung = v.findViewById(R.id.tv_username_pengunjung);
            password_pengunjung = v.findViewById(R.id.tv_password_pengunjung);
            ic_delete           = v.findViewById(R.id.ic_delete);

            v.setOnClickListener(v1 -> {
                Intent goInput = new Intent(ctx, FormAkunPengunjung.class);
                goInput.putExtra("id_login_pengunjung", dm.getId_login_pengunjung());
                goInput.putExtra("username_pengunjung", dm.getUsername_pengunjung());
                ctx.startActivity(goInput);
            });

            ic_delete.setOnClickListener(v12 -> {
                AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ctx);
                dialogPesan.setMessage("Apakah Anda Yakin Ingin Menghapus ?");
                //dialogPesan.setIcon(R.mipmap.ic_launcher_round);
                dialogPesan.setCancelable(true);

                IdLoginPengunjung = Integer.parseInt(id_login_pengunjung.getText().toString());

                dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                    deleteData();
                    dialogInterface.dismiss();
                    ((AkunPengunjung)ctx).semuaAkunPengunjung();
                });

                dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> {
                    dialogInterface.dismiss();
                    ((AkunPengunjung)ctx).semuaAkunPengunjung();
                });

                dialogPesan.show();
            });
        }

        private void deleteData(){
            APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
            Call<ResponseModel> hapusData = ardData.ardDeleteAkun(IdLoginPengunjung);

            hapusData.enqueue(new Callback<ResponseModel>() {
                @Override
                public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                    String pesan = response.body().getPesan();
                    Toast.makeText(ctx, ""+pesan, Toast.LENGTH_SHORT).show();
                    ((AkunPengunjung)ctx).semuaAkunPengunjung();
                }

                @Override
                public void onFailure(Call<ResponseModel> call, Throwable t) {
                    Toast.makeText(ctx, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
