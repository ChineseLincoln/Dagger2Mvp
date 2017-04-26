package org.unreal.common.update.http.server;

import org.unreal.common.update.http.vo.OnlineAppInfo;

import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * <b>类名称：</b> FirServer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 14:31<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface FirServer {

    @GET("apps/latest/{appId}")
    Single<Response<OnlineAppInfo>> checkUpdateState(
            @Path("appId") String appId,
            @Query("api_token") String apiToken,
            @Query("type") String type);
}
