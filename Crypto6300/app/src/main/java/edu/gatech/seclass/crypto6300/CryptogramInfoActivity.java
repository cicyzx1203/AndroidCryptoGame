package edu.gatech.seclass.crypto6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CryptogramInfoActivity extends AppCompatActivity {

    Application application = DataStore.getApplication();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cryptogram_info);

        String title = getIntent().getStringExtra("title");

        TextView txtInfoTitle = (TextView)findViewById(R.id.txtInfoTitle);
        TextView txtInfoEncodedPhase = (TextView)findViewById(R.id.txtInfoEncodedPhase);
        TextView txtInfoSolution = (TextView)findViewById(R.id.txtInfoSolution);
        TextView txtInfoHint = (TextView)findViewById(R.id.txtInfoHint);

        Cryptogram cryptogram = application.getCryptogram(title);

        txtInfoTitle.setText("Title: " + cryptogram.getTitle());
        txtInfoEncodedPhase.setText("Encrypted Phrase: " + cryptogram.getEncodedPhrase());
        txtInfoSolution.setText("Solution: " + cryptogram.getPlainText());
        txtInfoHint.setText("Hint: " + cryptogram.getHint());
    }
}
