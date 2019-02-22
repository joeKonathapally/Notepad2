package com.example.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Notes extends AppCompatActivity {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        LinearLayout linear = (LinearLayout)findViewById(R.id.ll);
        username = getIntent().getStringExtra("user");
        TextView heading =(TextView)findViewById(R.id.welcome);
        heading.setText("Welcome "+username);

        SQLiteDatabase mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE,null);
        String query= "Select * from Notes where Username='"+username+"'";
        final Cursor resultSet1 = mydatabase.rawQuery(query,null);
        try{
            int i=0;
            do{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);
                Button btn = new Button(this);
                btn.setId(i);
                final int id_ = btn.getId();
                btn.setText(resultSet1.getString(2));
                linear.addView(btn, params);
                btn = ((Button) findViewById(id_));
                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {

                        Intent button = new Intent(Notes.this,Notebook.class);
                        button.putExtra("user",username);
                        button.putExtra("title",resultSet1.getString(2));
                        button.putExtra("src",resultSet1.getString(3));
                        startActivity(button);
                    }
                });

                i=i+1;
            }while(resultSet1.moveToNext());
        }
        catch(Exception e){
            resultSet1.close();
            e.printStackTrace();
        }



    }


    public void createNote(View view){

        Intent namingNote = new Intent(Notes.this,SetNoteName.class);
        namingNote.putExtra("user",this.username);
        startActivity(namingNote);

    }
}
