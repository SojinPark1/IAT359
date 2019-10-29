package com.example.assignment2final;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MyDatabase {
    private SQLiteDatabase db;
    private Context context;
    private final MyHelper helper;

    public MyDatabase (Context c){
        context = c;
        helper = new MyHelper(context);
    }

    public long insertData (String wl, String Shots, String Saves)
    {
        db = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.WL, wl);
        contentValues.put(Constants.SHOTS, Shots);
        contentValues.put(Constants.SAVES, Saves);
        long id = db.insert(Constants.TABLE_NAME, null, contentValues);
        return id;
    }

    public Cursor getData()
    {
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = {Constants.UID, Constants.WL, Constants.SHOTS, Constants.SAVES};
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, null, null, null, null, null);
        return cursor;
    }


    public String getSelectedData(String wl)
    {
        //select plants from database of type 'herb'
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] columns = { Constants.WL, Constants.SHOTS, Constants.SAVES};

        String selection = Constants.WL + "='" +wl+ "'";  //Constants.TYPE = 'type'
        Cursor cursor = db.query(Constants.TABLE_NAME, columns, selection, null, null, null, null);

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {

            int index1 = cursor.getColumnIndex(Constants.WL);
            int index2 = cursor.getColumnIndex(Constants.SHOTS);
            int index3 = cursor.getColumnIndex(Constants.SAVES);
            String WL = cursor.getString(index1);
            String Shots = cursor.getString(index2);
            String Saves = cursor.getString(index2);
            buffer.append(WL + " " + Shots + " " + Saves + "\n");
        }
        return buffer.toString();
    }

}
