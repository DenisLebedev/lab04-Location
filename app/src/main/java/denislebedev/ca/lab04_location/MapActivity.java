package denislebedev.ca.lab04_location;

import android.app.SearchManager;
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

        if(!validateData(userInput)) {
            getLocation.setText("");
            errorMsg.setText(getResources().getString(R.string.invalidLoc));
        } else {
            Uri country = Uri.parse("geo:0.0,0.0?q=" +
                    Uri.encode(userInput));
            playMedia(country);
        }
    }


    public void googleSearchLocation(View view) {
        String userInput = getLocation.getText().toString();

        if(!validateData(userInput)) {
            getLocation.setText("");
            errorMsg.setText(getResources().getString(R.string.invalidLoc));
        } else {
            searchWeb(userInput);
        }
    }


    /**
     * Validate user input
     *
     * @param result
     * @return true if valid else false for invalid
     */
    private boolean validateData(String result) {
        if(result.isEmpty() || result == null)
            return false;
        return true;
    }

    private void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
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
