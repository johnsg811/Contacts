package msd.com.contacts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Update extends AppCompatActivity {

    TextView Title;
    EditText SearchName, UpdateName, UpdateMobile, UpdateEmail, UpdateLatidude, UpdateLongitude, UpdateAddressName, UpdateAddress;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;
    String searchName, updateName, updateMobile, updateEmail, updatelatitude, updatelongitute, updateAdressName, updateAddress;
    Button UpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        SearchName = (EditText)findViewById(R.id.searchName);
        UpdateName = (EditText)findViewById(R.id.updateName);
        UpdateEmail = (EditText)findViewById(R.id.updateEmail);
        UpdateMobile = (EditText)findViewById(R.id.updateMobile);
        UpdateLatidude = (EditText)findViewById(R.id.updateLatidude);
        UpdateLongitude = (EditText)findViewById(R.id.updateLongitude);
        UpdateAddressName = (EditText)findViewById(R.id.updateAddressName);
        UpdateAddress = (EditText)findViewById(R.id.updateAddress);
        UpdateButton = (Button)findViewById(R.id.update);
        Title = (TextView)findViewById(R.id.title);

        UpdateName.setVisibility(View.GONE);
        UpdateMobile.setVisibility(View.GONE);
        UpdateEmail.setVisibility(View.GONE);
        UpdateLatidude.setVisibility(View.GONE);
        UpdateLongitude.setVisibility(View.GONE);
        UpdateAddressName.setVisibility(View.GONE);
        UpdateAddress.setVisibility(View.GONE);
        UpdateButton.setVisibility(View.GONE);
        Title.setVisibility(View.GONE);
    }

    public void searchContact(View view)
    {
        searchName = SearchName.getText().toString();
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        Cursor cursor = userDb.getContact(searchName,sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            updateMobile = cursor.getString(0);
            updateEmail = cursor.getString(1);
            updatelatitude = cursor.getString(2);
            updatelongitute = cursor.getString(3);
            updateAdressName = cursor.getString(4);
            updateAddress = cursor.getString(5);

            updateName = searchName;
            UpdateName.setText(updateName);
            UpdateMobile.setText(updateMobile);
            UpdateEmail.setText(updateEmail);
            UpdateLatidude.setText(updatelatitude);
            UpdateLongitude.setText(updatelongitute);
            UpdateAddressName.setText(updateAdressName);
            UpdateAddress.setText(updateAddress);
            UpdateEmail.setVisibility(View.VISIBLE);
            UpdateLatidude.setVisibility(View.VISIBLE);
            UpdateLongitude.setVisibility(View.VISIBLE);
            UpdateAddressName.setVisibility(View.VISIBLE);
            UpdateAddress.setVisibility(View.VISIBLE);
            UpdateButton.setVisibility(View.VISIBLE);
            Title.setVisibility(View.VISIBLE);
        }
    }

    public void updateContact(View view)
    {
        String name, email, mobile, latitude, longitude, addressName, address;
        name = UpdateName.getText().toString();
        mobile = UpdateMobile.getText().toString();
        email = UpdateEmail.getText().toString();
        latitude = UpdateLatidude.getText().toString();
        longitude = UpdateLongitude.getText().toString();
        addressName = UpdateAddressName.getText().toString();
        address = UpdateAddress.getText().toString();

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
            userDb = new UserDb(getApplicationContext());
            sqLiteDatabase = userDb.getWritableDatabase();
            int count = userDb.updateInfo(searchName, name, mobile, email, latitude, longitude, addressName, address, sqLiteDatabase);
            Toast.makeText(getApplicationContext(),name+" updated",Toast.LENGTH_LONG).show();
            finish();
        }

        /*userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getWritableDatabase();
        int count = userDb.updateInfo(searchName, name, mobile, email, sqLiteDatabase);
        Toast.makeText(getApplicationContext(),count+" contact updated",Toast.LENGTH_LONG).show();
        finish();*/
    }

}
