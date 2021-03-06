package com.example.max.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by max on 01/09/15.
 */
public class HomeScreen extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // choose layout
        setContentView(R.layout.home_layout);

        Intent activityThatCalled = getIntent();

        // retrive data
        String previousData = activityThatCalled.getExtras().getString("callingActivity");

        TextView textView = (TextView) findViewById(R.id.calling_activity_info_textview);

        textView.setText("Data from login: " + previousData);
    }

    public void onUserLogout(View view) {
        Intent goingBack = new Intent();

        EditText editInput = (EditText) findViewById(R.id.users_name_edit_text);

        String inputName = String.valueOf(editInput.getText());

        goingBack.putExtra("username", inputName);

        setResult(RESULT_OK, goingBack);

        finish();
    }

    public void goDetailPage(View view) {
        Log.w("home", "going detail");

//        Toast.makeText(HomeScreen.this, "Try to go detail page!", Toast.LENGTH_SHORT).show();

        Intent goDetailPage = new Intent(this, DetailScreen.class);
        getIntent().putExtra("id", "12");
        startActivity(goDetailPage);
    }
}
