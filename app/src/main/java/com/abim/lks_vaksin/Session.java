package com.abim.lks_vaksin;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {
    SharedPreferences prefs;

    public Session(Context ctx){
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void setUser(int id, String nama, String nik, String noHp, String tempat_lahir, String tanggal_lahir){
        prefs.edit().putString("nama", nama).commit();
        prefs.edit().putString("nik", nik).commit();
        prefs.edit().putString("noHp", noHp).commit();
        prefs.edit().putString("tempat_lahir", tempat_lahir).commit();
        prefs.edit().putString("tanggal_lahir", tanggal_lahir).commit();
        prefs.edit().putInt("id", id).commit();
    }

    public int getId(){
        return prefs.getInt("id", 0);
    }

    public String getNama(){
        return prefs.getString("nama", "");
    }
    public String getNik(){
        return prefs.getString("nik", "");
    }
    public String getNoHp(){
        return prefs.getString("noHp", "");
    }
    public String getTempat_lahir(){
        return prefs.getString("tempat_lahir", "");
    }
    public String getTanggal_lahir(){
        return prefs.getString("tangal_lahir", "");
    }
}
