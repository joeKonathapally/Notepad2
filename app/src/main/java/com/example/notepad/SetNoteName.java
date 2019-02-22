package com.example.notepad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class SetNoteName extends AppCompatActivity {


    private String name;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_note_name);
        username=getIntent().getStringExtra("user");

    }


    public void next(View view){
        Intent finalNote = new Intent(SetNoteName.this,Notebook.class);
        finalNote.putExtra("user",this.username);
        TextView input = (TextView)findViewById(R.id.editText);
        name = input.getText().toString().trim();
        finalNote.putExtra("name",this.name);
        startActivity(finalNote);
    }

    public void back(View view){
        Intent goingBack = new Intent(SetNoteName.this,Notes.class);
        goingBack.putExtra("user",this.username);
        startActivity(goingBack);
    }
}
