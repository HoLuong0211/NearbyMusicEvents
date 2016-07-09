package com.motthoidecode.nearbymusicevents;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import model.GoogleApiLocation;
import utils.PreferenceUtils;

public class MusicEventsNearbyActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String ORANGE = "#FF8F29";
    private static final String GRAY = "#9a9289";
    private SeekBar radiusControl;
    private TextView nr_radius_txt_0, nr_radius_txt_1, nr_radius_txt_2, nr_radius_txt_3,
            nr_radius_txt_4, nr_radius_txt_5, nr_radius_txt_6, nr_radius_txt_7;

    private PreferenceUtils mPreference;
    private Location mCurrentLocation;
    private GoogleApiLocation googleApiLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_events_nearby);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nr_radius_txt_0 = (TextView) findViewById(R.id.nr_radius_txt_0);
        nr_radius_txt_1 = (TextView) findViewById(R.id.nr_radius_txt_1);
        nr_radius_txt_2 = (TextView) findViewById(R.id.nr_radius_txt_2);
        nr_radius_txt_3 = (TextView) findViewById(R.id.nr_radius_txt_3);
        nr_radius_txt_4 = (TextView) findViewById(R.id.nr_radius_txt_4);
        nr_radius_txt_5 = (TextView) findViewById(R.id.nr_radius_txt_5);
        nr_radius_txt_6 = (TextView) findViewById(R.id.nr_radius_txt_6);
        nr_radius_txt_7 = (TextView) findViewById(R.id.nr_radius_txt_7);
        radiusControl = (SeekBar) findViewById(R.id.nr_radius_bar);
        mPreference = new PreferenceUtils(getApplicationContext());

        radiusControl.setProgress(mPreference.getSeekBarProgress());
        radiusControl.setOnSeekBarChangeListener(this);

        setRadiusTextColor();
        googleApiLocation = new GoogleApiLocation(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        googleApiLocation.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        googleApiLocation.disconnect();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (i) {
            case 0:
                mPreference.saveSearchRadius(1);
                break;
            case 1:
                mPreference.saveSearchRadius(5);
                break;
            case 2:
                mPreference.saveSearchRadius(10);
                break;
            case 3:
                mPreference.saveSearchRadius(20);
                break;
            case 4:
                mPreference.saveSearchRadius(50);
                break;
            case 5:
                mPreference.saveSearchRadius(100);
                break;
            case 6:
                mPreference.saveSearchRadius(200);
                break;
            case 7:
                mPreference.saveSearchRadius(500);
                break;
            default:
                setRadiusTextColor();
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void getMusicEventsNearby() {
        if (!isNetworkAvailable()) {
            Toast.makeText(this, "Can not connect to the Internet!", Toast.LENGTH_LONG).show();
            return;
        }
        final ProgressDialog progressDialog = new ProgressDialog(MusicEventsNearbyActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
    }

    public void setCurrentLocation(Location location) {
        mCurrentLocation = location;
    }

    private void setRadiusTextColor() {
        switch (mPreference.getSeekBarProgress()) {
            case 0:
                nr_radius_txt_0.setTextColor(Color.parseColor(ORANGE));
                nr_radius_txt_1.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_2.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_3.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_4.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_5.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_7.setTextColor(Color.parseColor(GRAY));
                break;
            case 1:
                nr_radius_txt_0.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_1.setTextColor(Color.parseColor(ORANGE));
                nr_radius_txt_2.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_3.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_4.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_5.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_7.setTextColor(Color.parseColor(GRAY));
                break;
            case 2:
                nr_radius_txt_0.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_1.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_2.setTextColor(Color.parseColor(ORANGE));
                nr_radius_txt_3.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_4.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_5.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_7.setTextColor(Color.parseColor(GRAY));
                break;
            case 3:
                nr_radius_txt_0.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_1.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_2.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_3.setTextColor(Color.parseColor(ORANGE));
                nr_radius_txt_4.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_5.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_7.setTextColor(Color.parseColor(GRAY));
                break;
            case 4:
                nr_radius_txt_0.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_1.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_2.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_3.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_4.setTextColor(Color.parseColor(ORANGE));
                nr_radius_txt_5.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_7.setTextColor(Color.parseColor(GRAY));
                break;
            case 5:
                nr_radius_txt_0.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_1.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_2.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_3.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_4.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_5.setTextColor(Color.parseColor(ORANGE));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_7.setTextColor(Color.parseColor(GRAY));
                break;
            case 6:
                nr_radius_txt_0.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_1.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_2.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_3.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_4.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_5.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(ORANGE));
                nr_radius_txt_7.setTextColor(Color.parseColor(GRAY));
                break;
            case 7:
                nr_radius_txt_0.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_1.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_2.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_3.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_4.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_5.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_6.setTextColor(Color.parseColor(GRAY));
                nr_radius_txt_7.setTextColor(Color.parseColor(ORANGE));
                break;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(this.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (null == networkInfo)
            return false;
        return networkInfo.isConnectedOrConnecting();
    }
}
