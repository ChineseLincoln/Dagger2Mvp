package org.unreal.dagger.data.http.local.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * <b>类名称：</b> TaobaoRetrofit <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午3:56<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class LocalRetrofit {
    private static final String BASE_URL = "http://xxxxxx.xxx.xxxx/";
    private static Retrofit retrofit;

    public LocalRetrofit(OkHttpClient okHttpClient) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofit() {
        return retrofit;
    }
}
