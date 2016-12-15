package ir.masterArt.yourLove;



import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;


import ir.masterArt.yourLove.magic.ListContent;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class register extends Activity  {
	
	Typeface font;
	int sex =0;
	public ProgressDialog dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		SharedPreferences values = getSharedPreferences("YOUR_LOVE", 0);
		if(values.getBoolean("first", true))
		{
			values.edit().putBoolean("first", false);
		}
		else
		{
			Intent i = new Intent(register.this, MainActivity.class);
		    finish();
		    startActivity(i);
		}
		
		font = Typeface.createFromAsset(getAssets(), "BRoyaBd.ttf");
		final EditText phoneText = (EditText)findViewById(R.id.phone_e);
		final EditText nameText = (EditText)findViewById(R.id.name_e);
		final EditText mailText = (EditText)findViewById(R.id.mailedittext);
		TextView selectText = (TextView)findViewById(R.id.selectText);
		TextView titleText = (TextView)findViewById(R.id.titlePage);
		Button ok = (Button)findViewById(R.id.ok);
		
		phoneText.setTypeface(font);
		mailText.setTypeface(font);
		selectText.setTypeface(font);
		nameText.setTypeface(font);
		ok.setTypeface(font);
		titleText.setTypeface(font);
		
		Spinner dropdown = (Spinner)findViewById(R.id.spinnerSex);
		String[] items = new String[2];
		items[0] = "مرد";
		items[1] = "زن";
		MyArrayAdapter ma = new MyArrayAdapter(items);
		dropdown.setAdapter(ma);
		dropdown.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				sex = arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				final String name = nameText.getText().toString();
				final String phone = phoneText.getText().toString();
				final String mail = mailText.getText().toString();
				
				if(name.length() != 0 && phone.length() != 0 && mail.length() != 0)
				{
					if(mail.contains("@") && mail.contains(".com"))
					{
						progg_dialog();
						TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
						final String mPhoneIMEI = tMgr.getDeviceId();
						SharedPreferences values = getSharedPreferences("YOUR_LOVE", 0);
						values.edit().putString("name", name).commit();
						values.edit().putString("phone", phone).commit();
						values.edit().putString("mail", mail).commit();
						values.edit().putString("sex", sex+"").commit();
						values.edit().putString("imei", mPhoneIMEI).commit();
						ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
						NetworkInfo networkInfo = manager.getActiveNetworkInfo();
						if(!(networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable()))
							new Thread(new Runnable() {
								
								@Override
								public void run() {
									// TODO Auto-generated method stub
								}
							}).start();
						else
						{
						    Intent i = new Intent(register.this, MainActivity.class);
						    finish();
						    startActivity(i);
						}
					}
					else 
						Toast.makeText(register.this, "لطفن ایمیل را صحیح وارد کنید !", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(register.this, "لطفن اطلاعات را کامل  وارد کنید !", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
	
	private class MyArrayAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        String[] _items;
        public MyArrayAdapter(String[] con) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(register.this);
            _items = con;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return _items.length;
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            final ListContent holder;
            View v = convertView;
            if (v == null) {
                v = mInflater.inflate(R.layout.spinner_item, null);
                holder = new ListContent();

                holder.name = (TextView) v.findViewById(R.id.spinnerText);

                v.setTag(holder);
            } else {

                holder = (ListContent) v.getTag();
            }

            holder.name.setTypeface(font);
            holder.name.setText("" + _items[position]);

            return v;
        }

    }
	
	public void progg_dialog()
	{
		dialog = ProgressDialog.show(
				this, "در حال ارسال اطلاعات", "لطفن صبر کنید ...", true);
		
	}
	
}