package id.gpi.popplus;

import id.gpi.popplus.base.BaseViewPresenter;
import id.gpi.popplus.service.POSLink;

public class CredPres extends BaseViewPresenter implements Credentials.Presenter {
	private static final String TAG = "Credentials";

	public static String getToken()
	{
		return "Library berhasil";
	}

	@Override
	public void getTokenSend(POSLink posLink) {

	}
}
