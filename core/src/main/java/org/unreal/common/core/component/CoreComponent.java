package org.unreal.common.core.component;

import android.app.Application;

import com.github.aleksandermielczarek.napkin.scope.AppScope;


import org.unreal.common.core.module.CoreModule;
import org.unreal.common.core.preference.AppPreference;
import org.unreal.common.core.preference.EncryptionPreference;

import dagger.Component;

/**
 * <b>类名称：</b> CoreComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 14:47<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@AppScope
@Component(modules = CoreModule.class)
public interface CoreComponent {

    Application application();

    EncryptionPreference encryptionPreference();

    AppPreference appPreference();

}
