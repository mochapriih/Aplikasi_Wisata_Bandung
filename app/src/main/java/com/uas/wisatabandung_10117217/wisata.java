package com.uas.wisatabandung_10117217;

import android.os.Parcel;
import android.os.Parcelable;
/*
 * Nama : Mochamad Sapri hadi
 * Kelas : IF 7
 * NIM : 10117217
 * pengerjaan : 08-08-2020  - 12-08-2020
 * */
public class wisata implements Parcelable {
    //private String id;
     String judul;
     String alamat;
     int wisataImage;
     double lat,lng;

    public wisata(String judul,String alamat,String detail_alamat, double lat, double lng){
        this.judul = judul;
        this.alamat = alamat;
        this.detail_alamat = detail_alamat;
        this.lat = lat;
        this.lat = lng;
    }
    protected wisata(Parcel in) {
        judul = in.readString();
        alamat = in.readString();
        detail_alamat = in.readString();
        lat = in.readInt();
        lng = in.readInt();
    }
    public static final Parcelable.Creator<wisata> CREATOR = new Parcelable.Creator<wisata>() {
        @Override
        public wisata createFromParcel(Parcel in) {
            return new wisata(in);
        }

        @Override
        public wisata[] newArray(int size) {
            return new wisata[size];
        }
    };
    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }


    public String getDetail_alamat() {
        return detail_alamat;
    }

    public void setDetail_alamat(String detail_alamat) {
        this.detail_alamat = detail_alamat;
    }

    private String detail_alamat;
    //private byte[] wisataImage;

    public int getWisataImage() {
        return wisataImage;
    }

    public void  setWisataImage(int wisataImage) {
        this.wisataImage = wisataImage;

    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(alamat);
        dest.writeString(detail_alamat);
        dest.writeDouble(lat);
        dest.writeDouble(lng);
    }
}
