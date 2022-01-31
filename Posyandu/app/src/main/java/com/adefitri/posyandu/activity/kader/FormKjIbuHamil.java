package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormKjIbuHamil extends AppCompatActivity {
    EditText etIdIbuHamil, etIdKJIbuHamil, etHasilPenimbangan, etPMtPemulihan, etTglKunjungan,
            etUmurKehamilan, etLingkarLengan;
    Button btnSimpanKJ, btnEditKJ;
    ProgressDialog pd;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kj_ibu_hamil);

        //Inisialisasi
        etIdIbuHamil        = findViewById(R.id.et_id_ibu_hamil);
        etIdKJIbuHamil      = findViewById(R.id.et_id_kunjungan_ibu_hamil);
        etHasilPenimbangan  = findViewById(R.id.et_hasil_penimbangan);
        etPMtPemulihan      = findViewById(R.id.et_pmt_pemulihan);
        etTglKunjungan      = findViewById(R.id.et_tgl_kunjungan_ibu_hamil);
        etUmurKehamilan     = findViewById(R.id.et_umur_kehamilan);
        etLingkarLengan     = findViewById(R.id.et_lingkar_lengan);
        btnSimpanKJ         = findViewById(R.id.btn_simpan_kj_ibu_hamil);
        btnEditKJ           = findViewById(R.id.btn_edit_kj_ibu_hamil);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        //Mengambil id ibu hamil
        Intent data3 = getIntent();
        etIdIbuHamil.setText(String.valueOf(data3.getIntExtra("id_ibu_hamil",0)));

        myCalendar = Calendar.getInstance();
        date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        etTglKunjungan.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(FormKjIbuHamil.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Intent data2 = getIntent();
        String HasilPenimbanganCari = data2.getStringExtra("hasil_penimbangan");
        if (HasilPenimbanganCari != null){
            btnSimpanKJ.setVisibility(View.GONE);
            btnEditKJ.setVisibility(View.VISIBLE);

            Intent data = getIntent();
            etIdKJIbuHamil.setText(String.valueOf(data.getIntExtra("id_kunjungan_ibu_hamil",0)));
            etHasilPenimbangan.setText(data.getStringExtra("hasil_penimbangan"));
            etPMtPemulihan.setText(data.getStringExtra("pmt_pemulihan"));
            etTglKunjungan.setText(data.getStringExtra("tanggal"));
            etUmurKehamilan.setText(data.getStringExtra("umur_kehamilan"));
            etLingkarLengan.setText(data.getStringExtra("lingkar_lengan"));

            btnEditKJ.setOnClickListener(v -> {
                pd.setMessage("Menyimpan Data...");
                pd.setCancelable(false);
                pd.show();

                int IdKunjunganIbuHamil = Integer.parseInt(etIdKJIbuHamil.getText().toString());
                String TglKJIbuHamil    = etTglKunjungan.getText().toString();
                String HasilPenimbangan = etHasilPenimbangan.getText().toString();
                String UmurKehamilan = etUmurKehamilan.getText().toString();
                String LingkarLengan = etLingkarLengan.getText().toString();
                String PMTPemulihan     = etPMtPemulihan.getText().toString();

                if (TextUtils.isEmpty(TglKJIbuHamil)) {
                    etTglKunjungan.setError("Tidak Boleh Kosong");
                    etTglKunjungan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(HasilPenimbangan)) {
                    etHasilPenimbangan.setError("Tidak Boleh Kosong");
                    etHasilPenimbangan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(PMTPemulihan)) {
                    etPMtPemulihan.setError("Tidak Boleh Kosong");
                    etPMtPemulihan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(UmurKehamilan)) {
                    etUmurKehamilan.setError("Tidak Boleh Kosong");
                    etUmurKehamilan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(LingkarLengan)) {
                    etLingkarLengan.setError("Tidak Boleh Kosong");
                    etLingkarLengan.requestFocus();
                    return;
                }

                editkunjungan(IdKunjunganIbuHamil,TglKJIbuHamil,HasilPenimbangan,PMTPemulihan,UmurKehamilan,
                        LingkarLengan);
            });

        }

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpanKJ.setOnClickListener(v -> {
            int IdIbuHamil          = Integer.parseInt(etIdIbuHamil.getText().toString());
            String TglKJIbuHamil    = etTglKunjungan.getText().toString();
            String PMTPemulihan     = etPMtPemulihan.getText().toString();
            String HasilPenimbangan = etHasilPenimbangan.getText().toString();
            String UmurKehamilan    = etUmurKehamilan.getText().toString();
            String LingkarLengan    = etLingkarLengan.getText().toString();

            if (TextUtils.isEmpty(TglKJIbuHamil)) {
                etTglKunjungan.setError("Tidak Boleh Kosong");
                etTglKunjungan.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(HasilPenimbangan)) {
                etHasilPenimbangan.setError("Tidak Boleh Kosong");
                etHasilPenimbangan.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(PMTPemulihan)) {
                etPMtPemulihan.setError("Tidak Boleh Kosong");
                etPMtPemulihan.requestFocus();
                return;
            }

            if (TextUtils.isEmpty(UmurKehamilan)) {
                etUmurKehamilan.setError("Tidak Boleh Kosong");
                etUmurKehamilan.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(LingkarLengan)) {
                etLingkarLengan.setError("Tidak Boleh Kosong");
                etLingkarLengan.requestFocus();
                return;
            }

            simpanKJIbuHamil(IdIbuHamil,TglKJIbuHamil,HasilPenimbangan,PMTPemulihan,UmurKehamilan,
                    LingkarLengan);
        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //fungsi edit data
    private void editkunjungan(int IdKunjunganIbuHamil, String TglKJIbuHamil,
                               String HasilPenimbangan, String PMTPemulihan,
                               String UmurKehamilan, String LingkarLengan){

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> EditData = ardData.ardUpdateIBKJ(IdKunjunganIbuHamil,HasilPenimbangan,
                PMTPemulihan,TglKJIbuHamil,UmurKehamilan,LingkarLengan);

        EditData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormKjIbuHamil.this, "" + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormKjIbuHamil.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi simpan tambah data
    private void simpanKJIbuHamil(int IdIbuHamil, String TglKJIbuHamil,
                                  String HasilPenimbangan, String PMTPemulihan,
                                  String UmurKehamilan, String LingkarLengan){
        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertIBKJ(IdIbuHamil,HasilPenimbangan,
                PMTPemulihan,TglKJIbuHamil,UmurKehamilan,LingkarLengan);
        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();

                Toast.makeText(FormKjIbuHamil.this, ""+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormKjIbuHamil.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi Konversi Tanggal
    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglKunjungan.setText(sdf.format(myCalendar.getTime()));
    }
}
