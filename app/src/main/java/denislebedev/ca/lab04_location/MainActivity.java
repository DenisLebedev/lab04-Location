package denislebedev.ca.lab04_location;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    private ImageButton imgBut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgBut = (ImageButton) findViewById(R.id.imgBut);

    }

    private void playMedia(Uri file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(file);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void launchPreciseSearchActivity(View view) {
        Intent intent = new Intent(this, MapActivity.class);

        intent.putExtra(getResources().getString(R.string.country),
                getResources().getString(R.string.country));

        startActivity(intent);
    }


    public void googleMapAPI(View view) {
        Uri country = Uri.parse("geo:0.0,0.0?q=" +
                Uri.encode(getResources().getString(R.string.country)));
        playMedia(country);
    }

}
