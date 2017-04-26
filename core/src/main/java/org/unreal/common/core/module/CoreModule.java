package org.unreal.common.core.module;

import android.app.Application;

import com.blankj.utilcode.util.SPUtils;
import com.github.aleksandermielczarek.napkin.scope.AppScope;

import org.unreal.common.core.preference.AppPreference;
import org.unreal.common.core.preference.EncryptionPreference;
import org.unreal.common.core.preference.field.App;
import org.unreal.common.core.preference.field.Encryption;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> CoreModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 14:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@AppScope
@Module
public class CoreModule {

    private Application appContext;

    public CoreModule(Application appContext) {
        this.appContext = appContext;
    }

    @AppScope
    @Provides
    public Application providerApplication(){
        return appContext;
    }

    @AppScope
    @Provides
    @App
    public SPUtils providerAppSp(){
        return  new SPUtils("app");
    }

    @AppScope
    @Provides
    @Encryption
    public SPUtils providerUserSp(){
        return  new SPUtils("encryption");
    }

    @AppScope
    @Provides
    public EncryptionPreference providerEncryptionPreference(@Encryption SPUtils spUtils){
        return new EncryptionPreference(spUtils);
    }

    @AppScope
    @Provides
    public AppPreference providerAppPreference(@App SPUtils spUtils){
        return new AppPreference(spUtils);
    }

}
