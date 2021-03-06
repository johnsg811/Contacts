package msd.com.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Johns on 10/12/2015.
 */
public class UserDb extends SQLiteOpenHelper {

    private static final String DatabaseName = "UserInfo";
    private static final int DatabaseVersion = 1;

    private static final String CreateQuuery = "CREATE TABLE "+Contact.UserInfo.TABLE+"("+ Contact.UserInfo.Name+" TEXT,"+
            Contact.UserInfo.Phone+" TEXT,"+ Contact.UserInfo.Email+" TEXT,"+ Contact.UserInfo.Latitude+" TEXT,"+ Contact.UserInfo.Longitude+" TEXT,"+ Contact.UserInfo.AddressName+" TEXT,"+ Contact.UserInfo.Address+" TEXT);";

    public UserDb(Context context)
    {
        super(context, DatabaseName, null, DatabaseVersion);
        Log.e("Database Operations", "Database Created/opened");
        //context.deleteDatabase(DatabaseName);
        //Log.e("Database Operations", "Database Deleted");


    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /*db.execSQL("drop table if exists "+Contact.UserInfo.TABLE);
        Log.e("Database Operations", "Table deleted...");*/
        db.execSQL(CreateQuuery);
        Log.e("Database Operations", "Table Created...");
    }

    public Cursor getInfo(SQLiteDatabase db)
    {
        //Contact.UserInfo.Latitude, Contact.UserInfo.Longitude, Contact.UserInfo.AddressName, Contact.UserInfo.Address
        Cursor cursor;
        //String[] projections = {Contact.UserInfo.Name, Contact.UserInfo.Phone, Contact.UserInfo.Email};
        String[] projections = {Contact.UserInfo.Name, Contact.UserInfo.Phone, Contact.UserInfo.Email,
                Contact.UserInfo.Latitude, Contact.UserInfo.Longitude, Contact.UserInfo.AddressName, Contact.UserInfo.Address};
        cursor = db.query(Contact.UserInfo.TABLE, projections, null, null, null, null, null);

        return cursor;
    }

    public void addInfo(String name, String mobile, String email, String latitude, String longitude, String addressName, String address, SQLiteDatabase db)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contact.UserInfo.Name,name);
        contentValues.put(Contact.UserInfo.Phone,mobile);
        contentValues.put(Contact.UserInfo.Email,email);
        contentValues.put(Contact.UserInfo.Latitude,latitude);
        contentValues.put(Contact.UserInfo.Longitude,longitude);
        contentValues.put(Contact.UserInfo.AddressName,addressName);
        contentValues.put(Contact.UserInfo.Address,address);


        Log.e("Database Operations", "verifying the content I am inserting");


        Log.e("Database Operations", "name: " + name);
        Log.e("Database Operations", "latitude: " + latitude);

        Log.e("Database Operations", "from content values name: " + contentValues.get(Contact.UserInfo.Name) );
        Log.e("Database Operations", "from content values  latitude: " + contentValues.get(Contact.UserInfo.Latitude));

        Log.e("Database Operations", "giong to insert one row inserted");
        db.insert(Contact.UserInfo.TABLE, null, contentValues);
        Log.e("Database Operations", "One row inserted");
    }

    public Cursor getContact(String name, SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {Contact.UserInfo.Phone, Contact.UserInfo.Email, Contact.UserInfo.Latitude, Contact.UserInfo.Longitude, Contact.UserInfo.AddressName, Contact.UserInfo.Address};
        String select = Contact.UserInfo.Name+" LIKE ?";
        String[] select_arg = {name};
        Cursor cursor = sqLiteDatabase.query(Contact.UserInfo.TABLE, projections, select, select_arg, null, null, null);
        return cursor;
    }

    public void deleteInfo(String name, SQLiteDatabase sqLiteDatabase)
    {
        String select = Contact.UserInfo.Name+" LIKE ?";
        String[] select_arg = {name};
        sqLiteDatabase.delete(Contact.UserInfo.TABLE,select,select_arg);
    }

    public int updateInfo(String oldName, String newName, String newMobile, String newEmail, String newLatidue, String newLongitude, String newAddressName, String newAddress, SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contact.UserInfo.Name,newName);
        contentValues.put(Contact.UserInfo.Phone,newMobile);
        contentValues.put(Contact.UserInfo.Email,newEmail);
        contentValues.put(Contact.UserInfo.Latitude,newLatidue);
        contentValues.put(Contact.UserInfo.Longitude,newLongitude);
        contentValues.put(Contact.UserInfo.AddressName,newAddressName);
        contentValues.put(Contact.UserInfo.Address,newAddress);
        String selection = Contact.UserInfo.Name + " LIKE ?";
        String[] select_args = {oldName};
        int count = sqLiteDatabase.update(Contact.UserInfo.TABLE,contentValues,selection,select_args);
        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

}
