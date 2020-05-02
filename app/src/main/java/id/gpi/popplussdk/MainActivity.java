package id.gpi.popplussdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import id.gpi.popplus.CredLib;

public class MainActivity extends AppCompatActivity {
	private static final String TAG = "[MainActivity]";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final Map<String, String> temp = new HashMap<>();
		temp.clear();

		temp.put("Satu", "1");
		temp.put("Dua", "2");
		temp.put("Tiga", "3");

		TextView tvHello = findViewById(R.id.tvHello);

		tvHello.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Fungsi.storeObjectToSavePref(getApplicationContext(), temp, "CobaDulu");
				String test = CredLib.DataProcess(temp.toString());
				Log.d(TAG, "onCreate: " + test);
				Log.d(TAG, "onCreate: " +  CredLib.DataCheck(test));
			}
		});
	}
}
