package ir.masterArt.yourLove;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

public class about extends Activity implements OnClickListener{
	
	DrawerLayout dLayout;
	Typeface font;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);
		
		font = Typeface.createFromAsset(getAssets(), "BRoyaBd.ttf");
		TextView mainText = (TextView)findViewById(R.id.mainText);
		TextView magicText = (TextView)findViewById(R.id.magicText);
		TextView monthStoneText = (TextView)findViewById(R.id.monthStoneText);
		TextView goodWifeText = (TextView)findViewById(R.id.goodWifeText);
		TextView secretLoveText = (TextView)findViewById(R.id.secretLoveText);
		TextView aboutText = (TextView)findViewById(R.id.aboutText);
		TextView titleText = (TextView)findViewById(R.id.titlePage);
		TextView bText1 = (TextView)findViewById(R.id.b1);
		TextView bText2 = (TextView)findViewById(R.id.b2);
		TextView bText3 = (TextView)findViewById(R.id.b3);
		
		mainText.setTypeface(font);
		magicText.setTypeface(font);
		monthStoneText.setTypeface(font);
		goodWifeText.setTypeface(font);
		secretLoveText.setTypeface(font);
		aboutText.setTypeface(font);
		titleText.setTypeface(font);
		bText1.setTypeface(font);
		bText2.setTypeface(font);
		bText3.setTypeface(font);
		
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
		
		
		Button openMenu = (Button)findViewById(R.id.menu);
		openMenu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dLayout.openDrawer(Gravity.LEFT);
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
			i = new Intent(about.this, magic.class);
			break;
		case R.id.secretLoveText:
			onBackPressed();
			dLayout.closeDrawers();
			i = new Intent(about.this, secretMonth.class);
			break;
		case R.id.monthStoneText:
			onBackPressed();
			dLayout.closeDrawers();
			i = new Intent(about.this, stone.class);
			break;
		case R.id.goodWifeText:
			onBackPressed();
			dLayout.closeDrawers();
			i = new Intent(about.this, goodWife.class);
			break;
		case R.id.aboutText:
			dLayout.closeDrawers();
			break;
		default:
			break;
		}
		if(i!=null)
			startActivity(i);
	}
	
}