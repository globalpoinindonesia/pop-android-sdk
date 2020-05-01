package id.gpi.popplussdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import id.gpi.popplus.CredLib;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "[MainActivity]";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Map<String, String> temp = new HashMap<>();
		temp.clear();

		temp.put("Satu", "1");
		temp.put("Dua", "2");
		temp.put("Tiga", "3");

		Fungsi.storeObjectToSavePref(getApplicationContext(), temp, "CobaDulu");

		String test = CredLib.DataProcess(CredLib.GetPassAuth());
		Log.d(TAG, "onCreate: " + test);
		Log.d(TAG, "onCreate: " +  CredLib.DataCheck(test));
	}
}
