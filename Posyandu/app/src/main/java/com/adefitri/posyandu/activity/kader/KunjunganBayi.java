package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.adapter.AdapterKJBayi;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KunjunganBayi extends AppCompatActivity {
    private RecyclerView rvData;
    private RecyclerView.Adapter adKJBayi;
    private List<DataModel> listKJBayi = new ArrayList<>();
    private SwipeRefreshLayout srlData;
    private ProgressBar pbData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunjungan_bayi);

        rvData      = findViewById(R.id.rv_data);
        srlData     = findViewById(R.id.srl_data);
        pbData      = findViewById(R.id.pb_data);

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

    //Code Program pada Method dibawah ini akan Berjalan saat Option Menu Dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Memanggil/Memasang menu item pada toolbar dari layout menu_bar.xml
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Membuat event/kejadian saat salah satu item pada toolbar di klik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add :
                //Kode disini akan di eksekusi saat tombol add di klik
                Intent data = getIntent();
                int IdBayi  = data.getIntExtra("id_bayi",0);
                Intent goInput = new Intent(KunjunganBayi.this,FormKjBayi.class);
                goInput.putExtra("id_bayi", IdBayi);
                startActivity(goInput);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //method untuk selalu redresh data
    protected void onResume(){
        super.onResume();
        semuaKunjunganBayi();
    }

    //method tampil data semua bayi
    public void semuaKunjunganBayi() {
        pbData.setVisibility(View.VISIBLE);

        Intent data = getIntent();
        int IdBayi  = data.getIntExtra("id_bayi",0);

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardReadBayiKJ(IdBayi);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                if (kode == 1){
                    listKJBayi = response.body().getData();
                    adKJBayi = new AdapterKJBayi(KunjunganBayi.this,listKJBayi);
                    rvData.setAdapter(adKJBayi);
                    adKJBayi.notifyDataSetChanged();
                }
                pbData.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(KunjunganBayi.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
                pbData.setVisibility(View.INVISIBLE);
            }
        });
    }
}

