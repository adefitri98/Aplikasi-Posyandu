package com.adefitri.posyandu.activity.gizi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.login.SessionManager;
import com.adefitri.posyandu.activity.pengunjung.ProfilPengunjung;

public class ProfilGizi extends AppCompatActivity {
    TextView tvNamaPetugas, tvUsernamePetugas;
    Button btnLogout;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_gizi);

        //inisialisasi
        tvUsernamePetugas   = findViewById(R.id.tv_username_gizi);
        tvNamaPetugas       = findViewById(R.id.tv_nama_petugas);
        sm                  = new SessionManager(getApplicationContext());
        btnLogout           = findViewById(R.id.btn_logout_petugas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        btnLogout.setOnClickListener(v -> {
            AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ProfilGizi.this);
            dialogPesan.setMessage("Apakah Anda Yakin?");
            dialogPesan.setCancelable(true);
            dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                sm.Logout();
                dialogInterface.dismiss();
            });

            dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> dialogInterface.dismiss());
            dialogPesan.show();
        });

        Intent data = getIntent();
        tvNamaPetugas.setText(data.getStringExtra("nama_petugas"));
        tvUsernamePetugas.setText(data.getStringExtra("username"));

    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void EditAkunPetugas (View view){
        Intent data = getIntent();
        int IdLoginPetugas      = data.getIntExtra("id_login",0);
        String NamaPetugas      = data.getStringExtra("nama_petugas");
        String UsernamePetugas  = data.getStringExtra("username");

        Intent goInput = new Intent(ProfilGizi.this, FormAkunGizi.class);
        goInput.putExtra("id_petugas", IdLoginPetugas);
        goInput.putExtra("nama_petugas", NamaPetugas);
        goInput.putExtra("username", UsernamePetugas);
        startActivity(goInput);
    }
}
