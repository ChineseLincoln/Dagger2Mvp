package org.unreal.common.user.http.server;

import org.unreal.common.core.http.vo.LocalResponse;
import org.unreal.common.user.http.vo.User;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * <b>类名称：</b> UserServer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 17:19<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface UserServer {

    @GET("login")
    Single<Response<LocalResponse<User>>> login();
}
