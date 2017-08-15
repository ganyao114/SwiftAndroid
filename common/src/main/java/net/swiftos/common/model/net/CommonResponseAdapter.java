package net.swiftos.common.model.net;

import net.swiftos.common.exception.HttpServiceException;
import net.swiftos.common.model.bean.BaseResponse;

import dagger.Module;

/**
 * Created by ganyao on 2017/3/15.
 */

public class CommonResponseAdapter<T> implements IResponseAdapter<BaseResponse<T>,T> {

    private static CommonResponseAdapter commonResponseAdapter = new CommonResponseAdapter();

    public static <M> CommonResponseAdapter<M> getInstance() {
        return commonResponseAdapter;
    }

    @Override
    public T adapter(BaseResponse<T> baseResponse) {
        int status = baseResponse.getStatus();
        String msg = baseResponse.getMessage();
        T data = baseResponse.getData();
        if (status != BaseResponse.SUCCESS) {
            throw new HttpServiceException(msg, status);
        }
        return data;
    }
}
