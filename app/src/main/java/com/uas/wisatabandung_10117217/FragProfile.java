package com.uas.wisatabandung_10117217;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragProfile#newInstance} factory method to
 * create an instance of this fragment.
 */
/*
 * Nama : Mochamad Sapri hadi
 * Kelas : IF 7
 * NIM : 10117217
 * pengerjaan : 08-08-2020  - 12-08-2020
 * */
public class FragProfile extends Fragment implements MainView {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragProfile() {
        // Required empty public constructor
        //https://sendz.github.io/android/2016/10/26/android-mvp-pattern.html
        //https://www.petanikode.com/java-oop-setter-getter/
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragProfile.
     */
    // TODO: Rename and change types and number of parameters
    public static FragProfile newInstance(String param1, String param2) {
        FragProfile fragment = new FragProfile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    protected Cursor cursor;
    DataHelper dbHelper;
    TextView NIM,nama,kelas;
    ImageView img;
    String[] daftar;
    private MainPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        NIM =(TextView) view.findViewById(R.id.nim_tampil);
        nama =(TextView) view.findViewById(R.id.nama_tampil);
        kelas =(TextView) view.findViewById(R.id.kelas_tampil);
        img = (ImageView) view.findViewById(R.id.imageView2);
        img.setImageResource(R.mipmap.profile);
        presenter = new MainPresenter(this);
        dbHelper = new DataHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM profile",null);
        if (cursor != null)
        {
            cursor.moveToPosition(0);
            NIM.setText(cursor.getString(0).toString());
            nama.setText(cursor.getString(1).toString());
            kelas.setText(cursor.getString(2).toString());

        }
        /*if (cursor != null) {
            NIM.setText(cursor.getString(cursor
                    .getColumnIndex(DataHelper.NIM)));
        }*/


        return view;
    }

    public void getdata(){

        /*cursor = db.rawQuery("SELECT * FROM profile WHERE nama = '" +
                getActivity().getIntent().getStringExtra("nama") + "'",null);*/

    }


    @Override
    public void tampilkandata(User user) {
        NIM.setText("10117217");
        nama.setText("Moch Sapri Hadi");
        kelas.setText("IF7");
    }
}