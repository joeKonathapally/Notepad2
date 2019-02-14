package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Main4Activity extends AppCompatActivity {

    private String uname="";
    private String pword="";
    private ArrayList<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent garble = getIntent();
        usernames=garble.getStringArrayListExtra("usernames");

        final TextView user = (TextView)findViewById(R.id.user);
        final TextView password = (TextView)findViewById(R.id.password);
        final TextView passwrod1 = (TextView)findViewById(R.id.passwordReentry);

        Button btn = (Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(passwrod1.getText().toString()))
                {
                    if(usernames.contains(user.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(),"The username alreday exist!",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Intent goHome = new Intent(Main4Activity.this, MainActivity.class);
                        goHome.putExtra("Username",user.getText().toString());
                        goHome.putExtra("Password",password.getText().toString());
                        startActivity(goHome);
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Passwords do not match!!!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
