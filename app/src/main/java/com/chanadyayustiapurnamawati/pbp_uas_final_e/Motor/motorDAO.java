package com.chanadyayustiapurnamawati.pbp_uas_final_e.Motor;

import com.google.gson.annotations.SerializedName;

import java.time.Instant;

//import androidx.databinding.BindingAdapter;

public class motorDAO {
    private static Instant Glide;
    @SerializedName("id")
    private String id;

    @SerializedName("merk")
    private String merk;

    @SerializedName("kondisi")
    private String kondisi;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("harga")
    private int harga;

    @SerializedName("tahun")
    private int tahun;

    @SerializedName("foto")
    private String foto;
    public motorDAO(String id, String merk, String kondisi, String jenis, int harga, int tahun, String foto) {
        this.id = id;
        this.merk = merk;
        this.kondisi = kondisi;
        this.jenis = jenis;
        this.harga = harga;
        this.tahun = tahun;
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }


    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }


    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }


    public int geHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public void setStringHarga(String harga) {
        if(!harga.isEmpty()){
            this.harga = Integer.parseInt(harga);
        }else{
            this.harga = 0;
        }
    }

    public String getStringHarga() {
        return String.valueOf(harga);}


    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public void setStringTahun(String tahun) {
        if(!tahun.isEmpty()){
            this.tahun = Integer.parseInt(tahun);
        }else{
            this.tahun = 0;
        }
    }

    public String getStringTahun() {
        return String.valueOf(tahun);}


    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) { this.foto = foto;}

//    @BindingAdapter("android:loadImage")
//    public static void loadImage(ImageView imageView, String foto){
//        Glide.with(imageView)
//                .load(foto)
//                .into(imageView);
//    }

}
