package com.uas.wisatabandung_10117217;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
/*
 * Nama : Mochamad Sapri hadi
 * Kelas : IF 7
 * NIM : 10117217
 * pengerjaan : 08-08-2020  - 12-08-2020
 * */
//https://stackoverflow.com/questions/27818786/fetch-data-from-sqlite-and-display-in-gridview
public class DataHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "uas.db";
    public static final String TABLE_PROFILE = "profile";
    public static final String TABLE_WISATA = "wisata";
    public static final String NIM = "NIM";
    public static final String NAMA = "nama";
    public static final String KELAS = "kelas";
    public static final String ID = "id";
    public static final String JUDUL = "judul";
    public static final String ALAMAT = "alamat";
    public static final String DETAIL_ALAMAT = "detail_alamat";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String IMG = "img";
    private static final int DATABASE_VERSION = 1;
    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        //https://androidexample.com/SQLite_Database_Manipulation_Class_-_Android_Example/index.php?view=article_discription&aid=51&aaid=76
    }
    private static final String CREATE_TABLE_PROFILE = "CREATE TABLE if not exists "
            + TABLE_PROFILE +"(" + NIM + " VARCHAR PRIMARY KEY," + NAMA
            + " VARCHAR," + KELAS + " VARCHAR)";

    private static final String CREATE_TABLE_WISATA = "CREATE TABLE if not exists "
            + TABLE_WISATA +"(" + JUDUL + " VARCHAR PRIMARY KEY," + ALAMAT + " VARCHAR,"+ DETAIL_ALAMAT + " VARCHAR,"+ LAT + " DOUBLE,"+ LNG + " DOUBLE)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        /*String sql = "CREATE TABLE if not exists "
                + TABLE_PROFILE + " (" + NIM
                + " INTEGER PRIMARY KEY, " + NAMA + " VARCHAR, "
                + KELAS + " VARCHAR );";*/

        //String sql2 = "create table profile(+NIM+ primary key, +NAMA+ text null, +KELAS+ text null);";
        //Log.d("Data", "onCreate: " + sql);
        db.execSQL(CREATE_TABLE_PROFILE);
        db.execSQL(CREATE_TABLE_WISATA);

        String sql = "INSERT INTO profile (NIM, NAMA, KELAS) VALUES ('10117217', 'Moch Sapri Hadi', 'IF7');";
        String sql2 = "INSERT INTO wisata (JUDUL, ALAMAT, detail_alamat, lat, Lng) VALUES " +
                "('taman hutan raya', 'Bandung', 'Ir. H. Juanda No.99, Ciburial, Kec. Cimenyan, Bandung', -6.856437, 107.632667)," +
                "('monumen','Bandung', 'Jl. Dipati Ukur No.48, Lebakgede, Kecamatan Coblong, Kota Bandung, Jawa Barat 40132', -6.893411, 107.618530)," +
                "('Saung Angklung Mang Udjo', 'Bandung','Jl. Padasuka No.118, Pasirlayung, Kec. Cibeunying Kidul, Kota Bandung', -6.898021, 107.654954)," +
                "('Museum gedung sate','Bandung','Jl. Diponegoro No.22, Citarum, Kec. Bandung Wetan, Kota Bandung', -6.902502, 107.618810)," +
                "('Trans Studio Bandung','Bandung','Jl. Gatot Subroto No. 289, Cibangkong, Kec. Batununggal, Kota Bandung', -6.925542, 107.637005)," +
                "('Museum Geologi Bandung','Bandung','Jl. Diponegoro No. 57, Cihaur Geulis, Kec. Cibeunying Kaler, Bandung, Jawa Barat', -6.900684, 107.621455)," +
                "('Gunung Tangkuban Perahu','Bandung','Cikahuripan, Lembang, Kab. Bandung Barat', -6.759663, 107.616180)," +
                "('Kawah Putih','Ciwidey','Sugihmukti, Kec. Pasirjambu, Bandung', -7.166229, 107.402118)," +
                "('Ranca Upas','Kabupaten Bandung','Jl. Raya Ciwidey – Patengan No.KM. 11, Patengan, Kec. Rancabali, Kab. Bandung', -7.138140, 107.392249)," +
                "('Ciwidey Valley Resort','Ciwidey','Jl. Barutunggul KM. 17, Ciwidey, Alamendah, Kec. Rancabali, Kab. Bandung', -7.134669, 107.415940)," +
                "('Tebing Keraton','Kabupaten Bandung Barat','Ciburial, Kec. Cimenyan, Kab. Bandung Barat', -6.833813, 107.663626)," +
                "('Taman Maribaya','Lembang',' Langensari, Lembang, Kab. Bandung Barat', -6.828944, 107.688036)," +
                "('Farm House','Lembang','Jl. Raya Lembang No. 108, Gudangkahuripan, Lembang, Kab. Bandung Barat, Jawa Barat', -6.832929, 107.605711)," +
                "('Observatorium Boscha','Lembang','Jl. Peneropongan Bintang No. 45, Kec. Lembang, Kab. Bandung Barat', -6.824610, 107.617111)," +
                "('Lereng Anteng Panoramic Coffee','Lembang','Kampung Pagermaneuh, RT. 03/07, Pagerwangi, Lembang, Kabupaten Bandung', -6.842397, 107.623084);";
        db.execSQL(sql);
        db.execSQL(sql2);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WISATA);
    }
    public String readAllData(){
        //String query = "SELECT * FROM " + TABLE_WISATA;
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns={DataHelper.JUDUL,DataHelper.ALAMAT};
        Cursor cursor=db.query(DataHelper.TABLE_WISATA, columns, null, null, null, null, null);
        ArrayList<wisata> wisatasList = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()){
            String judul = cursor.getString(cursor.getColumnIndex(DataHelper.JUDUL));
            String alamat = cursor.getString(cursor.getColumnIndex(DataHelper.ALAMAT));
            String detailalamat = cursor.getString(cursor.getColumnIndex(DataHelper.DETAIL_ALAMAT));
            buffer.append(judul+" "+alamat+"\n");

        }
        return buffer.toString();

    }
    public List<wisata> allwisata() {

        ArrayList<wisata> wisatasList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM wisata";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        while (cursor.moveToNext()) {

                String judul = cursor.getString(0);
                String alamat = cursor.getString(1);
                String detail_alamat = cursor.getString(2);
                double lat = cursor.getDouble(3);
                double lng = cursor.getDouble(4);
                wisata newwisata = new wisata(judul, alamat,detail_alamat,lat,lng);
                wisatasList.add(newwisata);
            }


        db.close();
        return wisatasList;
    }
}