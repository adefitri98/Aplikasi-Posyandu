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

public class FormIbuHamil extends AppCompatActivity {
    EditText etIdIbuHamil, etTglPendaftaran, etNamaIbuHamil, etNamaSuami, etHamilKe;
    Button btnSimpanIbuHamil, btnEditIbuHamil;
    ProgressDialog pd;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ibu_hamil);

        //Inisialisasi EditText
        etIdIbuHamil        = findViewById(R.id.et_id_ibu_hamil);
        etTglPendaftaran    = findViewById(R.id.et_tgl_pendaftaran);
        etNamaIbuHamil      = findViewById(R.id.et_nama_ibu_hamil);
        etNamaSuami         = findViewById(R.id.et_nama_suami);
        etHamilKe           = findViewById(R.id.et_hamil_ke);
        btnSimpanIbuHamil   = findViewById(R.id.btn_simpan_ibu_hamil);
        btnEditIbuHamil     = findViewById(R.id.btn_edit_ibu_hamil);

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

        etTglPendaftaran.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(FormIbuHamil.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Intent data2 = getIntent();
        String NamaIbuHamil = data2.getStringExtra("nama_ibu_hamil");
        //Aksi Update Data
        if (NamaIbuHamil != null) {
            btnSimpanIbuHamil.setVisibility(View.GONE);
            btnEditIbuHamil.setVisibility(View.VISIBLE);

            Intent data = getIntent();
            etIdIbuHamil.setText(data.getStringExtra("id_ibu_hamil"));
            etNamaIbuHamil.setText(data.getStringExtra("nama_ibu_hamil"));
            etNamaSuami.setText(data.getStringExtra("nama_suami"));
            etHamilKe.setText(data.getStringExtra("hamil_ke"));
            etTglPendaftaran.setText(data.getStringExtra("tanggal"));

            btnEditIbuHamil.setOnClickListener(v -> {
                //Mengambil Data dari Form
                int IdIbuHamil = Integer.parseInt(etIdIbuHamil.getText().toString());
                String NamaIbuHamil12 = etNamaIbuHamil.getText().toString();
                String NamaSuami = etNamaSuami.getText().toString();
                String HamilKe = etHamilKe.getText().toString();
                String TglPendaftaran = etTglPendaftaran.getText().toString();

                //cek apakah semua data sudah terisi
                if (TextUtils.isEmpty(NamaIbuHamil12)) {
                    etNamaIbuHamil.setError("Tidak Boleh Kosong");
                    etNamaIbuHamil.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(NamaSuami)) {
                    etNamaSuami.setError("Tidak Boleh Kosong");
                    etNamaSuami.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(HamilKe)) {
                    etHamilKe.setError("Tidak Boleh Kosong");
                    etHamilKe.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(TglPendaftaran)) {
                    etTglPendaftaran.setError("Tidak Boleh Kosong");
                    etTglPendaftaran.requestFocus();
                    return;
                }

                editIbuHamil(IdIbuHamil, NamaIbuHamil12,NamaSuami,HamilKe,TglPendaftaran);
            });
        }

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpanIbuHamil.setOnClickListener(v -> {
            //Mengambil Data dari Form
            String NamaIbuHamil1    = etNamaIbuHamil.getText().toString();
            String NamaSuami        = etNamaSuami.getText().toString();
            String HamilKe          = etHamilKe.getText().toString();
            String TglPendaftaran   = etTglPendaftaran.getText().toString();

            //cek apakah semua data sudah terisi
            if (TextUtils.isEmpty(TglPendaftaran)) {
                etTglPendaftaran.setError("Tidak Boleh Kosong");
                etTglPendaftaran.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(NamaIbuHamil1)) {
                etNamaIbuHamil.setError("Tidak Boleh Kosong");
                etNamaIbuHamil.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(NamaSuami)) {
                etNamaSuami.setError("Tidak Boleh Kosong");
                etNamaSuami.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(HamilKe)) {
                etHamilKe.setError("Tidak Boleh Kosong");
                etHamilKe.requestFocus();
                return;
            }

            simpanIbuHamil(NamaIbuHamil1,NamaSuami,HamilKe,TglPendaftaran);

        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void simpanIbuHamil(String NamaIbuHamil, String NamaSuami, String HamilKe,
                                String TglPendaftaran){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertIB(NamaIbuHamil,NamaSuami,HamilKe,TglPendaftaran);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormIbuHamil.this, ""+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormIbuHamil.this, "Cek Koneksi Internet",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void editIbuHamil(int IdIbuHamil,String NamaIbuHamil, String NamaSuami, String HamilKe,
                              String TglPendaftaran){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> EditData = ardData.ardUpdateIB(IdIbuHamil,NamaIbuHamil,NamaSuami,
                HamilKe,TglPendaftaran);

        EditData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormIbuHamil.this, "" + pesan, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FormIbuHamil.this, IbuHamil.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormIbuHamil.this, "Cek Koneksi Internet",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglPendaftaran.setText(sdf.format(myCalendar.getTime()));
    }
}
