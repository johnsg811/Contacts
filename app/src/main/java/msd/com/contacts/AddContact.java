package msd.com.contacts;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddContact extends AppCompatActivity {

    EditText Name, Mobile, Email;
    Context context = this;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        Name = (EditText) findViewById(R.id.ContactName);
        Mobile = (EditText) findViewById(R.id.ContactPhone);
        Email = (EditText) findViewById(R.id.ContactEmail);
    }

    public void addContact(View view)
    {
        /*
        if (text == null || text.equals("")) {
    Toast.makeText(this, "You did not enter a number", Toast.LENGTH_SHORT).show();
    return;
}
        */
        String name = Name.getText().toString();
        String mobile = Mobile.getText().toString();
        String email = Email.getText().toString();

        String emailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String numerical = "^[0-9]*$";


        if(name == null || name.equals(""))
        {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(mobile == null || mobile.equals("") || !mobile.matches(numerical))
        {
            Toast.makeText(this, "Please Enter Mobile", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(email == null || email.equals("") || !email.matches(emailRegex))
        {
            Toast.makeText(this, "Please Enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        else
        {
            //String name = Name.getText().toString();
            //String mobile = Mobile.getText().toString();
            //String email = Email.getText().toString();
            userDb = new UserDb(context);
            sqLiteDatabase = userDb.getWritableDatabase();
            userDb.addInfo(name,mobile,email,sqLiteDatabase);
            Toast.makeText(getBaseContext(), "Data Saved", Toast.LENGTH_LONG).show();
            userDb.close();
            finish();
        }


    }

    public void cancel(View view)
    {
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_contact, menu);
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