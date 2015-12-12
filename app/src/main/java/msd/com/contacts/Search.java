package msd.com.contacts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    TextView Email, Phone;
    EditText SearchName, Message;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;
    String searchName;
    //helo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchName = (EditText)findViewById(R.id.searchName);
        Message = (EditText)findViewById(R.id.message);
        Phone = (TextView)findViewById(R.id.phone);
        Email = (TextView)findViewById(R.id.email);

        Email.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
    }

    public void searchContact(View view)
    {
        /*Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0862335871"));
        startActivity(callIntent);*/
        searchName = SearchName.getText().toString();
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        Cursor cursor = userDb.getContact(searchName,sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            String mobile = cursor.getString(0);
            String email = cursor.getString(1);
            Phone.setText(mobile);


            //CALL THE PERSON ON SEARCH BUTTON
            /*Log.d("Hi","I want to call "+ mobile);
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mobile));
            startActivity(callIntent);*/

            //SEND SOME EMERGENNCY MESSGAe
            /*String messageToSend = "this is a message";
            String number = "0877845450";
            SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null);*/

            Email.setText(email);
            Email.setVisibility(View.VISIBLE);
            Phone.setVisibility(View.VISIBLE);
        }
    }

    public void sendMessage(View view)
    {
        Log.d("Hi","this works");
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        Cursor cursor = userDb.getContact(searchName, sqLiteDatabase);

        Log.d("Hi","going to move on the cursor");
        if(cursor.moveToFirst())
        {
            Log.d("Hi","we are in the cursor move to first");
            String mobile = cursor.getString(0);
            //String email = cursor.getString(1);
            //Phone.setText(mobile);

            Log.d("Hi","now obtaining the sms from message.gettext ");
            String sms = Message.getText().toString();
            //String sms = ;
            try {
                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(mobile, null, sms, null, null);
                Toast.makeText(getApplicationContext(), "SMS Sent!",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(),
                        "SMS faild, please try again later!",
                        Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            Log.d("Hi","end of sendsms");
           //Email.setText(email);
            //Email.setVisibility(View.VISIBLE);
            //Phone.setVisibility(View.VISIBLE);
        }

        //String mobile = cursor.getString(0);


    }

/*    public void deleteContact(View view)
    {
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        userDb.deleteInfo(searchName, sqLiteDatabase);
        Toast.makeText(getBaseContext(),"Deleted", Toast.LENGTH_LONG).show();
        SearchName.setText("");
        Phone.setText("");
        Email.setText("");

    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
