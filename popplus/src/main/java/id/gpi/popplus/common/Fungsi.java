package id.gpi.popplus.common;

//Author by Ignat.

import android.os.Build;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fungsi
{

	public static String FormatDesimal(Integer intSource)
	{
		DecimalFormat fmt = new DecimalFormat();
		DecimalFormatSymbols fmts = new DecimalFormatSymbols();

		fmts.setGroupingSeparator('.');
		fmt.setGroupingSize(3);
		fmt.setGroupingUsed(true);
		fmt.setDecimalFormatSymbols(fmts);
		return fmt.format(intSource);
	}

	public static String FormatLongDesimal(Long intSource)
	{
		DecimalFormat fmt = new DecimalFormat();
		DecimalFormatSymbols fmts = new DecimalFormatSymbols();

		fmts.setGroupingSeparator('.');
		fmt.setGroupingSize(3);
		fmt.setGroupingUsed(true);
		fmt.setDecimalFormatSymbols(fmts);
		return fmt.format(intSource);
	}

	public static String getDate(String tgl, String DateTimeFormat, String DateFormat) {
		Date tanggal;
		String dateConvert = null;
		SimpleDateFormat form = new SimpleDateFormat(DateTimeFormat, Locale.US);

		try {
			java.text.DateFormat df = new SimpleDateFormat(DateFormat, Locale.US);
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
}
