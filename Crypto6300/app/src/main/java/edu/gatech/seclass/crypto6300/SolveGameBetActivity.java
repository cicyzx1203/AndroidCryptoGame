package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.seclass.crypto6300.Application;
import edu.gatech.seclass.crypto6300.Cryptogram;
import edu.gatech.seclass.crypto6300.DataStore;
import edu.gatech.seclass.crypto6300.ExistingPlayerActivity;
import edu.gatech.seclass.crypto6300.Game;
import edu.gatech.seclass.crypto6300.Player;
import edu.gatech.seclass.crypto6300.R;

public class SolveGameBetActivity extends AppCompatActivity {

    private Application application = DataStore.getApplication();
    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        player = application.getPlayer(DataStore.getLoggedInPlayerUsername());
        setContentView(R.layout.activity_solve_game_bet);
        TextView txtBetPoints = (TextView)findViewById(R.id.txtBetPoints);
        txtBetPoints.setText("Your Points: "+ player.getPoints());
    }

    public void onPlayClick(View view){

        boolean isValid = true;

        EditText txtbet = (EditText)findViewById(R.id.txtbet);
        String betStr = txtbet.getText().toString().trim();

        int bet = betStr.isEmpty() ? 0 : Integer.valueOf(betStr);

        if(bet > player.getPoints()){
            isValid = false;
            txtbet.setError("You have "+ player.getPoints() +" point(s) left to bet");
        }

        if(bet > 10 || bet < 1){
            isValid = false;
            txtbet.setError("Invalid bet range. Bets must be between [1~10] and can not be larger than you current number of points");
        }

        if(isValid){
            Cryptogram cryptogram = application.rand(player);
            if(cryptogram == null) {
                messageBox("No Cryptogram", "There are no cryptograms available for you");
            }
            else{
                Game g = application.createGame(player, bet, cryptogram);
                cryptogram.attempt(player);
                player.setCurrentGame(g);

                DataStore.save();
                finish();
                Intent myIntent = new Intent(this, SolveGameMainActivity.class);
                this.startActivity(myIntent);
            }
        }
    }

    // Generate dialog box
    public AlertDialog messageBox(String title, String message){
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message).setNeutralButton("Okay",null).show();
    }
}
