package com.example.bayrakuygulamasi;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class BayraklarDao {
    public ArrayList<Bayraklar> rastgele5getir(VeriTabani vt) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ArrayList<Bayraklar> yanlısseceneklist = new ArrayList<>();
        Cursor c = dbx.rawQuery("SELECT * FROM  bayraklar ORDER BY RANDOM() LIMIT 5", null);
        while (c.moveToNext()) {
            Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id")),
                    c.getString(c.getColumnIndex("bayrak_ad")),
                    c.getString(c.getColumnIndex("bayrak_resim"))
            );
            yanlısseceneklist.add(b);
        }
        return yanlısseceneklist;

    }

    public ArrayList<Bayraklar> yanlıssecenekgetır(VeriTabani vt, int bayrak_id) {
        SQLiteDatabase dbx = vt.getWritableDatabase();
        ArrayList<Bayraklar> ucyanlısgetir = new ArrayList<>();
        Cursor c = dbx.rawQuery("SELECT * FROM bayraklar WHERE bayrak_id !="+bayrak_id+" ORDER BY RANDOM() LIMIT 3", null);
        while (c.moveToNext()) {
            Bayraklar b = new Bayraklar(c.getInt(c.getColumnIndex("bayrak_id")),
                    c.getString(c.getColumnIndex("bayrak_ad")),
                    c.getString(c.getColumnIndex("bayrak_resim"))
            );
            ucyanlısgetir.add(b);

        }
        return ucyanlısgetir;
    }
}


