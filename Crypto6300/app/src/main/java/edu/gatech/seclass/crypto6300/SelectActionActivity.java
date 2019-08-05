package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SelectActionActivity extends AppCompatActivity {

    String title;
    private Application application = DataStore.getApplication();
    Cryptogram cryptogram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_action);
        title = getIntent().getStringExtra("title");

        TextView txtTitle = (TextView)findViewById(R.id.txtSelectActionCryptogramTitle);
        txtTitle.setText("Title: "+ title);

        cryptogram = application.getCryptogram(title);
    }

    public void onViewInfoClick(View view){
        Intent myIntent = new Intent(this, CryptogramInfoActivity.class);
        myIntent.putExtra("title", title);
        this.startActivity(myIntent);
    }

    public void OnDisableClick(View view){
        Intent myIntent = new Intent(this, DisableCryptogramActivity.class);
        myIntent.putExtra("title", title);
        this.startActivity(myIntent);
    }


    @Override
    public void onResume()
    {  // After a pause OR at startup
        super.onResume();
        Button btnDisable = (Button) findViewById(R.id.btn_disable);
        if(cryptogram.isDisabled()){
            btnDisable.setEnabled(false);
        }
    }
}
