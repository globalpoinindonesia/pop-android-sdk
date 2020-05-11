package id.gpi.popplussdk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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
				for(int i=0; i<150; i++)
				{
//					Log.d("Decode", "Decode" + " : " + CredLib.Demonstration("==QVT50NXlVVCVENFV0cz4UN"));

					Log.d("Username", "Username" + " : " + CredLib.UserAuth("012957d7ba2c6afe38d6eca8d58b3cfa"));
					Log.d("Password", "Password" + " : " + CredLib.PassAuth("bd6ab01f261ee05ebccfe19108f995d9"));
					Log.d("DeviceRSN", "Device" + " : " + CredLib.DeviceRSN("123", "456", "789"));

					String strTest = CredLib.DataProcess(temp.toString(),
						"==QVT50NXlVVCVENFV0cz4UN", "=IkS1UFb0EEa");
					Log.d("Enkrip", "Endkrip ke " + (i+1) + " : " + strTest);
					Log.d("Dekrip", "Dekrip ke " + (i+1) + " : " + CredLib.DataCheck(strTest,
						"==QVT50NXlVVCVENFV0cz4UN", "=IkS1UFb0EEa"));

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
}
