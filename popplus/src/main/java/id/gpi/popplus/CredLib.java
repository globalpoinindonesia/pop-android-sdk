package id.gpi.popplus;

import android.util.Log;

public class CredLib
{
  private static final String TAG = "[POPPlusLib]";

  public CredLib()
  {
  }

  static
  {
    try
    {
      Log.d(TAG,"Load POPPlus NDK started....");
      System.loadLibrary("POPPlusNDK");
      Log.d(TAG,"Load POPPlus NDK finished");
    }
    catch (UnsatisfiedLinkError ule)
    {
      Log.d(TAG, "Load POPPlus NDK failed");
    }
  }

  public static native String DataCheck(String str);
  public static native String DataProcess(String str);
  public static native String DeviceRSN(String DeviceID, String Serial, String Imei);
  public static native String UserAuth();
  public static native String PassAuth();
}
