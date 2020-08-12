package com.uas.wisatabandung_10117217;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragWisata#newInstance} factory method to
 * create an instance of this fragment.
 */
/*
 * Nama : Mochamad Sapri hadi
 * Kelas : IF 7
 * NIM : 10117217
 * pengerjaan : 08-08-2020  - 12-08-2020
 * */
public class FragWisata extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragWisata() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragWisata.
     */
    // TODO: Rename and change types and number of parameters
    public static FragWisata newInstance(String param1, String param2) {
        FragWisata fragment = new FragWisata();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
//https://medium.com/@silsly/sqlite-with-recycler-view-845cf67e0e8e
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private int mItemBGColor = Color.parseColor("#2573FA");
    DataHelper dbHelper;
    GridView gridView;

    private SearchView.OnQueryTextListener queryTextListener;

    double lat[] ={-6.856437,-6.893411,-6.898021,-6.902502,-6.925542,-6.900684,-6.759663,-7.166229,-7.138140,-7.134669,-6.833813,-6.828944,-6.832929,-6.824610,-6.842397};
    double lng[] ={107.632667,107.618530,107.654954,107.618810,107.637005,107.621455,107.616180,107.402118,107.392249,107.415940,107.663626,107.688036,107.605711,107.617111,107.623084};
    //private ArrayList<wisata> wisatasList = new ArrayList<>();
    //private List<wisata> wisatasList = new ArrayList<wisata>();
    TextView judul,alamat;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wisata, container, false);
        gridView = (GridView) view.findViewById(R.id.gridView);

        //judul = (TextView) view.findViewById(R.id.txtjudul);
        //alamat = (TextView) view.findViewById(R.id.txtalamat);
        //addData();
        dbHelper = new DataHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        final List<wisata> wisatasList = dbHelper.allwisata();
        WistaListAdapter adapter = new WistaListAdapter(getContext(),R.layout.wisata_list_activity,wisatasList);
        //dbHelper.getAllwisata();
        //WistaListAdapter wladapter =new WistaListAdapter(getContext(),wisatasList);
        //wladapter = new WistaListAdapter(getContext(), wisatasList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // start-media code could go in fragment or adapter

                Intent intent = new Intent(getActivity(), DetailWisata.class);
                intent.putExtra("judul", wisatasList.get(position).getJudul());
                intent.putExtra("alamat", wisatasList.get(position).getDetail_alamat());
                intent.putExtra("lat", lat[position]);
                intent.putExtra("long", lng[position]);
                startActivity(intent);

            }
        });
        return view;
    }


}

