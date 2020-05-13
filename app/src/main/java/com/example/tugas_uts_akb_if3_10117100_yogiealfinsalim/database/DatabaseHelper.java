package com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** NIM : 10117091
 * Nama : Dida Handian
 * Kelas : IF-3
 * Tanggal : 11-05-2020
 **/

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbDoyenFriendApp";
    private static final int DATABASE_VERSION =1;
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseAtribut.TABLE_NAME,
            DatabaseAtribut.NoteColumns._ID,
            DatabaseAtribut.NoteColumns.NIM,
            DatabaseAtribut.NoteColumns.NAMA,
            DatabaseAtribut.NoteColumns.KELAS,
            DatabaseAtribut.NoteColumns.TELPON,
            DatabaseAtribut.NoteColumns.EMAIL,
            DatabaseAtribut.NoteColumns.Facebook,
            DatabaseAtribut.NoteColumns.DATE

    );

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseAtribut.TABLE_NAME);
        onCreate(db);
    }
}
