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
import android.widget.ToggleButton;

public class Notes extends AppCompatActivity {

    private String username;
    private ToggleButton tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        LinearLayout linear = (LinearLayout)findViewById(R.id.ll);
        username = getIntent().getStringExtra("user");
        TextView heading =(TextView)findViewById(R.id.welcome);
        heading.setText("Welcome "+username);
        tb = (ToggleButton)findViewById(R.id.toggleButton);

        final SQLiteDatabase mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE,null);
        final String query= "Select * from Notes where Username='"+username+"'";
        final Cursor resultSet1 = mydatabase.rawQuery(query,null);
        if(resultSet1.moveToFirst())
        {
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
                    final String title=resultSet1.getString(2);
                    final String src = resultSet1.getString(3);
                    linear.addView(btn, params);
                    btn = ((Button) findViewById(id_));
                    btn.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {

                            if(tb.isChecked())
                            {
                                String query1="DELETE FROM Notes WHERE FileSrc='"+src+"';";
                                mydatabase.execSQL(query1);
                                Intent button = new Intent(Notes.this,Notes.class);
                                button.putExtra("user",username);
                                button.putExtra("title",title);
                                button.putExtra("src",src);
                                startActivity(button);
                            }
                            else
                            {
                                Intent button = new Intent(Notes.this,Notebook.class);
                                button.putExtra("user",username);
                                button.putExtra("title",title);
                                button.putExtra("src",src);
                                startActivity(button);
                            }
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
        else
        {


        }




    }


    public void createNote(View view){

        Intent namingNote = new Intent(Notes.this,SetNoteName.class);
        namingNote.putExtra("user",this.username);
        startActivity(namingNote);

    }

    public void logOut(View view){
        startActivity(new Intent(Notes.this,Login_Page.class));
    }
}
