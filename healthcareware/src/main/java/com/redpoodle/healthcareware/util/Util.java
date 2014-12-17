package com.redpoodle.healthcareware.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Util {

	public static void showDialog(Activity activity, String message) {
		// 確認ダイアログの生成
		AlertDialog.Builder alertDlg = new AlertDialog.Builder(activity);
		alertDlg.setTitle("HealthcareWare");
		alertDlg.setMessage(message);
		alertDlg.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// 表示
		alertDlg.create().show();
	}

}
