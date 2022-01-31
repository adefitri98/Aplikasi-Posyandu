package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.login.SessionManager;
import com.adefitri.posyandu.activity.pengunjung.CuciTangan;
import com.adefitri.posyandu.activity.pengunjung.Gejala;
import com.adefitri.posyandu.activity.pengunjung.Germas;
import com.adefitri.posyandu.activity.pengunjung.ImunisasiInfo;
import com.adefitri.posyandu.activity.pengunjung.Masker;

import java.util.HashMap;

public class HomeKader extends AppCompatActivity{
    TextView tvUsernameKader, tvNamaPetugas, tvIdPetugas;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_kader);

        //inisialisasi
        tvUsernameKader = findViewById(R.id.tv_username_kader);
        tvNamaPetugas = findViewById(R.id.tv_nama_petugas);
        tvIdPetugas = findViewById(R.id.tv_id_login_petugas);
        sm = new SessionManager(getApplicationContext());

        HashMap<String, String> map = sm.getDetailLoginKader();
        tvUsernameKader.setText(map.get(sm.KEY_USERNAME));
        tvIdPetugas.setText(map.get(sm.KEY_ID_LOGIN));
        tvNamaPetugas.setText(map.get(sm.KEY_NAMA_PETUGAS));
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profil_menu, menu);
        return true;
    }

    public void onProfil(MenuItem main){
        HashMap<String, String> map = sm.getDetailLoginKader();
        tvUsernameKader.setText(map.get(sm.KEY_USERNAME));
        tvIdPetugas.setText(map.get(sm.KEY_ID_LOGIN));
        tvNamaPetugas.setText(map.get(sm.KEY_NAMA_PETUGAS));

        int IdLogin             = Integer.parseInt(tvIdPetugas.getText().toString());
        String NamaPetugas      = tvNamaPetugas.getText().toString();
        String UsernameKader    = tvUsernameKader.getText().toString();
        Intent intent = new Intent(HomeKader.this, ProfilKader.class);
        intent.putExtra("id_login", IdLogin);
        intent.putExtra("username", UsernameKader);
        intent.putExtra("nama_petugas", NamaPetugas);
        startActivity(intent);
    }

    public void JadwalPosyandu (View view){
        Intent intent = new Intent(HomeKader.this, JadwalPosyandu.class);
        startActivity(intent);
    }

    public void DataIbuHamil (View view){
        Intent intent = new Intent(HomeKader.this, IbuHamil.class);
        startActivity(intent);
    }

    public void DataBayi (View view){
        Intent intent = new Intent(HomeKader.this,Bayi.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void ImunisasiInfo (View view){
        Intent intent = new Intent(HomeKader.this, ImunisasiInfo.class);
        startActivity(intent);
    }
    public void Germas (View view){
        Intent intent = new Intent(HomeKader.this, Germas.class);
        startActivity(intent);
    }
    public void GejalaCovid (View view){
        Intent intent = new Intent(HomeKader.this, Gejala.class);
        startActivity(intent);
    }
    public void CaraCuciTangan (View view){
        Intent intent = new Intent(HomeKader.this, CuciTangan.class);
        startActivity(intent);
    }
    public void CaraMasker (View view){
        Intent intent = new Intent(HomeKader.this, Masker.class);
        startActivity(intent);
    }
}
