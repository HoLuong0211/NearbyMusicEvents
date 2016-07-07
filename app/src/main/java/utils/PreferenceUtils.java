package utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 7/7/2016.
 */
public class PreferenceUtils {

    private static final String SEARCH_RADIUS = "search_radius";

    private static final String PREFS_NAME = "PreferenceUtils";

    private SharedPreferences mPrefs;

    public PreferenceUtils(Context context) {
        mPrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSearchRadius(int radius) {
        SharedPreferences.Editor editor = mPrefs.edit();

        editor.putInt(SEARCH_RADIUS, radius);

        editor.commit();
    }

    public int getSearchRadius() {
        return mPrefs.getInt(SEARCH_RADIUS, 10);
    }

    public int getSeekBarProgress() {
        int progress = 2;
        switch (getSearchRadius()) {
            case 1:
                progress = 0;
            break;
            case 5:
                progress = 1;
            break;
            case 10:
                progress = 2;
            break;
            case 20:
                progress = 3;
            break;
            case 50:
                progress = 4;
            break;
            case 100:
                progress = 5;
            break;
            case 200:
                progress = 6;
            break;
            case 500:
                progress = 7;
            break;
        }
        return progress;
    }
}
