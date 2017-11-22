package juniar.porkat;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public class Main extends Application {

    public static String base_url="http://192.168.43.251/porkat_web/"; ///localhost
//    public static String base_url="http://services.iterasi.id/porkat_web/"; //hendri punya
//    public static String base_url="http://192.168.100.19/porkat_web/";

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
    }
}
