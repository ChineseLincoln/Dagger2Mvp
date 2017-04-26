package org.unreal.common.core.http.covert;


import org.unreal.common.core.http.vo.LocalResponse;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * <b>类名称：</b> ResponseTransformer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:25<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class LocalResponseTransformer<T> implements SingleTransformer<Response<LocalResponse<T>>, T> {


    @Override
    public SingleSource<T> apply(@NonNull Single<Response<LocalResponse<T>>> upstream) {
        return upstream.subscribeOn(Schedulers.io())
                .flatMap(new ResponseFunc<>())
                .flatMap(new LocalResponseFunc<>())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
