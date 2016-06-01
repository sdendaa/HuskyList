package tcss450.uw.edu.mynewapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tcss450.uw.edu.mynewapp.model.ItemContent;

/**
 * Created by vsmirnov on 4/26/16.z
 */
public class SQLite {

    public static final int DB_VERSION = 4;
    public static String DB_NAME;
    public static String mName;
    public static int ID_count;

    private ItemDBHelper mItemDBHelper;
    private SQLiteDatabase mSQLiteDatabase;
    private static String DB_TABLE;

    public SQLite(Context context, String name) {
        DB_TABLE = name;
        DB_NAME = name + ".db";
        mName = name;
        ID_count = 0;
        mItemDBHelper = new ItemDBHelper(
                context, DB_NAME, null, DB_VERSION, name);
        switch(mName) {
            case "Book":
                SQLiteDatabase mBookDatabase = mItemDBHelper.getWritableDatabase();
                mSQLiteDatabase = mBookDatabase;
                break;
            case "Cellphone":
                SQLiteDatabase mCellphoneDatabase = mItemDBHelper.getWritableDatabase();
                mSQLiteDatabase = mCellphoneDatabase;
                break;
            case "VideoGame":
                SQLiteDatabase mVideoGameDatabase = mItemDBHelper.getWritableDatabase();
                mSQLiteDatabase = mVideoGameDatabase;
                break;
            case "HouseHold":
                SQLiteDatabase mHouseHoldDatabase = mItemDBHelper.getWritableDatabase();
                mSQLiteDatabase = mHouseHoldDatabase;
                break;
            case "Vehicle":
                SQLiteDatabase mVehicleDatabase = mItemDBHelper.getWritableDatabase();
                mSQLiteDatabase = mVehicleDatabase;
                break;
            case "Computer":
                SQLiteDatabase mComputerDatabase = mItemDBHelper.getWritableDatabase();
                mSQLiteDatabase = mComputerDatabase;
                break;
        }
    }


    public void deleteItems() {
        mSQLiteDatabase.delete(DB_TABLE, null, null);
    }


    public List<ItemContent> getItems() {

        String[] columns = {
                "ItemID", "SellerUserName", "ItemTitle", "ItemPrice", "ItemCond", "ItemDesc", "SellerLocation", "SellerContact"
        };

        Cursor c = mSQLiteDatabase.query(
                DB_TABLE,  // The table to query
                columns,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        c.moveToFirst();
        List<ItemContent> list = new ArrayList<ItemContent>();
        for (int i=0; i<c.getCount(); i++) {
            int ItemID = c.getInt(0);
            String SellerUserName = c.getString(1);
            String ItemTitle = c.getString(2);
            String ItemPrice = c.getString(3);
            String ItemCond = c.getString(4);
            String ItemDesc = c.getString(5);
            String SellerLocation = c.getString(6);
            String SellerContact = c.getString(7);
            ItemContent item = new ItemContent(SellerUserName, ItemTitle, ItemPrice, ItemCond, ItemDesc, SellerLocation, SellerContact);
            item.setItemId(ItemID);
            list.add(item);
            c.moveToNext();
        }

        //    System.out.println(mName + " " + c.getCount());

        return list;
    }



    public boolean insertItem(int ItemID, String SellerUserName, String ItemTitle, String ItemPrice, String ItemCond, String ItemDesc, String SellerLocation, String SellerContact) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ItemID", ItemID);
        contentValues.put("SellerUserName", SellerUserName);
        contentValues.put("ItemTitle", ItemTitle);
        contentValues.put("ItemPrice", ItemPrice);
        contentValues.put("ItemCond", ItemCond);
        contentValues.put("ItemDesc", ItemDesc);
        contentValues.put("SellerLocation", SellerLocation);
        contentValues.put("SellerContact", SellerContact);

        long rowId = mSQLiteDatabase.insert(DB_TABLE, null, contentValues);
        return rowId != -1;
    }

    public void closeDB() {
        mSQLiteDatabase.close();
    }

    public class ItemDBHelper extends SQLiteOpenHelper {

        private String CREATE_ITEM_SQL;

        private String DROP_ITEM_SQL;

        public ItemDBHelper(Context context, String DBname, SQLiteDatabase.CursorFactory factory, int version, String name) {
            super(context, DBname, factory, version);
            CREATE_ITEM_SQL = "CREATE TABLE IF NOT EXISTS " + name
                    + " (ItemID INTEGER PRIMARY KEY, SellerUserName TEXT, ItemTitle TEXT, ItemPrice TEXT, ItemCond TEXT, ItemDesc TEXT, SellerLocation TEXT, SellerContact TEXT)";
            DROP_ITEM_SQL = "DROP TABLE IF EXISTS " + name;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_ITEM_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_ITEM_SQL);
            onCreate(sqLiteDatabase);
        }


    }
}
