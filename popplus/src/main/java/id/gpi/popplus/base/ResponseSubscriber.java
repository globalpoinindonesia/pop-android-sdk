package id.gpi.popplus.base;

import androidx.annotation.CallSuper;

import id.gpi.popplus.model.ResponsePojo;
import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by Ebizu-User on 07/07/2017.
 */
public class ResponseSubscriber<WP extends ResponsePojo> extends Subscriber<WP> {

    private final String TAG = "Manis Api Response : ";
//    private BaseActivity baseActivity;
//    private BaseView baseView;


//    public ResponseSubscriber(BaseActivity baseActivity) {
//        this.baseActivity = baseActivity;
//    }

//    public ResponseSubscriber(BaseView baseView) {
//        this.baseView = baseView;
//    }


    @Override
    public void onCompleted() {

    }

    @Override
    @CallSuper
    public void onError(Throwable throwable) {
        Throwable error = throwable;
        try {
            if (throwable instanceof HttpException) {
                HttpException httpException = (HttpException) error;
//                ErrorResponse errorResponse = new Gson().fromJson(httpException.response().errorBody().string(), ErrorResponse.class);
                String errorTokenIsRequired = "Token is required".trim().toLowerCase();
                String errorTokenExpired = "Field 'session' is required, can't be empty".trim().toLowerCase();
//                String errorMessage = errorResponse.getMessage().trim().toLowerCase();
                String errorLoggedOut = "hmmm...that's weird. you seem to have been logged out. please login again.".trim().toLowerCase();
//                if (errorMessage.equals(errorTokenIsRequired) || errorMessage.equals(errorTokenExpired) || errorMessage.equals(errorLoggedOut)) {
//                    autoLogout(errorLoggedOut);
//                    return;
//                }
//                onErrorFailure(errorResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void onErrorFailure(ErrorResponse errorResponse) {
//
//    }

    @Override
    @CallSuper
    public void onNext(WP wp) {
    }

//    private void autoLogout(String messageError) {
//        if (null != baseActivity) {
//            baseActivity.showAlertDialog(baseActivity.getString(R.string.error), messageError, false,
//                    R.drawable.manis_logo, "Ok", (dialogInterface, i) -> {
//                        baseActivity.signOut();
//                    });
//        } else if (null != baseView) {
//            baseView.getBaseActivity().showAlertDialog(baseView.getContext().getString(R.string.error), messageError, false,
//                    R.drawable.manis_logo, "Ok", (dialogInterface, i) -> {
//                        baseView.getBaseActivity().signOut();
//                    });
//        } else if (null != baseDialogManis) {
//            baseDialogManis.getBaseActivity().showAlertDialog(baseDialogManis.getContext().getString(R.string.error), messageError, false,
//                    R.drawable.manis_logo, "Ok", (dialogInterface, i) -> {
//                        baseDialogManis.getBaseActivity().signOut();
//                    });
//        }
//    }
}