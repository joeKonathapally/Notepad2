package com.example.notepad;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Notebook extends AppCompatActivity {

    private Button goHome;
    private String username;
    private String title;
    private String src;
    private SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notebook);

        username = getIntent().getStringExtra("user");
        title  = getIntent().getStringExtra("title");
        src = getIntent().getStringExtra("src");

        mydatabase = openOrCreateDatabase("userData",MODE_PRIVATE,null);
        String query="Select Id from Notes";
        Cursor resultSet1 = mydatabase.rawQuery(query,null);
        if(src.equals("nope"))
        {
            //Toast.makeText(getApplicationContext(),"Testy",Toast.LENGTH_LONG).show();
            try{
                resultSet1.moveToLast();
                int temp=resultSet1.getInt(0)+1;
                src = "Note"+temp+".txt";
            }
            catch(Exception e){

            }

            query="Insert Into Notes (Username,Filename,FileSrc) Values('"+this.username+"','"+this.title+"','"+src+"');";
            mydatabase.execSQL(query);
        }
        else
        {

            TextInputEditText input = (TextInputEditText) findViewById(R.id.textInput);
            input.setText(Open(src).toString());
        }
        TextView titles = (TextView)findViewById(R.id.textView3);
        titles.setText(this.title);

    }



    public void saveNote(View veiw){

        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(src, 0));
            TextInputEditText input = (TextInputEditText)findViewById(R.id.textInput);
            out.write(input.getText().toString().trim());
            out.close();
            Toast.makeText(this, "Note Saved!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
        Intent temp = new Intent(Notebook.this,Notes.class);
        temp.putExtra("user",username);
        startActivity(temp);

    }

    public boolean FileExists(String fname){
        File file = getBaseContext().getFileStreamPath(fname);
        return file.exists();
    }



    public String Open(String fileName) {
        String content = "";
        if (FileExists(fileName)) {
            try {
                InputStream in = openFileInput(fileName);
                if ( in != null) {
                    InputStreamReader tmp = new InputStreamReader( in );
                    BufferedReader reader = new BufferedReader(tmp);
                    String str;
                    StringBuilder buf = new StringBuilder();
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + "\n");
                    } in .close();
                    content = buf.toString();
                }
            } catch (java.io.FileNotFoundException e) {} catch (Throwable t) {
                Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
            }
        }
        return content;
    }
}
