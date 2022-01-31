package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.adefitri.posyandu.R;
import com.adefitri.posyandu.api.APIRequestData;
import com.adefitri.posyandu.api.RetroServer;
import com.adefitri.posyandu.model.ResponseModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormJadwalPosyandu extends AppCompatActivity {
    EditText etIdJadwal, etTglJadwal, etWaktuJadwal, etAcaraJadwal;
    Button btnSimpanJadwal, btnEditJadwal;
    ProgressDialog pd;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_jadwal_posyandu);

        //Inisialisasi
        etIdJadwal      = findViewById(R.id.et_id_jadwal);
        etTglJadwal     = findViewById(R.id.et_tgl_jadwal);
        etWaktuJadwal   = findViewById(R.id.et_waktu_jadwal);
        etAcaraJadwal   = findViewById(R.id.et_acara_jadwal);
        btnSimpanJadwal = findViewById(R.id.btn_simpan_jadwal);
        btnEditJadwal   = findViewById(R.id.btn_edit_jadwal);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        myCalendar = Calendar.getInstance();
        date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        etTglJadwal.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(FormJadwalPosyandu.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Intent data2 = getIntent();
        String WaktuJadwal = data2.getStringExtra("waktu_jadwal");

        //Aksi Update Data
        if (WaktuJadwal != null) {
            btnSimpanJadwal.setVisibility(View.GONE);
            btnEditJadwal.setVisibility(View.VISIBLE);

            Intent data = getIntent();
            etIdJadwal.setText(String.valueOf(data.getIntExtra("id_jadwal",0)));
            etWaktuJadwal.setText(data.getStringExtra("waktu_jadwal"));
            etAcaraJadwal.setText(data.getStringExtra("acara_jadwal"));
            etTglJadwal.setText(data.getStringExtra("tanggal"));

            btnEditJadwal.setOnClickListener(v -> {
                int IdJadwal = Integer.parseInt(etIdJadwal.getText().toString());
                String TglJadwal = etTglJadwal.getText().toString();
                String WaktuJadwal12 = etWaktuJadwal.getText().toString();
                String AcaraJadwal = etAcaraJadwal.getText().toString();

                if (TextUtils.isEmpty(TglJadwal)) {
                    etTglJadwal.setError("Tidak Boleh Kosong");
                    etTglJadwal.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(WaktuJadwal12)) {
                    etWaktuJadwal.setError("Tidak Boleh Kosong");
                    etWaktuJadwal.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(AcaraJadwal)) {
                    etAcaraJadwal.setError("Tidak Boleh Kosong");
                    etAcaraJadwal.requestFocus();
                    return;
                }

                editJadwal(IdJadwal,TglJadwal, WaktuJadwal12,AcaraJadwal);
            });
        }

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpanJadwal.setOnClickListener(v -> {
            String TglJadwal = etTglJadwal.getText().toString();
            String WaktuJadwal1 = etWaktuJadwal.getText().toString();
            String AcaraJadwal = etAcaraJadwal.getText().toString();

            if (TextUtils.isEmpty(TglJadwal)) {
                etTglJadwal.setError("Tidak Boleh Kosong");
                etTglJadwal.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(WaktuJadwal1)) {
                etWaktuJadwal.setError("Tidak Boleh Kosong");
                etWaktuJadwal.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(AcaraJadwal)) {
                etAcaraJadwal.setError("Tidak Boleh Kosong");
                etAcaraJadwal.requestFocus();
                return;
            }

            simpanJadwal(TglJadwal, WaktuJadwal1,AcaraJadwal);
        });

    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void editJadwal(int IdJadwal, String TglJadwal, String WaktuJadwal, String AcaraJadwal){
        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> EditData = ardData.ardUpdateJP(IdJadwal,TglJadwal,WaktuJadwal,
                AcaraJadwal);

        EditData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormJadwalPosyandu.this, "" + pesan,
                        Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormJadwalPosyandu.this, "Cek Koneksi Internet",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpanJadwal(String TglJadwal, String WaktuJadwal, String AcaraJadwal){
        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertJP(TglJadwal,WaktuJadwal,AcaraJadwal);
        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormJadwalPosyandu.this, ""+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormJadwalPosyandu.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateLabel(){
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglJadwal.setText(sdf.format(myCalendar.getTime()));
    }
}
