package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAkunPengunjung extends AppCompatActivity {
    ProgressDialog pd;
    EditText etUsername, etPassword;
    Button btnSimpan, btnEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_akun_pengunjung);

        //inisialisasi
        etUsername  = findViewById(R.id.et_username_pengunjung);
        etPassword  = findViewById(R.id.et_password_pengunjung);
        btnSimpan   = findViewById(R.id.btn_simpan_akun);
        btnEdit     = findViewById(R.id.btn_edit_akun);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        Intent data2 = getIntent();
        String UsernamePengunjung = data2.getStringExtra("username_pengunjung");
        if (UsernamePengunjung != null) {
            btnSimpan.setVisibility(View.GONE);
            btnEdit.setVisibility(View.VISIBLE);

            etUsername.setText(data2.getStringExtra("username_pengunjung"));

            btnEdit.setOnClickListener(v -> {
                int IdLoginPengunjung = Integer.parseInt(data2.getStringExtra("id_login_pengunjung"));
                String Username = etUsername.getText().toString();
                String Password = etPassword.getText().toString();

                //cek apakah data sudah terisi
                if (TextUtils.isEmpty(Username)) {
                    etUsername.setError("Tidak Boleh Kosong");
                    etUsername.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    etPassword.setError("Tidak Boleh Kosong");
                    etPassword.requestFocus();
                    return;
                }

                editAkunPengunjung(IdLoginPengunjung, Username, Password);

            });

        }

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpan.setOnClickListener(v -> {
            Intent data = getIntent();
            int IdBayi = data.getIntExtra("id_bayi",0);
            String Username = etUsername.getText().toString();
            String Password = etPassword.getText().toString();

            //cek apakah data sudah terisi
            if (TextUtils.isEmpty(Username)) {
                etUsername.setError("Tidak Boleh Kosong");
                etUsername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(Password)) {
                etPassword.setError("Tidak Boleh Kosong");
                etPassword.requestFocus();
                return;
            }

            simpanAkunPengunjung(IdBayi,Username,Password);

        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //Fungsi edit data
    private void editAkunPengunjung(int IdLoginPengunjung, String Username, String Password){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> EditData = ardData.ardUpdateAkun(IdLoginPengunjung,Username,Password);

        EditData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormAkunPengunjung.this, "" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormAkunPengunjung.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi simpan tambah data
    private void simpanAkunPengunjung(int IdBayi, String Username, String Password){
        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertAkun(IdBayi,Username,Password);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormAkunPengunjung.this, "" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormAkunPengunjung.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
