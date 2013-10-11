package com.example.BasicUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private final static String TAG = "LoginActivity";
    EditText name;
    EditText pass;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        name = (EditText)findViewById(R.id.name);
        pass = (EditText)findViewById(R.id.pass);
        Button buttonLogin = (Button)findViewById(R.id.loginButoon);




        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0)
            {
                if(checkLogin(name, pass)!=null && checkLogin(name,pass))
                {
                    Intent nextActivity = new Intent(getApplicationContext(), ProfileActivity.class);
                    //send data another activity
                    nextActivity.putExtra("name", name.getText().toString());
                    Log.d(TAG,"button login called");
                    startActivity(nextActivity);
                }
            }
        });
    }
    private Boolean checkLogin(EditText _userName, EditText _password)
    {
        String userName= _userName.getText().toString();
        String password= _password.getText().toString();
        if(userName.equals("android") && password.equals("1234"))
        {
            Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(userName.equals("") || password.equals(""))
        {
            Toast.makeText(getApplicationContext(), "must be not null" , Toast.LENGTH_SHORT ).show();
            return null;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "exist account" , Toast.LENGTH_SHORT ).show();
            return false;
        }
    }
}
