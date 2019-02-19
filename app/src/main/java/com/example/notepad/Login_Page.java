package com.example.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login_Page extends AppCompatActivity {

    private TextView uname;
    private TextView pword;
    private Button login;
    private String username="";
    private String password="";
    SQLiteDatabase mydatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //startActivity(new Intent(Login_Page.this, TableView.class));
        setContentView(R.layout.activity_main);
        uname = (TextView)findViewById(R.id.username);
        pword = (TextView)findViewById(R.id.password);
        mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE, null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Accounts(Username VARCHAR(255) PRIMARY KEY,Password VARCHAR(255));");
        try{
            mydatabase.execSQL("INSERT INTO Accounts VALUES('admin','1234');");
            mydatabase.execSQL("INSERT INTO Accounts VALUES('joe','1234');");
        }
        catch(Exception e){
            //Toast.makeText(getApplicationContext(),"Failed to add two rows",Toast.LENGTH_LONG).show();
        }
        login = (Button)findViewById(R.id.login);
        Button notMember = (Button)findViewById(R.id.register);
        notMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login_Page.this, Register_Page.class));
            }
        });


    }


    public void login(View view){

        this.username=uname.getText().toString().trim();
        this.password=pword.getText().toString().trim();
        String query= "Select * from Accounts where Username='"+username+"'";
        Cursor resultSet1 = mydatabase.rawQuery(query,null);
        if(resultSet1.moveToFirst()){
            if(resultSet1.getString(0).equals(username))
            {
                if(resultSet1.getString(1).equals(password))
                {
                    resultSet1.close();
                    startActivity(new Intent(Login_Page.this, Succesfully_Logged_In.class));
                }
                else
                {
                    resultSet1.close();
                    startActivity((new Intent(Login_Page.this, Failed_Login.class)));
                }
            }

        }
        else
        {
            resultSet1.close();
            startActivity(new Intent(Login_Page.this, Failed_Login.class));

        }


    }







}
