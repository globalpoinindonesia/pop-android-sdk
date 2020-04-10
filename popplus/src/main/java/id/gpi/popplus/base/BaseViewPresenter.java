package id.gpi.popplus.base;

import android.view.View;

/**
 * Created by Ebizu-User on 13/07/2017.
 */

public abstract class BaseViewPresenter implements IBaseViewPresenter {

    private View view;

    @Override
    public void attachView(View view) {
        this.view = view;
    }
}
