package org.unreal.common.core.component;

import com.github.aleksandermielczarek.napkin.scope.SessionScope;


import org.unreal.common.core.http.OkHttpModule;
import org.unreal.common.core.http.qualifiers.DefaultClient;
import org.unreal.common.core.http.qualifiers.HttpsClient;
import org.unreal.common.core.http.qualifiers.NoCacheClient;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * <b>类名称：</b> NetComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 16:23<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@SessionScope
@Component(dependencies = CoreComponent.class,
        modules = {OkHttpModule.class})
public interface NetComponent {

    @DefaultClient
    OkHttpClient defaultClient();

    @HttpsClient
    OkHttpClient httpsClient();

    @NoCacheClient
    OkHttpClient noCacheClient();

}
