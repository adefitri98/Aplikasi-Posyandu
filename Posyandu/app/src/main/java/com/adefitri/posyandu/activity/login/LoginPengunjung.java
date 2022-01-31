package com.adefitri.posyandu.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.pengunjung.HomePengunjung;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPengunjung extends AppCompatActivity{
    TextView klikPetugas, lupa_password, belum_akun, usernamePengunjung, passwordPengunjung;
    Button btnLoginPengunjung;
    private SessionManager sm;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pengunjung);

        //Inisialisai
        usernamePengunjung  = findViewById(R.id.et_username_pengunjung);
        passwordPengunjung  = findViewById(R.id.et_password_pengunjung);
        klikPetugas         = findViewById(R.id.klik_petugas);
        lupa_password       = findViewById(R.id.lupa_password);
        belum_akun          = findViewById(R.id.belum_akun);
        btnLoginPengunjung  = findViewById(R.id.btn_login_pengunjung);
        sm = new SessionManager(getApplicationContext());
        pd = new ProgressDialog(this);

        klikPetugas.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPengunjung.this, LoginPetugas.class);
            startActivity(intent);
        });

        lupa_password.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPengunjung.this, Info_kader.class);
            startActivity(intent);
        });

        belum_akun.setOnClickListener(v -> {
            Intent intent = new Intent(LoginPengunjung.this, Info_kader.class);
            startActivity(intent);
        });

        btnLoginPengunjung.setOnClickListener(v -> {
            final String username = usernamePengunjung.getText().toString();
            final String password = passwordPengunjung.getText().toString();
            //validating inputs
            if (TextUtils.isEmpty(username)) {
                usernamePengunjung.setError("Please enter username");
                usernamePengunjung.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                passwordPengunjung.setError("Please enter password");
                passwordPengunjung.requestFocus();
                return;
            }

            cekLogin(username,password);
        });
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void cekLogin(String username, String password){
        pd.setMessage("Silahkan Tunggu...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> cekLogin = ardData.ardLoginPengunjung(username,password);

        cekLogin.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                List<DataModel> user = response.body().getData();
                if (kode == 1){
                    pd.hide();
                    sm.storeLoginPengunjung(usernamePengunjung.getText().toString(),user.get(0).getId_login_pengunjung(),user.get(0).getId_bayi2());
                    Intent intent = new Intent(LoginPengunjung.this, HomePengunjung.class);
                    intent.putExtra("username_pengunjung", user.get(0).getUsername_pengunjung());
                    intent.putExtra("id_login_pengunjung", user.get(0).getId_login_pengunjung());
                    intent.putExtra("id_bayi", user.get(0).getId_bayi2());
                    startActivity(intent);
                } else {
                    pd.hide();
                    Toast.makeText(LoginPengunjung.this, "Username & Password Salah", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(LoginPengunjung.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
