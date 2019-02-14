package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView uname;
    private TextView pword;
    private Button login;
    private String username="";
    private String password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (TextView)findViewById(R.id.username);
        pword = (TextView)findViewById(R.id.password);

        login = (Button)findViewById(R.id.login);


    }


    public void login(View view){

        this.username=uname.getText().toString();
        this.password=pword.getText().toString();
        if(username.equals("admin") && password.equals("1234"))
        {
            startActivity(new Intent(MainActivity.this, Main2Activity.class));
        }
        else
        {
            startActivity(new Intent(MainActivity.this, Main3Activity.class));
        }

    }
}
