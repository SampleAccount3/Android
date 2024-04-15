package com.example.android.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";
    public static final String ID = "ID";
    public static final String CUSTOMER_SAMPLE_TEXT = "CUSTOMER_SAMPLE_TEXT";
    public static final int DB_VERSION = 11;
    private static final String CUSTOMER_INCOME = "CUSTOMER_INCOME" ;
    private static final String DATE_CREATED = "DATE_CREATEDS";

    CustomerModel customerModel;

    public DataBaseHelper(@Nullable Context context) {
        super(context, "Customer.db", null, DB_VERSION);
    }
    /**
    * This is called the first time a database is accessed.
    */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String dropTable = "DROP TABLE IF EXISTS " + CUSTOMER_TABLE;
        db.execSQL(dropTable);

        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CUSTOMER_NAME + " TEXT, " + CUSTOMER_AGE + " INT, "
                + ACTIVE_CUSTOMER + " BOOL)";
        db.execSQL(createTableStatement);
    }

    /**
     * This is called if the database Version changes. It Prevents previous user's app from breaking
     * when you change the database design.
     */

//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < DB_VERSION){
//            String addColumnStatement = "ALTER TABLE " + CUSTOMER_TABLE + " ADD COLUMN " + CUSTOMER_SAMPLE_TEXT + " TEXT";
//            db.execSQL(addColumnStatement);
//        }
//
//    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 8:
                if (!isColumnExists(db, CUSTOMER_TABLE, CUSTOMER_SAMPLE_TEXT)) {
                    db.execSQL("ALTER TABLE " + CUSTOMER_TABLE + " ADD COLUMN " + CUSTOMER_SAMPLE_TEXT + " TEXT");
                }
            case 9:
                if (!isColumnExists(db, CUSTOMER_TABLE, CUSTOMER_INCOME)) {
                    db.execSQL("ALTER TABLE " + CUSTOMER_TABLE + " ADD COLUMN " + CUSTOMER_INCOME + " INTEGER");
                }
            case 10:
                if (!isColumnExists(db, CUSTOMER_TABLE, DATE_CREATED)) {
                    db.execSQL("ALTER TABLE " + CUSTOMER_TABLE + " ADD COLUMN " + DATE_CREATED + " TEXT");
                }
                break;
        }

    }

    private boolean isColumnExists(SQLiteDatabase db, String tableName, String columnName) {
        Cursor cursor = db.rawQuery("PRAGMA table_info(" + tableName + ")", null);
        while (cursor.moveToNext()) {
            String existingColumnName = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            if (existingColumnName.equals(columnName)) {
                cursor.close();
                return true;
            }
        }
        cursor.close();
        return false;
    }

    public boolean AddCustomer(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_NAME,customerModel.getName());
        cv.put(CUSTOMER_AGE,customerModel.getAge());
        cv.put(ACTIVE_CUSTOMER,customerModel.isActiveCustomer());
        cv.put(CUSTOMER_SAMPLE_TEXT,customerModel.getSample());
        cv.put(CUSTOMER_INCOME,customerModel.getIncome());
        cv.put(DATE_CREATED,customerModel.getDateCreated());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    /**
     * Find the CustomerModel in the database. if found, delete it and return true. if not returns false
     */

    public boolean DeleteCustomer(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        String stringQuery = "DELETE FROM " + CUSTOMER_TABLE + " WHERE " + ID + " = " + customerModel.getId();

        Cursor cursor = db.rawQuery(stringQuery, null);

        if (cursor.moveToFirst()){
            return true;
        }else{
            return false;
        }
    }

    public List<CustomerModel> GetAll(){
        List<CustomerModel> returnList = new ArrayList<>();

        // get Data from the Database

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " ORDER BY ID DESC";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor  = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                int customerAge = cursor.getInt(2);
                boolean customerActive = cursor.getInt(3) == 1? true : false;
                String sampleText =  cursor.getString(4);
                int customerIncome = cursor.getInt(5);
                String dateCreated = cursor.getString(6);

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, sampleText, customerAge, customerActive,customerIncome,dateCreated);
                returnList.add(newCustomer);
            }while(cursor.moveToNext());
        }else {}
        cursor.close();
        db.close();
        return returnList;
    }

}
