package msd.com.contacts;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    public void NewContact(View v)
    {
        startActivity(new Intent(MainActivity.this, AddContact.class));
        //overridePendingTransition(R.anim.right_animation, R.anim.left_animation);
    }

    public void viewContact(View v)
    {
        startActivity(new Intent(MainActivity.this, listLayout.class));
    }

    public void search(View v)
    {
        startActivity(new Intent(MainActivity.this, Search.class));
    }

    public void update(View v)
    {
        startActivity(new Intent(MainActivity.this, Update.class));
    }

    public void delete(View v)
    {
        startActivity(new Intent(MainActivity.this, Delete.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
