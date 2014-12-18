package com.redpoodle.healthcareware;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import android.app.Activity;
import android.os.SystemClock;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import com.redpoodle.healthcareware.util.Constants;
import com.redpoodle.healthcareware.util.Util;

/**
 * @author mariji
 * 
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

	// private long time = 0;

	// メッセージボックス
	@ViewById(R.id.rscMsgBox)
	TextView tvMsgBox;

	// ナビ１
	@ViewById(R.id.rscNavi1)
	TextView tvNavi1;

	// ナビ２
	@ViewById(R.id.rscNavi2)
	TextView tvNavi2;

	// ナビ３
	@ViewById(R.id.rscNavi3)
	TextView tvNavi3;

	// ナビ４
	@ViewById(R.id.rscNavi4)
	TextView tvNavi4;

	// インターバルタイマー
	@ViewById(R.id.rscIntervalTimer)
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

	// インターバルタイム１
	@ViewById(R.id.rscIntervalTime1)
	TextView tvIntervalTime1;

	// インターバルタイム２
	@ViewById(R.id.rscIntervalTime2)
	TextView tvIntervalTime2;

	// インターバルタイム３
	@ViewById(R.id.rscIntervalTime3)
	TextView tvIntervalTime3;

	// インターバルタイム４
	@ViewById(R.id.rscIntervalTime4)
	TextView tvIntervalTime4;

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
	private static final long VIB_TIME = 50;

	private Vibrator vib;

	private int currentSet;

	// ■イベント一覧
	// ・初期イベント
	// 　１、値をすべて初期化する
	// 　２．メッセージに「待機中」を表示する
	// 　３．ロールバック/終了ボタンを非活性
	// ・開始イベント
	// 　１．メッセージに「ワークアウト中」を表示する
	// 　２．カレントセットを1++
	// 　３．開始ボタンを非活性にする
	// 　４．終了ボタンを活性にする
	// 　５．セットナビゲータをセット
	// 　６．２セット目以降の場合、カウントをインターバルラベルにセットする
	// 　７．セットカウント開始
	// ・終了イベント
	// 　１．メッセージに「インターバル」を表示する
	// 　２．開始ボタンを活性にする
	// 　３．終了ボタンを非活性にする
	// 　４．カウントをカレントセットタイムラベルにセットする
	// 　５．インターバルカウント開始
	// ・ロールバックイベント
	// 　１．カレントセットが0以上の場合、カレントセットを1--
	// 　２、値をすべて初期化する
	// 　３．メッセージに「待機中」を表示する
	// ４．カレントセットが0の場合、ロールバックボタンを非活性
	// ・クリアイベント
	// 　初期イベントと同じ振る舞い

	@AfterViews()
	public void init() {
		// 　１、値をすべて初期化する
		clearData();
		currentSet = 0;

		// 　２．メッセージに「待機中」を表示する
		tvMsgBox.setText("待機中");

		// 　３．ロールバック/終了ボタンを非活性
		btStart.setEnabled(true);
		btEnd.setEnabled(false);
		btRollback.setEnabled(false);
		btClear.setEnabled(true);

		setNavigator(0);

		vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);

	}

	@Click(R.id.rscStart)
	public void execStart() {
		vib.vibrate(VIB_TIME);

		// 　１．メッセージに「ワークアウト中」を表示する
		tvMsgBox.setText("ワークアウト中");

		// 　２．カレントセットを1++
		currentSet++;

		// 　３．開始ボタンを非活性にする
		btStart.setEnabled(false);

		// 　４．終了ボタンを活性にする
		btEnd.setEnabled(true);

		// 　５．セットナビゲータをセット
		setNavigator(currentSet);

		// ５．１　２セット目移行の開始であればロールバックボタンを活性
		if (currentSet > 1) {
			btRollback.setEnabled(true);
		}

		// 　６．２セット目以降の場合、カウントをインターバルラベルにセットする
		if (currentSet == 2) {
			tvIntervalTime1.setText(tvIntervalTimer.getText());
		} else if (currentSet == 3) {
			tvIntervalTime2.setText(tvIntervalTimer.getText());
		} else if (currentSet == 4) {
			tvIntervalTime3.setText(tvIntervalTimer.getText());
		}

		// 　７．セットカウント開始
		tvIntervalTimer.setBase(android.os.SystemClock.elapsedRealtime());
		tvIntervalTimer.start();
	}

	@Click(R.id.rscEnd)
	public void execEnd() {
		vib.vibrate(VIB_TIME);

		// １．カウント停止
		tvIntervalTimer.stop();
		// time = SystemClock.elapsedRealtime() - tvIntervalTimer.getBase();

		// １．５ ワークアウト終了判断
		if (currentSet == 4 && !tvSetTime4.getText().equals(Constants.BLANK)) {
			// インターバルセット４の終了とみなす
			tvIntervalTime4.setText(tvIntervalTimer.getText());
			btStart.setEnabled(false);
			btEnd.setEnabled(false);
			btRollback.setEnabled(false);
			btClear.setEnabled(true);
			tvIntervalTimer.setBase(android.os.SystemClock.elapsedRealtime());
			setNavigator(0);
			return;
		}

		// 　２．メッセージに「インターバル」を表示する
		tvMsgBox.setText("インターバル");

		// 　３．開始ボタンを活性にする
		if (currentSet == 4) {
			btStart.setEnabled(false);
		} else {
			btStart.setEnabled(true);
		}

		// 　４．終了ボタンを非活性にする
		if (currentSet == 4) {
			btEnd.setEnabled(true);
		} else {
			btEnd.setEnabled(false);
		}

		// 　５．カウントをカレントセットタイムラベルにセットする
		if (currentSet == 1) {
			tvSetTime1.setText(tvIntervalTimer.getText());
		} else if (currentSet == 2) {
			tvSetTime2.setText(tvIntervalTimer.getText());
		} else if (currentSet == 3) {
			tvSetTime3.setText(tvIntervalTimer.getText());
		} else if (currentSet == 4) {
			tvSetTime4.setText(tvIntervalTimer.getText());
		}

		// 　６．インターバルカウント開始
		tvIntervalTimer.setBase(android.os.SystemClock.elapsedRealtime());
		tvIntervalTimer.start();

	}

	@Click(R.id.rscRollback)
	public void execRollback() {
		vib.vibrate(VIB_TIME);

		// １．カウント停止
		tvIntervalTimer.stop();
		// time = SystemClock.elapsedRealtime() - tvIntervalTimer.getBase();

		// ２．カレントセットが0以上の場合、カレントセットを1--
		if (currentSet > 0) {
			currentSet--;
		}
		// ３．カレントセット以降の値をすべて初期化する
		clearData(currentSet);
		// ４．メッセージに「待機中」を表示する
		tvMsgBox.setText("待機中");
		// ５．セットナビゲータをセット
		setNavigator(currentSet);
		// ６．カレントセットが0の場合、ロールバックボタンを非活性
		if (currentSet == 0) {
			btRollback.setEnabled(false);
		}
	}

	@Click(R.id.rscClear)
	public void execClear() {
		vib.vibrate(VIB_TIME);

		tvIntervalTimer.stop();
		// time = 0;

		// ・クリアイベント
		// 　初期イベントと同じ振る舞い
		init();

	}

	private void clearData() {
		tvMsgBox.setText(Constants.BLANK);
		// tvIntervalTimer.setText(Constants.BLANK);
		tvIntervalTimer.setBase(android.os.SystemClock.elapsedRealtime());
		tvSetTime1.setText(Constants.BLANK);
		tvSetTime2.setText(Constants.BLANK);
		tvSetTime3.setText(Constants.BLANK);
		tvSetTime4.setText(Constants.BLANK);
		tvIntervalTime1.setText(Constants.BLANK);
		tvIntervalTime2.setText(Constants.BLANK);
		tvIntervalTime3.setText(Constants.BLANK);
		tvIntervalTime4.setText(Constants.BLANK);
	}

	/**
	 * 値の削除
	 * 
	 * @param setCount
	 *            指定セット以降の数値データをクリアします
	 */
	private void clearData(int setCount) {
		tvMsgBox.setText(Constants.BLANK);
		// tvIntervalTimer.setText(Constants.BLANK);
		tvIntervalTimer.setBase(android.os.SystemClock.elapsedRealtime());
		if (setCount < 1) {
			tvSetTime1.setText(Constants.BLANK);
			tvIntervalTime1.setText(Constants.BLANK);
		}
		if (setCount < 2) {
			tvSetTime2.setText(Constants.BLANK);
			tvIntervalTime2.setText(Constants.BLANK);
		}
		if (setCount < 3) {
			tvSetTime3.setText(Constants.BLANK);
			tvIntervalTime3.setText(Constants.BLANK);
		}
		if (setCount < 4) {
			tvSetTime4.setText(Constants.BLANK);
			tvIntervalTime4.setText(Constants.BLANK);
		}
	}

	private void setNavigator(int currentSet) {
		tvNavi1.setBackgroundResource(R.color.main_color2);
		tvNavi2.setBackgroundResource(R.color.main_color2);
		tvNavi3.setBackgroundResource(R.color.main_color2);
		tvNavi4.setBackgroundResource(R.color.main_color2);
		if (currentSet == 1) {
			tvNavi1.setBackgroundResource(R.color.main_color3);
		} else if (currentSet == 2) {
			tvNavi2.setBackgroundResource(R.color.main_color3);
		} else if (currentSet == 3) {
			tvNavi3.setBackgroundResource(R.color.main_color3);
		} else if (currentSet == 4) {
			tvNavi4.setBackgroundResource(R.color.main_color3);
		}
	}

}
