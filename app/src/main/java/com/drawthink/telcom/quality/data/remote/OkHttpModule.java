package com.drawthink.telcom.quality.data.remote;


import android.app.Application;

import com.blankj.utilcode.utils.StringUtils;
import com.drawthink.telcom.quality.component.field.NetScoped;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * <b>类名称：</b> OkHttpModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年06月20日 上午10:53<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@NetScoped
@Module
public class OkHttpModule {

    @Provides
    @NetScoped
    @Named("default")
    public OkHttpClient providerDefaultOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);

            String cacheControl = request.cacheControl().toString();
            if (StringUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=" + 3600 * 6 + " ,max-stale=2419200";
            }
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        };
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    @NetScoped
    @Named("noCache")
    public OkHttpClient providerNoCacheOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }


    @Provides
    @NetScoped
    @Named("local")
    public OkHttpClient providerLocalServiceOkHttpClient(Application application) {
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);

            String cacheControl = request.cacheControl().toString();
            if (StringUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=60 ,max-stale=2419200";
            }
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        };
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}


