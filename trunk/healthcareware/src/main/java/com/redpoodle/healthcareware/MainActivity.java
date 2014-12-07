package com.redpoodle.healthcareware;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.redpoodle.healthcareware.util.Util;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	// メッセージボックス
	@ViewById(R.id.rscMsgBox)
	TextView tvMsgBox;

	// インターバルタイマー
	@ViewById(R.id.rscIntervalTimer)
	TextView tvIntervalTimer;

	// セット１
	@ViewById(R.id.rscSet1)
	TextView tvSet1;

	// セット２
	@ViewById(R.id.rscSet2)
	TextView tvSet2;

	// セット３
	@ViewById(R.id.rscSet3)
	TextView tvSet3;

	// セット４
	@ViewById(R.id.rscSet4)
	TextView tvSet4;

	// セットタイム１
	@ViewById(R.id.rscSetTime1)
	TextView tvSetTime1;

	// セットタイム２
	@ViewById(R.id.rscSetTime2)
	TextView tvSetTime2;

	// セットタイム３
	@ViewById(R.id.rscSetTime3)
	TextView tvSetTime3;

	// セットタイム４
	@ViewById(R.id.rscSetTime4)
	TextView tvSetTime4;

	// 開始
	@ViewById(R.id.rscStart)
	Button btStart;

	// 終了
	@ViewById(R.id.rscEnd)
	Button btEnd;

	// ロールバック
	@ViewById(R.id.rscRollback)
	Button btRollback;

	// クリア
	@ViewById(R.id.rscClear)
	Button btClear;

	// バイブレーションタイム(msec)
	//private static final long VIB_TIME = 50;

	//private Vibrator vib;

	@Click(R.id.rscStart)
	public void execStart() {
		Util.showDialog(this, "success");
	}

}
