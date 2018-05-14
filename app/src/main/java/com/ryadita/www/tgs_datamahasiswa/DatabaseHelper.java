package com.ryadita.www.tgs_datamahasiswa;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Database version
    private static final int DATABASE_VERSION = 1;

    //Database name
    private static final String DATABASE_NAME = "datamhs_db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creatting tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create data table
        db.execSQL(DataMahasiswa.CREATE_TABLE);
    }

    //Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DataMahasiswa.TABLE_NAME);

        //Create tables again
        onCreate(db);
    }

    public long insertData(String nama, String nim, String prodi, String email) {
        //get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(DataMahasiswa.COLUMN_NAMA, nama);
        values.put(DataMahasiswa.COLUMN_NIM, nim);
        values.put(DataMahasiswa.COLUMN_PRODI, prodi);
        values.put(DataMahasiswa.COLUMN_EMAIL, email);

        //insert row
        long id = db.insert(DataMahasiswa.TABLE_NAME, null, values);

        //close db connection
        db.close();

        //return newly inserted row id
        return id;
    }

    public DataMahasiswa getDataMhs (long id) {
        //get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DataMahasiswa.TABLE_NAME,
                new String[]{DataMahasiswa.COLUMN_ID, DataMahasiswa.COLUMN_NAMA, DataMahasiswa.COLUMN_NIM, DataMahasiswa.COLUMN_PRODI, DataMahasiswa.COLUMN_EMAIL},
                DataMahasiswa.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null,null,null );

        if (cursor != null)
            cursor.moveToFirst();

        //prepare data mahasiswa object
        DataMahasiswa dataMhs = new DataMahasiswa(
                cursor.getInt(cursor.getColumnIndex(DataMahasiswa.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_NAMA)),
                cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_NIM)),
                cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_PRODI)),
                cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_EMAIL)));

        //close the db connection
        db.close();

        return dataMhs;
    }

    public List<DataMahasiswa> getAllData() {
        List<DataMahasiswa> data = new ArrayList<>();

        //Select All Query
        String selectQuery = "SELECT * FROM " + DataMahasiswa.TABLE_NAME + " ORDER BY " +
                DataMahasiswa.COLUMN_ID + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst()) {
            do {
                DataMahasiswa dataMhs = new DataMahasiswa();
                dataMhs.setId(cursor.getInt(cursor.getColumnIndex(DataMahasiswa.COLUMN_ID)));
                dataMhs.setNama(cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_NAMA)));
                dataMhs.setNim(cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_NIM)));
                dataMhs.setProdi(cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_PRODI)));
                dataMhs.setEmail(cursor.getString(cursor.getColumnIndex(DataMahasiswa.COLUMN_EMAIL)));

                data.add(dataMhs);
            } while (cursor.moveToNext());
        }

        //close db connection
        db.close();

        //return data list
        return data;
    }

    public int getDataCount() {
        String countQuery = "SELECT * FROM " + DataMahasiswa.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();

        //return count
        return count;
    }

    public int updateData(DataMahasiswa dataMhs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DataMahasiswa.COLUMN_NAMA, dataMhs.getNama());
        values.put(DataMahasiswa.COLUMN_NIM, dataMhs.getNim());
        values.put(DataMahasiswa.COLUMN_PRODI, dataMhs.getProdi());
        values.put(DataMahasiswa.COLUMN_EMAIL, dataMhs.getEmail());

        //updating row
        return db.update(dataMhs.TABLE_NAME,values, DataMahasiswa.COLUMN_ID + "=?",
                new String[]{String.valueOf(dataMhs.getId())});
    }

    public void deleteData(DataMahasiswa dataMhs) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DataMahasiswa.TABLE_NAME, DataMahasiswa.COLUMN_ID + "=?",
                new String[]{String.valueOf(dataMhs.getId())});
        db.close();
    }
}
