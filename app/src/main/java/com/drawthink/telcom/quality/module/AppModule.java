package com.drawthink.telcom.quality.module;

import android.app.Application;

import com.blankj.utilcode.utils.SPUtils;
import com.drawthink.telcom.quality.data.local.preference.UserPreference;
import com.drawthink.telcom.quality.data.local.preference.field.App;
import com.drawthink.telcom.quality.data.local.preference.field.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> AppModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 14:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class AppModule {

    private Application appContext;

    public AppModule(Application appContext) {
        this.appContext = appContext;
    }

    @Singleton
    @Provides
    public Application providerApplication(){
        return appContext;
    }

    @Singleton
    @Provides
    @App
    public SPUtils providerAppSp(){
        return  new SPUtils("app");
    }

    @Singleton
    @Provides
    @User
    public SPUtils providerUserSp(){
        return  new SPUtils("user");
    }

    @Singleton
    @Provides
    public UserPreference providerUserPreference(@User SPUtils spUtils){
        return new UserPreference(spUtils);
    }

}
