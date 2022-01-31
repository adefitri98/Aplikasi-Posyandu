package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.adefitri.posyandu.R;

public class DetailIbuHamil extends AppCompatActivity {
    TextView IdIbuHamil, NamaIbuHamil, NamaIbuHamilJudul,  NamaSuami, HamilKe,
            TanggalPendaftaran, Tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ibu_hamil);

        //inisialisasi
        IdIbuHamil          = findViewById(R.id.tv_id_ibu_hamil);
        NamaIbuHamil        = findViewById(R.id.tv_nama_ibu_hamil);
        NamaIbuHamilJudul   = findViewById(R.id.tv_nama_ibu_hamil_judul);
        NamaSuami           = findViewById(R.id.tv_nama_suami);
        TanggalPendaftaran  = findViewById(R.id.tv_tanggal_pendaftaran);
        HamilKe             = findViewById(R.id.tv_hamil_ke);
        Tanggal             = findViewById(R.id.tv_tanggal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        //Mengambil Data dari yang sudah dipilih sebelumnya
        Intent data = getIntent();
        IdIbuHamil.setText(String.valueOf(data.getIntExtra("id_ibu_hamil",0)));
        NamaIbuHamilJudul.setText(data.getStringExtra("nama_ibu_hamil"));
        NamaIbuHamil.setText(data.getStringExtra("nama_ibu_hamil"));
        NamaSuami.setText(data.getStringExtra("nama_suami"));
        TanggalPendaftaran.setText(data.getStringExtra("tanggal_pendaftaran"));
        HamilKe.setText(data.getStringExtra("hamil_ke"));
        Tanggal.setText(data.getStringExtra("tanggal"));
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
                Intent goInput = new Intent(DetailIbuHamil.this,FormIbuHamil.class);
                goInput.putExtra("id_ibu_hamil", IdIbuHamil.getText().toString());
                goInput.putExtra("nama_ibu_hamil", NamaIbuHamil.getText().toString());
                goInput.putExtra("nama_suami", NamaSuami.getText().toString());
                goInput.putExtra("tanggal_pendaftaran", TanggalPendaftaran.getText().toString());
                goInput.putExtra("hamil_ke", HamilKe.getText().toString());
                goInput.putExtra("tanggal", Tanggal.getText().toString());
                startActivity(goInput);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void KunjunganIbuHamil (View view){
        Intent data     = getIntent();
        int IdIbuHamil  = data.getIntExtra("id_ibu_hamil",0);
        Intent goInput  = new Intent(DetailIbuHamil.this,KunjunganIbuHamil.class);
        goInput.putExtra("id_ibu_hamil", IdIbuHamil);
        startActivity(goInput);
    }

}
