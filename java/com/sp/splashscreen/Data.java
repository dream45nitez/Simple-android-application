package com.sp.splashscreen;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Data extends AppCompatActivity {
    private EditText locationName;
    private EditText locationAddress;
    private EditText locationDate;
    private EditText locationDescription;
    private Button buttonSave;

    private TouristHelper helper = null;
    private String locationID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data);

        locationName = findViewById(R.id.location_name);
        locationDate = findViewById(R.id.location_date);
        locationDescription = findViewById(R.id.location_description);
        locationAddress = findViewById(R.id.location_address);

        buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(onSave);
        helper = new TouristHelper(this);

        locationID = getIntent().getStringExtra("ID");
        if(locationID != null) {
            load();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    private void load() {
        Cursor c = helper.getById(locationID);
        c.moveToFirst();
        locationName.setText(helper.getlocationName(c));
        locationDate.setText(helper.getlocationDate(c));
        locationDescription.setText(helper.getlocationDescription(c));
        locationAddress.setText(helper.getlocationAddress(c));
    }

    View.OnClickListener onSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //To read data from EditText
            String nameStr = locationName.getText().toString();
            String dateStr = locationDate.getText().toString();
            String descStr = locationDescription.getText().toString();
            String addrStr = locationAddress.getText().toString();


            if (locationID == null) {
                helper.insert(nameStr, dateStr, descStr, addrStr);
            } else {
                helper.update(locationID, nameStr, dateStr, descStr, addrStr);
            }

            //To close current Activity class and exit
            finish();
        }
    };

}
