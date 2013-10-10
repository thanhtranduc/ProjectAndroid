package com.example.BasicUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private final static String TAG = "LoginActivity";
    EditText name;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        name = (EditText)findViewById(R.id.name);
        Button buttonLogin = (Button)findViewById(R.id.loginButoon);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0)
            {
                Intent nextActivity = new Intent(getApplicationContext(), ProfileActivity.class);
                //send data another activity
                nextActivity.putExtra("name", name.getText().toString());
                Log.d(TAG,"button login called");
                startActivity(nextActivity);
            }
        });
    }
}
