package id.gpi.popplus;

import android.content.Context;
import android.view.View;

import id.gpi.popplus.base.BaseViewPresenter;
import id.gpi.popplus.service.POSLink;

public class CredPres extends BaseViewPresenter implements Credentials.Presenter {
	private static final String TAG = "Credentials";
	private Context context;
	private Credentials.View view;

/*
	public static String getToken()
	{
		return "Library berhasil";
	}
*/

	public CredPres(Context context, Credentials.View view) {
		this.context = context;
		this.view = view;
	}

	@Override
	public void attachView(View view) {
		super.attachView(view);
	}

	@Override
	public void getTokenSend(POSLink posLink) {

	}
}
