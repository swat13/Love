package ir.masterArt.yourLove;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import ir.masterArt.yourLove.stone.ListContent;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class result extends Activity implements OnClickListener{
	
	Typeface font;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.result);
		
		font = Typeface.createFromAsset(getAssets(), "BRoyaBd.ttf");
		
//		TextView shareText = (TextView)findViewById(R.id.shairText);
		TextView titleText = (TextView)findViewById(R.id.titlePage);
		TextView contentText = (TextView)findViewById(R.id.content);
		
		titleText.setTypeface(font);
//		shareText.setTypeface(font);
		contentText.setTypeface(font);
		
		contentText.setText(getIntent().getStringExtra("content"));
		titleText.setText(getIntent().getStringExtra("header"));
		
    }

    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	String packageName = "";
		switch (v.getId()) {
		case R.id.tweet:
			packageName = "com.twitter.android";
			break;
		case R.id.fb:
			packageName = "com.facebook.katana";
			break;
		case R.id.insta:
			packageName = "com.instagram.android";
			break;

		default:
			break;
		}
		
		if(isInstallApp(packageName))
			share(takeScreenshot(),packageName);
		else
			Toast.makeText(result.this, "برنامه نصب نمی باشد ! ", Toast.LENGTH_SHORT).show();
	}
    
    private File takeScreenshot() {

        SimpleDateFormat formatter = new SimpleDateFormat(
                "HH.mm.ss");
        String dateString = formatter.format(new Date());
        try {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + dateString + ".jpg";

            // create bitmap screen capture
            LinearLayout v1 = (LinearLayout)findViewById(R.id.li);
//            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();
            
            return imageFile;

        } catch (Throwable e) {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
        return null;
    }
    
    private void share(File imageFile,String apppPackage){
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, "#androidappsG");
        shareIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(imageFile));  //optional//use this when you want to send an image
        shareIntent.setPackage(apppPackage);
        shareIntent.setType("image/jpeg");
        startActivity(shareIntent);
   }
    
    private boolean isInstallApp(String appPackage){
        boolean instalado = false;

        try {
            ApplicationInfo info = getPackageManager().getApplicationInfo(appPackage, 0);
            instalado = true;
        } catch (NameNotFoundException e) {
            instalado = false;
        }
            return instalado;
        }
}
