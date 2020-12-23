package com.sp.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class About extends AppCompatActivity {

    private TextView empty =null;
    private ImageView imageView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);

        empty = findViewById(R.id.empty);
        imageView = findViewById(R.id.imageView2);
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
