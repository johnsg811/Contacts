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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity {

    TextView Email, Phone, EmailAddress, EmailSubject, EmailMessage, TextMobile, TextEmail;
    EditText SearchName, Message;
    UserDb userDb;
    SQLiteDatabase sqLiteDatabase;
    String searchName;
    Button Search, EmailSend, SmsButton;
    ImageButton MapButton, CallButton, EmailButton, MessageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchName = (EditText)findViewById(R.id.searchName);
        Message = (EditText)findViewById(R.id.message);
        Phone = (TextView)findViewById(R.id.phone);
        Email = (TextView)findViewById(R.id.email);
        TextMobile = (TextView)findViewById(R.id.txtmobile);
        TextEmail = (TextView)findViewById(R.id.txtemail);
        EmailAddress = (EditText)findViewById(R.id.emailAddress);
        EmailSubject = (EditText)findViewById(R.id.emailSubject);
        EmailMessage = (EditText)findViewById(R.id.emailMessage);
        /*Latitude = (TextView)findViewById(R.id.latitude);
        Longitude = (TextView)findViewById(R.id.longitude);
        AddressName = (TextView)findViewById(R.id.addressName);
        Address = (TextView)findViewById(R.id.address);*/
        Search = (Button)findViewById(R.id.search);
        MessageButton = (ImageButton)findViewById(R.id.messageButton);
        //InstantSms = (ImageButton)findViewById(R.id.instants);
        MapButton = (ImageButton)findViewById(R.id.btnMaps);
        CallButton = (ImageButton)findViewById(R.id.btnCall);
        EmailButton = (ImageButton)findViewById(R.id.btnEmail);
        EmailSend = (Button)findViewById(R.id.buttonSend);
        SmsButton = (Button)findViewById(R.id.smsButton);

        Message.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        EmailAddress.setVisibility(View.GONE);
        EmailSubject.setVisibility(View.GONE);
        EmailMessage.setVisibility(View.GONE);
        TextMobile.setVisibility(View.GONE);
        TextEmail.setVisibility(View.GONE);
        MessageButton.setVisibility(View.GONE);
        MapButton.setVisibility(View.GONE);
        CallButton.setVisibility(View.GONE);
        EmailButton.setVisibility(View.GONE);
        EmailSend.setVisibility(View.GONE);
        SmsButton.setVisibility(View.GONE);
        //InstantSms.setVisibility(View.GONE);


        /*Email.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        Latitude.setVisibility(View.GONE);
        Longitude.setVisibility(View.GONE);
        AddressName.setVisibility(View.GONE);
        Message.setVisibility(View.GONE);
        MessageButton.setVisibility(View.GONE);*/
    }

    public void email(View view)
    {
        SearchName.setVisibility(View.GONE);
        Message.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        MessageButton.setVisibility(View.GONE);
        MapButton.setVisibility(View.GONE);
        CallButton.setVisibility(View.GONE);
        EmailButton.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        TextMobile.setVisibility(View.GONE);
        TextEmail.setVisibility(View.GONE);

        EmailAddress.setVisibility(View.VISIBLE);
        EmailSubject.setVisibility(View.VISIBLE);
        EmailMessage.setVisibility(View.VISIBLE);
        EmailSend.setVisibility(View.VISIBLE);


        EmailSend.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                String emailRegex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                String to = EmailAddress.getText().toString();
                String subject = EmailSubject.getText().toString();
                String message = EmailMessage.getText().toString();

                if (to == null || to.equals("") || !to.matches(emailRegex)) {
                    Toast.makeText(v.getContext(), "Please correct email",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {;
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                    //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
                    //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, message);

                    //need this to prompts email client only
                    email.setType("message/rfc822");

                    startActivity(Intent.createChooser(email, "Choose an Email client :"));
                    finish();

                }

            }
        });

    }

    public void maps(View view)
    {
        Intent intent = new Intent(Search.this, Maps.class);
        //startActivity(new Intent(Search.this, Maps.class));
        intent.putExtra("searchname", searchName);
        startActivity(intent);
    }

    /*public void instantMessage(View view)
    {
        String messageToSend = "this is a message";
        String number = "0877845450";
        SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null);
    }*/

    public void searchContact(View view)
    {
        if(SearchName == null || SearchName.equals(""))
        {
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
        /*Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0862335871"));
        startActivity(callIntent);*/
        searchName = SearchName.getText().toString();
        userDb = new UserDb(getApplicationContext());
        sqLiteDatabase = userDb.getReadableDatabase();
        Cursor cursor = userDb.getContact(searchName,sqLiteDatabase);
        if(cursor.moveToFirst()) {
            String mobile = cursor.getString(0);
            String email = cursor.getString(1);

            Phone.setText(mobile);
            Email.setText(email);

            /*//CALL THE PERSON ON SEARCH BUTTON
            Log.d("Hi","I want to call "+ mobile);
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mobile));
            startActivity(callIntent);*/

            //SEND SOME EMERGENNCY MESSGAe
            /*String messageToSend = "this is a message";
            String number = "0877845450";
            SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null, null);*/


            Email.setVisibility(View.VISIBLE);
            Phone.setVisibility(View.VISIBLE);
            CallButton.setVisibility(View.VISIBLE);
            EmailButton.setVisibility(View.VISIBLE);
            MapButton.setVisibility(View.VISIBLE);
            MessageButton.setVisibility(View.VISIBLE);
            TextMobile.setVisibility(View.VISIBLE);
            TextEmail.setVisibility(View.VISIBLE);
            //InstantSms.setVisibility(View.VISIBLE);


            /*Latitude.setVisibility(View.VISIBLE);
            Longitude.setVisibility(View.VISIBLE);
            AddressName.setVisibility(View.VISIBLE);
            Address.setVisibility(View.VISIBLE);*/

            /*Message.setVisibility(View.GONE);
            MessageButton.setVisibility(View.GONE);*/
        }
        }
    }

    public void call(View view)
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

            Log.d("Hi","I want to call "+ mobile);
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+mobile));
            startActivity(callIntent);



        }
    }

    /*public void smsSend(View view)
    {
        SearchName.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        MessageButton.setVisibility(View.GONE);
        MapButton.setVisibility(View.GONE);
        CallButton.setVisibility(View.GONE);
        EmailButton.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        TextMobile.setVisibility(View.GONE);
        TextEmail.setVisibility(View.GONE);

        Message.setVisibility(View.VISIBLE);
        SmsButton.setVisibility(View.VISIBLE);

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


    }*/

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

    public void sendMessage(View view)
    {
        SearchName.setVisibility(View.GONE);
        Phone.setVisibility(View.GONE);
        Email.setVisibility(View.GONE);
        MessageButton.setVisibility(View.GONE);
        MapButton.setVisibility(View.GONE);
        CallButton.setVisibility(View.GONE);
        EmailButton.setVisibility(View.GONE);
        Search.setVisibility(View.GONE);
        TextMobile.setVisibility(View.GONE);
        TextEmail.setVisibility(View.GONE);

        Message.setVisibility(View.VISIBLE);
        SmsButton.setVisibility(View.VISIBLE);


        SmsButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                finish();

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

                    Log.d("Hi", "now obtaining the sms from message.gettext ");
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

                    Log.d("Hi", "end of sendsms");
                    //Email.setText(email);
                    //Email.setVisibility(View.VISIBLE);
                    //Phone.setVisibility(View.VISIBLE);
                }

            }
        });

    }

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
