package com.adefitri.posyandu.activity.gizi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.login.SessionManager;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAkunGizi extends AppCompatActivity {
    EditText etNamaPetugas, etUsername, etPasswordBaru;
    Button btnEdit;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_akun_gizi);

        //inisialisasi
        etNamaPetugas   = findViewById(R.id.et_nama_petugas);
        etUsername      = findViewById(R.id.et_username_petugas);
        etPasswordBaru  = findViewById(R.id.et_password_baru);
        btnEdit         = findViewById(R.id.btn_edit_akun);
        sm              = new SessionManager(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        Intent data = getIntent();
        int IdLoginPetugas = data.getIntExtra("id_petugas",0);
        etNamaPetugas.setText(data.getStringExtra("nama_petugas"));
        etUsername.setText(data.getStringExtra("username"));

        btnEdit.setOnClickListener(v -> {
            final String namaPetugas    = etNamaPetugas.getText().toString();
            final String usernamePetugas= etUsername.getText().toString();
            final String passwordBaru   = etPasswordBaru.getText().toString();

            //validating inputs
            if (TextUtils.isEmpty(namaPetugas)) {
                etUsername.setError("Masukkan Nama");
                etUsername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(usernamePetugas)) {
                etUsername.setError("Masukkan Username");
                etUsername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(passwordBaru)) {
                etPasswordBaru.setError("Masukkan Password Baru");
                etPasswordBaru.requestFocus();
                return;
            }

            AlertDialog.Builder dialogPesan = new AlertDialog.Builder(FormAkunGizi.this);
            dialogPesan.setMessage("Apakah Anda Yakin?");
            dialogPesan.setCancelable(true);
            dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                SimpanPerubahan(IdLoginPetugas,namaPetugas,usernamePetugas,passwordBaru);
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

    public void SimpanPerubahan(int IdLoginPetugas, String namaPetugas, String usernamePetugas, String passwordBaru){

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardUpdateAkunPetugas(IdLoginPetugas, namaPetugas,
                usernamePetugas, passwordBaru);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                if (kode == 1) {
                    sm.Logout();
                } else {
                    Toast.makeText(FormAkunGizi.this, ""+pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormAkunGizi.this, "Cek Koneksi Internet",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
