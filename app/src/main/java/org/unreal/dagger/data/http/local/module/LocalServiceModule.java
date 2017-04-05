package org.unreal.dagger.data.http.local.module;

import org.unreal.dagger.data.http.local.retrofit.LocalRetrofit;
import org.unreal.dagger.data.http.local.service.UserService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> LocalServiceModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午3:59<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class LocalServiceModule {

    @Singleton
    @Provides
    public UserService providerUserService(LocalRetrofit retrofit){
        return retrofit.getRetrofit().create(UserService.class);
    }
}
