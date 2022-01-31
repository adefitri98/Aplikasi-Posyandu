package com.adefitri.posyandu.model;

import java.util.Date;

public class DataModel {
    //tb_ibu_hamil
    private int id_ibu_hamil;
    private int id_bayi;
    private int id_jadwal;
    private int id_kunjungan_bayi;
    private int id_kunjungan_ibu_hamil;
    private int id_sasaran;
    private int id_imunisasi;
    private int id_covid;
    private int id_berita;

    public int getId_imunisasi() {
        return id_imunisasi;
    }

    public void setId_imunisasi(int id_imunisasi) {
        this.id_imunisasi = id_imunisasi;
    }

    public String getTanggal_imunisasi() {
        return tanggal_imunisasi;
    }

    public void setTanggal_imunisasi(String tanggal_imunisasi) {
        this.tanggal_imunisasi = tanggal_imunisasi;
    }

    public String getJenis_imunisasi() {
        return jenis_imunisasi;
    }

    public void setJenis_imunisasi(String jenis_imunisasi) {
        this.jenis_imunisasi = jenis_imunisasi;
    }

    public String getUsia_pemberian() {
        return usia_pemberian;
    }

    public void setUsia_pemberian(String usia_pemberian) {
        this.usia_pemberian = usia_pemberian;
    }

    public int getId_bayi() {
        return id_bayi;
    }

    public void setId_bayi(int id_bayi) {
        this.id_bayi = id_bayi;
    }

    public int getId_kunjungan_bayi() {
        return id_kunjungan_bayi;
    }

