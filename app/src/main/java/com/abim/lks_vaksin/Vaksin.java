package com.abim.lks_vaksin;

import java.util.Date;

public class Vaksin {
    private int periode;
    private String nama_dokter, jenis_vaksin;
    private String tgl;

    public Vaksin(int periode, String nama_dokter, String jenis_vaksin, String tgl) {
        this.periode = periode;
        this.nama_dokter = nama_dokter;
        this.jenis_vaksin = jenis_vaksin;
        this.tgl = tgl;
    }

    public int getPeriode() {
        return periode;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public String getJenis_vaksin() {
        return jenis_vaksin;
    }

    public String getTgl() {
        return tgl;
    }
}
