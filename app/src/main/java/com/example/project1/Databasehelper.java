package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class Databasehelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "parameters.db";
    public static final String TABLE_NAME = "parameters_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "No_of_Range_Gate";
    public static final String COL_3 = "No_of_Doppler_Filter";
    public static final String COL_4 = "Frequency";
    public static final String COL_5 = "No_of_Clear_PRF";
    public static final String COL_6 = "Antenna_BeamWidthAzimuth";
    public static final String COL_7 = "Antenna_BeamWidthElevation";
    public static final String COL_8 = "Minimum_Range";
    public static final String COL_9 = "Maximum_Range";
    public static final String COL_10 = "Target_Minimum_Velocity";
    public static final String COL_11 = "Target_Maximum_Velocity";
    public static final String COL_12 = "Pulse_Width";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, No_of_Range_Gate INTEGER, No_of_Doppler_Filter INTEGER, Frequency INTEGER, No_of_Clear_PRF INTEGER, Antenna_BeamWidthAzimuth INTEGER, Antenna_BeamWidthElevation INTEGER, Minimum_Range INTEGER, Maximum_Range INTEGER, Target_Minimum_Velocity INTEGER, Target_Maximum_Velocity INTEGER, Pulse_Width INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table If Exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String No_of_Range_Gate, String No_of_Doppler_Filter, String Frequency, String No_of_Clear_PRF, String Antenna_BeamWidthAzimuth, String Antenna_BeamWidthElevation, String Minimum_Range, String Maximum_Range, String Target_Minimum_Velocity, String Target_Maximum_Velocity, String Pulse_Width) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, No_of_Range_Gate);
        contentValues.put(COL_3, No_of_Doppler_Filter);
        contentValues.put(COL_4, Frequency);
        contentValues.put(COL_5, No_of_Clear_PRF);
        contentValues.put(COL_6, Antenna_BeamWidthAzimuth);
        contentValues.put(COL_7, Antenna_BeamWidthElevation);
        contentValues.put(COL_8, Minimum_Range);
        contentValues.put(COL_9, Maximum_Range);
        contentValues.put(COL_10, Target_Minimum_Velocity);
        contentValues.put(COL_11, Target_Maximum_Velocity);
        contentValues.put(COL_12, Pulse_Width);
        Log.d(TAG, "Add Data : Adding " + Frequency + " to " + TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}
