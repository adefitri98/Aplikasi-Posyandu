package com.adefitri.posyandu.activity.pengunjung;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.login.Info_kader;
import com.adefitri.posyandu.activity.login.SessionManager;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.ResponseModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAkun extends AppCompatActivity {
    TextView tvUsername, tvPasswordLama, tvPasswordBaru, lupa_password_lama;
    Button btnEdit;
    SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_akun);

        //inisialisasi
        tvUsername          = findViewById(R.id.et_username_pengunjung);
        tvPasswordLama      = findViewById(R.id.et_password_lama);
        tvPasswordBaru      = findViewById(R.id.et_password_baru);
        btnEdit             = findViewById(R.id.btn_edit_akun);
        lupa_password_lama  = findViewById(R.id.lupa_password_lama);
        sm              = new SessionManager(getApplicationContext());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        lupa_password_lama.setOnClickListener(v -> {
            Intent intent = new Intent(FormAkun.this, Info_kader.class);
            startActivity(intent);
        });

        Intent data = getIntent();
        int IdLoginPengunjung = data.getIntExtra("id_pengunjung",0);
        tvUsername.setText(data.getStringExtra("username"));

        btnEdit.setOnClickListener(v -> {
            final String usernamePengunjung = tvUsername.getText().toString();
            final String passwordLama       = tvPasswordLama.getText().toString();
            final String passwordBaru       = tvPasswordBaru.getText().toString();

            //validating inputs
            if (TextUtils.isEmpty(usernamePengunjung)) {
                tvUsername.setError("Masukkan Username");
                tvUsername.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(passwordLama)) {
                tvPasswordLama.setError("Masukkan Password Lama");
                tvPasswordLama.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(passwordBaru)) {
                tvPasswordBaru.setError("Masukkan Password Baru");
                tvPasswordBaru.requestFocus();
                return;
            }

            AlertDialog.Builder dialogPesan = new AlertDialog.Builder(FormAkun.this);
            dialogPesan.setMessage("Apakah Anda Yakin?");
            dialogPesan.setCancelable(true);
            dialogPesan.setPositiveButton("Yakin", (dialogInterface, i) -> {
                SimpanPerubahan(IdLoginPengunjung,usernamePengunjung,passwordLama,passwordBaru);
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

    public void SimpanPerubahan(int IdLoginPengunjung, String usernamePengunjung,
                                String passwordLama, String passwordBaru){

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ardData.ardUpdateAkunPengunjung(IdLoginPengunjung,
                usernamePengunjung,passwordLama,passwordBaru);

        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                if (kode == 1) {
                    sm.Logout();
                } else {
                    Toast.makeText(FormAkun.this, ""+pesan, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormAkun.this, "Cek Koneksi Internet",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
