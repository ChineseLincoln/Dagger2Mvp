package org.unreal.common.user.function.http.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;

import org.unreal.common.core.http.qualifiers.DefaultClient;
import org.unreal.common.user.function.http.server.UserServer;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

/**
 * <b>类名称：</b> UserHttpModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 17:13<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class UserHttpModule {

    @ActivityScope
    @Provides
    public Retrofit providerUserRetrofit(@DefaultClient OkHttpClient okHttpClient){
        String BASE_URL = "http://192.168.0.186:8082/HospitalServer/";
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(FastJsonConverterFactory.create())
                .build();
    }

    @ActivityScope
    @Provides
    public UserServer providerUserServer(Retrofit retrofit){
        return retrofit.create(UserServer.class);
    }
}
