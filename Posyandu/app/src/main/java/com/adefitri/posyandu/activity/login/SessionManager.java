package com.adefitri.posyandu.activity.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.adefitri.posyandu.activity.gizi.HomeGizi;
import com.adefitri.posyandu.activity.kader.HomeKader;
import com.adefitri.posyandu.activity.pengunjung.HomePengunjung;

import java.util.HashMap;

public class SessionManager {
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    public static final String KEY_USERNAME = "username";
    public static final String KEY_ID_LOGIN = "id_login";
    public static final String KEY_ID_BAYI = "id_bayi";
    public static final String KEY_NAMA_PETUGAS = "nama_petugas";
    public static final String is_login_pengunjung = "loginstatuspengunjung";
    public static final String is_login_kader = "loginstatuskader";
    public static final String is_login_gizi = "loginstatusgizi";
    private final String SHARE_NAME = "loginsession";
    private final int MODE_PRIVATE = 0;
    private Context _context;

    public SessionManager (Context context){
        this._context = context;
        sp = _context.getSharedPreferences(SHARE_NAME,MODE_PRIVATE);
        editor = sp.edit();
    }

    public void storeLoginPengunjung(String username, String id_login, String id_bayi){
        editor.putBoolean(is_login_pengunjung, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ID_LOGIN, id_login);
        editor.putString(KEY_ID_BAYI, id_bayi);
        editor.commit();
    }

    public void storeLoginKader(String username, String id_login, String nama_petugas){
        editor.putBoolean(is_login_kader, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ID_LOGIN, id_login);
        editor.putString(KEY_NAMA_PETUGAS, nama_petugas);
        editor.commit();
    }

    public void storeLoginGizi(String username, String id_login, String nama_petugas){
        editor.putBoolean(is_login_gizi, true);
        editor.putString(KEY_USERNAME, username);
        editor.putString(KEY_ID_LOGIN, id_login);
        editor.putString(KEY_NAMA_PETUGAS, nama_petugas);
        editor.commit();
    }


    public HashMap getDetailLoginPengunjung(){
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_USERNAME, sp.getString(KEY_USERNAME,null));
        map.put(KEY_ID_LOGIN, sp.getString(KEY_ID_LOGIN,null));
        map.put(KEY_ID_BAYI, sp.getString(KEY_ID_BAYI,null));
        return map;
    }

    public HashMap getDetailLoginKader(){
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_USERNAME, sp.getString(KEY_USERNAME,null));
        map.put(KEY_ID_LOGIN, sp.getString(KEY_ID_LOGIN,null));
        map.put(KEY_NAMA_PETUGAS, sp.getString(KEY_NAMA_PETUGAS,null));
        return map;
    }

    public HashMap getDetailLoginGizi(){
        HashMap<String,String> map = new HashMap<>();
        map.put(KEY_USERNAME, sp.getString(KEY_USERNAME,null));
        map.put(KEY_ID_LOGIN, sp.getString(KEY_ID_LOGIN,null));
        map.put(KEY_NAMA_PETUGAS, sp.getString(KEY_NAMA_PETUGAS,null));
        return map;
    }


    public void checkLogin(){
        if (this.is_login_pengunjung()){
            Intent i = new Intent(_context, HomePengunjung.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        } else if (this.is_login_kader()) {
            Intent i = new Intent(_context, HomeKader.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        } else if (this.is_login_gizi()) {
            Intent i = new Intent(_context, HomeGizi.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        } else {
            Intent i = new Intent(_context, LoginPengunjung.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    public void Logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, LoginPengunjung.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }

    private boolean is_login_pengunjung() {
        return sp.getBoolean(is_login_pengunjung, false);
    }

    private boolean is_login_kader() {
        return sp.getBoolean(is_login_kader, false);
    }

    private boolean is_login_gizi() {
        return sp.getBoolean(is_login_gizi, false);
    }
}
