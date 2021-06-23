package com.myapplicationdev.android.demointernshipassessment;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "customer.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CUSTOMER = "customer";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CONTACT = "contact";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_HOME = "home";
    private static final String COLUMN_STARS = "stars";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_CUSTOMER + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"

                + COLUMN_NAME + " TEXT,"
                + COLUMN_CONTACT + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_HOME + " TEXT,"

                + COLUMN_STARS + " INTEGER ) ";
        db.execSQL(createNoteTableSql);
        Log.i("info", "created tables");

        // TODO: to prepare the database for testing,
        //  we could create some dummy data during the table creation process.
        //  Dummy records, to be inserted when the database is created
        for (int i = 0; i < 4; i++) {
            ContentValues values = new ContentValues();

            values.put(COLUMN_NAME, "Data number " + i);
            values.put(COLUMN_CONTACT, "Data number " + i);
            values.put(COLUMN_EMAIL, "Data number " + i);
            values.put(COLUMN_HOME, "Data number " + i);
            values.put(COLUMN_STARS, "Data number " + i);


            db.insert(TABLE_CUSTOMER, null, values);
        }
        Log.i("info", "dummy records inserted");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMER);
        onCreate(db);
    }

    //Insert a new record.
    public long insertcustomer(String name, String contact, String email, String home, int stars) {


        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, name);
        values.put(COLUMN_CONTACT, contact);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_HOME, home);


        values.put(COLUMN_STARS, stars);

        // TODO: This line of code will return a number
        //  that represents the record id (the primary key, _id)
        //  of the table for the record that was inserted.
        //  If the insert fails, the id will be -1.
        //  As a result, we can use it to determine whether or not a record was successfully inserted.
        long result = db.insert(TABLE_CUSTOMER, null, values);

        db.close();
        if (result == -1) {
            Log.d("DBHelper", "Insert failed");
        } else {
            Log.d("SQL Insert", "ID:" + result);
        }
        return result;
    }

    // TODO: Record retrieval from database table
    //  This method will retrieve the records and convert each one into a String.
    //  Following that, the Strings are placed in an ArrayList to be returned.
    public ArrayList<Customer> getAllcustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        String selectQuery = "SELECT "
                + COLUMN_ID + ", "

                + COLUMN_NAME + " TEXT,"
                + COLUMN_CONTACT + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_HOME + " TEXT,"


                + COLUMN_STARS + " FROM " + TABLE_CUSTOMER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        if (cursor.moveToFirst()) {
            do {
                customers.add(new Customer(
                        cursor.getInt(0),


                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),

                        cursor.getInt(5)
                ));


            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return customers;
    }

    /*TODO: In order to perform an update,
       a method called updateNote() must be implemented in DBHelper.java.
        The method will accept a Note object and perform a database update. */
    public int updatecustomer(Customer data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_NAME, data.getName());
        values.put(COLUMN_CONTACT, data.getContact());
        values.put(COLUMN_EMAIL, data.getEmail());
        values.put(COLUMN_HOME, data.getHome());


        values.put(COLUMN_STARS, data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.getId())};
        int result = db.update(TABLE_CUSTOMER, values, condition, args);
        if (result < 1) {
            Log.d("DBHelper", "Update failed");
        }

        db.close();
        return result;
    }
    /*TODO:To delete a record from the database,
       the method will accept an ID as the primary reference. */
    public int deletecustomer(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_CUSTOMER, condition, args);
        if (result < 1) {
            Log.d("DBHelper", "Deletion failed");
        }

        db.close();
        return result;
    }


}
