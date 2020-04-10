package id.gpi.popplus.service;

import android.util.Log;

public class GetPlusLib
{
  private static final String TAG = "[GetPlusLib]";

  public GetPlusLib()
  {
  }

  static
  {
    try
    {
      Log.d(TAG,"Loads GetPlusLib started....");
      System.loadLibrary("GetPlusNDK");
      Log.d(TAG,"Load GetPlusLib finished");
    }
    catch (UnsatisfiedLinkError ule)
    {
      Log.d(TAG, "Loads GetPlusLib failed");
    }
  }

  public static native double Version();
  public static native String EncryptionProcess();
  public static native String GetRSNProcess(String DeviceID, String Serial, String Imei);
}
