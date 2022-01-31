package com.adefitri.posyandu.activity.pengunjung;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.login.SessionManager;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilPengunjung extends AppCompatActivity {
    TextView Username, KKBayi, NamaBayi, TglLahir, JKBayi, NamaAyah, NamaIbu, Alamat,
            AnakKe, BeratLahir;
    Button btnLogout;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_pengunjung);

        //inisialisasi
        Username    = findViewById(R.id.tv_username_pengunjung);
        KKBayi      = findViewById(R.id.tv_kk_bayi);
        NamaBayi    = findViewById(R.id.tv_nama_bayi);
        TglLahir    = findViewById(R.id.tv_tgl_lahir);
        JKBayi      = findViewById(R.id.tv_jk_bayi);
        NamaAyah    = findViewById(R.id.tv_nama_ayah);
        NamaIbu     = findViewById(R.id.tv_nama_ibu);
        Alamat      = findViewById(R.id.tv_alamat_bayi);
        AnakKe      = findViewById(R.id.tv_anak_ke);
        BeratLahir  = findViewById(R.id.tv_berat_lahir);
        btnLogout   = findViewById(R.id.btn_logout_pengunjung);
        sm          = new SessionManager(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        Intent data = getIntent();
        int IdBayi = data.getIntExtra("id_bayi",0);
        Username.setText(data.getStringExtra("username"));

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardReadProfilBayi(IdBayi);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                if (kode == 1) {
                    List<DataModel> user = response.body().getData();
                    NamaBayi.setText(user.get(0).getNama_bayi());
                    NamaIbu.setText(user.get(0).getNama_ibu());
                    NamaAyah.setText(user.get(0).getNama_ayah());
                    TglLahir.setText(user.get(0).getTgl_lahir());
                    KKBayi.setText(user.get(0).getKk_bayi());
                    JKBayi.setText(user.get(0).getJk_bayi());
                    Alamat.setText(user.get(0).getAlamat_bayi());
                    AnakKe.setText(user.get(0).getAnak_ke());
                    BeratLahir.setText(user.get(0).getBerat_lahir());
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ProfilPengunjung.this, "Cek Koneksi Internet",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnLogout.setOnClickListener(v -> {
            AlertDialog.Builder dialogPesan = new AlertDialog.Builder(ProfilPengunjung.this);
            dialogPesan.setMessage("Apakah Anda Yakin?");
            dialogPesan.setCancelable(true);
            dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                sm.Logout();
                dialogInterface.dismiss();
            });

            dialogPesan.setNegativeButton("Tidak", (dialogInterface, i) -> dialogInterface.dismiss());
            dialogPesan.show();
        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    public void EditAkun (View view){
        Intent data = getIntent();
        int IdPengunjung = data.getIntExtra("id_pengunjung",0);
        String Username = data.getStringExtra("username");

        Intent goInput = new Intent(ProfilPengunjung.this, FormAkun.class);
        goInput.putExtra("id_pengunjung", IdPengunjung);
        goInput.putExtra("username", Username);
        startActivity(goInput);
    }
}
