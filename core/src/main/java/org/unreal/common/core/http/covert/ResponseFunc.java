package org.unreal.common.core.http.covert;

import io.reactivex.Single;
import io.reactivex.functions.Function;
import retrofit2.Response;

/**
 * <b>类名称：</b> ResponseFunc <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 下午3:14<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class ResponseFunc<T> implements Function<Response<T>, Single<T>> {

    @Override
    public Single<T> apply(Response<T> tResponse) {
        if (tResponse.code() == 200) {
            return Single.just(tResponse.body());
        } else {
            if (tResponse.code() == 400) {
                return Single.error(new Exception("请求参数不完整"));
            } else if (tResponse.code() == 403) {
                return Single.error(new Exception("鉴权失败"));
            } else if (tResponse.code() == 404) {
                return Single.error(new Exception("资源未找到"));
            } else if (tResponse.code() == 500) {
                return Single.error(new Exception("服务器错误，请联系管理员"));
            } else {
                return Single.error(new Exception("未知错误，错误代码" + tResponse.code()));
            }
        }
    }
}
