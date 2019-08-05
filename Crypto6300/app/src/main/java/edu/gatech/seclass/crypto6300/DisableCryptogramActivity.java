package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DisableCryptogramActivity extends AppCompatActivity {

    private Application application = DataStore.getApplication();
    private Player creator;
    private Cryptogram cryptogram;
    Administrator administrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disable_cryptogram);
        String title = getIntent().getStringExtra("title");

        administrator = new Administrator(application);

        cryptogram = application.getCryptogram(title);
        creator = cryptogram.getCreator();

        TextView txtTitle = (TextView)findViewById(R.id.txtDisableTitle);
        txtTitle.setText("Title: "+ title);
        TextView txtDisableCreator = (TextView)findViewById(R.id.txtDisableCreator);
        txtDisableCreator.setText("Creator: "+ creator.getUsername());
        TextView txtDisableCreatorPoints = (TextView)findViewById(R.id.txtDisableCreatorPoints);
        txtDisableCreatorPoints.setText("Creator's Points: "+ creator.getPoints());

    }


    public void onDisableClick(View view){

        boolean isValid = true;

        EditText txtPenalize = (EditText)findViewById(R.id.txtPenalize);
        String penalize = txtPenalize.getText().toString().trim();

        int points = penalize.isEmpty() ? 0 : Integer.valueOf(penalize);

        if(points > creator.getPoints()){
            isValid = false;
            txtPenalize.setError("The creator only has "+ creator.getPoints() +" point(s) that can be penalized");
        }

        if(points > 10 || points < 0){
            isValid = false;
            txtPenalize.setError("Invalid bet range. Points must be between [1~10] and cannot be larger than the creator's current number of points");
        }

        if(isValid){
            administrator.disableCryptogram(cryptogram.getTitle(), points);
            DataStore.save();
            finish();
            }
        }

}



