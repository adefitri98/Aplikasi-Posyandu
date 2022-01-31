package com.adefitri.posyandu.activity.pengunjung;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.login.SessionManager;
import java.util.HashMap;

public class HomePengunjung extends AppCompatActivity {
    TextView tvUsernamePengunjung, tvIdPengunjung, tvIdBayi;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pengunjung);

        tvUsernamePengunjung    = findViewById(R.id.tv_username_pengunjung);
        tvIdPengunjung          = findViewById(R.id.tv_id_pengunjung);
        tvIdBayi                = findViewById(R.id.tv_id_bayi);
        sm                      = new SessionManager(getApplicationContext());

        HashMap<String, String> map = sm.getDetailLoginPengunjung();
        tvUsernamePengunjung.setText(map.get(sm.KEY_USERNAME));
    }

    public void JadwalPosyandu (View view){
        Intent intent = new Intent(HomePengunjung.this, Jadwal_Posyandu2.class);
        startActivity(intent);
    }
    public void ImunisasiAnak (View view){
        sm = new SessionManager(getApplicationContext());
        HashMap<String, String> map = sm.getDetailLoginPengunjung();
        tvIdBayi.setText(map.get(sm.KEY_ID_BAYI));

        int IdBayi = Integer.parseInt(tvIdBayi.getText().toString());
        Intent intent = new Intent(HomePengunjung.this, ImunisasiPengunjung.class);
        intent.putExtra("id_bayi", IdBayi);
        startActivity(intent);
    }
    public void PerkembanganAnak (View view){
        sm = new SessionManager(getApplicationContext());
        HashMap<String, String> map = sm.getDetailLoginPengunjung();
        tvIdBayi.setText(map.get(sm.KEY_ID_BAYI));

        int IdBayi = Integer.parseInt(tvIdBayi.getText().toString());
        Intent intent = new Intent(HomePengunjung.this, KunjunganBayiPengunjung.class);
        intent.putExtra("id_bayi", IdBayi);
        startActivity(intent);
    }
    public void DataCovidKunjungan (View view){
        sm = new SessionManager(getApplicationContext());
        HashMap<String, String> map = sm.getDetailLoginPengunjung();
        tvIdBayi.setText(map.get(sm.KEY_ID_BAYI));

        int IdBayi = Integer.parseInt(tvIdBayi.getText().toString());
        Intent intent = new Intent(HomePengunjung.this, KunjunganCovid.class);
        intent.putExtra("id_bayi", IdBayi);
        startActivity(intent);
    }

    public void ImunisasiInfo (View view){
        Intent intent = new Intent(HomePengunjung.this, ImunisasiInfo.class);
        startActivity(intent);
    }
    public void Germas (View view){
        Intent intent = new Intent(HomePengunjung.this, Germas.class);
        startActivity(intent);
    }
    public void GejalaCovid (View view){
        Intent intent = new Intent(HomePengunjung.this, Gejala.class);
        startActivity(intent);
    }
    public void CaraCuciTangan (View view){
        Intent intent = new Intent(HomePengunjung.this, CuciTangan.class);
        startActivity(intent);
    }
    public void CaraMasker (View view){
        Intent intent = new Intent(HomePengunjung.this, Masker.class);
        startActivity(intent);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profil_menu, menu);
        return true;
    }
    public void onProfil(MenuItem main){
        sm = new SessionManager(getApplicationContext());
        HashMap<String, String> map = sm.getDetailLoginPengunjung();
        tvUsernamePengunjung.setText(map.get(sm.KEY_USERNAME));
        tvIdPengunjung.setText(map.get(sm.KEY_ID_LOGIN));
        tvIdBayi.setText(map.get(sm.KEY_ID_BAYI));

        int IdBayi = Integer.parseInt(tvIdBayi.getText().toString());
        int IdPengunjung = Integer.parseInt(tvIdPengunjung.getText().toString());
        String UsernamePengunjung = tvUsernamePengunjung.getText().toString();
        Intent goInput = new Intent(HomePengunjung.this, ProfilPengunjung.class);
        goInput.putExtra("id_bayi", IdBayi);
        goInput.putExtra("id_pengunjung", IdPengunjung);
        goInput.putExtra("username", UsernamePengunjung);
        startActivity(goInput);
    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
