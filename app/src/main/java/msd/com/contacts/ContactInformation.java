package msd.com.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactInformation extends AppCompatActivity {

    TextView Name;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);

        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();

        Name = (TextView) findViewById(R.id.name);

        Bundle extras = getIntent().getExtras();
        String searchName= "";
        if (extras != null) {
            searchName = extras.getString("searchname");
        }

        Cursor cursor = userDb.getContact(searchName,sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            String name = cursor.getString(0);

            Name.setText(name);

//            String latitudeStr = cursor.getString(2);
//            String longitudeStr = cursor.getString(3);
//            String addressName = cursor.getString(4);
//            String address = cursor.getString(5);

            //now i have got required fields from db
            //now i am going to play with the google map api
            //after parsing from string to double




        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_information, menu);
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
