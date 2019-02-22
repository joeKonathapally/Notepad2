package com.example.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TableView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_view);
        SQLiteDatabase mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE,null);
        String query = "Select * from Notes where Username='admin';";
        Cursor resultSet = mydatabase.rawQuery(query,null);
        String table="";
        if(resultSet.moveToFirst())
        {
            do{
                table = table+resultSet.getInt(0)+" "+resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)+"\n";
            }while(resultSet.moveToNext());
        }
        table = table+"end of values";
        TextView garble = (TextView)findViewById(R.id.textView2);
        garble.setText(table);
        Button returning = (Button)findViewById(R.id.button5);
        returning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TableView.this, Login_Page.class));
            }
        });
    }
}
