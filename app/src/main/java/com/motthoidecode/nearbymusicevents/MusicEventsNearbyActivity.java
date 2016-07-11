package com.motthoidecode.nearbymusicevents;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.ListEventsAdapter;
import model.GoogleApiLocation;
import model.MusicEvent;
import network.DownloadXMLDataTask;
import network.DownloadXMLDataTask.OnDownloadXMLCompleteListener;
import network.XMLPullParserHandler;
import utils.Config;
import utils.PreferenceUtils;

public class MusicEventsNearbyActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, OnDownloadXMLCompleteListener {

    public static final String ORANGE = "#FF8F29";
    private static final String GRAY = "#9a9289";
    private SeekBar radiusControl;
    private TextView nr_radius_txt_0, nr_radius_txt_1, nr_radius_txt_2, nr_radius_txt_3,
            nr_radius_txt_4, nr_radius_txt_5, nr_radius_txt_6, nr_radius_txt_7;
    private ProgressDialog progressDialog;

    private PreferenceUtils mPreference;
    private Location mCurrentLocation;
    private GoogleApiLocation googleApiLocation;

    private List<MusicEvent> musicEvents;

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

        musicEvents = new ArrayList<>();

        radiusControl.setProgress(mPreference.getSeekBarProgress());
        radiusControl.setOnSeekBarChangeListener(this);

        setRadiusTextColor();
        googleApiLocation = new GoogleApiLocation(this);
        googleApiLocation.connect();
    }

    @Override
    protected void onDestroy() {
        googleApiLocation.disconnect();
        super.onDestroy();
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
        }
        setRadiusTextColor();
        googleApiLocation.disconnect();
        googleApiLocation.connect();

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    public void getMusicEventsNearby() {
        if (!isNetworkAvailable()) {
            showToastMessage("Can not connect to the Internet!");
            return;
        }
        progressDialog = new ProgressDialog(MusicEventsNearbyActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String urlStr = Config.URL_EVENT_SEARCH + mCurrentLocation.getLatitude() + ","
                + mCurrentLocation.getLongitude() + Config.URL_WITHIN_SEARCH + mPreference.getSearchRadius()
                + Config.URL_IMAGE_SIZES_SEARCH;
        DownloadXMLDataTask downloadTask = new DownloadXMLDataTask(this);
        downloadTask.execute(urlStr);
    }

       @Override
    public void onDownloadXMLComplete(String xmlStr) {
        if(progressDialog.isShowing())  progressDialog.dismiss();
        if(xmlStr == null){
            showToastMessage("Null XML String!");
            return;
        }
        XMLPullParserHandler parserHandler = new XMLPullParserHandler();
        musicEvents = parserHandler.parse(xmlStr);
        ListView lvListMusicEvents = (ListView)findViewById(R.id.lvListMusicEvents);
        ListEventsAdapter eventsAdapter = new ListEventsAdapter(this,R.layout.music_event_row,musicEvents);
        lvListMusicEvents.setAdapter(eventsAdapter);
           lvListMusicEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                   MusicEvent event = musicEvents.get(position);
                   Intent i = new Intent(MusicEventsNearbyActivity.this,EventDetailActivity.class);
                   i.putExtra(Config.KEY_ID, event.getId());
                   i.putExtra(Config.KEY_TITLE, event.getTitle());
                   i.putExtra(Config.KEY_DESCRIPTION, event.getDescription());
                   i.putExtra(Config.KEY_URL, event.getUrl());
                   i.putExtra(Config.KEY_START_TIME, event.getStart_time());
                   i.putExtra(Config.KEY_STOP_TIME, event.getStop_time());
                   i.putExtra(Config.KEY_VENUE_ID, event.getVenue_id());
                   i.putExtra(Config.KEY_VENUE_NAME, event.getVenue_name());
                   i.putExtra(Config.KEY_VENUE_ADDRESS, event.getVenue_address());
                   i.putExtra(Config.KEY_LATITUDE, event.getLatitude());
                   i.putExtra(Config.KEY_LONGITUDE, event.getLongitude());
                   startActivity(i);
               }
           });
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

    public void showToastMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
