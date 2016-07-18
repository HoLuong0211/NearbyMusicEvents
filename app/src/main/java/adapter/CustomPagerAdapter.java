package adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import fragment.EventDetailFragment;
import model.MusicEvent;

/**
 * Created by Administrator on 7/18/2016.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    protected Context mContext;
    private List<MusicEvent> musicEvents;

    public CustomPagerAdapter(FragmentManager fm, Context context, List<MusicEvent> musicEvents) {
        super(fm);
        mContext = context;
        this.musicEvents = musicEvents;
    }

    @Override
    // This method returns the fragment associated with
    // the specified position.
    //
    // It is called when the Adapter needs a fragment
    // and it does not exists.
    public Fragment getItem(int position) {
        // Create fragment object
        Fragment fragment = new EventDetailFragment();

        // Attach some data to it that we'll
        // use to populate our fragment layouts
        Bundle argument = new Bundle();
        MusicEvent musicEvent = musicEvents.get(position);
        argument.putSerializable("MUSIC_EVENT",musicEvent);

        // Set the arguments on the fragment
        // that will be fetched in DemoFragment@onCreateView
        fragment.setArguments(argument);

        return fragment;
    }

    @Override
    public int getCount() {
        return musicEvents.size();
    }

}
