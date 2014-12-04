package com.redpoodle.healthcareware;

import android.app.Activity;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.ViewById;

public class MainActivity extends Activity {

	// メッセージボックス
	@ViewById(R.id.rscMsgBox)
	private TextView tvMsgBox;
	
	// インターバルタイマー
	@ViewById(R.id.rscIntervalTimer)
	private TextView tvIntervalTimer;
	
	// セット１
	@ViewById(R.id.rscSet1)
	private TextView tvSet1;

	// セット２
	@ViewById(R.id.rscSet2)
	private TextView tvSet2;

	// セット３
	@ViewById(R.id.rscSet3)
	private TextView tvSet3;

	// セット４
	@ViewById(R.id.rscSet4)
	private TextView tvSet4;
	
	private static final long VIB_TIME = 50;

	private Vibrator vib;
	

}
