package com.adefitri.posyandu.activity.login;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.adefitri.posyandu.R;
import com.adefitri.posyandu.activity.gizi.HomeGizi;
import com.adefitri.posyandu.activity.kader.HomeKader;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.DataModel;
import com.adefitri.posyandu.model.ResponseModel;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPetugas extends AppCompatActivity implements View.OnClickListener{
    TextView klikPengunjung, usernamePetugas, passwordPetugas;
    Spinner levelPetugas;
    Button btnLoginPetugas;
    private SessionManager sm;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_petugas);

        //Inisialisasi
        usernamePetugas = findViewById(R.id.et_username_petugas);
        passwordPetugas = findViewById(R.id.et_password_petugas);
        levelPetugas    = findViewById(R.id.pil_petugas);
        btnLoginPetugas = findViewById(R.id.btn_login_petugas);
        klikPengunjung  = findViewById(R.id.klik_pengunjung);
        sm = new SessionManager(getApplicationContext());
        pd = new ProgressDialog(this);

        btnLoginPetugas.setOnClickListener(this);
        klikPengunjung.setOnClickListener(this);
    }

    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public void onClick (View v){
        switch (v.getId()){
            case R.id.btn_login_petugas:
                final String Username = usernamePetugas.getText().toString();
                final String Password = passwordPetugas.getText().toString();
                final String Level = levelPetugas.getSelectedItem().toString();
                //validating inputs
                if (TextUtils.isEmpty(Username)) {
                    usernamePetugas.setError("Please enter username");
                    usernamePetugas.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(Password)) {
                    passwordPetugas.setError("Please enter password");
                    passwordPetugas.requestFocus();
                    return;
                }
                if (Level.equals("Kader")){
                    cekLoginPetugas(Username,Password,Level);
                }
                if (Level.equals("Gizi")){
                    cekLoginGizi(Username,Password,Level);
                }

                break;

            case R.id.klik_pengunjung:
                Intent intent = new Intent(LoginPetugas.this, LoginPengunjung.class);
                startActivity(intent);
                break;
        }
    }

    private void cekLoginPetugas(String Username,String Password,String Level){
        pd.setMessage("Silahkan Tunggu...");
        pd.setCancelable(false);
        pd.show();
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> cekLogin = ardData.ardLoginPetugas(Username,Password,Level);
        cekLogin.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                List<DataModel> user = response.body().getData();
                if (kode == 1){
                    pd.hide();
                    sm.storeLoginKader(usernamePetugas.getText().toString(),
                            user.get(0).getId_login_petugas(),user.get(0).getNama_petugas());
                    Intent intent = new Intent(LoginPetugas.this, HomeKader.class);
                    intent.putExtra("username", user.get(0).getUsername_petugas());
                    intent.putExtra("id_login", user.get(0).getId_login_petugas());
                    intent.putExtra("nama_petugas", user.get(0).getNama_petugas());
                    startActivity(intent);

                } else {
                    pd.hide();
                    Toast.makeText(LoginPetugas.this, "Username & Password Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(LoginPetugas.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cekLoginGizi(String Username,String Password,String Level){
        pd.setMessage("Silahkan Tunggu...");
        pd.setCancelable(false);
        pd.show();
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> cekLogin = ardData.ardLoginPetugas(Username,Password,Level);
        cekLogin.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                List<DataModel> user = response.body().getData();
                if (kode == 1){
                    pd.hide();
                    sm.storeLoginGizi(usernamePetugas.getText().toString(),
                            user.get(0).getId_login_petugas(),user.get(0).getNama_petugas());
                    Intent intent = new Intent(LoginPetugas.this, HomeGizi.class);
                    intent.putExtra("username", user.get(0).getUsername_petugas());
                    intent.putExtra("id_login", user.get(0).getId_login_petugas());
                    intent.putExtra("nama_petugas", user.get(0).getNama_petugas());
                    startActivity(intent);

                } else {
                    pd.hide();
                    Toast.makeText(LoginPetugas.this, "Username & Password Salah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pd.hide();
                Toast.makeText(LoginPetugas.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
