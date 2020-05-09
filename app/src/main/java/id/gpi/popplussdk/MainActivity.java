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
					Log.d("Username", "Username" + " : " + CredLib.UserAuth("FdPpKf/BD9U5Ele74jC3ys0/s3jV3e+uxHjrGIRGu7w="));
					Log.d("Password", "Password" + " : " + CredLib.PassAuth("Y7n53lj7S7qwzV3lvwvjonW5UWbyNqjBNO0XQ8FEAoY="));
					Log.d("DeviceRSN", "Device" + " : " + CredLib.DeviceRSN("123", "456", "789"));

					String strTest = CredLib.DataProcess(temp.toString(),
						"QAcDKvg2I95rdrguoH9TSwUCqJlO5k7151FJo31xc1Y=", "evxKx2TjABJFhnejymJNXjWajlEgFR3m8uQZaXEOBKo=");
					Log.d("Enkrip", "Endkrip ke " + (i+1) + " : " + strTest);
					Log.d("Dekrip", "Dekrip ke " + (i+1) + " : " + CredLib.DataCheck(strTest,
						"QAcDKvg2I95rdrguoH9TSwUCqJlO5k7151FJo31xc1Y=", "evxKx2TjABJFhnejymJNXjWajlEgFR3m8uQZaXEOBKo="));

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
