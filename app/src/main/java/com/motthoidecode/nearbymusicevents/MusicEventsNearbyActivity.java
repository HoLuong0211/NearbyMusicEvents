package com.motthoidecode.nearbymusicevents;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.SeekBar;
import android.widget.TextView;

import utils.PreferenceUtils;

public class MusicEventsNearbyActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    private static final String ORANGE = "#FF8F29";
    private static final String GRAY = "#9a9289";
    private SeekBar radiusControl;
    private TextView nr_radius_txt_0, nr_radius_txt_1, nr_radius_txt_2, nr_radius_txt_3,
            nr_radius_txt_4, nr_radius_txt_5, nr_radius_txt_6, nr_radius_txt_7;

    private PreferenceUtils mPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_events_nearby);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nr_radius_txt_0 = (TextView)findViewById(R.id.nr_radius_txt_0);
        nr_radius_txt_1 = (TextView)findViewById(R.id.nr_radius_txt_1);
        nr_radius_txt_2 = (TextView)findViewById(R.id.nr_radius_txt_2);
        nr_radius_txt_3 = (TextView)findViewById(R.id.nr_radius_txt_3);
        nr_radius_txt_4 = (TextView)findViewById(R.id.nr_radius_txt_4);
        nr_radius_txt_5 = (TextView)findViewById(R.id.nr_radius_txt_5);
        nr_radius_txt_6 = (TextView)findViewById(R.id.nr_radius_txt_6);
        nr_radius_txt_7 = (TextView)findViewById(R.id.nr_radius_txt_7);
        radiusControl = (SeekBar) findViewById(R.id.nr_radius_bar);
        mPreference = new PreferenceUtils(getApplicationContext());

        nr_radius_txt_2.setTextColor(Color.parseColor(ORANGE));
        radiusControl.setProgress(mPreference.getSeekBarProgress());
        radiusControl.setOnSeekBarChangeListener(this);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        switch (i) {
            case 0:
                mPreference.saveSearchRadius(1);
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
                mPreference.saveSearchRadius(5);
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
                mPreference.saveSearchRadius(10);
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
                mPreference.saveSearchRadius(20);
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
                mPreference.saveSearchRadius(50);
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
                mPreference.saveSearchRadius(100);
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
                mPreference.saveSearchRadius(200);
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
                mPreference.saveSearchRadius(500);
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

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }
}
