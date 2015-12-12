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
    EditText SearchName, UpdateName, UpdateMobile, UpdateEmail;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;
    String searchName, updateName, updateMobile, updateEmail;
    Button UpdateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        SearchName = (EditText)findViewById(R.id.searchName);
        UpdateName = (EditText)findViewById(R.id.updateName);
        UpdateEmail = (EditText)findViewById(R.id.updateEmail);
        UpdateMobile = (EditText)findViewById(R.id.updateMobile);
        UpdateButton = (Button)findViewById(R.id.update);
        Title = (TextView)findViewById(R.id.title);

        UpdateName.setVisibility(View.GONE);
        UpdateMobile.setVisibility(View.GONE);
        UpdateEmail.setVisibility(View.GONE);
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
            updateName = searchName;
            UpdateName.setText(updateName);
            UpdateMobile.setText(updateMobile);
            UpdateEmail.setText(updateEmail);
            UpdateName.setVisibility(View.VISIBLE);
            UpdateMobile.setVisibility(View.VISIBLE);
            UpdateEmail.setVisibility(View.VISIBLE);
            UpdateButton.setVisibility(View.VISIBLE);
            Title.setVisibility(View.VISIBLE);
        }
    }

    public void updateContact(View view)
    {
        String name, email, mobile;
        name = UpdateName.getText().toString();
        mobile = UpdateMobile.getText().toString();
        email = UpdateEmail.getText().toString();

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
            int count = userDb.updateInfo(searchName, name, mobile, email, sqLiteDatabase);
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
