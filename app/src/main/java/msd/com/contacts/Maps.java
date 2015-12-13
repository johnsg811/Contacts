package msd.com.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class Maps extends AppCompatActivity {

    //private static final LatLng Address = new LatLng(53.417784, -6.384220);
    private GoogleMap maps;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        maps = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();


        Bundle extras = getIntent().getExtras();
        String searchName= "";
        if (extras != null) {
            searchName = extras.getString("searchname");
        }

        Cursor cursor = userDb.getContact(searchName,sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            String latitudeStr = cursor.getString(2);
            String longitudeStr = cursor.getString(3);
            String addressName = cursor.getString(4);
            String address = cursor.getString(5);

            //now i have got required fields from db
            //now i am going to play with the google map api
            //after parsing from string to double


            Double latitude = Double.parseDouble(latitudeStr); //cursor will give us latitudeStr in String type. and this line can convert from strign to double
            Double longitude = Double.parseDouble(longitudeStr);
            LatLng Address = new LatLng(latitude, longitude);

            Marker davao = maps.addMarker(new MarkerOptions().position(Address).title(addressName).snippet(address));

            // zoom in the address
            maps.moveCamera(CameraUpdateFactory.newLatLngZoom(Address, 15));

            // animate the zoom
            maps.animateCamera(CameraUpdateFactory.zoomTo(15), 1000, null);


        }
// read from cursor
        //




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_maps, menu);
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
