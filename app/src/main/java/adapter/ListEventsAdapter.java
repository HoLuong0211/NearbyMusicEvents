package adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.motthoidecode.nearbymusicevents.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.MusicEvent;

/**
 * Created by Administrator on 7/9/2016.
 */
public class ListEventsAdapter extends ArrayAdapter<MusicEvent> {

    private List<MusicEvent> musicEvents;
    private int resourceID;
    private Context mContext;

    public ListEventsAdapter(Context context, int resource, List<MusicEvent> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.resourceID = resource;
        this.musicEvents = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = View.inflate(mContext, resourceID, null);

        TextView tvMusicEventTitle = (TextView) convertView.findViewById(R.id.tvMusicEventTitle);
        TextView tvMusicEventTime = (TextView) convertView.findViewById(R.id.tvMusicEventTime);
        ImageView ivMusicEvent = (ImageView) convertView.findViewById(R.id.ivMusicEvent);

        MusicEvent musicEvent = musicEvents.get(position);
        tvMusicEventTitle.setText(musicEvent.getTitle());
        tvMusicEventTime.setText(musicEvent.getStart_time());
        if (musicEvent.getImageUrl() != null)
            Picasso.with(mContext).load(musicEvent.getImageUrl()).into(ivMusicEvent);

        return convertView;
    }
}
