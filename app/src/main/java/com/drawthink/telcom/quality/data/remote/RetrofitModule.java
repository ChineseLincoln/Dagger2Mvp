package com.drawthink.telcom.quality.data.remote;


import com.drawthink.telcom.quality.data.remote.qualifiers.DefaultClient;
import com.drawthink.telcom.quality.data.remote.qualifiers.LocalClient;
import com.drawthink.telcom.quality.data.remote.qualifiers.LocalRetrofit;
import com.drawthink.telcom.quality.data.remote.qualifiers.TaoBaoRetrofit;
import com.github.aleksandermielczarek.napkin.scope.SessionScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * <b>类名称：</b> RetrofitModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年06月20日 上午10:51<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@SessionScope
@Module
public class RetrofitModule {

    @Provides
    @SessionScope
    @TaoBaoRetrofit
    public Retrofit providerTaoBaoRetrofitClient(@DefaultClient OkHttpClient okHttpClient) {
        String TAOBAO_BASE_URL = "http://ip.taobao.com/";
        return new Retrofit.Builder()
                .baseUrl(TAOBAO_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }

    @Provides
    @SessionScope
    @LocalRetrofit
    public Retrofit providerLocalJsonRetrofitClient(@LocalClient OkHttpClient okHttpClient) {
        String LOCAL_BASE_URL = "http://192.168.0.186:8082/HospitalServer/";
//        String LOCAL_BASE_URL = "http://218.203.104.60:8089/HospitalServer/";
        return new Retrofit.Builder()
                .baseUrl(LOCAL_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }


}
