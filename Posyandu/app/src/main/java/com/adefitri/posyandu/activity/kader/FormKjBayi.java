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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class FormKjBayi extends AppCompatActivity {
    RadioButton pilVitamin, pilOralit, rbVitamin;
    RadioGroup rgVitamin, rgOralit;
    EditText etIdBayi, etIdKJBayi, etBeratBadan, etTinggiBadan, etLingkarKepala, etUmurSekarang,
            etTglKunjunganBayi;
    Spinner spStatusGizi;
    Button btnSimpanKJBayi, btnEditKJBayi;
    ProgressDialog pd;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_kj_bayi);

        //inisialisasi
        etIdBayi            = findViewById(R.id.et_id_bayi);
        etIdKJBayi          = findViewById(R.id.et_id_kunjungan_bayi);
        etBeratBadan        = findViewById(R.id.et_berat_badan);
        etTinggiBadan       = findViewById(R.id.et_tinggi_badan);
        etLingkarKepala     = findViewById(R.id.et_lingkar_kepala);
        etUmurSekarang      = findViewById(R.id.et_umur_sekarang);
        etTglKunjunganBayi  = findViewById(R.id.et_tgl_kunjungan_bayi);
        rgVitamin           = findViewById(R.id.rg_vitamin);
        rbVitamin           = findViewById(R.id.rb_ya_vit);
        rgOralit            = findViewById(R.id.rg_oralit);
        spStatusGizi        = findViewById(R.id.sp_status_gizi);
        btnSimpanKJBayi     = findViewById(R.id.btn_simpan_kj_bayi);
        btnEditKJBayi       = findViewById(R.id.btn_edit_kj_bayi);

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

        etTglKunjunganBayi.setOnClickListener(v -> {
            // TODO Auto-generated method stub
            new DatePickerDialog(FormKjBayi.this, date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
        });

        Intent data2 = getIntent();
        String BeratBadanCari = data2.getStringExtra("berat_badan");
        if (BeratBadanCari != null){
            btnSimpanKJBayi.setVisibility(View.GONE);
            btnEditKJBayi.setVisibility(View.VISIBLE);

            Intent data = getIntent();
            etIdKJBayi.setText(String.valueOf(data.getIntExtra("id_kunjungan_bayi",0)));
            etTglKunjunganBayi.setText(data.getStringExtra("tanggal"));
            etBeratBadan.setText(data.getStringExtra("berat_badan"));
            etTinggiBadan.setText(data.getStringExtra("tinggi_badan"));
            etLingkarKepala.setText(data.getStringExtra("lingkar_kepala"));
            etUmurSekarang.setText(data.getStringExtra("umur_sekarang"));

            String statusgizi = data.getStringExtra("status_gizi");
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.gizi, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spStatusGizi.setAdapter(adapter);
            if (statusgizi != null) {
                int spinnerPosition = adapter.getPosition(statusgizi);
                spStatusGizi.setSelection(spinnerPosition);
            }

            String VitaminA = data.getStringExtra("vitamin_a");

            if (VitaminA.equals("Ya")){
                rgVitamin.check(R.id.rb_ya_vit);
            } else {
                rgVitamin.check(R.id.rb_tidak_vit);
            }

            String Oralit = data.getStringExtra("oralit");
            if (Oralit.equals("Ya")){
                rgOralit.check(R.id.rb_oralit_ya);
            } else {
                rgOralit.check(R.id.rb_oralit_tidak);
            }

            btnEditKJBayi.setOnClickListener(v -> {
                int IdKunjunganBayi     = Integer.parseInt(etIdKJBayi.getText().toString());
                String TglKJBayi        = etTglKunjunganBayi.getText().toString();
                String BeratBadan       = etBeratBadan.getText().toString();
                String TinggiBadan      = etTinggiBadan.getText().toString();
                String LingkarKepala    = etLingkarKepala.getText().toString();
                String UmurSekarang     = etUmurSekarang.getText().toString();
                String StatusGizi       = spStatusGizi.getSelectedItem().toString();

                //cek apakah data sudah terisi
                if (TextUtils.isEmpty(TglKJBayi)) {
                    etTglKunjunganBayi.setError("Tidak Boleh Kosong");
                    etTglKunjunganBayi.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(BeratBadan)) {
                    etBeratBadan.setError("Tidak Boleh Kosong");
                    etBeratBadan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(TinggiBadan)) {
                    etTinggiBadan.setError("Tidak Boleh Kosong");
                    etTinggiBadan.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(LingkarKepala)) {
                    etLingkarKepala.setError("Tidak Boleh Kosong");
                    etLingkarKepala.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(UmurSekarang)) {
                    etUmurSekarang.setError("Tidak Boleh Kosong");
                    etUmurSekarang.requestFocus();
                    return;
                }

                //Mengambil data RadioButton
                int IdVitaminA = rgVitamin.getCheckedRadioButtonId();
                int IdOralit = rgOralit.getCheckedRadioButtonId();

                //mencek radio button
                if (IdVitaminA == -1) {
                    Toast.makeText(getBaseContext(), "Vitamin A Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdOralit == -1) {
                    Toast.makeText(getBaseContext(), "Oralit Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                //mengambil pilihan radiobutton
                pilVitamin      = findViewById(IdVitaminA);
                pilOralit       = findViewById(IdOralit);
                String VitA     = pilVitamin.getText().toString();
                String Oralit1 = pilOralit.getText().toString();

                editKJBayi(VitA, Oralit1,IdKunjunganBayi,TglKJBayi,BeratBadan,TinggiBadan,
                        LingkarKepala,UmurSekarang,StatusGizi);

            });

        }

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpanKJBayi.setOnClickListener(v -> {
            int IdBayi              = Integer.parseInt(etIdBayi.getText().toString());
            String TglKJBayi        = etTglKunjunganBayi.getText().toString();
            String BeratBadan       = etBeratBadan.getText().toString();
            String TinggiBadan      = etTinggiBadan.getText().toString();
            String LingkarKepala    = etLingkarKepala.getText().toString();
            String UmurSekarang     = etUmurSekarang.getText().toString();
            String StatusGizi       = spStatusGizi.getSelectedItem().toString();

            //cek apakah data sudah terisi
            if (TextUtils.isEmpty(TglKJBayi)) {
                etTglKunjunganBayi.setError("Tidak Boleh Kosong");
                etTglKunjunganBayi.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(BeratBadan)) {
                etBeratBadan.setError("Tidak Boleh Kosong");
                etBeratBadan.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(TinggiBadan)) {
                etTinggiBadan.setError("Tidak Boleh Kosong");
                etTinggiBadan.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(LingkarKepala)) {
                etLingkarKepala.setError("Tidak Boleh Kosong");
                etLingkarKepala.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(UmurSekarang)) {
                etUmurSekarang.setError("Tidak Boleh Kosong");
                etUmurSekarang.requestFocus();
                return;
            }

            //Mengambil Data Radio
            int IdVitaminA = rgVitamin.getCheckedRadioButtonId();
            int IdOralit = rgOralit.getCheckedRadioButtonId();

            if (IdVitaminA == -1) {
                Toast.makeText(getBaseContext(), "Vitamin A Belum Dipilih", Toast.LENGTH_SHORT).show();
                return;
            }

            if (IdOralit == -1) {
                Toast.makeText(getBaseContext(), "Oralit Belum Dipilih", Toast.LENGTH_SHORT).show();
                return;
            }

            pilVitamin = findViewById(IdVitaminA);
            pilOralit = findViewById(IdOralit);
            String VitA             = pilVitamin.getText().toString();
            String Oralit           = pilOralit.getText().toString();

            simpanKJBayi(VitA,Oralit,IdBayi,TglKJBayi,BeratBadan,TinggiBadan,LingkarKepala,
                    UmurSekarang,StatusGizi);
        });
    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //Fungsi edit data
    private void editKJBayi(String VitA, String Oralit, int IdKunjunganBayi, String TglKJBayi,
                            String BeratBadan, String TinggiBadan, String LingkarKepala,
                            String UmurSekarang, String StatusGizi){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> EditData = ardData.ardUpdateBayiKJ(IdKunjunganBayi,
                TglKJBayi,BeratBadan,TinggiBadan,LingkarKepala,UmurSekarang,VitA,
                Oralit,StatusGizi);

        EditData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormKjBayi.this, "" + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormKjBayi.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi simpan tambah data
    private void simpanKJBayi(String VitA,String Oralit,int IdBayi,String TglKJBayi,
                              String BeratBadan,String TinggiBadan,String LingkarKepala,
                              String UmurSekarang,String StatusGizi){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertBayiKJ(IdBayi,TglKJBayi,BeratBadan,
                TinggiBadan,LingkarKepala,UmurSekarang,VitA,Oralit,StatusGizi);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormKjBayi.this, "" +pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormKjBayi.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi Konversi Tanggal
    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        etTglKunjunganBayi.setText(sdf.format(myCalendar.getTime()));
    }
}
