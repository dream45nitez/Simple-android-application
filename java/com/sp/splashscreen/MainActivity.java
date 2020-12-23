package com.sp.splashscreen;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
/** Called when the activity is first created.*/

    private TextView empty = null;  //Declare Textview empty
    private Button btn1;
    private Button btn2;
    private Button btn3;

    private EditText locationName;
    private EditText locationAddress;
    private Button buttonSave;
    private  EditText locationDate;;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Creating a screen
        super.onCreate(savedInstanceState);             //Creating activities
        setContentView(R.layout.activity_main);         //Directing user to R(resources) then to layout and lastly to main

        empty = findViewById(R.id.empty);               //Find using id on activity_main.xml

        btn1 = findViewById(R.id.btnexit);  //Find using id on btnexit on activity_main.xml
        btn1.setOnClickListener(onExit);    //Instructing btn1(btnexit) to detect click

        btn2 = findViewById(R.id.data);
        btn2.setOnClickListener(onData);

        btn3 = findViewById(R.id.info);
        btn3.setOnClickListener(onInfo);
    }

    private View.OnClickListener onData = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.data);
        }
    };

    private View.OnClickListener onInfo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.information);
        }
    };

    private View.OnClickListener onExit = new View.OnClickListener() {     //Creating onExit as new input to detect click
        @Override
        public void onClick(View v) { //when clicked it will run what is inside this {}
            finish(); //Exit the entire app
            //System.exit(0);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {  //Create Menu
        getMenuInflater().inflate(R.menu.option, menu); //To get menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //When selected menu, it will run the following
        switch (item.getItemId()) {                       //Create item id
            case (R.id.about):                            //Show about in menu
                Intent intent;
                intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}