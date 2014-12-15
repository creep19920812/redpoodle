package com.redpoodle.healthcareware;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.redpoodle.healthcareware.util.Util;

/**
 * @author mariji
 * 
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	private long time = 0;

	// メッセージボックス
	@ViewById(R.id.rscMsgBox)
	TextView tvMsgBox;

	// インターバルタイマー
	@ViewById(R.id.rscIntervalTimer)
	// TextView tvIntervalTimer;
	Chronometer tvIntervalTimer;

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
	// private static final long VIB_TIME = 50;

	// private Vibrator vib;

	// ■イベント一覧
	// ・初期イベント
	// 　１、値をすべて初期化する
	// 　２．メッセージに「待機中」を表示する
	// 　３．ロールバックボタンを非活性
	// ・開始イベント
	// 　１．メッセージに「ワークアウト中」を表示する
	// 　２．開始ボタンを非活性にする
	// 　３．前回セットラベルの色を戻す
	// 　４．カレントセットラベルに色付する
	// 　５．新規カウント開始
	// ・終了イベント
	// 　１．メッセージに「インターバル」を表示する
	// 　２．終了ボタンを非活性にする
	// 　３．カウントをカレントセットタイムラベルにセットする
	// 　４．新規カウント開始
	// ・ロールバックイベント
	// 　１．カレントセットを1--
	// 　２、値をすべて初期化する
	// 　３．メッセージに「待機中」を表示する
	// 　４．１セットの場合ロールバックボタンを非活性
	// ・クリアイベント
	// 　初期イベントと同じ振る舞い

	@AfterViews()
	public void init() {
		// 　１、値をすべて初期化する
		tvMsgBox.setText("");
		tvIntervalTimer.setText("");
		tvSetTime1.setText("");
		tvSetTime2.setText("");
		tvSetTime3.setText("");
		tvSetTime4.setText("");
		tvSetTime1.setText("");
		tvSetTime2.setText("");
		tvSetTime3.setText("");
		tvSetTime4.setText("");
		// 　２．メッセージに「待機中」を表示する
		tvMsgBox.setText("waiting...");

		// 　３．ロールバックボタンを非活性
		btStart.setEnabled(true);
		btEnd.setEnabled(true);
		btRollback.setEnabled(false);
		btClear.setEnabled(true);

	}

	@Click(R.id.rscStart)
	public void execStart() {
		// 　１．メッセージに「ワークアウト中」を表示する
		tvMsgBox.setText("execute workout");
		// 　２．開始ボタンを非活性にする
		btStart.setEnabled(false);
		btEnd.setEnabled(true);
		btRollback.setEnabled(true);
		btClear.setEnabled(true);

		// 　３．前回セットラベルの色を戻す
		// 　４．カレントセットラベルに色付する
		// 　５．新規カウント開始
		tvIntervalTimer.setBase(SystemClock.elapsedRealtime() - time);
		tvIntervalTimer.start();
	}

	@Click(R.id.rscEnd)
	public void execEnd() {
		tvIntervalTimer.stop();
		time = SystemClock.elapsedRealtime() - tvIntervalTimer.getBase();
	}

	@Click(R.id.rscRollback)
	public void execRollback() {
		Util.showDialog(this, "rollback!");
	}

	@Click(R.id.rscClear)
	public void execClear() {
		tvIntervalTimer.stop();
		time = 0;

		// 　１、値をすべて初期化する
		tvMsgBox.setText("");
		// tvIntervalTimer.setText("");
		tvIntervalTimer.setBase(SystemClock.elapsedRealtime());
		tvSetTime1.setText("");
		tvSetTime2.setText("");
		tvSetTime3.setText("");
		tvSetTime4.setText("");
		tvSetTime1.setText("");
		tvSetTime2.setText("");
		tvSetTime3.setText("");
		tvSetTime4.setText("");
		// 　２．メッセージに「待機中」を表示する
		tvMsgBox.setText("waiting...");

		// 　３．ロールバックボタンを非活性
		btStart.setEnabled(true);
		btEnd.setEnabled(true);
		btRollback.setEnabled(false);
		btClear.setEnabled(true);

	}

}
