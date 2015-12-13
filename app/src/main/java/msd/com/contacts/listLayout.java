package msd.com.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class listLayout extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    UserDb userDb;
    Cursor cursor;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_layout);
        listView = (ListView)findViewById(R.id.listView);
        listAdapter = new ListAdapter(getApplicationContext(), R.layout.row_layout);
        listView.setAdapter(listAdapter);
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        cursor = userDb.getInfo(sqLiteDatabase);

        if(cursor.moveToFirst())
        {
            do{
                String name, mobile, email, latitude, longitude, addressName, address;
                name = cursor.getString(0);
                mobile = cursor.getString(1);
                email = cursor.getString(2);
                latitude = cursor.getString(3);
                longitude = cursor.getString(4);
                addressName = cursor.getString(5);
                address = cursor.getString(6);

                Log.e("listlayout Operations", "name: " + name);
                Log.e("listlayout Operations", "latitude: " + latitude);


                Data data = new Data(name, mobile, email, latitude, longitude, addressName, address);
                listAdapter.add(data);
            }while (cursor.moveToNext());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
