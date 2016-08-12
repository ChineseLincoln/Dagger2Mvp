package org.unreal.dagger.data.http.local.service;

import org.unreal.dagger.data.http.local.vo.User;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * <b>类名称：</b> UserService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午4:00<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface UserService {

    @GET("/users")
    Observable<Response<List<User>>> getUser();

    @GET("/users/{id}")
    Observable<Response<User>> getUserById(@Path("id") String id);
}
