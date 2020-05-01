package id.gpi.popplussdk;

/**
 * Created by ignat on 16-Jun-16.
 */
public class myFixValue {
	public static final String strNamePref = "id.mygetplus.getpluspos.pref";

	// Base URL to POS API
//	public static final String POP_URL = "http://192.168.237.2:31013/";
	public static final String POP_URL = "http://192.168.137.1:31013/";
//	public static final String POP_URL = "https://dev-popplus.gpiapis.com/";
//	public static final String POP_URL = "https://dev-popplus.gpiapis.com/";
//	public static final String POP_URL = "https://popplus.gpiapis.com/";

	// Route credentials path to POS API
	public static final String PathCredentials = "/credential/v2/202003";

	// Route account path to POS API
	public static final String PathAccount = "/pos/v2/202003";

	// Route path auth to POS API
	public static final String PathGetToken = "/auth/gettoken";

	// Route path account to POS API
	public static final String PathUserAuth = "/account/login";
	public static final String PathUserLogout = "/account/logout";

	// Route path voucher transaction to POS API
	public static final String PathVoucherTrans = "/transaction/vouchertrans/{PersonalID}";

	// Route path partner to POS API
	public static final String PathPartner = "/partner/partnertoken/{AccountID}";

	//Network Connectivity
	public static final int TimeoutConnection = 45000;
	public static final int TYPE_NONE = 0;
	public static final int TYPE_WIFI = 1;
	public static final int TYPE_MOBILE = 2;

	public static final int intSuccess = 0;
	public static final int intError = -1;
	public static final int intEmpty = -2;

	public static final String BasicUser = "602027FB561E08105A23F098B4488D0B97AD53265E1BF58522D79A535A4FF715";
	public static final String BasicPass = "8FDA2B4A2E4675F7379FE33CAA22DA8B0892E8EFFE2F09F8268E208CA5818FA8";
}
