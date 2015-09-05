package com.example.max.myapplication;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView txtUsername;
    private TextView txtPassword;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Get data from previous Activity
        String nameReceived = data.getStringExtra("username");
        txtUsername.setText(nameReceived);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = (TextView) findViewById(R.id.username);
        txtPassword = (TextView) findViewById(R.id.password);


        String[] inspirations = {"Coffee Machine", "Flying boards", "Inside Out Design"};


//        ListAdapter theAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, inspirations);
//        ListAdapter theAdapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.textView1, inspirations);

        ListAdapter theAdapter = new MyAdapter(this, inspirations);

        ListView listview = (ListView) findViewById(R.id.listInspiration);
        listview.setAdapter(theAdapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String inspiraiton = "You selecte" + String.valueOf(parent.getItemAtPosition(position));

                Toast.makeText(MainActivity.this, inspiraiton, Toast.LENGTH_SHORT).show();
            }
        });



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
            DialogFragment myFragment = new MyDialogFragment();

            myFragment.show(getFragmentManager(), "theDialog");

            return true;
        } else if (id == R.id.action_sync) {
            Toast.makeText(MainActivity.this, "Sync Started...", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_exit) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tryUserLogin(View view) {
        String username = String.valueOf(txtUsername.getText());
        String password = String.valueOf(txtPassword.getText());
        Boolean valid = false;

        Log.w("Login", "username:" + username + ", password:" + password);

        if (username.equals("admin") && password.equals("admin")) {
            valid = true;
        }

        if (valid) {
            Toast.makeText(MainActivity.this, R.string.msg_success_login, Toast.LENGTH_SHORT).show();

            // move to home page
            Intent getNameScreenIntent = new Intent(this, HomeScreen.class);
            final int result = 1;
            getNameScreenIntent.putExtra("callingActivity", "Data to Send from Actv 1");
//            startActivity(getNameScreenIntent);
            startActivityForResult(getNameScreenIntent, result);
        } else {
            Toast.makeText(MainActivity.this, R.string.msg_fail_login, Toast.LENGTH_SHORT).show();
        }


    }
}
