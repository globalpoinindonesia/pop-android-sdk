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

  static native byte[] DataDecrypt(String str, String PublicKey, String PublicVector);
  public static native String DataProcess(String str, String PublicKey, String PublicVector);
  public static native String DeviceRSN(String DeviceID, String Serial, String Imei);
  public static native String UserAuth(String PublicUsername);
  public static native String PassAuth(String PublicPassword);
//  public static native String Demonstration(String test1);

  public static String DataCheck(String Data, String PublicKey, String PublicVector)
  {
    String strTmp = new String(DataDecrypt(Data, PublicKey, PublicVector));
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(strTmp);
    stringBuilder.reverse();
    strTmp = stringBuilder.substring(0);

    strTmp = strTmp.substring(strTmp.indexOf("#") + 1);
    stringBuilder = new StringBuilder();
    stringBuilder.append(strTmp);
    stringBuilder.reverse();

    return stringBuilder.substring(0);
  }
}
