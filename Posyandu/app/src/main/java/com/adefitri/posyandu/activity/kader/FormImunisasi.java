package com.adefitri.posyandu.activity.kader;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

public class FormImunisasi extends AppCompatActivity {
    EditText etIdBayi, etIdImunisasi, etTglImunisasi, etUsiaPemberian;
    Spinner spJenisImunisasi;
    Button btnSimpanImunisasi, btnEditImunisasi;
    ProgressDialog pd;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_imunisasi);

        //inisialisasi
        etIdBayi            = findViewById(R.id.et_id_bayi);
        etIdImunisasi       = findViewById(R.id.et_id_imunisasi);
        etTglImunisasi      = findViewById(R.id.et_tgl_vaksin);
        spJenisImunisasi    = findViewById(R.id.sp_jenis_vaksin);
        etUsiaPemberian     = findViewById(R.id.et_usia_vaksin);
        btnSimpanImunisasi  = findViewById(R.id.btn_simpan_imunisasi);
        btnEditImunisasi    = findViewById(R.id.btn_edit_imunisasi);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);


        //Mengambil id bayi
        Intent data3 = getIntent();
        etIdBayi.setText(String.valueOf(data3.getIntExtra("id_bayi",0)));

        myCalendar = Calendar.getInstance();
        date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        };

        etTglImunisasi.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(FormImunisasi.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Intent data2 = getIntent();
        String UsiaPemberian = data2.getStringExtra("usia_pemberian");
        if (UsiaPemberian != null){
            btnSimpanImunisasi.setVisibility(View.GONE);
            btnEditImunisasi.setVisibility(View.VISIBLE);

            Intent data = getIntent();
            etIdImunisasi.setText(String.valueOf(data.getIntExtra("id_imunisasi",0)));
            etTglImunisasi.setText(data.getStringExtra("tanggal"));
            etUsiaPemberian.setText(data.getStringExtra("usia_pemberian"));
            String jenisimunisasi = data.getStringExtra("jenis_imunisasi");

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.vaksin, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spJenisImunisasi.setAdapter(adapter);
            if (jenisimunisasi != null) {
                int spinnerPosition = adapter.getPosition(jenisimunisasi);
                spJenisImunisasi.setSelection(spinnerPosition);
            }


            btnEditImunisasi.setOnClickListener(v -> {
                int IdImunisasi         = Integer.parseInt(etIdImunisasi.getText().toString());
                String TglImunisasi     = etTglImunisasi.getText().toString();
                String UsiaPemberian12  = etUsiaPemberian.getText().toString();
                String JenisImunisasi   = spJenisImunisasi.getSelectedItem().toString();

                if (TextUtils.isEmpty(TglImunisasi)) {
                    etTglImunisasi.setError("Tidak Boleh Kosong");
                    etTglImunisasi.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(UsiaPemberian12)) {
                    etUsiaPemberian.setError("Tidak Boleh Kosong");
                    etUsiaPemberian.requestFocus();
                    return;
                }

                editImunisasi(IdImunisasi,TglImunisasi, UsiaPemberian12,JenisImunisasi);

            });

        }

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpanImunisasi.setOnClickListener(v -> {
            int IdBayi              = Integer.parseInt(etIdBayi.getText().toString());
            String TglImunisasi     = etTglImunisasi.getText().toString();
            String UsiaPemberian1   = etUsiaPemberian.getText().toString();
            String JenisImunisasi   = spJenisImunisasi.getSelectedItem().toString();

            if (TextUtils.isEmpty(TglImunisasi)) {
                etTglImunisasi.setError("Tidak Boleh Kosong");
                etTglImunisasi.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(UsiaPemberian1)) {
                etUsiaPemberian.setError("Tidak Boleh Kosong");
                etUsiaPemberian.requestFocus();
                return;
            }

            simpanImunisasi(IdBayi,TglImunisasi, UsiaPemberian1,JenisImunisasi);

        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //fungsi edit data
    private void editImunisasi(int IdImunisasi, String TglImunisasi, String UsiaPemberian,
                               String JenisImunisasi){
        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> EditData = ardData.ardUpdateImunisasi(IdImunisasi,TglImunisasi,JenisImunisasi,UsiaPemberian);
        EditData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormImunisasi.this, "" + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormImunisasi.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi simpan tambah data
    private void simpanImunisasi(int IdBayi, String TglImunisasi, String UsiaPemberian,
                                 String JenisImunisasi){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertImunisasi(IdBayi,TglImunisasi,JenisImunisasi,UsiaPemberian);
        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormImunisasi.this, "" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormImunisasi.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi Konversi Tanggal
    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglImunisasi.setText(sdf.format(myCalendar.getTime()));
    }
}

