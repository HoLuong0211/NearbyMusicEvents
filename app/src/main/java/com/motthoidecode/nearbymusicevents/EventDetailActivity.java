package com.motthoidecode.nearbymusicevents;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import adapter.CustomPagerAdapter;
import model.MusicEvent;

public class EventDetailActivity extends AppCompatActivity {

    private List<MusicEvent> musicEvents;
    private int position;
    private ActionBar mActionBar;
    CustomPagerAdapter mCustomPagerAdapter;
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        position = getIntent().getIntExtra("POSITION",0);
        musicEvents = (List<MusicEvent>) getIntent().getSerializableExtra("LIST_EVENTS");
        // == Setting up the ViewPager ==
        mCustomPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), this, musicEvents);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mViewPager.setCurrentItem(position);
        mActionBar.setTitle(musicEvents.get(position).getTitle());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
