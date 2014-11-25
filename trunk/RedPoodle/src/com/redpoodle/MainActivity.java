package com.redpoodle;

import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class MainActivity extends ActionBarActivity {

	private Button startButton;
	private Button stopButton;
	private Button resetButton;
	private Button set1;
	private Button set2;
	private Button set3;
	private Button set4;
	private Chronometer chronometer;
	private long time = 0;
	
	private boolean isComplateSet1;
	private boolean isComplateSet2;
	private boolean isComplateSet3;
	private boolean isComplateSet4;
	
	private static final long VIB_TIME = 60;

	private Vibrator vib;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Viewの初期化
		chronometer = (Chronometer) findViewById(R.id.chronometer);
		startButton = (Button) findViewById(R.id.startButton);
		stopButton = (Button) findViewById(R.id.stopButton);
		resetButton = (Button) findViewById(R.id.resetButton);
		set1 = (Button) findViewById(R.id.toggleButton1);
		set2 = (Button) findViewById(R.id.toggleButton2);
		set3 = (Button) findViewById(R.id.toggleButton3);
		set4 = (Button) findViewById(R.id.toggleButton4);
		
		vib = (Vibrator)getSystemService(VIBRATOR_SERVICE);
		
		stopButton.setEnabled(false);

		// ボタンが押された時の処理
		startButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// スタート
				vib.vibrate(VIB_TIME);
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
				chronometer.setBase(SystemClock.elapsedRealtime() - time);
				chronometer.start();
			}
		});
		stopButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// ストップ
				vib.vibrate(VIB_TIME);
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
				chronometer.stop();
				time = SystemClock.elapsedRealtime() - chronometer.getBase();
			}
		});
		resetButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// リセット
				vib.vibrate(VIB_TIME);
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
				chronometer.stop();
				chronometer.setBase(SystemClock.elapsedRealtime());
				time = 0;
			}
		});
		set1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				vib.vibrate(VIB_TIME);
			}
		});
		set2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				vib.vibrate(VIB_TIME);
			}
		});
		set3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				vib.vibrate(VIB_TIME);
			}
		});
		set4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				vib.vibrate(VIB_TIME);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
