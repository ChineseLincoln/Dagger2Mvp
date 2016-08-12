package org.unreal.dagger.data.http;

import org.unreal.dagger.data.http.local.retrofit.LocalRetrofit;
import org.unreal.dagger.data.http.taobao.retrofit.TaobaoRetrofit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * <b>类名称：</b> RetrofitModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午3:52<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class RetrofitModule {

    @Singleton
    @Provides
    public LocalRetrofit providerLocalRetrofit(@Named("default") OkHttpClient okHttpClient){
        return new LocalRetrofit(okHttpClient);
    }

    @Singleton
    @Provides
    public TaobaoRetrofit providerTaobaoRetrofit(@Named("cache") OkHttpClient okHttpClient){
        return new TaobaoRetrofit(okHttpClient);
    }
}
