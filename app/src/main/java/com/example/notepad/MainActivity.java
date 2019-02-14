package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView uname;
    private TextView pword;
    private Button login;
    private String username="";
    private String password="";
    private HashMap<String,String> users;
    private ArrayList<String> usernames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uname = (TextView)findViewById(R.id.username);
        pword = (TextView)findViewById(R.id.password);

        login = (Button)findViewById(R.id.login);
        users = new HashMap<>();
        usernames = new ArrayList<>();
        users.put("admin","1234");
        if(usernames.contains("admin"))
        {

        }
        else
        {
            usernames.add("admin");
        }
        users.put(getIntent().getStringExtra("Username"),getIntent().getStringExtra("Password"));
        usernames.add(getIntent().getStringExtra("Username"));




    }


    public void login(View view){

        this.username=uname.getText().toString();
        this.password=pword.getText().toString();
        if(users.containsKey(username))
        {
            if(password.equals(users.get(username)))
            {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
            else
            {
                startActivity(new Intent(MainActivity.this, Main3Activity.class));
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"This username does not exist",Toast.LENGTH_LONG).show();
        }

    }

    public void notMember(View view){

        Intent stap=new Intent(MainActivity.this,Main4Activity.class);
        stap.putStringArrayListExtra("usernames",usernames);
        startActivity(stap);
    }

}
