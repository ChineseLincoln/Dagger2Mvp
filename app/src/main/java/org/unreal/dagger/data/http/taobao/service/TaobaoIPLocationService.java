package org.unreal.dagger.data.http.taobao.service;


import org.unreal.dagger.data.http.taobao.vo.TaobaoIPLocationInfo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <b>类名称：</b> TaobaoIPLocationService <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年05月23日 下午2:39<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface TaobaoIPLocationService {

    @GET("/service/getIpInfo2.php")
    Observable<TaobaoIPLocationInfo> getIPInfo(@Query("ip") String ip);
}
