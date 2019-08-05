package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class SolveGameMainActivity extends AppCompatActivity {

    Application application = DataStore.getApplication();
    TextView txtTitle;
    TextView txtRemaining;
    TextView txtEncodedPhrase;
    TextView txtHint;
    TextView txtLetters;
    String letters;
    String encodedPhase;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solve_game_main);

        Player player = application.getPlayer(DataStore.getLoggedInPlayerUsername());
        Cryptogram cryptogram = player.getCurrentGame().getCryptogram();
        game = player.getCurrentGame();

        txtTitle = (TextView) findViewById(R.id.txtGameTitle);
        txtTitle.setText("Title: " + cryptogram.getTitle());

        txtRemaining = (TextView) findViewById(R.id.txtGameRemaining);
        txtRemaining.setText("Remaining Attempts: " + game.getAttemptsLeft());

        txtEncodedPhrase = (TextView) findViewById(R.id.txtGameEncodedPhase);
        txtEncodedPhrase.setText("Encoded Phase: " + cryptogram.getEncodedPhrase());
        encodedPhase= cryptogram.getEncodedPhrase();

        txtHint = (TextView) findViewById(R.id.txtGameHint);
        txtHint.setText("Hint: " + cryptogram.getHint());
        txtHint.setVisibility(View.INVISIBLE);

        txtLetters = (TextView) findViewById(R.id.txtGameLetters);
        letters = getUniqueCharacters(cryptogram.getEncodedPhrase());
        txtLetters.setText("Letters: " + letters);

    }

    public void viewClick(View view){

        EditText txtSolution = (EditText) findViewById(R.id.txtGameSolution);
        String solution = txtSolution.getText().toString();

        if(solution.isEmpty()){
            txtSolution.setError("Solution is empty");
            return;
        }

        if(letters.length() != solution.length()){
            txtSolution.setError("Replacement letters and solution must be of the same length");
            return;
        }
        String potentialSolution = generateEncodingPhrase(solution,letters,encodedPhase);
        messageBox("Potential Solution", potentialSolution);
    }

    public void onSubmitClick(View view){
        EditText txtSolution = (EditText) findViewById(R.id.txtGameSolution);
        String solution = txtSolution.getText().toString();
        Player player = application.getPlayer(DataStore.getLoggedInPlayerUsername());
        Cryptogram cryptogram = player.getCurrentGame().getCryptogram();
        if(solution.isEmpty()){
            txtSolution.setError("Solution is empty");
            return;
        }

        if(letters.length() != solution.length()){
            txtSolution.setError("Replacement letters and solution must be of the same length");
            return;
        }

        String potentialSolution = generateEncodingPhrase(solution,letters,encodedPhase);
        if(potentialSolution.equals(cryptogram.getPlainText())){

            player.setCurrentGame(null);
            player.setCryptogramsAttempted(player.getAttempts()+1);
            int completed = cryptogram.getNumberOfCompletedGame();
            cryptogram.setNumberOfCompletedGame(completed +1);

            int wins = cryptogram.getNumberOfWins();
            cryptogram.setNumberOfWins(wins +1);

            if(game.isHintUsed()){
                messageBox2("You won", "Your solution was successful. \n You got no points");
            }
            else {
                player.addPoints(game.getBetAmount());
                messageBox2("You won", "Your solution was successful. \n You won " + game.getBetAmount() + " points!");
            }
        }
        else {
            txtRemaining.setText("Remaining Attempts: "+ game.reduceAttempts());
            if (game.getAttemptsLeft() > 0){
                messageBox("Ooops!!", "Your answer was incorrect");
            }
            else {
                player.setCurrentGame(null);
                player.setCryptogramsAttempted(player.getAttempts()+1);
                player.addPoints(-1*game.getBetAmount());
                int completed = cryptogram.getNumberOfCompletedGame();
                cryptogram.setNumberOfCompletedGame(completed +1);
                messageBox2("You lost!!", "The cryptogram was lost");
            }
        }
        if (game.getAttemptsLeft() ==2){
            txtHint.setVisibility(View.VISIBLE);
            game.setHintUsed(true);
        }
        DataStore.save();
    }

    // Generate dialog box
    public AlertDialog messageBox2(String title, String message){
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message).setPositiveButton("Okay",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataStore.save();
                        finish();
                    }
                }).show();
    }

    public String generateEncodingPhrase(String encoding, String replacement, String solution){
        replacement = replacement.toLowerCase();
        encoding = encoding.toLowerCase();
        String result = solution;
        for(int i = 0; i < encoding.length();i++){
            String currentEncoding = encoding.substring(i,i+1);
            String currentReplacement = replacement.substring(i,i+1);
            result = result.replace(currentReplacement,currentEncoding);
            result = result.replace(currentReplacement.toUpperCase(),currentEncoding.toUpperCase());
        }

        return result;
    }


    public String getUniqueCharacters(String solution){
        String temp = "";
        // extract unique letters
        solution = solution.toLowerCase();
        for (int i = 0; i < solution.length(); i++){
            char current = solution.charAt(i);
            if (Character.isLetter(current) && temp.indexOf(current) < 0){
                temp = temp + current;
            }
        }
        char uniqueLetters[] = temp.toCharArray();
        Arrays.sort(uniqueLetters);

        return new String(uniqueLetters);
    }

    // Generate dialog box
    public AlertDialog messageBox(String title, String message){
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message).setNegativeButton("Ok",null).show();
    }
}
