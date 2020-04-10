package id.gpi.popplus;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import android.view.View;

import androidx.core.app.ActivityCompat;

import id.gpi.popplus.base.BaseViewPresenter;
import id.gpi.popplus.base.ResponseSubscriber;
import id.gpi.popplus.common.Fungsi;
import id.gpi.popplus.model.CredentialsData;
import id.gpi.popplus.model.DeviceData;
import id.gpi.popplus.model.ResponsePojo;
import id.gpi.popplus.service.POSLink;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CredPres extends BaseViewPresenter implements Credentials.Presenter {
	private static final String TAG = "Credentials";
	private Context context;
	private Credentials.View view;

	public CredPres(Context context, Credentials.View view) {
		this.context = context;
		this.view = view;
	}

	@Override
	public void getTokenSend(POSLink posLink)
	{
		TelephonyManager telephonyManager = (TelephonyManager)
			context.getSystemService(Context.TELEPHONY_SERVICE);

		if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
			!= PackageManager.PERMISSION_GRANTED) {
			return;
		}

		String deviceId = telephonyManager.getDeviceId();
		String serial = telephonyManager.getSimSerialNumber();
		String imei = telephonyManager.getImei();

		DeviceData deviceData = new DeviceData();
		deviceData.setBrand(Fungsi.DeviceName());
		deviceData.setImei("000000000000000");
		deviceData.setSerial("000000000000000");
		deviceData.setDeviceID("000000000000000");
		deviceData.setOS(Fungsi.AndroidVersion());
//		deviceData.setRSN(GetPlusLib.GetRSNProcess(deviceId, serial, imei));
		deviceData.setRSN("Test1234567890");
		deviceData.setScope("Mobile POS");

		posLink.GetTokenService(deviceData).subscribeOn(Schedulers.io())
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(new ResponseSubscriber<ResponsePojo>() {
				@Override
				public void onError(Throwable throwable) {
					super.onError(throwable);
					if((throwable. getMessage().toLowerCase().contains("failed to connect to")) ||
						(throwable.getMessage().toLowerCase().contains("sockettimeoutexception")) ||
						(throwable.getMessage().toLowerCase().contains("unable to resolve host")))
					{
						ResponsePojo responsePojo = new ResponsePojo();
						responsePojo.setaFaultCode("-1");
						responsePojo.setaFaultDescription(throwable.getMessage());
						view.getTokenRsp(responsePojo);
					}
					else
						view.getTokenRsp(CredentialsData.getInstance().getResponsePojo());
				}

				@Override
				public void onNext(ResponsePojo responsePojo) {
					super.onNext(responsePojo);
					view.getTokenRsp(CredentialsData.getInstance().getResponsePojo());
				}
			});
	}
}
