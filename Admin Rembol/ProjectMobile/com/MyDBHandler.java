package ProjectMobile.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class MyDBHandler extends SQLiteOpenHelper {

    //Deklarasi variabel konstanta untuk pembuatan database, tabel dan kolom yang diperlukan
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CATATANADMIN.db";
    private static final String TABLE_NAME = "admin";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_CATATAN = "_catatan";

    //Constructor untuk Class MyDBHandler
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Method untuk Create Database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_BARANG = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATATAN + " VARCHAR(500) NOT NULL )";
        sqLiteDatabase.execSQL(CREATE_TABLE_BARANG);
    }

    //Method yang dipakai untuk upgrade tabel
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    /*---- Insert, Select, Update, Delete ----*/

    private SQLiteDatabase database;

    //Method untuk open database connection
    public void open() throws SQLException {
        database = this.getWritableDatabase();
    }

    //Inisialisasi semua kolom di tabel database
    private String[] allColumns =
            {COLUMN_ID, COLUMN_CATATAN};

    //Method untuk memindahkan isi cursor ke objek barang
    private Catatan cursorToCatatan(Cursor cursor) {
        Catatan catatan = new Catatan();
        catatan.setID(cursor.getLong(0));
        catatan.set_catatan(cursor.getString(1));
        return catatan;
    }

    //Method untuk menambahkan barang baru
    public void createCatatan(String catatan){
        ContentValues values = new ContentValues();
        values.put(COLUMN_CATATAN, catatan);
        database.insert(TABLE_NAME, null, values);
    }

    //Method untuk mendapatkan detail per barang
    public Catatan getCatatan(long id) {
        Catatan catatan = new Catatan();
        Cursor cursor = database.query(TABLE_NAME, allColumns, "_id=" + id, null, null, null, null);
        cursor.moveToFirst();
        catatan = cursorToCatatan(cursor);
        cursor.close();
        return catatan;
    }

    public ArrayList<Catatan> getAllCatatan() {
        ArrayList<Catatan> daftarCatatan = new ArrayList<Catatan>();
        Cursor cursor = database.query(TABLE_NAME, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Catatan catatan = cursorToCatatan(cursor);
            daftarCatatan.add(catatan);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarCatatan;
    }

    //Method untuk mengupdate data barang
    public void updateCatatan(Catatan catatan) {
        String filter = "_id="+ catatan.getID();
        ContentValues args = new ContentValues();
        args.put(COLUMN_CATATAN, catatan.get_catatan());
        database.update(TABLE_NAME, args, filter, null);
    }

    //Method untuk menghapus data barang
    public void deleteCatatan(long id) {
        String filter = "_id=" + id;
        database.delete(TABLE_NAME, filter, null);
    }
}

