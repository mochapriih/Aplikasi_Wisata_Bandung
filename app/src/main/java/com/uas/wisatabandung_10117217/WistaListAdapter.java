package com.uas.wisatabandung_10117217;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

/*
 * Nama : Mochamad Sapri hadi
 * Kelas : IF 7
 * NIM : 10117217
 * pengerjaan : 08-08-2020  - 12-08-2020
 * */
public class WistaListAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    List<wisata> wisataListFiltered;
     List<wisata> wisatasList = new ArrayList<>();
    LayoutInflater inflter;
    private int mItemBGColor = Color.parseColor("#2573FA");
    int image[] ={R.mipmap.hutanraya,R.mipmap.monumen,R.mipmap.angklung,R.mipmap.gdsate,R.mipmap.tsm,R.mipmap.museum,R.mipmap.perahu,R.mipmap.kawah,R.mipmap.rancaupas,R.mipmap.valley,R.mipmap.tebing,R.mipmap.maribaya,R.mipmap.farm,R.mipmap.obser,R.mipmap.lereng};
    public WistaListAdapter(Context context, int textViewResourceId, List<wisata> wisatasList) {
        this.context = context;
        this.wisatasList = wisatasList;
        this.wisataListFiltered = wisatasList;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return wisatasList.size();
    }

    @Override
    public Object getItem(int position) {
        return wisatasList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    private class ViewHolder implements View.OnClickListener{
        ImageView imageView;
        TextView txtjudul, txtalamat;
        GridView gridView;
        @Override
        public void onClick(View v) {

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        ViewHolder holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wisata_list_activity, viewGroup, false);

            holder.txtjudul = (TextView) view.findViewById(R.id.txtjudul);
            //holder.txtalamat = (TextView) view.findViewById(R.id.txtalamat);
            holder.imageView = (ImageView) view.findViewById(R.id.imgwisata);
            holder.gridView = (GridView) view.findViewById(R.id.gridView);
            //holder.txtjudul.setBackgroundColor(mItemBGColor);
            holder.txtjudul.setGravity(Gravity.CENTER);
            holder.imageView.setForegroundGravity(Gravity.CENTER);
            //holder.imageView.setBackgroundColor(mItemBGColor);

            view.setTag(holder);


        wisata wst = wisatasList.get(position);

        holder.txtjudul.setText(wisatasList.get(position).getJudul());
        //holder.txtalamat.setText(wisatasList.get(position).getAlamat());
        holder.imageView.setImageResource(image[position]);
        /*byte[] foodImage = food.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(foodImage, 0, foodImage.length);
        holder.imageView.setImageBitmap(bitmap);*/

        return view;
    }
}
