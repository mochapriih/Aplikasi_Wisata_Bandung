package com.uas.wisatabandung_10117217;
/*
 * Nama : Mochamad Sapri hadi
 * Kelas : IF 7
 * NIM : 10117217
 * pengerjaan : 08-08-2020  - 12-08-2020
 * */
public class MainPresenter {
    private MainView view;
    private User user;

    public MainPresenter(MainView view) {
        this.view = view;
    }
    public void tampildata(String nim, String nama, String kelas) {
        String data = tampilandata(nim, nama, kelas);
        user = new User();
        view.tampilkandata(user);
    }
    private String tampilandata(String nim, String nama, String kelas) {
        return nim;
    }
}
