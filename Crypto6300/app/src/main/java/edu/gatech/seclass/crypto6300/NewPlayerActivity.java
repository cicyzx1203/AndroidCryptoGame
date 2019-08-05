package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class NewPlayerActivity extends AppCompatActivity {
    //get application state from singleton object
    Application application = DataStore.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_player);
    }

    public void onCreateClick(View view){
        boolean isValid = true;

        EditText txtUsername = (EditText)findViewById(R.id.txtUsername);
        String username = txtUsername.getText().toString().trim();
        EditText txtEmail = (EditText)findViewById(R.id.txtEmail);
        String email = txtEmail.getText().toString().trim();

        if(!application.isUsernameUnique(username)){
            txtUsername.setError("Your chosen username is already taken");
            isValid = false;
        }
        if(username.isEmpty()){
            txtUsername.setError("Your cannot have an empty  username");
            isValid = false;
        }
        if(!isValidEmail(email)){
            txtEmail.setError("Email must be a valid email Address");
            isValid = false;
        }


        // if after all the checks no error create new player
        if(isValid){
            // Create new player object
            Player player = application.createPlayer(username,email);
            // Add player to list
            application.savePlayer(player);

            // Persist data
            DataStore.save();
            messageBox("Success", "Hello " + username+ "! \nYour player has been created successfully!");
        }
    }

    private boolean isValidEmail(String email){
        final Pattern EMAIL_REGEX = Pattern.compile("^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$", Pattern.CASE_INSENSITIVE);
        return EMAIL_REGEX.matcher(email).matches();
    }

    // Generate dialog box
    public AlertDialog messageBox(String title, String message){
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message).setNeutralButton("Okay",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Navigate to login Activity
                        finish();
                    }
                }).show();
    }
}
