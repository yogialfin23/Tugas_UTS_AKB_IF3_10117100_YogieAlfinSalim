package com.example.tugas_uts_akb_if3_10117100_yogiealfinsalim.database;

import android.database.Cursor;

import java.util.ArrayList;

/** NIM : 10117091
 * Nama : Dida Handian
 * Kelas : IF-3
 * Tanggal : 11-05-2020
 **/

public class MapHelper {
    public static ArrayList<friend> mapCursorToArrayList(Cursor friendsCursor){
        ArrayList<friend> friendsList = new ArrayList<>();

        while (friendsCursor.moveToNext()){
            int id  = friendsCursor.getInt(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns._ID));
            String nim = friendsCursor.getString(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns.NIM));
            String nama = friendsCursor.getString(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns.NAMA));
            String kelas = friendsCursor.getString(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns.KELAS));
            String telp = friendsCursor.getString(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns.TELPON));
            String email = friendsCursor.getString(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns.EMAIL));
            String ig = friendsCursor.getString(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns.Facebook));
            String date = friendsCursor.getString(friendsCursor.getColumnIndexOrThrow(DatabaseAtribut.NoteColumns.DATE));
            friendsList.add(new friend(id, nim, nama, kelas, telp, email, ig, date));

        }
        return friendsList;
    }
}
