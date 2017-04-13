package com.drawthink.telcom.quality.data.remote;


import android.app.Application;

import com.blankj.utilcode.utils.NetworkUtils;
import com.drawthink.telcom.quality.data.remote.qualifiers.DefaultClient;
import com.drawthink.telcom.quality.data.remote.qualifiers.LocalClient;
import com.drawthink.telcom.quality.data.remote.qualifiers.NoCacheClient;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.github.aleksandermielczarek.napkin.scope.SessionScope;
import com.ncornette.cache.OkCacheControl;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import static java.util.concurrent.TimeUnit.MINUTES;

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
@SessionScope
@Module
public class OkHttpModule {

    @Provides
    @SessionScope
    @DefaultClient
    public OkHttpClient providerDefaultOkHttpClient(Application application) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return OkCacheControl.on(
                new OkHttpClient.Builder()
                        .addNetworkInterceptor(interceptor)
                        .addNetworkInterceptor(new StethoInterceptor())
                        .retryOnConnectionFailure(true)
                        .connectTimeout(10, TimeUnit.SECONDS))
                .overrideServerCachePolicy(30, MINUTES)
                .forceCacheWhenOffline(NetworkUtils::isConnected)
                .apply() // return to the OkHttpClient.Builder instance
                .cache(new Cache(application.getCacheDir(),10*10*1024))
                .build();
    }

    @Provides
    @SessionScope
    @NoCacheClient
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
    @SessionScope
    @LocalClient
    public OkHttpClient providerLocalServiceOkHttpClient(Application application) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return OkCacheControl.on(
                new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .addNetworkInterceptor(new StethoInterceptor())
                .retryOnConnectionFailure(true)
                .connectTimeout(50, TimeUnit.SECONDS)
                .writeTimeout(50, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS))
                .overrideServerCachePolicy(30, MINUTES)
                .forceCacheWhenOffline(NetworkUtils::isConnected)
                .apply() // return to the OkHttpClient.Builder instance
                .cache(new Cache(application.getCacheDir(),10*10*1024))
                .build();
    }
}


