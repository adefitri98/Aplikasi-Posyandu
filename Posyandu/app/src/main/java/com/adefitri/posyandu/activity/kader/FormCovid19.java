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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

public class FormCovid19 extends AppCompatActivity {
    RadioGroup rgMasker, rgCuciTangan, rgJagaJarak, rgBatuk, rgDemam, rgSakitTenggorokan, rgSesakNapas;
    RadioButton pilMasker, pilCuciTangan, pilJagaJarak, pilBatuk, pilDemam, pilSakitTenggorokan, pilSesakNapas;
    TextView tglCovid, idCovid, idBayi;
    Button btnSimpanCovid, btnEditCovid;
    ProgressDialog pd;
    private Calendar myCalendar;
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_covid19);

        //inisialisasi
        rgMasker            = findViewById(R.id.rg_masker);
        rgCuciTangan        = findViewById(R.id.rg_cuci_tangan);
        rgJagaJarak         = findViewById(R.id.rg_jaga_jarak);
        rgBatuk             = findViewById(R.id.rg_batuk);
        rgDemam             = findViewById(R.id.rg_demam);
        rgSakitTenggorokan  = findViewById(R.id.rg_sakit_tenggorokan);
        rgSesakNapas        = findViewById(R.id.rg_sesak_napas);
        tglCovid            = findViewById(R.id.et_tgl_covid);
        idCovid             = findViewById(R.id.et_id_covid);
        idBayi              = findViewById(R.id.et_id_bayi);
        btnSimpanCovid      = findViewById(R.id.btn_simpan_covid);
        btnEditCovid        = findViewById(R.id.btn_edit_covid);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow);

        //Mengambil id bayi
        Intent data3 = getIntent();
        idBayi.setText(String.valueOf(data3.getIntExtra("id_bayi",0)));


        Intent data2 = getIntent();
        String tanggalCovid = data2.getStringExtra("tanggal");
        if (tanggalCovid != null){
            btnSimpanCovid.setVisibility(View.GONE);
            btnEditCovid.setVisibility(View.VISIBLE);

            Intent data = getIntent();
            idBayi.setText(String.valueOf(data.getIntExtra("id_bayi",0)));
            idCovid.setText(String.valueOf(data.getIntExtra("id_covid",0)));
            tglCovid.setText(data.getStringExtra("tanggal"));
            String Masker = data.getStringExtra("masker");
            String CuciTangan = data.getStringExtra("cuci_tangan");
            String JagaJarak = data.getStringExtra("jaga_jarak");
            String Batuk = data.getStringExtra("batuk");
            String Demam = data.getStringExtra("demam");
            String SakitTenggorokan = data.getStringExtra("sakit_tenggorokan");
            String SesakNapas = data.getStringExtra("sesak_napas");

            if (Masker.equals("Ya")){
                rgMasker.check(R.id.rb_ya_masker);
            } else {
                rgMasker.check(R.id.rb_tidak_masker);
            }

            if (CuciTangan.equals("Ya")){
                rgCuciTangan.check(R.id.rb_ya_cc_tgn);
            } else {
                rgCuciTangan.check(R.id.rb_tidak_cc_tgn);
            }

            if (JagaJarak.equals("Ya")){
                rgJagaJarak.check(R.id.rb_ya_jg_jrk);
            } else {
                rgJagaJarak.check(R.id.rb_tidak_jg_jrk);
            }

            if (Batuk.equals("Ada")){
                rgBatuk.check(R.id.rb_ada_batuk);
            } else {
                rgBatuk.check(R.id.rb_tidak_batuk);
            }

            if (Demam.equals("Ada")){
                rgDemam.check(R.id.rb_ada_demam);
            } else {
                rgDemam.check(R.id.rb_tidak_demam);
            }

            if (SakitTenggorokan.equals("Ada")){
                rgSakitTenggorokan.check(R.id.rb_ada_sk_tg);
            } else {
                rgSakitTenggorokan.check(R.id.rb_tidak_sk_tg);
            }

            if (SesakNapas.equals("Ada")){
                rgSesakNapas.check(R.id.rb_ada_ss_nps);
            } else {
                rgSesakNapas.check(R.id.rb_tidak_ss_nps);
            }

            btnEditCovid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Mengambil Data dari Form
                    int IdCovid             = Integer.parseInt(idCovid.getText().toString());

                    String TglCovid     = tglCovid.getText().toString();
                    if (TextUtils.isEmpty(TglCovid)) {
                        tglCovid.setError("Tidak Boleh Kosong");
                        tglCovid.requestFocus();
                        return;
                    }

                    int IdMasker            = rgMasker.getCheckedRadioButtonId();
                    int IdCuciTangan        = rgCuciTangan.getCheckedRadioButtonId();
                    int IdJagaJarak         = rgJagaJarak.getCheckedRadioButtonId();
                    int IdBatuk             = rgBatuk.getCheckedRadioButtonId();
                    int IdDemam             = rgDemam.getCheckedRadioButtonId();
                    int IdSakitTenggorokan  = rgSakitTenggorokan.getCheckedRadioButtonId();
                    int IdSesakNapas        = rgSesakNapas.getCheckedRadioButtonId();

                    if (IdMasker == -1) {
                        Toast.makeText(getBaseContext(), "Masker Belum Dipilih", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (IdCuciTangan == -1) {
                        Toast.makeText(getBaseContext(), "Cuci Tangan Belum Dipilih", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (IdJagaJarak == -1) {
                        Toast.makeText(getBaseContext(), "Jaga Jarak Belum Dipilih", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (IdBatuk == -1) {
                        Toast.makeText(getBaseContext(), "Batuk Belum Dipilih", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (IdDemam == -1) {
                        Toast.makeText(getBaseContext(), "Demam Belum Dipilih", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (IdSakitTenggorokan == -1) {
                        Toast.makeText(getBaseContext(), "Sakit Tenggorokan Belum Dipilih", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (IdSesakNapas == -1) {
                        Toast.makeText(getBaseContext(), "Sesak Napas Belum Dipilih", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    pilMasker               = findViewById(IdMasker);
                    pilCuciTangan           = findViewById(IdCuciTangan);
                    pilJagaJarak            = findViewById(IdJagaJarak);
                    pilBatuk                = findViewById(IdBatuk);
                    pilDemam                = findViewById(IdDemam);
                    pilSakitTenggorokan     = findViewById(IdSakitTenggorokan);
                    pilSesakNapas           = findViewById(IdSesakNapas);

                    String Masker           = pilMasker.getText().toString();
                    String CuciTangan       = pilCuciTangan.getText().toString();
                    String JagaJarak        = pilJagaJarak.getText().toString();
                    String Batuk            = pilBatuk.getText().toString();
                    String Demam            = pilDemam.getText().toString();
                    String SakitTenggorokan = pilSakitTenggorokan.getText().toString();
                    String SesakNapas       = pilSesakNapas.getText().toString();

                    editCovid(Masker,CuciTangan,JagaJarak,Batuk,Demam,SakitTenggorokan,SesakNapas,
                            IdCovid,TglCovid);
                }
            });
        }

        myCalendar = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        tglCovid.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(FormCovid19.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //Menyimpan Data ketika klik tombol simpan
        pd = new ProgressDialog(this);
        btnSimpanCovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Mengambil Data dari Form
                Intent data3 = getIntent();
                idBayi.setText(String.valueOf(data3.getIntExtra("id_bayi",0)));

                int IdBayi          = Integer.parseInt(idBayi.getText().toString());
                String TglCovid     = tglCovid.getText().toString();

                if (TextUtils.isEmpty(TglCovid)) {
                    tglCovid.setError("Tidak Boleh Kosong");
                    tglCovid.requestFocus();
                    return;
                }

                int IdMasker            = rgMasker.getCheckedRadioButtonId();
                int IdCuciTangan        = rgCuciTangan.getCheckedRadioButtonId();
                int IdJagaJarak         = rgJagaJarak.getCheckedRadioButtonId();
                int IdBatuk             = rgBatuk.getCheckedRadioButtonId();
                int IdDemam             = rgDemam.getCheckedRadioButtonId();
                int IdSakitTenggorokan  = rgSakitTenggorokan.getCheckedRadioButtonId();
                int IdSesakNapas        = rgSesakNapas.getCheckedRadioButtonId();

                if (IdMasker == -1) {
                    Toast.makeText(getBaseContext(), "Masker Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdCuciTangan == -1) {
                    Toast.makeText(getBaseContext(), "Cuci Tangan Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdJagaJarak == -1) {
                    Toast.makeText(getBaseContext(), "Jaga Jarak Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdBatuk == -1) {
                    Toast.makeText(getBaseContext(), "Batuk Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdDemam == -1) {
                    Toast.makeText(getBaseContext(), "Demam Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdSakitTenggorokan == -1) {
                    Toast.makeText(getBaseContext(), "Sakit Tenggorokan Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (IdSesakNapas == -1) {
                    Toast.makeText(getBaseContext(), "Sesak Napas Belum Dipilih", Toast.LENGTH_SHORT).show();
                    return;
                }

                pilMasker               = findViewById(IdMasker);
                pilCuciTangan           = findViewById(IdCuciTangan);
                pilJagaJarak            = findViewById(IdJagaJarak);
                pilBatuk                = findViewById(IdBatuk);
                pilDemam                = findViewById(IdDemam);
                pilSakitTenggorokan     = findViewById(IdSakitTenggorokan);
                pilSesakNapas           = findViewById(IdSesakNapas);

                String Masker           = pilMasker.getText().toString();
                String CuciTangan       = pilCuciTangan.getText().toString();
                String JagaJarak        = pilJagaJarak.getText().toString();
                String Batuk            = pilBatuk.getText().toString();
                String Demam            = pilDemam.getText().toString();
                String SakitTenggorokan = pilSakitTenggorokan.getText().toString();
                String SesakNapas       = pilSesakNapas.getText().toString();

                simpanCovid(Masker,CuciTangan,JagaJarak,Batuk,Demam,SakitTenggorokan,SesakNapas,
                        IdBayi,TglCovid);
            }
        });

    }

    //this method call when you press back button
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void editCovid(String Masker, String CuciTangan, String JagaJarak, String Batuk,
                           String Demam, String SakitTenggorokan, String SesakNapas, int IdCovid,
                           String TglCovid){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        //Menyimpan Data
        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardUpdateCovid(IdCovid,TglCovid,
                Masker,CuciTangan,JagaJarak,Batuk,Demam,SakitTenggorokan,SesakNapas);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormCovid19.this, ""+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormCovid19.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void simpanCovid(String Masker, String CuciTangan, String JagaJarak, String Batuk,
                             String Demam, String SakitTenggorokan, String SesakNapas, int IdBayi,
                             String TglCovid){

        pd.setMessage("Menyimpan Data...");
        pd.setCancelable(false);
        pd.show();

        APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ardData.ardInsertCovid(IdBayi,TglCovid,Masker,CuciTangan,
                JagaJarak,Batuk,Demam,SakitTenggorokan,SesakNapas);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                Toast.makeText(FormCovid19.this, ""+pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(FormCovid19.this, "Cek Koneksi Internet", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Fungsi Konversi Tanggal
    private void updateLabel() {
        String myFormat = "dd-MM-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tglCovid.setText(sdf.format(myCalendar.getTime()));
    }
}
