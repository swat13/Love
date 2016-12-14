package ir.masterArt.yourLove;

import ir.masterArt.yourLove.stone.ListContent;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class goodWife extends Activity implements OnClickListener{
	
	DrawerLayout dLayout;
	Typeface font;
	
	int month,sex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.good_wife);
		
		font = Typeface.createFromAsset(getAssets(), "BRoyaBd.ttf");
		TextView mainText = (TextView)findViewById(R.id.mainText);
		TextView magicText = (TextView)findViewById(R.id.magicText);
		TextView monthStoneText = (TextView)findViewById(R.id.monthStoneText);
		TextView goodWifeText = (TextView)findViewById(R.id.goodWifeText);
		TextView secretLoveText = (TextView)findViewById(R.id.secretLoveText);
		TextView aboutText = (TextView)findViewById(R.id.aboutText);
		TextView selectText = (TextView)findViewById(R.id.selectText);
		TextView monthText = (TextView)findViewById(R.id.monthText);
		TextView sexText = (TextView)findViewById(R.id.sexText);
		TextView titleText = (TextView)findViewById(R.id.titlePage);
		Button ok = (Button)findViewById(R.id.ok);
		
		mainText.setTypeface(font);
		magicText.setTypeface(font);
		monthStoneText.setTypeface(font);
		goodWifeText.setTypeface(font);
		secretLoveText.setTypeface(font);
		aboutText.setTypeface(font);
		selectText.setTypeface(font);
		monthText.setTypeface(font);
		sexText.setTypeface(font);
		ok.setTypeface(font);
		titleText.setTypeface(font);
		
		dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        dLayout.setDrawerListener(new LeftMenuListener());
        dLayout.setScrimColor(getResources().getColor(android.R.color.transparent));
		
		Button closMenu = (Button)findViewById(R.id.close);
		closMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dLayout.closeDrawers();
			}
		});
		
		ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(goodWife.this, result.class);
				String[] total = getResources().getStringArray(R.array.goodWife);
				i.putExtra("content", total[month]);
				i.putExtra("header", "همسر ایده آل");
				startActivity(i);
			}
		});
		
		Button openMenu = (Button)findViewById(R.id.menu);
		openMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dLayout.openDrawer(Gravity.LEFT);
			}
		});
		
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
				// TODO Auto-generated method stub
				
			}
		});
		
		Spinner dropdown2 = (Spinner)findViewById(R.id.spinnerMonth);
		String[] items2 = new String[12];
		items2[0] = "فروردین";
		items2[1] = "اردیبهشت";
		items2[2] = "خرداد";
		items2[3] = "تیر";
		items2[4] = "مرداد";
		items2[5] = "شهریور";
		items2[6] = "مهر";
		items2[7] = "آبان";
		items2[8] = "آذر";
		items2[9] = "دی";
		items2[10] = "بهمن";
		items2[11] = "اسفند";
		
		MyArrayAdapter ma2 = new MyArrayAdapter(items2);
		dropdown2.setAdapter(ma2);
		dropdown2.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				month = arg2;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private class LeftMenuListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerClosed(View view) {
        }

		@Override
		public void onDrawerOpened(View arg0) {
			
		}

		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {

	        dLayout.bringChildToFront(drawerView);
	        dLayout.requestLayout();
		}

		@Override
		public void onDrawerStateChanged(int newState) {
		}
		
    }

	private class MyArrayAdapter extends BaseAdapter {

        private LayoutInflater mInflater;

        String[] _items;
        public MyArrayAdapter(String[] con) {
            // TODO Auto-generated constructor stub
            mInflater = LayoutInflater.from(goodWife.this);
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

    static class ListContent {

        TextView name;

    }

    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i = null;
		switch (v.getId()) {
		case R.id.mainText:
			onBackPressed();
			dLayout.closeDrawers();
			break;
		case R.id.magicText:
			onBackPressed();
			dLayout.closeDrawers();
			i = new Intent(goodWife.this, magic.class);
			break;
		case R.id.secretLoveText:
			onBackPressed();
			dLayout.closeDrawers();
			i = new Intent(goodWife.this, secretMonth.class);
			break;
		case R.id.monthStoneText:
			onBackPressed();
			dLayout.closeDrawers();
			i = new Intent(goodWife.this, stone.class);
			break;
		case R.id.goodWifeText:
			dLayout.closeDrawers();
			break;
		case R.id.aboutText:
			onBackPressed();
			dLayout.closeDrawers();
			i = new Intent(goodWife.this, about.class);
			break;
		default:
			break;
		}
		if(i!=null)
			startActivity(i);
	}

}
