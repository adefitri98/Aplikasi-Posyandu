package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.adefitri.posyandu.R;
import java.util.Objects;

public class DetailBayi extends AppCompatActivity {
    TextView tvIdBayi, tvNamaBayi, tvNamaBayiJudul, tvTglLahir, tvJKBayi, tvNamaIbu, tvNamaAyah,
            tvKKBayi, tvAlamatBayi, tvAnakKe, tvBeratLahir, tvSasaran, tvTanggal;
    CardView cvAkun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_bayi);

        //inisialisasi
        tvIdBayi        = findViewById(R.id.tv_id_bayi);
        tvNamaBayi      = findViewById(R.id.tv_nama_bayi);
        tvNamaBayiJudul = findViewById(R.id.tv_nama_bayi_judul);
        tvTglLahir      = findViewById(R.id.tv_tgl_lahir);
        tvTanggal       = findViewById(R.id.tv_tanggal);
        tvJKBayi        = findViewById(R.id.tv_jk_bayi);
        tvNamaIbu       = findViewById(R.id.tv_nama_ibu);
        tvNamaAyah      = findViewById(R.id.tv_nama_ayah);
        tvKKBayi        = findViewById(R.id.tv_kk_bayi);
        tvAlamatBayi    = findViewById(R.id.tv_alamat_bayi);
        tvAnakKe        = findViewById(R.id.tv_anak_ke);
        tvBeratLahir    = findViewById(R.id.tv_berat_lahir);
        tvSasaran       = findViewById(R.id.tv_sasaran);
        cvAkun          = findViewById(R.id.cv_akun);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        //Mengambil Data dari yang sudah dipilih sebelumnya
        Intent data = getIntent();
        tvIdBayi.setText(String.valueOf(data.getIntExtra("id_bayi",0)));
        tvNamaBayi.setText(data.getStringExtra("nama_bayi"));
        tvNamaBayiJudul.setText(data.getStringExtra("nama_bayi"));
        tvTglLahir.setText(data.getStringExtra("tgl_lahir"));
        tvJKBayi.setText(data.getStringExtra("jk_bayi"));
        tvNamaIbu.setText(data.getStringExtra("nama_ibu"));
        tvNamaAyah.setText(data.getStringExtra("nama_ayah"));
        tvKKBayi.setText(data.getStringExtra("kk_bayi"));
        tvAlamatBayi.setText(data.getStringExtra("alamat_bayi"));
        tvAnakKe.setText(data.getStringExtra("anak_ke"));
        tvBeratLahir.setText(data.getStringExtra("berat_lahir"));
        tvSasaran.setText(data.getStringExtra("sasaran"));
        tvTanggal.setText(data.getStringExtra("tanggal"));

        String Sasaran = data.getStringExtra("sasaran");
        if (Objects.equals(Sasaran, "Sasaran")){
            cvAkun.setVisibility(View.VISIBLE);
        } else {
            cvAkun.setVisibility(View.GONE);
        }
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
        inflater.inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //Membuat event/kejadian saat salah satu item pada toolbar di klik
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.edit :
                //Kode disini akan di eksekusi saat tombol about di klik
                Intent goInput = new Intent(DetailBayi.this,FormBayi.class);
                goInput.putExtra("id_bayi", tvIdBayi.getText().toString());
                goInput.putExtra("nama_bayi", tvNamaBayi.getText().toString());
                goInput.putExtra("nama_ibu", tvNamaIbu.getText().toString());
                goInput.putExtra("nama_ayah", tvNamaAyah.getText().toString());
                goInput.putExtra("kk_bayi", tvKKBayi.getText().toString());
                goInput.putExtra("alamat_bayi", tvAlamatBayi.getText().toString());
                goInput.putExtra("anak_ke", tvAnakKe.getText().toString());
                goInput.putExtra("berat_lahir", tvBeratLahir.getText().toString());
                goInput.putExtra("status", tvSasaran.getText().toString());
                goInput.putExtra("jk_bayi", tvJKBayi.getText().toString());
                goInput.putExtra("tanggal", tvTanggal.getText().toString());
                goInput.putExtra("tgl_lahir", tvTglLahir.getText().toString());
                startActivity(goInput);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void KunjunganBayi (View view){
        Intent data     = getIntent();
        int IdBayi      = data.getIntExtra("id_bayi",0);
        Intent goInput  = new Intent(DetailBayi.this, KunjunganBayi.class);
        goInput.putExtra("id_bayi", IdBayi);
        startActivity(goInput);
    }

    public void ImunisasiBayi (View view){
        Intent data     = getIntent();
        int IdBayi      = data.getIntExtra("id_bayi",0);
        Intent goInput  = new Intent(DetailBayi.this, Imunisasi.class);
        goInput.putExtra("id_bayi", IdBayi);
        startActivity(goInput);
    }

    public void Covid19 (View view){
        Intent data     = getIntent();
        int IdBayi      = data.getIntExtra("id_bayi",0);
        Intent goInput  = new Intent(DetailBayi.this, Covid19.class);
        goInput.putExtra("id_bayi", IdBayi);
        startActivity(goInput);
    }

    public void AkunPengunjung (View view){
        Intent data     = getIntent();
        int IdBayi      = data.getIntExtra("id_bayi",0);
        Intent goInput  = new Intent(DetailBayi.this, AkunPengunjung.class);
        goInput.putExtra("id_bayi", IdBayi);
        startActivity(goInput);
    }
}
