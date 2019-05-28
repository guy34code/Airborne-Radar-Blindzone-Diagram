package com.example.project1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Databasehelper mydb;
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7, ed8, ed9, ed10, ed11, ed12;
    Button btnadd;
    Button btnview;
    Button btnnxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new Databasehelper(this);
        ed1 = (EditText) findViewById(R.id.editText1);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        ed5 = (EditText) findViewById(R.id.editText5);
        ed6 = (EditText) findViewById(R.id.editText6);
        ed7 = (EditText) findViewById(R.id.editText7);
        ed8 = (EditText) findViewById(R.id.editText8);
        ed9 = (EditText) findViewById(R.id.editText9);
        ed10 = (EditText) findViewById(R.id.editText10);
        ed11 = (EditText) findViewById(R.id.editText11);
        ed12 = (EditText) findViewById(R.id.editText12);
        btnadd = (Button) findViewById(R.id.button1);
        btnview = (Button) findViewById(R.id.button2);
        btnnxt = (Button) findViewById(R.id.button3);
        btnnxt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                openActivity2();
            }
        });
        AddData();
        ViewData();

    }

    public void openActivity2() {
        Intent i = new Intent(this,DynamicVariables.class);
                startActivity(i);
    }

    public void AddData() {
        btnadd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = mydb.insertData(ed1.getText().toString(), ed2.getText().toString(), ed3.getText().toString(), ed4.getText().toString(), ed10.getText().toString(), ed11.getText().toString(), ed6.getText().toString(), ed7.getText().toString(), ed8.getText().toString(), ed9.getText().toString(), ed12.getText().toString());
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void ViewData() {
        btnview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if (res.getCount() == 0) {
                            //show message
                            showMessage("ERROR", "NO DATA FOUND");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        if (res.moveToFirst()) {
                            do {
                                buffer.append("ID :" + res.getString(0) + "\n");
                                buffer.append("Range Gate :" + res.getString(1) + "\n");
                                buffer.append("Doppler Filter :" + res.getString(2) + "\n");
                                buffer.append("Frequency :" + res.getString(3) + "\n");
                                buffer.append("Clear PRF :" + res.getString(4) + "\n");
                                buffer.append("Azimuth Antenna BeamWidth :" + res.getString(5) + "\n");
                                buffer.append("Elevation Antenna BeamWidth :" + res.getString(6) + "\n");
                                buffer.append("Minimum Range :" + res.getString(7) + "\n");
                                buffer.append("Maximum Range :" + res.getString(8) + "\n");
                                buffer.append("Target Minimum Velocity :" + res.getString(9) + "\n");
                                buffer.append("Target Maximum Velocity :" + res.getString(10) + "\n");
                                buffer.append("Pulse Width :" + res.getString(11) + "\n\n");


                            } while (res.moveToNext());
                            showMessage("DATA", buffer.toString());
                        }
                    }
                }
        );
    }

    public void showMessage(String Title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }

}
