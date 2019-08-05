package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    Application application = DataStore.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    @Override
    public void onBackPressed() {
        messageBox("Logout", "Do you want to log out?");
    }

    public void onLogOutClick(View view){
        messageBox("Logout", "Do you want to log out?");
    }

    public void onCreateCryptogramClick(View view){
        final Intent myIntent = new Intent(this, CreateCryptogramActivity.class);
        startActivity(myIntent);
    }

    public void onRandomCryptogramClick(View view) {
        Player currentPlayer = application.getPlayer(DataStore.getLoggedInPlayerUsername());
        final Intent myIntent;
        if (currentPlayer.getCurrentGame() == null){
            myIntent = new Intent(this, SolveGameBetActivity.class);
        }
        else{
            myIntent = new Intent(this, SolveGameMainActivity.class);
        }
        startActivity(myIntent);

    }

    public void onViewPlayerListClick(View view){
        final Intent myIntent = new Intent(this, ViewPlayerListActivity.class);
        startActivity(myIntent);
    }


    // Generate dialog box
    public AlertDialog messageBox(String title, String message){
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message).setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to login Activity
                        DataStore.setLoggedInPlayerUsername("");
                        finish();
                    }
                }).setNegativeButton("No",null).show();
    }
}
