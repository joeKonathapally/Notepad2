package com.example.notepad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private TextView uname;
    private TextView pword;
    private Button login;
    private String username="";
    private String password="";
    private HashMap<String,String> users;
    private ArrayList<String> usernames;
    SQLiteDatabase mydatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname = (TextView)findViewById(R.id.username);
        pword = (TextView)findViewById(R.id.password);
        mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Accounts(Username VARCHAR ,Password VARCHAR, PRIMARY KEY (Username));");
        try{
            mydatabase.execSQL("INSERT INTO Accounts VALUES('admin','1234');");
        }
        catch(Exception e){

        }
        login = (Button)findViewById(R.id.login);
    }


    public void login(View view){

        this.username=uname.getText().toString();
        this.password=pword.getText().toString();
        String query= "Select * from Accounts";
        Cursor resultSet1 = mydatabase.rawQuery(query,null);
        if(resultSet1.moveToFirst())
        {
            do
            {
                if(resultSet1.getString(0)==username)
                {
                    if(resultSet1.getString(1).equals(password))
                    {
                        startActivity(new Intent(MainActivity.this, Main2Activity.class));
                    }
                }
            }while(resultSet1.moveToNext());
        }


    }

    public void notMember(View view){

        Intent stap=new Intent(MainActivity.this,Main4Activity.class);
        stap.putStringArrayListExtra("usernames",usernames);
        startActivity(stap);
    }

    private boolean isEmpty(Cursor resultSet)
    {
        for(int i=0;i<resultSet.getCount();i++)
        {
            if(resultSet.isNull(i)==false)
            {
                return false;
            }
        }
        return true;
    }



}
