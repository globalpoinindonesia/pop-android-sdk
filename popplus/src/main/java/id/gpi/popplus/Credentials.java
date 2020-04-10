package id.gpi.popplus;

import id.gpi.popplus.base.IBaseViewPresenter;
import id.gpi.popplus.model.ResponsePojo;
import id.gpi.popplus.service.POSLink;

public interface Credentials {

    interface Presenter extends IBaseViewPresenter {
        void getTokenSend(POSLink posLink);
    }

    interface View {
        void getTokenRsp(ResponsePojo responsePojo);
    }
}
