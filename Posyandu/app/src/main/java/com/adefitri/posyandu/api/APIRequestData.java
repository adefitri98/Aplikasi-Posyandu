package com.adefitri.posyandu.api;

import com.adefitri.posyandu.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    //Fungsi Memanggil API untuk Login Pengunjung
    @FormUrlEncoded
    @POST("login/CekLoginPengunjung.php")
    Call<ResponseModel> ardLoginPengunjung(
            @Field("username_pengunjung") String username_pengunjung,
            @Field("password_pengunjung") String password_pengunjung
    );

    //Fungsi Memanggil API untuk Profil Pengunjung
    @FormUrlEncoded
    @POST("login/ProfilPengunjung.php")
    Call<ResponseModel> ardReadProfilBayi(
            @Field("id_bayi") int id_bayi
    );

    //Fungsi Memanggil API untuk Edit Akun Pengunjung
    @FormUrlEncoded
    @POST("login/UpdateAkunPengunjung.php")
    Call<ResponseModel> ardUpdateAkunPengunjung(
            @Field("id_login_pengunjung") int id_login_pengunjung,
            @Field("username_pengunjung") String username_pengunjung,
            @Field("password_lama") String password_lama,
            @Field("password_baru") String Password_baru
    );

    //Fungsi Memanggil API untuk Edit Akun Pengunjung
    @FormUrlEncoded
    @POST("login/UpdateAkunPetugas.php")
    Call<ResponseModel> ardUpdateAkunPetugas(
            @Field("id_login_petugas") int id_login_petugas,
            @Field("nama_petugas") String nama_petugas,
            @Field("username_petugas") String username_petugas,
            @Field("password_baru") String Password_baru
    );

    //Fungsi Memanggil API untuk Login Petugas
    @FormUrlEncoded
    @POST("login/CekLoginPetugas.php")
    Call<ResponseModel> ardLoginPetugas(
            @Field("username") String username,
            @Field("password") String password,
            @Field("level") String level
    );

    //Fungsi Memanggil API untuk Read Berita
    @GET("kader/beritacovid/Read.php")
    Call<ResponseModel> ardReadBerita();

    //Fungsi Memanggil API untuk Create Jadwal Posyandu
    @FormUrlEncoded
    @POST("kader/jadwalposyandu/Insert.php")
    Call<ResponseModel> ardInsertJP(
            @Field("tgl_jadwal") String tgl_jadwal,
            @Field("waktu_jadwal") String waktu_jadwal,
            @Field("acara_jadwal") String acara_jadwal
    );

    //Fungsi Memanggil API untuk Read Jadwal Posyandu
    @GET("kader/jadwalposyandu/Read.php")
    Call<ResponseModel> ardReadJP();

    //Fungsi Memanggil API untuk Update Jadwal Posyandu
    @FormUrlEncoded
    @POST("kader/jadwalposyandu/Update.php")
    Call<ResponseModel> ardUpdateJP(
            @Field("id_jadwal") int id_jadwal,
            @Field("tgl_jadwal") String tgl_jadwal,
            @Field("waktu_jadwal") String waktu_jadwal,
            @Field("acara_jadwal") String acara_jadwal
    );

    //Fungsi Memanggil API untuk Delete Jadwal Posyandu
    @FormUrlEncoded
    @POST("kader/jadwalposyandu/Delete.php")
    Call<ResponseModel> ardDeleteJP(
            @Field("id_jadwal") int id_jadwal
    );

    //Fungsi Memanggil API untuk Read Kunjungan Gizi
    @GET("gizi/ReadKunjungan.php")
    Call<ResponseModel> ardReadKunjunganGizi();

    //Fungsi Memanggil API untuk Read Kunjungan Gizi Tanggal
    @FormUrlEncoded
    @POST("gizi/ReadKunjunganTanggal.php")
    Call<ResponseModel> ardReadKunjunganPosyanduTanggal(
            @Field("tanggal") String tanggal
    );

    //Fungsi Memanggil API untuk Create Ibu Hamil
    @FormUrlEncoded
    @POST("kader/ibuhamil/Insert.php")
    Call<ResponseModel> ardInsertIB(
            @Field("nama_ibu_hamil") String nama_ibu_hamil,
            @Field("nama_suami") String nama_suami,
            @Field("hamil_ke") String hamil_ke,
            @Field("tanggal_pendaftaran") String tanggal_pendaftaran
    );

    //Fungsi Memanggil API untuk Read Ibu Hamil
    @GET("kader/ibuhamil/Read.php")
    Call<ResponseModel> ardReadIB();

    //Fungsi Memanggil API untuk Read Ibu Hamil Search
    @FormUrlEncoded
    @POST("kader/ibuhamil/ReadSearch.php")
    Call<ResponseModel> ardReadIBSearch(
            @Field("search") String search
    );

    //Fungsi Memanggil API untuk Update Ibu Hamil
    @FormUrlEncoded
    @POST("kader/ibuhamil/Update.php")
    Call<ResponseModel> ardUpdateIB(
            @Field("id_ibu_hamil") int id_ibu_hamil,
            @Field("nama_ibu_hamil") String nama_ibu_hamil,
            @Field("nama_suami") String nama_suami,
            @Field("hamil_ke") String hamil_ke,
            @Field("tanggal_pendaftaran") String tanggal_pendaftaran
    );

    //Fungsi Memanggil API untuk Delete Ibu Hamil
    @FormUrlEncoded
    @POST("kader/ibuhamil/Delete.php")
    Call<ResponseModel> ardDeleteIB(
            @Field("id_ibu_hamil") int id_ibu_hamil
    );

    //Fungsi Memanggil API untuk Create Kunjungan Ibu Hamil
    @FormUrlEncoded
    @POST("kader/ibuhamil/kunjungan/Insert.php")
    Call<ResponseModel> ardInsertIBKJ(
            @Field("id_ibu_hamil") int id_ibu_hamil,
            @Field("hasil_penimbangan") String hasil_penimbangan,
            @Field("pmt_pemulihan") String pmt_pemulihan,
            @Field("tanggal_kunjungan") String tanggal_kunjungan,
            @Field("umur_kehamilan") String umur_kehamilan,
            @Field("lingkar_lengan") String lingkar_lengan
    );

    //Fungsi Memanggil API untuk Read Kunjungan Ibu Hamil
    @FormUrlEncoded
    @POST("kader/ibuhamil/kunjungan/Read.php")
    Call<ResponseModel> ardReadIBKJ(
            @Field("id_ibu_hamil") int id_ibu_hamil
    );

    //Fungsi Memanggil API untuk Update Kunjungan Ibu Hamil
    @FormUrlEncoded
    @POST("kader/ibuhamil/kunjungan/Update.php")
    Call<ResponseModel> ardUpdateIBKJ(
            @Field("id_kunjungan_ibu_hamil") int id_kunjungan_ibu_hamil,
            @Field("hasil_penimbangan") String hasil_penimbangan,
            @Field("pmt_pemulihan") String pmt_pemulihan,
            @Field("tanggal_kunjungan") String tanggal_kunjungan,
            @Field("umur_kehamilan") String umur_kehamilan,
            @Field("lingkar_lengan") String lingkar_lengan
    );

    //Fungsi Memanggil API untuk Delete Kunjungan Ibu Hamil
    @FormUrlEncoded
    @POST("kader/ibuhamil/kunjungan/Delete.php")
    Call<ResponseModel> ardDeleteIBKJ(
            @Field("id_kunjungan_ibu_hamil") int id_kunjungan_ibu_hamil
    );

    //Fungsi Memanggil API untuk Create Bayi
    @FormUrlEncoded
    @POST("kader/bayi/Insert.php")
    Call<ResponseModel> ardInsertBayi(
            @Field("nama_bayi") String nama_bayi,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jk_bayi") String jk_bayi,
            @Field("nama_ibu") String nama_ibu,
            @Field("nama_ayah") String nama_ayah,
            @Field("kk_bayi") String kk_bayi,
            @Field("alamat_bayi") String alamat_bayi,
            @Field("anak_ke") String anak_ke,
            @Field("berat_lahir") String berat_lahir,
            @Field("sasaran") String sasaran
    );

    //Fungsi Memanggil API untuk Read Bayi
    @GET("kader/bayi/Read.php")
    Call<ResponseModel> ardReadBayi();

    //Fungsi Memanggil API untuk Read Bayi
    @FormUrlEncoded
    @POST("kader/bayi/ReadSearch.php")
    Call<ResponseModel> ardReadBayiSearch(
            @Field("search") String search
    );

    //Fungsi Memanggil API untuk Update Bayi
    @FormUrlEncoded
    @POST("kader/bayi/Update.php")
    Call<ResponseModel> ardUpdateBayi(
            @Field("id_bayi") int id_bayi,
            @Field("nama_bayi") String nama_bayi,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("jk_bayi") String jk_bayi,
            @Field("nama_ibu") String nama_ibu,
            @Field("nama_ayah") String nama_ayah,
            @Field("kk_bayi") String kk_bayi,
            @Field("alamat_bayi") String alamat_bayi,
            @Field("anak_ke") String anak_ke,
            @Field("berat_lahir") String berat_lahir,
            @Field("sasaran") String sasaran
    );

    //Fungsi Memanggil API untuk Delete Bayi
    @FormUrlEncoded
    @POST("kader/bayi/Delete.php")
    Call<ResponseModel> ardDeleteBayi(
            @Field("id_bayi") int id_bayi
    );

    //Fungsi Memanggil API untuk Create Kunjungan Bayi
    @FormUrlEncoded
    @POST("kader/bayi/kunjungan/Insert.php")
    Call<ResponseModel> ardInsertBayiKJ(
            @Field("id_bayi") int id_bayi,
            @Field("tanggal_kunjungan_bayi") String tanggal_kunjungan_bayi,
            @Field("berat_badan") String berat_badan,
            @Field("tinggi_badan") String tinggi_badan,
            @Field("lingkar_kepala") String lingkar_kepala,
            @Field("umur_sekarang") String umur_sekarang,
            @Field("vit_a") String vit_a,
            @Field("oralit") String oralit,
            @Field("status_gizi") String status_gizi
    );

    //Fungsi Memanggil API untuk Read Kunjungan Bayi
    @FormUrlEncoded
    @POST("kader/bayi/kunjungan/Read.php")
    Call<ResponseModel> ardReadBayiKJ(
            @Field("id_bayi") int id_bayi
    );

    //Fungsi Memanggil API untuk Update Kunjungan Bayi
    @FormUrlEncoded
    @POST("kader/bayi/kunjungan/Update.php")
    Call<ResponseModel> ardUpdateBayiKJ(
            @Field("id_kunjungan_bayi") int id_kunjungan_bayi,
            @Field("tanggal_kunjungan_bayi") String tanggal_kunjungan_bayi,
            @Field("berat_badan") String berat_badan,
            @Field("tinggi_badan") String tinggi_badan,
            @Field("lingkar_kepala") String lingkar_kepala,
            @Field("umur_sekarang") String umur_sekarang,
            @Field("vit_a") String vit_a,
            @Field("oralit") String oralit,
            @Field("status_gizi") String status_gizi
    );

    //Fungsi Memanggil API untuk Delete Kunjungan Bayi
    @FormUrlEncoded
    @POST("kader/bayi/kunjungan/Delete.php")
    Call<ResponseModel> ardDeleteBayiKJ(
            @Field("id_kunjungan_bayi") int id_kunjungan_bayi
    );

    //Fungsi Memanggil API untuk Create Imunisasi
    @FormUrlEncoded
    @POST("kader/bayi/imunisasi/Insert.php")
    Call<ResponseModel> ardInsertImunisasi(
            @Field("id_bayi") int id_bayi,
            @Field("tanggal_imunisasi") String tanggal_imunisasi,
            @Field("jenis_imunisasi") String jenis_imunisasi,
            @Field("usia_pemberian") String usia_pemberian
    );

    //Fungsi Memanggil API untuk Read Imunisasi
    @FormUrlEncoded
    @POST("kader/bayi/imunisasi/Read.php")
    Call<ResponseModel> ardReadImunisasi(
            @Field("id_bayi") int id_bayi
    );

    //Fungsi Memanggil API untuk Update Imunisasi
    @FormUrlEncoded
    @POST("kader/bayi/imunisasi/Update.php")
    Call<ResponseModel> ardUpdateImunisasi(
            @Field("id_imunisasi") int id_imunisasi,
            @Field("tanggal_imunisasi") String tanggal_imunisasi,
            @Field("jenis_imunisasi") String jenis_imunisasi,
            @Field("usia_pemberian") String usia_pemberian
    );

    //Fungsi Memanggil API untuk Delete Imunisasi
    @FormUrlEncoded
    @POST("kader/bayi/imunisasi/Delete.php")
    Call<ResponseModel> ardDeleteImunisasi(
            @Field("id_imunisasi") int id_imunisasi
    );

    //Fungsi Memanggil API untuk Create Data Covid-19
    @FormUrlEncoded
    @POST("kader/bayi/covid19/Insert.php")
    Call<ResponseModel> ardInsertCovid(
            @Field("id_bayi") int id_bayi,
            @Field("tanggal_covid") String tanggal_covid,
            @Field("masker") String masker,
            @Field("cuci_tangan") String cuci_tangan,
            @Field("jaga_jarak") String jaga_jarak,
            @Field("batuk") String batuk,
            @Field("demam") String demam,
            @Field("sakit_tenggorokan") String sakit_tenggorokan,
            @Field("sesak_napas") String sesak_napas
    );

    //Fungsi Memanggil API untuk Read Data Covid-19
    @FormUrlEncoded
    @POST("kader/bayi/covid19/Read.php")
    Call<ResponseModel> ardReadCovid(
            @Field("id_bayi") int id_bayi
    );

    //Fungsi Memanggil API untuk Update Data Covid-19
    @FormUrlEncoded
    @POST("kader/bayi/covid19/Update.php")
    Call<ResponseModel> ardUpdateCovid(
            @Field("id_covid") int id_covid,
            @Field("tanggal_covid") String tanggal_covid,
            @Field("masker") String masker,
            @Field("cuci_tangan") String cuci_tangan,
            @Field("jaga_jarak") String jaga_jarak,
            @Field("batuk") String batuk,
            @Field("demam") String demam,
            @Field("sakit_tenggorokan") String sakit_tenggorokan,
            @Field("sesak_napas") String sesak_napas
    );

    //Fungsi Memanggil API untuk Delete Data COvid-19
    @FormUrlEncoded
    @POST("kader/bayi/covid19/Delete.php")
    Call<ResponseModel> ardDeleteCovid(
            @Field("id_covid") int id_covid
    );

    //Fungsi Memanggil API untuk Create Akun
    @FormUrlEncoded
    @POST("kader/bayi/akun/Insert.php")
    Call<ResponseModel> ardInsertAkun(
            @Field("id_bayi") int id_bayi,
            @Field("username_pengunjung") String username_pengunjung,
            @Field("password_pengunjung") String password_pengunjung
    );

    //Fungsi Memanggil API untuk Read Akun
    @FormUrlEncoded
    @POST("kader/bayi/akun/Read.php")
    Call<ResponseModel> ardReadAkun(
            @Field("id_bayi") int id_bayi
    );

    //Fungsi Memanggil API untuk Update Akun
    @FormUrlEncoded
    @POST("kader/bayi/akun/Update.php")
    Call<ResponseModel> ardUpdateAkun(
            @Field("id_login_pengunjung") int id_login_pengunjung,
            @Field("username_pengunjung") String username_pengunjung,
            @Field("password_pengunjung") String password_pengunjung
    );

    //Fungsi Memanggil API untuk Delete Akun
    @FormUrlEncoded
    @POST("kader/bayi/akun/Delete.php")
    Call<ResponseModel> ardDeleteAkun(
            @Field("id_login_pengunjung") int id_login_pengunjung
    );

}
