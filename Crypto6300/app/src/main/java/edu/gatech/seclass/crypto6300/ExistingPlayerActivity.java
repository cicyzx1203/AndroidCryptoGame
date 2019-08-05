package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ExistingPlayerActivity extends AppCompatActivity {
    //get application state from singleton object
    Application application = DataStore.getApplication();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_existing_player);


    }

    public void onLoginClick(View view){
        boolean isValid = true;

        EditText txtUsername = (EditText)findViewById(R.id.txtLoginUsername);
        String username = txtUsername.getText().toString().trim();
        EditText txtEmail = (EditText)findViewById(R.id.txtLoginEmail);
        String email = txtEmail.getText().toString().trim();


        if(username.isEmpty()){
            txtUsername.setError("Your cannot have an empty  username");
            isValid = false;
        }
        else if(!isValidEmail(email)){
            txtEmail.setError("Email must be a valid email Address");
            isValid = false;
        }
        else {
            if(!application.login(username, email)){
                txtUsername.setError("No player with this username or email combination exists");
                txtEmail.setError("No player with this username or email combination exists");
                isValid = false;
            }
        }




        // if after all the checks no error create new player
        if(isValid){
            // sets the user of the currently logged in player.
            // Does not persist
            DataStore.setLoggedInPlayerUsername(username);

            // Create new player object
            messageBox("Logged In", "Welcome back " + username+ "!");
        }
    }

    private boolean isValidEmail(String email){
        final Pattern EMAIL_REGEX = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    // Generate dialog box
    public AlertDialog messageBox(String title, String message){
        final Intent myIntent = new Intent(this, MainMenuActivity.class);
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message).setNeutralButton("Okay",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to login Activity
                        startActivity(myIntent);
                        finish();
                    }
                }).show();
    }
}
