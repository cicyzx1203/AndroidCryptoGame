package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {


    public void init(){

        // sets context to access storage file in android filesystem
        // Should only called here!! No need to set context twice
        DataStore.setContext(this.getApplicationContext());
        //DataStore.save();
        // Loads data from file into memory
        // should only be called here in the whole application as there is no need to load the data more than once
        DataStore.load();

        // Statistics
        Application application = DataStore.getApplication();
        Log.d("Player count",String.valueOf(application.getPlayerList().size()));
        Log.d("Cryptogram Count", String.valueOf(application.getAllCryptogram().size()));

        // To save data to DataStore used DataStore.save().
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.activity_login);

    }

    public void onNewPlayerClick(View view){
        Intent myIntent = new Intent(this, NewPlayerActivity.class);
        this.startActivity(myIntent);
    }

    public void onExistingPlayerClick(View view){
        Intent myIntent = new Intent(this, ExistingPlayerActivity.class);
        this.startActivity(myIntent);
    }

    public void onAdministratorClick(View view){
        Intent myIntent = new Intent(this, ViewStatisticActivity.class);
        this.startActivity(myIntent);
    }

}
