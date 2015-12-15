package msd.com.contacts;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Delete extends AppCompatActivity {

    TextView Name, Email, Phone, TextMobile, TextEmail, Latitude, Longitude, AddressName, Address;
    EditText SearchName;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;
    String searchName;
    //Button deleteButton;
    ImageButton deleteButton;


    public void searchContact(View view)
    {
        Log.d("does it?", "work");
        searchName = SearchName.getText().toString();
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        Cursor cursor = userDb.getContact(searchName,sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            deleteButton.setVisibility(View.VISIBLE);

            String mobile = cursor.getString(0);
            String email = cursor.getString(1);
            /*String latidude = cursor.getString(2);
            String longitude = cursor.getString(3);
            String addressName = cursor.getString(4);
            String address = cursor.getString(5);*/
            Phone.setText(mobile);
            Email.setText(email);
            /*Latitude.setText(latidude);
            Longitude.setText(longitude);
            AddressName.setText(addressName);
            Address.setText(address);*/

            Email.setVisibility(View.VISIBLE);
            Phone.setVisibility(View.VISIBLE);
            TextMobile.setVisibility(View.VISIBLE);
            TextEmail.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        SearchName = (EditText)findViewById(R.id.searchName);
        Phone = (TextView)findViewById(R.id.phone);
        Email = (TextView)findViewById(R.id.email);
        deleteButton = (ImageButton)findViewById(R.id.delete);
        TextMobile = (TextView)findViewById(R.id.txtmobile);
        TextEmail = (TextView)findViewById(R.id.txtemail);

        deleteButton.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        TextMobile.setVisibility(View.GONE);
        TextEmail.setVisibility(View.GONE);
        Log.d("invisible", "made up to invisible");
    }

    public void deleteContact(View view)
    {
        AlertDialog diaBox = AskOption();
        diaBox.show();
        /*userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        userDb.deleteInfo(searchName, sqLiteDatabase);
        Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_LONG).show();
        SearchName.setText("");
        Phone.setText("");
        Email.setText("");
        finish();*/
    }

    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                //.setIcon(R.drawable.delete)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        //your deleting code
                        userDb = new UserDb(getApplicationContext());
                        sqLiteDatabase = userDb.getReadableDatabase();
                        userDb.deleteInfo(searchName, sqLiteDatabase);
                        Toast.makeText(getBaseContext(), "Deleted", Toast.LENGTH_LONG).show();
                        SearchName.setText("");
                        Phone.setText("");
                        Email.setText("");
                        finish();
                        dialog.dismiss();
                    }

                })

                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_delete, menu);
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
