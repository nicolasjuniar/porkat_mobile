package juniar.porkat.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static PreferenceHelper instance;

    private SharedPreferences preferences;

    // Be careful with memory leak
    public static PreferenceHelper getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceHelper(context);
        }
        return instance;
    }

    private PreferenceHelper(Context context) {
        preferences = context.getSharedPreferences("DreamCatcher", Context.MODE_PRIVATE);
    }

    public String getString(String key, String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        preferences.edit().putString(key, value).apply();
    }

    public int getInt(String key, int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    public void putInt(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    public void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }
}
