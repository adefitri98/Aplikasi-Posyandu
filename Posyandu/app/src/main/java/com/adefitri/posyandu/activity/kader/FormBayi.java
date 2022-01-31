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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class FormBayi extends AppCompatActivity {
    RadioGroup rgJenisKelamin, rgStatus;
    RadioButton pilJenisKelamin, pilStatus;
    EditText etIdBayi, etNamaBayi, etTglLahir, etNamaIbu, etNamaAyah, etKkBayi, etAlamatBayi,
            etAnakKe, etBeratLahir;
    Button btnSimpanBayi, btnEditBayi;
    ProgressDialog pd;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_bayi);

        //Inisialisasi
        etIdBayi        = findViewById(R.id.et_id_bayi);
        etNamaBayi      = findViewById(R.id.et_nama_bayi);
        etTglLahir      = findViewById(R.id.et_tgl_lahir);
        etNamaIbu       = findViewById(R.id.et_nama_ibu);
        etNamaAyah      = findViewById(R.id.et_nama_ayah);
        etKkBayi        = findViewById(R.id.et_kk_bayi);
        etAlamatBayi    = findViewById(R.id.et_alamat_bayi);
        etAnakKe        = findViewById(R.id.et_anak_ke);
        etBeratLahir    = findViewById(R.id.et_berat_lahir);
        rgJenisKelamin  = findViewById(R.id.rg_jenis_kelamin);
        rgStatus        = findViewById(R.id.rg_status);
        btnSimpanBayi   = findViewById(R.id.btn_simpan_bayi);
        btnEditBayi     = findViewById(R.id.btn_edit_bayi);

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

        etTglLahir.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(FormBayi.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Intent data2 = getIntent();
        String NamaIbu = data2.getStringExtra("nama_ibu");
        //Aksi Update Data
        if (NamaIbu != null) {
            btnSimpanBayi.setVisibility(View.GONE);
            btnEditBayi.setVisibility(View.VISIBLE);

            Intent data = getIntent();
            etIdBayi.setText(data.getStringExtra("id_bayi"));
            etNamaBayi.setText(data.getStringExtra("nama_bayi"));
            etNamaIbu.setText(data.getStringExtra("nama_ibu"));
            etNamaAyah.setText(data.getStringExtra("nama_ayah"));
            etKkBayi.setText(data.getStringExtra("kk_bayi"));
            etAlamatBayi.setText(data.getStringExtra("alamat_bayi"));
            etAnakKe.setText(data.getStringExtra("anak_ke"));
            etBeratLahir.setText(data.getStringExtra("berat_lahir"));
            etTglLahir.setText(data.getStringExtra("tanggal"));
            String JenisKelamin = data.getStringExtra("jk_bayi");
            String Status       = data.getStringExtra("status");

            if (JenisKelamin.equals("Laki-Laki")){
                rgJenisKelamin.check(R.id.rb_laki_laki);
            } else {
                rgJenisKelamin.check(R.id.rb_perempuan);
            }

            if (Status.equals("Sasaran")){
                rgStatus.check(R.id.rb_sasaran);
            } else {
                rgStatus.check(R.id.rb_bukan_sasaran);
            }

            btnEditBayi.setOnClickListener(v -> {
                //Mengambil data
                int IdBayi          = Integer.parseInt(etIdBayi.getText().toString());
                String NamaBayi     = etNamaBayi.getText().toString();
                String TglLahir     = etTglLahir.getText().toString();
                String NamaIbu12 = etNamaIbu.getText().toString();
                String NamaAyah     = etNamaAyah.getText().toString();
                String KKBayi       = etKkBayi.getText().toString();
                String AlamatBayi   = etAlamatBayi.getText().toString();
                String AnakKe       = etAnakKe.getText().toString();
                String BeratLahir   = etBeratLahir.getText().toString();

                //cek apakah data sudah terisi
                if (TextUtils.isEmpty(NamaBayi)) {
                    etNamaBayi.setError("Tidak Boleh Kosong");
                    etNamaBayi.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(TglLahir)) {
                    etTglLahir.setError("Tidak Boleh Kosong");
                    etTglLahir.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(NamaIbu12)) {
                    etNamaIbu.setError("Tidak Boleh Kosong");
                    etNamaIbu.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(NamaAyah)) {
                    etNamaAyah.setError("Tidak Boleh Kosong");
                    etNamaAyah.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(KKBayi)) {
                    etKkBayi.setError("Tidak Boleh Kosong");
                    etKkBayi.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(AlamatBayi)) {
                    etAlamatBayi.setError("Tidak Boleh Kosong");
                    etAlamatBayi.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(AnakKe)) {
                    etAnakKe.setError("Tidak Boleh Kosong");
                    etAnakKe.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(BeratLahir)) {
                    etBeratLahir.setError("Tidak Boleh Kosong");
                    etBeratLahir.requestFocus();
                    return;
                }

                int IdJenisKelamin  = rgJenisKelamin.getCheckedRadioButtonId();
                int IdStatus        = rgStatus.getCheckedRadioButtonId();

                if (IdJenisKelamin == -1) {
                    Toast.makeText(getBaseContext(), "Jenis Kelamin Belum Dipilih",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdStatus == -1) {
                    Toast.makeText(getBaseContext(), "Status Bayi Belum Dipilih",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                pilJenisKelamin         = findViewById(IdJenisKelamin);
                pilStatus               = findViewById(IdStatus);
                String JenisKelamin1    = pilJenisKelamin.getText().toString();
                String Status1          = pilStatus.getText().toString();

                editBayi(IdBayi, JenisKelamin1, Status1, NamaBayi, TglLahir, NamaIbu12, NamaAyah,
                        KKBayi, AlamatBayi, AnakKe, BeratLahir);

            });
        }

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpanBayi.setOnClickListener(v -> {
            //Mengambil data
            String NamaBayi     = etNamaBayi.getText().toString();
            String TglLahir     = etTglLahir.getText().toString();
            String NamaIbu1     = etNamaIbu.getText().toString();
            String NamaAyah     = etNamaAyah.getText().toString();
            String KKBayi       = etKkBayi.getText().toString();
            String AlamatBayi   = etAlamatBayi.getText().toString();
            String AnakKe       = etAnakKe.getText().toString();
            String BeratLahir   = etBeratLahir.getText().toString();

            //cek apakah semua data sudah terisi
            if (TextUtils.isEmpty(NamaBayi)) {
                etNamaBayi.setError("Tidak Boleh Kosong");
                etNamaBayi.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(TglLahir)) {
                etTglLahir.setError("Tidak Boleh Kosong");
                etTglLahir.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(NamaIbu1)) {
                etNamaIbu.setError("Tidak Boleh Kosong");
                etNamaIbu.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(NamaAyah)) {
                etNamaAyah.setError("Tidak Boleh Kosong");
                etNamaAyah.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(KKBayi)) {
                etKkBayi.setError("Tidak Boleh Kosong");
                etKkBayi.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(AlamatBayi)) {
                etAlamatBayi.setError("Tidak Boleh Kosong");
                etAlamatBayi.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(AnakKe)) {
                etAnakKe.setError("Tidak Boleh Kosong");
                etAnakKe.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(BeratLahir)) {
                etBeratLahir.setError("Tidak Boleh Kosong");
                etBeratLahir.requestFocus();
                return;
            }

            int IdJenisKelamin  = rgJenisKelamin.getCheckedRadioButtonId();
            int IdStatus        = rgStatus.getCheckedRadioButtonId();

            if (IdJenisKelamin == -1) {
                Toast.makeText(getBaseContext(), "Jenis Kelamin Belum Dipilih",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            if (IdStatus == -1) {
                Toast.makeText(getBaseContext(), "Status Bayi Belum Dipilih",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            pilJenisKelamin     = findViewById(IdJenisKelamin);
            pilStatus           = findViewById(IdStatus);
            String JenisKelamin = pilJenisKelamin.getText().toString();
            String Status       = pilStatus.getText().toString();

            simpanBayi(JenisKelamin, Status, NamaBayi, TglLahir, NamaIbu1, NamaAyah, KKBayi,
                    AlamatBayi, AnakKe, BeratLahir);

        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void editBayi(int IdBayi, String JenisKelamin, String Status, String NamaBayi,
                          String TglLahir, String NamaIbu, String NamaAyah, String KKBayi,
                          String AlamatBayi, String AnakKe, String BeratLahir){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> EditData = ardData.ardUpdateBayi(IdBayi,NamaBayi,TglLahir,JenisKelamin,
                NamaIbu,NamaAyah,KKBayi,AlamatBayi,AnakKe,BeratLahir,Status);

        EditData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormBayi.this, "" + pesan, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(FormBayi.this, Bayi.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormBayi.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpanBayi(String JenisKelamin, String Status, String NamaBayi, String TglLahir,
                            String NamaIbu, String NamaAyah, String KKBayi, String AlamatBayi,
                            String AnakKe, String BeratLahir){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertBayi(NamaBayi,TglLahir,JenisKelamin,
                NamaIbu,NamaAyah,KKBayi,AlamatBayi,AnakKe,BeratLahir,Status);
        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormBayi.this, ""+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormBayi.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglLahir.setText(sdf.format(myCalendar.getTime()));
    }
}
