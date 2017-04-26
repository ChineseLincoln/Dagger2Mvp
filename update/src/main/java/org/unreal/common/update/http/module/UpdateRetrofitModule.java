package org.unreal.common.update.http.module;

import com.github.aleksandermielczarek.napkin.scope.UserScope;

import org.unreal.common.core.http.qualifiers.NoCacheClient;
import org.unreal.common.update.http.server.FirServer;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * <b>类名称：</b> UpdateRetrofitModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 13:55<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class UpdateRetrofitModule {

    @UserScope
    @Provides
    public Retrofit providerFirRetrofit(@NoCacheClient OkHttpClient okHttpClient){
        String BASE_URL = "http://api.fir.im/";
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }

    @UserScope
    @Provides
    public FirServer providerFirServer(Retrofit retrofit){
        return retrofit.create(FirServer.class);
    }

}
