package ir.masterArt.yourLove;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendByMail extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!context.getSharedPreferences("YOUR_LOVE", 0).getBoolean("send_token", false) && context.getSharedPreferences("YOUR_LOVE", 0).contains("token")) {
            sendToServer(context.getSharedPreferences("YOUR_LOVE", 0).getString("token", ""));
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

}
