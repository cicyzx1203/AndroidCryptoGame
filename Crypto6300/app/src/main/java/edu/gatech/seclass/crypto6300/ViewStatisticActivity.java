package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class ViewStatisticActivity extends AppCompatActivity {

    ListView listView;
    Application application = DataStore.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_statistic);

        listView = (ListView) findViewById(R.id.statisticView);

        Administrator admin = new Administrator(application);
        List<Cryptogram> cryptograms = admin.getStatistics();

        final ArrayAdapter arrayAdapter = new CryptogramAdapter(this, R.layout.statistic_list_view,cryptograms);
        listView.setAdapter(arrayAdapter);

        final Intent intent = new Intent(this, SelectActionActivity.class);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String message = ((Cryptogram)arrayAdapter.getItem(position)).getTitle();
                Log.d("intent", message);
                intent.putExtra("title", message);
                startActivity(intent);
            }
        });

    }





}
