package com.adefitri.posyandu.activity.pengunjung;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.adefitri.posyandu.R;

public class DetailKjBayiPengunjung extends AppCompatActivity {
    TextView tvTglKunjunganBayi, tvBeratBadan, tvTinggiBadan, tvLingkarKepala, tvUmurSekarang,
            tvVitA, tvOralit, tvStatusGizi;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kj_bayi_pengunjung);

        //inisialisasi
        tvTglKunjunganBayi  = findViewById(R.id.tv_tgl_kunjungan_bayi);
        tvBeratBadan        = findViewById(R.id.tv_berat_badan);
        tvTinggiBadan       = findViewById(R.id.tv_tinggi_badan);
        tvLingkarKepala     = findViewById(R.id.tv_lingkar_kepala);
        tvUmurSekarang      = findViewById(R.id.tv_umur_sekarang);
        tvVitA              = findViewById(R.id.tv_vit_a);
        tvOralit            = findViewById(R.id.tv_oralit);
        tvStatusGizi        = findViewById(R.id.tv_status_gizi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        //Mengambil Data dari yang sudah dipilih sebelumnya
        Intent data = getIntent();
        tvTglKunjunganBayi.setText(data.getStringExtra("tanggal_kunjungan_bayi"));
        tvBeratBadan.setText(data.getStringExtra("berat_badan"));
        tvTinggiBadan.setText(data.getStringExtra("tinggi_badan"));
        tvLingkarKepala.setText(data.getStringExtra("lingkar_kepala"));
        tvUmurSekarang.setText(data.getStringExtra("umur_sekarang"));
        tvVitA.setText(data.getStringExtra("vitamin_a"));
        tvOralit.setText(data.getStringExtra("oralit"));
        tvStatusGizi.setText(data.getStringExtra("status_gizi"));
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}