    public void setId_kunjungan_bayi(int id_kunjungan_bayi) {
        this.id_kunjungan_bayi = id_kunjungan_bayi;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getTinggi_badan() {
        return tinggi_badan;
    }

    public void setTinggi_badan(String tinggi_badan) {
        this.tinggi_badan = tinggi_badan;
    }

    public String getLingkar_kepala() {
        return lingkar_kepala;
    }

    public void setLingkar_kepala(String lingkar_kepala) {
        this.lingkar_kepala = lingkar_kepala;
    }

    public String getUmur_sekarang() {
        return umur_sekarang;
    }

    public void setUmur_sekarang(String umur_sekarang) {
        this.umur_sekarang = umur_sekarang;
    }

    public String getVitamin_a() {
        return vitamin_a;
    }

    public void setVitamin_a(String vitamin_a) {
        this.vitamin_a = vitamin_a;
    }

    public String getOralit() {
        return oralit;
    }

    public void setOralit(String oralit) {
        this.oralit = oralit;
    }

    public String getTanggal_kunjungan_bayi() {
        return tanggal_kunjungan_bayi;
    }

    public void setTanggal_kunjungan_bayi(String tanggal_kunjungan_bayi) {
        this.tanggal_kunjungan_bayi = tanggal_kunjungan_bayi;
    }

    public String getStatus_gizi() {
        return status_gizi;
    }

    public void setStatus_gizi(String status_gizi) {
        this.status_gizi = status_gizi;
    }

    public int getId_sasaran() {
        return id_sasaran;
    }

    public void setId_sasaran(int id_sasaran) {
        this.id_sasaran = id_sasaran;
    }

    public int getId_kunjungan_ibu_hamil() {
        return id_kunjungan_ibu_hamil;
    }

    public void setId_kunjungan_ibu_hamil(int id_kunjungan_ibu_hamil) {
        this.id_kunjungan_ibu_hamil = id_kunjungan_ibu_hamil;
    }

    public int getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(int id_jadwal) {
        this.id_jadwal = id_jadwal;
    }

    public int getId_ibu_hamil() {
        return id_ibu_hamil;
    }

    public void setId_ibu_hamil(int id_ibu_hamil) {
        this.id_ibu_hamil = id_ibu_hamil;
    }

    public String getNama_ibu_hamil() {
        return nama_ibu_hamil;
    }

    public void setNama_ibu_hamil(String nama_ibu_hamil) {
        this.nama_ibu_hamil = nama_ibu_hamil;
    }

    public String getNama_suami() {
        return nama_suami;
    }

    public void setNama_suami(String nama_suami) {
        this.nama_suami = nama_suami;
    }

    public String getUmur_kehamilan() {
        return umur_kehamilan;
    }

    public void setUmur_kehamilan(String umur_kehamilan) {
        this.umur_kehamilan = umur_kehamilan;
    }

    public String getHamil_ke() {
        return hamil_ke;
    }

    public void setHamil_ke(String hamil_ke) {
        this.hamil_ke = hamil_ke;
    }

    public String getLingkar_lengan() {
        return lingkar_lengan;
    }

    public void setLingkar_lengan(String lingkar_lengan) {
        this.lingkar_lengan = lingkar_lengan;
    }

    public String getWaktu_jadwal() {
        return waktu_jadwal;
    }

    public void setWaktu_jadwal(String waku_jadwal) {
        this.waktu_jadwal = waktu_jadwal;
    }

    public String getAcara_jadwal() {
        return acara_jadwal;
    }

    public void setAcara_jadwal(String acara_jadwal) {
        this.acara_jadwal = acara_jadwal;
    }

    public String getTanggal_pendaftaran() {
        return tanggal_pendaftaran;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getBln() {
        return bln;
    }

    public void setBln(String bln) {
        this.bln = bln;
    }

    public void setTanggal_pendaftaran(String tanggal_pendaftaran) {
        this.tanggal_pendaftaran = tanggal_pendaftaran;
    }

    private String id_login_pengunjung, id_login_petugas, id_bayi2;

    public String getId_bayi2() {
        return id_bayi2;
    }

    public void setId_bayi2(String id_bayi2) {
        this.id_bayi2 = id_bayi2;
    }

    public String getId_login_pengunjung() {
        return id_login_pengunjung;
    }

    public void setId_login_pengunjung(String id_login_pengunjung) {
        this.id_login_pengunjung = id_login_pengunjung;
    }

    public String getId_login_petugas() {
        return id_login_petugas;
    }

    public void setId_login_petugas(String id_login_petugas) {
        this.id_login_petugas = id_login_petugas;
    }

    private String nama_ibu_hamil, nama_suami, umur_kehamilan, hamil_ke, lingkar_lengan, tanggal_pendaftaran;
    private String nama_bayi, tgl_lahir, jk_bayi, nama_ibu, nama_ayah, kk_bayi, alamat_bayi, anak_ke, berat_lahir, sasaran;
    private String tgl, bln, waktu_jadwal, acara_jadwal;
    private String hasil_penimbangan, pmt_pemulihan, tanggal_kunjungan;
    private String berat_badan, tinggi_badan, lingkar_kepala, umur_sekarang, vitamin_a, oralit, tanggal_kunjungan_bayi, status_gizi;
    private String tanggal;
    private String tanggal_imunisasi, jenis_imunisasi, usia_pemberian;
    private String masker, cuci_tangan, jaga_jarak, demam, batuk, sesak_napas, sakit_tenggorokan, tanggal_covid;
    private String username_sasaran, username, password_sasaran, password;
    private String username_pengunjung, password_pengunjung, nama_petugas, username_petugas, password_petugas, level;
    private String total_bayi, total_ibu_hamil;
    private String judul_berita, isi_berita, tanggal_berita, sumber_berita, gambar;

    public String getSumber_berita() {
        return sumber_berita;
    }

    public void setSumber_berita(String sumber_berita) {
        this.sumber_berita = sumber_berita;
    }

    public int getId_berita() {
        return id_berita;
    }

    public void setId_berita(int id_berita) {
        this.id_berita = id_berita;
    }

    public String getJudul_berita() {
        return judul_berita;
    }

    public void setJudul_berita(String judul_berita) {
        this.judul_berita = judul_berita;
    }

    public String getIsi_berita() {
        return isi_berita;
    }

    public void setIsi_berita(String isi_berita) {
        this.isi_berita = isi_berita;
    }

    public String getTanggal_berita() {
        return tanggal_berita;
    }

    public void setTanggal_berita(String tanggal_berita) {
        this.tanggal_berita = tanggal_berita;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getNama_petugas() {
        return nama_petugas;
    }

    public void setNama_petugas(String nama_petugas) {
        this.nama_petugas = nama_petugas;
    }

    public String getUsername_pengunjung() {
        return username_pengunjung;
    }

    public void setUsername_pengunjung(String username_pengunjung) {
        this.username_pengunjung = username_pengunjung;
    }

    public String getPassword_pengunjung() {
        return password_pengunjung;
    }

    public void setPassword_pengunjung(String password_pengunjung) {
        this.password_pengunjung = password_pengunjung;
    }

    public String getUsername_petugas() {
        return username_petugas;
    }

    public void setUsername_petugas(String username_petugas) {
        this.username_petugas = username_petugas;
    }

    public String getPassword_petugas() {
        return password_petugas;
    }

    public void setPassword_petugas(String password_petugas) {
        this.password_petugas = password_petugas;
    }

    public int getId_covid() {
        return id_covid;
    }

    public void setId_covid(int id_covid) {
        this.id_covid = id_covid;
    }

    public String getMasker() {
        return masker;
    }

    public void setMasker(String masker) {
        this.masker = masker;
    }

    public String getCuci_tangan() {
        return cuci_tangan;
    }

    public void setCuci_tangan(String cuci_tangan) {
        this.cuci_tangan = cuci_tangan;
    }

    public String getJaga_jarak() {
        return jaga_jarak;
    }

    public void setJaga_jarak(String jaga_jarak) {
        this.jaga_jarak = jaga_jarak;
    }

    public String getDemam() {
        return demam;
    }

    public void setDemam(String demam) {
        this.demam = demam;
    }

    public String getBatuk() {
        return batuk;
    }

    public void setBatuk(String batuk) {
        this.batuk = batuk;
    }

    public String getSesak_napas() {
        return sesak_napas;
    }

    public void setSesak_napas(String sesak_napas) {
        this.sesak_napas = sesak_napas;
    }

    public String getSakit_tenggorokan() {
        return sakit_tenggorokan;
    }

    public void setSakit_tenggorokan(String sakit_tenggorokan) {
        this.sakit_tenggorokan = sakit_tenggorokan;
    }

    public String getTanggal_covid() {
        return tanggal_covid;
    }

    public void setTanggal_covid(String tanggal_covid) {
        this.tanggal_covid = tanggal_covid;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getSasaran() {
        return sasaran;
    }

    public void setSasaran(String sasaran) {
        this.sasaran = sasaran;
    }

    public String getUsername_sasaran() {
        return username_sasaran;
    }

    public String getTotal_bayi() {
        return total_bayi;
    }

    public void setTotal_bayi(String total_bayi) {
        this.total_bayi = total_bayi;
    }

    public String getTotal_ibu_hamil() {
        return total_ibu_hamil;
    }

    public void setTotal_ibu_hamil(String total_ibu_hamil) {
        this.total_ibu_hamil = total_ibu_hamil;
    }

    public String getNama_bayi() {
        return nama_bayi;
    }

    public void setNama_bayi(String nama_bayi) {
        this.nama_bayi = nama_bayi;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }

    public void setTgl_lahir(String tgl_lahir) {
        this.tgl_lahir = tgl_lahir;
    }

    public String getJk_bayi() {
        return jk_bayi;
    }

    public void setJk_bayi(String jk_bayi) {
        this.jk_bayi = jk_bayi;
    }

    public String getNama_ibu() {
        return nama_ibu;
    }

    public void setNama_ibu(String nama_ibu) {
        this.nama_ibu = nama_ibu;
    }

    public String getNama_ayah() {
        return nama_ayah;
    }

    public void setNama_ayah(String nama_ayah) {
        this.nama_ayah = nama_ayah;
    }

    public String getKk_bayi() {
        return kk_bayi;
    }

    public void setKk_bayi(String kk_bayi) {
        this.kk_bayi = kk_bayi;
    }

    public String getAlamat_bayi() {
        return alamat_bayi;
    }

    public void setAlamat_bayi(String alamat_bayi) {
        this.alamat_bayi = alamat_bayi;
    }

    public String getAnak_ke() {
        return anak_ke;
    }

    public void setAnak_ke(String anak_ke) {
        this.anak_ke = anak_ke;
    }

    public String getBerat_lahir() {
        return berat_lahir;
    }

    public void setBerat_lahir(String berat_lahir) {
        this.berat_lahir = berat_lahir;
    }

    public void setUsername_sasaran(String username_sasaran) {
        this.username_sasaran = username_sasaran;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword_sasaran() {
        return password_sasaran;
    }

    public void setPassword_sasaran(String password_sasaran) {
        this.password_sasaran = password_sasaran;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getHasil_penimbangan() {
        return hasil_penimbangan;
    }

    public void setHasil_penimbangan(String hasil_penimbangan) {
        this.hasil_penimbangan = hasil_penimbangan;
    }

    public String getPmt_pemulihan() {
        return pmt_pemulihan;
    }

    public void setPmt_pemulihan(String pmt_pemulihan) {
        this.pmt_pemulihan = pmt_pemulihan;
    }

    public String getTanggal_kunjungan() {
        return tanggal_kunjungan;
    }

    public void setTanggal_kunjungan(String tanggal_kunjungan) {
        this.tanggal_kunjungan = tanggal_kunjungan;
    }
}
