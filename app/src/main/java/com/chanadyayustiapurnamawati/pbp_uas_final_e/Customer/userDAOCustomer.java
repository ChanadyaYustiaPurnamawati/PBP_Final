package com.chanadyayustiapurnamawati.pbp_uas_final_e.Customer;

import com.google.gson.annotations.SerializedName;

public class userDAOCustomer {
    @SerializedName("id")
    private String id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("no_telp")
    private String no_telp;

    @SerializedName("no_ktp")
    private String no_ktp;

    @SerializedName("motor")
    private String motor;

    @SerializedName("waktu")
    private int waktu;
    public userDAOCustomer(String id, String nama, String alamat, String no_telp, String no_ktp, String motor, int waktu) {
        this.id = id;
        this.nama = nama;
        this.alamat = alamat;
        this.no_telp = no_telp;
        this.no_ktp = no_ktp;
        this.motor = motor;
        this.waktu = waktu;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public String getNo_ktp() {
        return no_ktp;
    }

    public String getMotor() {
        return motor;
    }

    public int getWaktu() {
        return waktu;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public void setNo_ktp(String no_ktp) {
        this.no_ktp = no_ktp;
    }

    public void setWaktu(int waktu) {
        this.waktu = waktu;
    }

    public void setStringWaktu(String waktu) {
        if(!waktu.isEmpty()){
            this.waktu = Integer.parseInt(waktu);
        }else{
            this.waktu = 0;
        }
    }

    public String getStringWaktu() {
        return String.valueOf(waktu);}
}
