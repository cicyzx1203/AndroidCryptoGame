package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import java.util.Arrays;

public class CreateCryptogramActivity extends AppCompatActivity {
    Application application = DataStore.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_cryptogram);

        EditText txtCreateCryptogramSolution = (EditText)findViewById(R.id.txtCreateCryptogramSolution);
        txtCreateCryptogramSolution.addTextChangedListener(new TextWatcher() {
            EditText txtToReplace = (EditText)findViewById(R.id.txtToReplace);

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String uniqueCharacter = getUniqueCharacters(s.toString());
                txtToReplace.setText(uniqueCharacter);
            }
        });
    }



    public void saveClick(View view){
        boolean isValid = true;

        EditText txtCreateCryptogramTitle = (EditText)findViewById(R.id.txtCreateCryptogramTitle);
        String title = txtCreateCryptogramTitle.getText().toString().trim();

        EditText txtCreateCryptogramSolution = (EditText)findViewById(R.id.txtCreateCryptogramSolution);
        String solution = txtCreateCryptogramSolution.getText().toString().trim();

        EditText txtCreateCryptogramHint = (EditText)findViewById(R.id.txtCreateCryptogramHint);
        String hint = txtCreateCryptogramHint.getText().toString().trim();

        EditText txtCreateCryptogramEncoding = (EditText)findViewById(R.id.txtCreateCryptogramEncoding);
        String encoding = txtCreateCryptogramEncoding.getText().toString().trim();

        EditText txtToReplace = (EditText)findViewById(R.id.txtToReplace);
        String toReplace = txtToReplace.getText().toString().trim();


        if(title.isEmpty()){
            txtCreateCryptogramTitle.setError("Title must not be empty");
            isValid = false;
        }


        if(!application.isTitleUnique(title)){
            txtCreateCryptogramTitle.setError("Title is already in use by another cryptogram, try another");
            isValid = false;
        }

        if(solution.isEmpty()){
            txtCreateCryptogramSolution.setError("Solution must not be empty");
            isValid = false;
        }

        if(encoding.isEmpty()){
            txtCreateCryptogramEncoding.setError("Encoding must not be empty");
            isValid = false;
        }

        if(toReplace.length() != encoding.length()){
            txtCreateCryptogramEncoding.setError("Encoding length must match replacement letters length");
            isValid = false;
        }

        if(isEncodedToSelf(toReplace,encoding)){
            txtCreateCryptogramEncoding.setError("A letter is encoded to itself");
            isValid = false;
        }

        if(getUniqueCharacters(encoding).length() != encoding.length()){
            txtCreateCryptogramEncoding.setError("Each encoding character must be unique");
            isValid = false;
        }

        if(hint.isEmpty()){
            txtCreateCryptogramHint.setError("Hint must not be empty");
            isValid = false;
        }

        if(isValid){

            Cryptogram cryptogram = new Cryptogram(application);
            String loggedInUser = DataStore.getLoggedInPlayerUsername();
            Player creator = application.getPlayer(loggedInUser);
            cryptogram.create(creator,title,solution,encoding);
            cryptogram.setHint(hint);
            cryptogram.setEncodedPhrase(generateEncodingPhrase(encoding, toReplace,solution));
            application.saveCryptogram(cryptogram);
            DataStore.save();
            messageBox2("Cryptogram Created", title + " is Unique. You have been awarded 5 points!");
        }


    }

    // Generate dialog box
    public AlertDialog messageBox2(String title, String message){
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

    public void viewClick(View view){
        boolean isValid = true;
        EditText txtCreateCryptogramSolution = (EditText)findViewById(R.id.txtCreateCryptogramSolution);
        String solution = txtCreateCryptogramSolution.getText().toString().trim();

        EditText txtCreateCryptogramEncoding = (EditText)findViewById(R.id.txtCreateCryptogramEncoding);
        String encoding = txtCreateCryptogramEncoding.getText().toString().trim();

        EditText txtToReplace = (EditText)findViewById(R.id.txtToReplace);
        String toReplace = txtToReplace.getText().toString().trim();

        if(solution.isEmpty()){
            txtCreateCryptogramSolution.setError("Solution must not be empty");
            isValid = false;
        }

        if(encoding.isEmpty()){
            txtCreateCryptogramEncoding.setError("Encoding must not be empty");
            isValid = false;
        }

        if(toReplace.length() != encoding.length()){
            txtCreateCryptogramEncoding.setError("Encoding length must match replacement letters length");
            isValid = false;
        }

        if(isEncodedToSelf(toReplace,encoding)){
            txtCreateCryptogramEncoding.setError("A letter is encoded to itself");
            isValid = false;
        }

        if(getUniqueCharacters(encoding).length() != encoding.length()){
            txtCreateCryptogramEncoding.setError("Each encoding character must be unique");
            isValid = false;
        }


        if(isValid){
            String encodedPhrase = generateEncodingPhrase(encoding,toReplace,solution);

            messageBox("Encoded Phrase", encodedPhrase);
        }

    }

    // Generate dialog box
    public AlertDialog messageBox(String title, String message){
        final Intent myIntent = new Intent(this, ExistingPlayerActivity.class);
        return new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message).setNeutralButton("Okay",null).show();
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

    // checks if plainText letters are encoded to self
    public boolean isEncodedToSelf(String toReplace, String encoding) {
        for(int i=0; i < encoding.length(); i++){
            if(toReplace.length() == encoding.length() && encoding.substring(i,i+1).equalsIgnoreCase(toReplace.substring(i,i+1))){
                return true;
            }
        }
        return false;
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


}


