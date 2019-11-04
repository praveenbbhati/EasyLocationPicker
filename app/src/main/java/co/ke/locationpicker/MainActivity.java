package co.ke.locationpicker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loginext.easylocationpicker.EasyLocation;
import com.loginext.easylocationpicker.EasyLocationPickerActivity;
import com.loginext.easylocationpicker.SelectedLocation;


public class MainActivity extends AppCompatActivity {

    private EasyLocation easyLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button pickLocationBtn = findViewById(R.id.btnPickLocation);
        pickLocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*//todo create builder class to pass data to the activity
                Intent intent = new Intent(MainActivity.this, EasyLocationPickerActivity.class);
                startActivityForResult(intent, EasyLocationPickerActivity.LOCATION_REQUEST_CODE);*/

                easyLocation = new EasyLocation.Builder(MainActivity.this,getString(R.string.PLACES_API_KEY))
                        //.placesApiKey(getString(R.string.PLACES_API_KEY))
                        .showCurrentLocation(true)
                        .useGeoCoder(true)
                        .setResultOnBackPressed(false)
                        .setCallbacks(new EasyLocation.Callbacks() {
                            @Override
                            public void onSuccess(SelectedLocation selectedLocation) {
                                Toast.makeText(MainActivity.this, selectedLocation.toString(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailed(String kk) {
                                Toast.makeText(MainActivity.this, kk, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();


            }
        });







    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //todo create class to get the results
       /* //get location
        if (requestCode == EasyLocationPickerActivity.LOCATION_REQUEST_CODE){

            if (resultCode == RESULT_OK){
                //location results received
                String latitude = data.getStringExtra(EasyLocationPickerActivity.EXTRA_LATITUDE);
                String longitude = data.getStringExtra(EasyLocationPickerActivity.EXTRA_LONGITUDE);

                Toast.makeText(this, "LOC = "+latitude+ " --- "+longitude, Toast.LENGTH_SHORT).show();
            }else {
                //location not received
                Toast.makeText(this, "Location not received", Toast.LENGTH_SHORT).show();
            }
        }*/

        easyLocation.onActivityResult(requestCode, resultCode, data);
    }

}