package com.adefitri.posyandu.activity.gizi;

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
import com.adefitri.posyandu.activity.pengunjung.Jadwal_Posyandu2;
import com.adefitri.posyandu.activity.pengunjung.Masker;
import java.util.HashMap;

public class HomeGizi extends AppCompatActivity {
    TextView tvUsernameGizi, tvNamaPetugas, tvIdPetugas;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_gizi);

        //inisialisasi
        tvUsernameGizi  = findViewById(R.id.tv_username_gizi);
        tvNamaPetugas   = findViewById(R.id.tv_nama_petugas);
        tvIdPetugas     = findViewById(R.id.tv_id_login_petugas);
        sm              = new SessionManager(getApplicationContext());

        HashMap<String, String> map = sm.getDetailLoginKader();
        tvUsernameGizi.setText(map.get(sm.KEY_USERNAME));
        tvIdPetugas.setText(map.get(sm.KEY_ID_LOGIN));
        tvNamaPetugas.setText(map.get(sm.KEY_NAMA_PETUGAS));

    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.profil_menu, menu);
        return true;
    }

    public void onProfil(MenuItem main){
        HashMap<String, String> map = sm.getDetailLoginKader();
        tvUsernameGizi.setText(map.get(sm.KEY_USERNAME));
        tvIdPetugas.setText(map.get(sm.KEY_ID_LOGIN));
        tvNamaPetugas.setText(map.get(sm.KEY_NAMA_PETUGAS));

        int IdPetugas       = Integer.parseInt(tvIdPetugas.getText().toString());
        String UsernameGizi = tvUsernameGizi.getText().toString();
        String NamaPetugas  = tvNamaPetugas.getText().toString();

        Intent intent = new Intent(HomeGizi.this, ProfilGizi.class);
        intent.putExtra("id_login", IdPetugas);
        intent.putExtra("username", UsernameGizi);
        intent.putExtra("nama_petugas", NamaPetugas);
        startActivity(intent);
    }

    public void JadwalPosyanduGizi (View view){
        Intent intent = new Intent(HomeGizi.this, Jadwal_Posyandu2.class);
        startActivity(intent);
    }

    public void DataBayiGizi (View view){
        Intent intent = new Intent(HomeGizi.this, KunjunganPosyandu.class);
        startActivity(intent);
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void ImunisasiInfo (View view){
        Intent intent = new Intent(HomeGizi.this, ImunisasiInfo.class);
        startActivity(intent);
    }
    public void Germas (View view){
        Intent intent = new Intent(HomeGizi.this, Germas.class);
        startActivity(intent);
    }
    public void GejalaCovid (View view){
        Intent intent = new Intent(HomeGizi.this, Gejala.class);
        startActivity(intent);
    }
    public void CaraCuciTangan (View view){
        Intent intent = new Intent(HomeGizi.this, CuciTangan.class);
        startActivity(intent);
    }
    public void CaraMasker (View view){
        Intent intent = new Intent(HomeGizi.this, Masker.class);
        startActivity(intent);
    }
}
