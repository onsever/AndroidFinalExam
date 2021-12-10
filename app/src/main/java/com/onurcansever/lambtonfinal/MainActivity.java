package com.onurcansever.lambtonfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/*
ONURCAN SEVER - C0830345

Note about highlighted selection on first appear (Main Activity):
-----------------------------------------------------------------
It is not selected at the first start, but sometimes when
we start the emulator, it highlights. It is probably caused
by emulator itself (No solution). If you click anywhere on the view, it will
go back to normal and not highlight the first list row as it
needs to be.
(This is not always the case, sometimes it doesn't appear as highlighted)
 */

public class MainActivity extends AppCompatActivity {

    private Spinner countriesSpinner;
    private ImageView countryFlagImage;
    private TextView capitalText, totalText, numberOfVisitorsText;
    private ListView poiListView;
    private SeekBar numberOfVisitorsBar;
    private Button calculateButton;

    private final ArrayList<String> countryNames = new ArrayList<>();
    private final ArrayList<Country> countries = new ArrayList<>();

    private Country chosenCountry = null;
    private Place chosenPlace = null;
    private double total = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectLayouts();
        fillData();
        configureSpinner();
        configureListView();
        configureSeekBar();
        
        calculateButton.setOnClickListener(view -> {
            if (numberOfVisitorsBar.getProgress() < 1) {
                Toast.makeText(getApplicationContext(), "You need to have at least 1 visitor to proceed!", Toast.LENGTH_SHORT).show();
                total = 0.0;
                totalText.setText(String.format("%s$", total));
            }
            else {
                if (chosenPlace == null) {
                    Toast.makeText(getApplicationContext(), "Please choose a place from the list!", Toast.LENGTH_SHORT).show();
                }
                else {
                    int numberOfVisitors = Integer.parseInt(numberOfVisitorsText.getText().toString());
                    double visitingPrice = chosenPlace.getVisitPrice();

                    if (numberOfVisitors <= 15) total = numberOfVisitors * visitingPrice;
                    else total = (numberOfVisitors * visitingPrice) - ((numberOfVisitors * visitingPrice) * 0.05);

                    totalText.setText(String.format("%s$", total));
                }
            }
        });

    }

    private void configureSpinner() {
        ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, countryNames);
        countriesSpinner.setAdapter(arrayAdapter);

        countriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                countryFlagImage.setImageResource(countries.get(i).getCountryFlag());
                capitalText.setText(countries.get(i).getCountryCapital());
                chosenCountry = countries.get(i);
                poiListView.setAdapter(new PoiAdapter(MainActivity.this, chosenCountry));
                resetLayout();
                chosenPlace = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void configureListView() {
        poiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                chosenPlace = chosenCountry.getPlaces()[i];
                resetLayout();
            }
        });
    }

    private void configureSeekBar() {
        numberOfVisitorsBar.setProgress(1);
        numberOfVisitorsBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                numberOfVisitorsText.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void connectLayouts() {
        countriesSpinner = findViewById(R.id.countriesSpinner);
        countryFlagImage = findViewById(R.id.countryFlagImage);
        capitalText = findViewById(R.id.capitalText);
        totalText = findViewById(R.id.totalText);
        numberOfVisitorsText = findViewById(R.id.numberOfVisitorsText);
        poiListView = findViewById(R.id.poiListView);
        numberOfVisitorsBar = findViewById(R.id.numberOfVisitorsBar);
        calculateButton = findViewById(R.id.calculateButton);

        totalText.setText(String.format("%s$", total));
    }

    private void fillData() {
        countries.add(new Country("Canada", "Ottowa", R.drawable.canada, new Place[] {
                new Place("Niagara Falls", 100, R.drawable.niagara),
                new Place("CN Tower", 30, R.drawable.cntower),
                new Place("The Butchart Gardens", 30, R.drawable.butchart),
                new Place("Notre-Dame Basilica", 50, R.drawable.notre)
        }));

        countries.add(new Country("USA", "Washington", R.drawable.usa, new Place[] {
                new Place("The Statue of Liberty", 90, R.drawable.liberty),
                new Place("The White House", 60, R.drawable.whitehouse),
                new Place("Times Square", 75, R.drawable.timessquare)
        }));

        countries.add(new Country("England", "London", R.drawable.england, new Place[] {
                new Place("Big Ben", 30, R.drawable.bigben),
                new Place("Westminster Abbey", 25, R.drawable.westminster),
                new Place("Hyde Park", 15, R.drawable.hydepark)
        }));

        for (Country country: countries) {
            countryNames.add(country.getCountryName());
        }

    }

    private void resetLayout() {
        total = 0.0;
        totalText.setText(String.format("%s$", total));
        numberOfVisitorsBar.setProgress(1);
        numberOfVisitorsText.setText(String.valueOf(numberOfVisitorsBar.getProgress()));
    }
}