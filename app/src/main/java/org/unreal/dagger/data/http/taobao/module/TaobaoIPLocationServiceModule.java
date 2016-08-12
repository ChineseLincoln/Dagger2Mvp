package org.unreal.dagger.data.http.taobao.module;


import org.unreal.dagger.data.http.taobao.retrofit.TaobaoRetrofit;
import org.unreal.dagger.data.http.taobao.service.TaobaoIPLocationService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> IPLocaltionServer <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年06月22日 下午4:37<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

@Module
public class TaobaoIPLocationServiceModule {

    @Singleton
    @Provides
    public TaobaoIPLocationService proidverIPLocationServiceModule(TaobaoRetrofit taoBaoRetrofitClient) {
        return taoBaoRetrofitClient.getRetrofit().create(TaobaoIPLocationService.class);
    }
}
