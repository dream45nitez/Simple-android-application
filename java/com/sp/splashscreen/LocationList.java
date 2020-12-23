package com.sp.splashscreen;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.CursorAdapter;

public class LocationList extends AppCompatActivity {
    private Cursor model = null;
    private LocationAdapter adapter = null;                       //adapter
    private ListView list;                                          //List
    private TouristHelper helper = null;                         //SQLite
    private TextView empty = null;                                  //TextView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);

        empty = findViewById(R.id.empty);
        helper = new TouristHelper(this);
        //listView List
        list = findViewById(R.id.list);                      //Finding using Id on 'restaurants' list
        model = helper.getAll();
        adapter = new LocationAdapter(this, model, 0);
        list.setOnItemClickListener(onListClick);
        list.setAdapter(adapter);                                   //Setting adapter in list
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (model != null) {
            model.close();
        }
        model = helper.getAll();
        if (model.getCount() > 0) {
            empty.setVisibility(View.INVISIBLE);
        }
        adapter.swapCursor(model);
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            model.moveToPosition(position);
            String recordID = helper.getID(model);
            Intent intent;
            intent = new Intent(LocationList.this, Data.class);
            intent.putExtra("ID", recordID);
            startActivity(intent);
        }
    };

    static class LocationHolder {
        private TextView locaName = null;
        private TextView locaaddr = null;


        LocationHolder(View row) {
            locaName = row.findViewById(R.id.locaName);
            locaaddr = row.findViewById(R.id.locaAddr);
        }
        void populateFrom(Cursor c, TouristHelper helper) {
            locaName.setText(helper.getlocationName(c));
            locaaddr.setText(helper.getlocationAddress(c));

        }
    }

    class LocationAdapter extends CursorAdapter {
        LocationAdapter(Context context, Cursor cursor, int flags) {
            super(context, cursor, flags);
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            LocationHolder holder = (LocationHolder) view.getTag();
            holder.populateFrom(cursor, helper);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            LocationHolder holder = new LocationHolder(row);
            row.setTag(holder);
            return (row);
        }
    }
}
