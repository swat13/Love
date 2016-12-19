/*
package ir.masterArt.yourLove.Firebase;

*/
/**
 * Created by Alif on 10/5/2016.
 *//*


import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

*/
/**
 * Created by Belal on 5/27/2016.
 *//*


*/
/*
* rt
* *//*

//Class extending FirebaseInstanceIdService
public class InstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e(TAG, "Refreshed token: " + refreshedToken);
        getSharedPreferences("YOUR_LOVE", 0).edit().putString("token", refreshedToken).apply();
        if (sendToServer(refreshedToken)) {
            getSharedPreferences("YOUR_LOVE", 0).edit().putBoolean("send_token", true).apply();
        }
    }

    public boolean sendToServer(String token) {
        try {

            URL url = new URL("http://af1993.ir/tele.php");
            Log.e("1111111", "doInBackground: " + url);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
            httpConn.setAllowUserInteraction(false);
            httpConn.setRequestMethod("POST");
            httpConn.setConnectTimeout(20000);
            httpConn.setReadTimeout(20000);
//            httpConn.setRequestProperty("token",token);
            OutputStream os = httpConn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

            writer.write("token=" + token);
            writer.flush();
            writer.close();
            os.close();

            int resCode = httpConn.getResponseCode();
            Log.e("0000000", "doInBackground: " + resCode);
            if (resCode != 200) {
                return false;
            }
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }


}*/
