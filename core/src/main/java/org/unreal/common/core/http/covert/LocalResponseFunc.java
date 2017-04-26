package org.unreal.common.core.http.covert;

import org.unreal.common.core.http.vo.LocalResponse;

import io.reactivex.Single;
import io.reactivex.functions.Function;

/**
 * <b>类名称：</b> LocalResponseFunc <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

class LocalResponseFunc<T> implements Function<LocalResponse<T>, Single<T>> {
    @Override
    public Single<T> apply(LocalResponse<T> tResponse) {
        if (tResponse.getCode() == 200) {
            return Single.just(tResponse.getData());
        } else {
            return Single.error(new Exception(tResponse.getMsg()));
        }
    }
}
