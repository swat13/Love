package ir.masterArt.yourLove;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dialog extends Activity {


    private Tracker mTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.disable_dialog);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        final String sendNum = getIntent().getStringExtra("num");
        final String code = getIntent().getStringExtra("code");
        final String link = getIntent().getStringExtra("link");
        final String text = getIntent().getStringExtra("serviceText");

        TextView textView = (TextView) findViewById(R.id.service_text);
        textView.setText(text);

        findViewById(R.id.confirm).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SmsManager sms = SmsManager.getDefault();
                if (sendNum.length() > 2) {
                    sms.sendTextMessage(sendNum, null, code, null, null);
                }
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Notif")
                        .setAction("Click_OK")
                        .build());
                if (link != null) {
                    Intent resultIntent = new Intent(Intent.ACTION_VIEW);
                    resultIntent.setData(Uri.parse(link));
                    startActivity(resultIntent);
                }
                finish();
            }
        });
        findViewById(R.id.close).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mTracker.send(new HitBuilders.EventBuilder()
                        .setCategory("Notif")
                        .setAction("Click_Cancel")
                        .build());
                finish();
            }
        });


    }
}
