package id.gpi.popplussdk;

//Author by Ignat.

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import id.gpi.popplus.CredLib;

@SuppressWarnings("deprecation")
public class Fungsi extends AppCompatActivity
{
	public static String getDate(String tgl, String DateTimeFormat, String DateFormat) {
		Date tanggal;
		String dateConvert = null;
		SimpleDateFormat form = new SimpleDateFormat(DateTimeFormat, Locale.US);

		try {
			DateFormat df = new SimpleDateFormat(DateFormat, Locale.US);
			tanggal = form.parse(tgl);
			dateConvert = df.format(tanggal);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dateConvert;
	}

	public static String getTime(String time, String DateTimeFormat, String TimeFormat) {
		Date tanggal;
		String dateConvert = null;
		SimpleDateFormat form = new SimpleDateFormat(DateTimeFormat, Locale.US);

		try {
			tanggal = form.parse(time);
			DateFormat tf = new SimpleDateFormat(TimeFormat, Locale.US);
			dateConvert = tf.format(tanggal);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateConvert;
	}

	public static String AndroidVersion()
	{
		String release = Build.VERSION.RELEASE;
		int sdkVersion = Build.VERSION.SDK_INT;
		return "Android SDK: " + sdkVersion + " (" + release +")";
	}

	public static String DeviceName()
	{
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;

		if (model.startsWith(manufacturer))
			return capitalize(model);
		else
			return capitalize(manufacturer) + " " + model;
	}

	private static String capitalize(String s)
	{
		if (s == null || s.length() == 0)
			return "";

		char first = s.charAt(0);

		if (Character.isUpperCase(first))
			return s;
		else
			return Character.toUpperCase(first) + s.substring(1);
	}

	public static void SimpanGambar(Bitmap bmpGambar, String strNamafile)
	{
		File file = new File(strNamafile);

		try
		{
			Log.d("", "SimpanGambar: ");

			file.createNewFile();

			FileOutputStream out = new FileOutputStream(file);
			bmpGambar.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		}
		catch (Exception e)
		{
			// e.printStackTrace();
		}
	}

	public static String TransaksiID()
	{
		Random random = new Random();
		String TransID = "";

		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

		for (int i=0; i<=18; i++) {
			int intTemp = random.nextInt(alphabet.length() - 2);
			TransID += alphabet.substring(intTemp, intTemp+1);
			System.out.println(alphabet.charAt(random.nextInt(alphabet.length())));
		}

//		return TransID;
		return UUID.randomUUID().toString();
	}

	public static void storeToSavePref(final Context context, String value, String key)
	{
		SharedPreferences.Editor editor = context.getSharedPreferences(myFixValue.strNamePref, Context.MODE_PRIVATE).edit();
		editor.putString(key, CredLib.DataProcess(value)).apply();
	}

	public static void storeToSavePref(final Context context, int value, String key)
	{
		SharedPreferences.Editor editor = context.getSharedPreferences(myFixValue.strNamePref, Context.MODE_PRIVATE).edit();
		editor.putString(key, CredLib.DataProcess(String.valueOf(value))).apply();
	}

	public static void storeObjectToSavePref(final Context context, Map<String, String> strTmp, String key)
	{
//		String strTmp = new Gson().toJson(object);
		String value = CredLib.DataProcess(strTmp.toString());

		SharedPreferences.Editor editor = context.getSharedPreferences(myFixValue.strNamePref, Context.MODE_PRIVATE).edit();
		editor.putString(key, value).commit();
	}

	public static String getStringFromSavePref(final Context context, String key)
	{
		SharedPreferences prefs = context.getSharedPreferences(myFixValue.strNamePref,  Context.MODE_PRIVATE);
		return CredLib.DataCheck(prefs.getString(key, ""));
	}

	public static int getIntFromSavePref(final Context context, String key)
	{
		SharedPreferences prefs = context.getSharedPreferences(myFixValue.strNamePref,  Context.MODE_PRIVATE);
		return Integer.parseInt(CredLib.DataCheck(prefs.getString(key, "")));
	}

	public static <GenericClass> GenericClass getObjectFromSavePref(Context context, Class<GenericClass> classType, String key)
	{
		SharedPreferences prefs = context.getSharedPreferences(myFixValue.strNamePref, Context.MODE_PRIVATE);

		if (prefs.contains(key))
			return new Gson().fromJson(CredLib.DataCheck(prefs.getString(key, "")), classType);

		return null;
	}
}
