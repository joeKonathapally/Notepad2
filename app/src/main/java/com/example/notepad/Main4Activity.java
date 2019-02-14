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

        usernames=getIntent().getStringArrayListExtra("usernames");

        final TextView user = (TextView)findViewById(R.id.user);
        final TextView password = (TextView)findViewById(R.id.password);
        final TextView password1 = (TextView)findViewById(R.id.passwordReentry);

        Button btn = (Button)findViewById(R.id.register);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getText().toString().isEmpty() && password.getText().toString().isEmpty() && password1.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(password.getText().toString().equals(password1.getText().toString()))
                    {
                        if(usernames.contains(user.getText().toString()))
                        {
                            Toast.makeText(getApplicationContext(),"The username already exist!",Toast.LENGTH_LONG).show();
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
            }
        });

        Button btn1 = (Button)findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main4Activity.this, MainActivity.class));
            }
        });


    }
}
