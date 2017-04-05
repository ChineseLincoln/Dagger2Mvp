package org.unreal.dagger;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> AppModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:23<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class AppModule {

    private Context context;

    public AppModule(DaggerApplication application) {
        this.context = application;
    }

    @Singleton
    @Provides
    public Context ProviderApplicationContext(){
        return context;
    }

    @Singleton
    @Provides
    @Named("default")
    public SharedPreferences providerDefaultSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Singleton
    @Provides
    @Named("encode")
    public SharedPreferences providerEncodeSharedPreferences(){
        return context.getSharedPreferences("encode",Context.MODE_PRIVATE);
    }

}
