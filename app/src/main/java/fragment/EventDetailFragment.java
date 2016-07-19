package fragment;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.motthoidecode.nearbymusicevents.FullMapViewActivity;
import com.motthoidecode.nearbymusicevents.MusicEventsNearbyActivity;
import com.motthoidecode.nearbymusicevents.R;
import com.squareup.picasso.Picasso;

import model.MusicEvent;
import utils.Config;

/**
 * Created by Administrator on 7/12/2016.
 */
public class EventDetailFragment extends Fragment  implements OnMapReadyCallback {

    private static final float DEFAULT_ZOOM = 14;
    private GoogleMap mMap;
    private Location mEventLocation;
    private MusicEvent musicEvent;

    public EventDetailFragment(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TextView tvEventTitle, tvLocation, tvDate, tvEventDetails, tvEventVenue;
        Button btnGetTickets;
        ImageView ivEventDetail;
        MapView mMapView;

        View rootView = inflater.inflate(R.layout.event_detail_fragment,container,false);

        tvEventTitle = (TextView) rootView.findViewById(R.id.tvEventTitle);
        tvLocation = (TextView) rootView.findViewById(R.id.tvLocation);
        tvDate = (TextView) rootView.findViewById(R.id.tvDate);
        tvEventDetails = (TextView) rootView.findViewById(R.id.tvEventDetails);
        tvEventVenue = (TextView) rootView.findViewById(R.id.tvEventVenue);
        btnGetTickets = (Button) rootView.findViewById(R.id.btnGetTickets);
        ivEventDetail = (ImageView) rootView.findViewById(R.id.ivEventDetail);
        mEventLocation = new Location("");

        musicEvent = (MusicEvent) getArguments().getSerializable("MUSIC_EVENT");

        tvEventTitle.setText(musicEvent.getTitle());
        tvLocation.setText(musicEvent.getVenue_address());
        tvDate.setText(musicEvent.getStart_time());
        tvEventDetails.setText(musicEvent.getDescription());
        tvEventVenue.setText(musicEvent.getVenue_name());
        if (musicEvent.getImageUrl() == null) {
            ivEventDetail.setBackgroundColor(Color.parseColor(MusicEventsNearbyActivity.ORANGE));
        } else {
            Picasso.with(getContext()).load(musicEvent.getImageUrl()).into(ivEventDetail);
            tvEventTitle.setTextColor(Color.parseColor(MusicEventsNearbyActivity.ORANGE));
        }

        mEventLocation.setLatitude(musicEvent.getLatitude());
        mEventLocation.setLongitude(musicEvent.getLongitude());

        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        btnGetTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent website = new Intent(Intent.ACTION_VIEW);
                website.setData(Uri.parse(musicEvent.getUrl()));
                startActivity(website);
            }
        });

        Config.makeTextViewResizable(tvEventDetails,3,"View More", true);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(getContext());
        LatLng mLatLng = new LatLng(mEventLocation.getLatitude(), mEventLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(mLatLng));
        // Move the captureImage
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng, DEFAULT_ZOOM));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent fullMapView = new Intent(getContext(), FullMapViewActivity.class);
                fullMapView.putExtra("LATITUDE",mEventLocation.getLatitude());
                fullMapView.putExtra("LONGITUDE",mEventLocation.getLongitude());
                fullMapView.putExtra("VENUE_NAME",musicEvent.getVenue_name());
                startActivity(fullMapView);
            }
        });
    }
}
