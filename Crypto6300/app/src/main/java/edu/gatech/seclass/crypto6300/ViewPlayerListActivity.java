package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewPlayerListActivity extends AppCompatActivity {

    ListView listView;
    Application application = DataStore.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_player_list);

        listView = (ListView) findViewById(R.id.playerList);

        ArrayList<Player> players = new ArrayList<>(application.getPlayerList());
        Collections.sort(players, new Desc());

        ArrayAdapter arrayAdapter = new PlayerAdapter(this, R.layout.player_list_view,players);
        listView.setAdapter(arrayAdapter);
    }

    class Desc implements Comparator<Player>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Player a, Player b)
        {
            return b.getPoints() - a.getPoints();
        }
    }
}
