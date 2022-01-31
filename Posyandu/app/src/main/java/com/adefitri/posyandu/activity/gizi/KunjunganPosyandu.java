package com.adefitri.posyandu.activity.gizi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.adefitri.posyandu.R;
import com.adefitri.posyandu.adapter.AdapterKJPosyandu;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KunjunganPosyandu extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adKJBayiGizi;
    private List<DataModel> listKJBayiGizi = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunjungan_posyandu);

        rvData  = findViewById(R.id.rv_data);
        srlData = findViewById(R.id.srl_data);
        pbData  = findViewById(R.id.pb_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        RecyclerView.LayoutManager lmData = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);

        srlData.setOnRefreshListener(() -> {
            srlData.setRefreshing(true);
            semuaKunjunganBayi();
            srlData.setRefreshing(false);
        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //method untuk selalu refresh data
    protected void onResume(){
        super.onResume();
        semuaKunjunganBayi();
    }

    //method tampil data semua bayi
    private void semuaKunjunganBayi() {
        pbData.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardReadKunjunganGizi();

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                if (kode == 1){
                    listKJBayiGizi = response.body().getData();
                    adKJBayiGizi = new AdapterKJPosyandu(KunjunganPosyandu.this,
                            listKJBayiGizi);
                    rvData.setAdapter(adKJBayiGizi);
                    adKJBayiGizi.notifyDataSetChanged();
                }
                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(KunjunganPosyandu.this, "Cek Koneksi Internet",
                        Toast.LENGTH_SHORT).show();
                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}
