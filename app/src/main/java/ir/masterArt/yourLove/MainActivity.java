package ir.masterArt.yourLove;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import ir.masterArt.yourLove.magic.ListContent;


public class MainActivity extends Activity implements OnClickListener {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    Typeface font;
    int sex = 0;
    public ProgressDialog dialog;
    RelativeLayout reg;
    LinearLayout main;
    boolean regGo = true;
    private Tracker mTracker;
    String action = "", sendNum = "", code = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnalyticsApplication application = (AnalyticsApplication) getApplication();
        mTracker = application.getDefaultTracker();

        final SharedPreferences values = getSharedPreferences("YOUR_LOVE", 0);
        reg = (RelativeLayout) findViewById(R.id.regLayout);
        main = (LinearLayout) findViewById(R.id.mainLayout);

//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
//// ...Irrelevant code for customizing the buttons and title
//        LayoutInflater inflater = this.getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.disable_dialog, null);
//        dialogBuilder.setView(dialogView);
//        dialogBuilder.setCancelable(false);

//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//        dialogBuilder.show();
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String carrierName = manager.getNetworkOperatorName();
        Log.e("@@@@@@@@@##########", "onCreate: " + carrierName);
        if (!values.getBoolean("first", false)) {
            final Dialog dialog = new Dialog(MainActivity.this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.disable_dialog, null);
            TextView textView = (TextView) dialogView.findViewById(R.id.service_text);
            if (carrierName.contains("IR-MCI")) {
                action = "IR-MCI_0";
                sendNum = "307212";
                code = "100";
                textView.setText(getResources().getString(R.string.mci));
            } else if (carrierName.contains("Irancell")) {
                action = "Irancell_0";
                sendNum = "738501";
                code = "100";
                textView.setText(getResources().getString(R.string.mtn));
            } else {
                action = "Other_0";
            }
            dialogView.findViewById(R.id.confirm).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    values.edit().putBoolean("first", true).commit();
                    SmsManager sms = SmsManager.getDefault();
                    if (sendNum.length() > 2) {
                        sms.sendTextMessage(sendNum, null, code, null, null);
                    }
                    mTracker.send(new HitBuilders.EventBuilder()
                            .setCategory(action)
                            .setAction("Click_OK")
                            .build());
                    dialog.dismiss();
                }
            });
            dialogView.findViewById(R.id.close).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    mTracker.send(new HitBuilders.EventBuilder()
                            .setCategory(action)
                            .setAction("Click_Cancel")
                            .build());
                }
            });
            dialog.setContentView(dialogView);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.show();
        }

        /*new AlertDialog.Builder(MainActivity.this)
                .setTitle("هشدار")
                .setCancelable(false)
                .setMessage("این برنامه برای کنترل فرزندان می باشد و هرگونه استفاده از این برنامه به عهده مصرف کننده است !!")
                .setPositiveButton("باشه", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();*/

		/*if(values.getBoolean("first", true))
        {
			regGo = true;
			main.setVisibility(View.GONE);
			reg.setVisibility(View.VISIBLE);
		}
		else
		{
			regGo = false;
			main.setVisibility(View.VISIBLE);
			reg.setVisibility(View.GONE);
		}*/

        /*font = Typeface.createFromAsset(getAssets(), "BRoyaBd.ttf");
        TextView selectText = (TextView) findViewById(R.id.selectText);
        TextView titleText = (TextView) findViewById(R.id.titlePage);
        Button ok = (Button) findViewById(R.id.ok);

        selectText.setTypeface(font);
        ok.setTypeface(font);
        titleText.setTypeface(font);

        Spinner dropdown = (Spinner) findViewById(R.id.spinnerSex);
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
        });*/
    }

    private class MyArrayAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        String[] _items;

        public MyArrayAdapter(String[] con) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(MainActivity.this);
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

    public void progg_dialog() {
        dialog = ProgressDialog.show(
                this, "در حال ارسال اطلاعات", "لطفن صبر کنید ...", true);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        Intent i = null;
        v.startAnimation(buttonClick);
        switch (v.getId()) {
            case R.id.loveSecret:
                i = new Intent(MainActivity.this, secretMonth.class);
                break;
            case R.id.goodWife:
                i = new Intent(MainActivity.this, goodWife.class);
                break;
            case R.id.monthStone:
                i = new Intent(MainActivity.this, stone.class);
                break;
            case R.id.magic:
                i = new Intent(MainActivity.this, magic.class);
                break;
            case R.id.aboutUs:
                break;
            default:
                break;
        }

        startActivity(i);
    }

    /*@Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        if (regGo) {
            regGo = false;
            main.setVisibility(View.VISIBLE);
            reg.setVisibility(View.GONE);
        } else
            super.onBackPressed();

    }*/
}
