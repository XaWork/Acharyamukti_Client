package com.acharyamukti.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "register_details";
    private static final int VERSION_CODE = 1;
    private static final String TABLE_NAME = "registerData";
    private static final String ID = "id";
    private static final String First_NAME = "f_name";
    private static final String Last_NAME = "l_name";
    private static final String MOBILE = "mobile";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, VERSION_CODE);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry = "CREATE TABLE TABLE_NAME(" +
                "ID INTEGER PRIMARY KEY," +
                " First_NAME TEXT," +
                "Last_NAME TEXT," +
                "MOBILE TEXT," +
                "EMAIL TEXT," +
                "PASSWORD TEXT)";
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insertData(String fName, String lName, String mobile, String email, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(First_NAME, fName);
        values.put(Last_NAME, lName);
        values.put(MOBILE, mobile);
        values.put(EMAIL, email);
        values.put(PASSWORD, password);
        database.insert(TABLE_NAME, null, values);
        database.close();
    }
}
