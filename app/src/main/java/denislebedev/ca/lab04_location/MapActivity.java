package denislebedev.ca.lab04_location;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MapActivity extends AppCompatActivity {
    private EditText getLocation;
    private TextView errorMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getLocation = (EditText) findViewById(R.id.geoLocation);
        errorMsg = (TextView) findViewById(R.id.errorDisplay);

        String userCountry = getIntent().getExtras()
                .getString(getResources().getString(R.string.country));

        getLocation.setText(userCountry);

    }

    public void parseUserResult(View view) {
        String userInput = getLocation.getText().toString();

        if(userInput.isEmpty() || userInput == null) {
            getLocation.setText("");
            errorMsg.setText(getResources().getString(R.string.invalidLoc));
        } else {
            Uri country = Uri.parse("geo:0.0,0.0?q=" +
                    Uri.encode(userInput));
            playMedia(country);
        }
    }


    private void playMedia(Uri file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(file);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


}
