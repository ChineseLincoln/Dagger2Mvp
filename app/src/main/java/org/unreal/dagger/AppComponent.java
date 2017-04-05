package org.unreal.dagger;

import org.unreal.dagger.data.http.OkhttpModule;
import org.unreal.dagger.data.http.RetrofitModule;
import org.unreal.dagger.data.http.local.module.LocalServiceModule;
import org.unreal.dagger.data.http.taobao.module.TaobaoIPLocationServiceModule;
import org.unreal.dagger.function.main.component.MainComponent;
import org.unreal.dagger.function.main.module.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <b>类名称：</b> AppComponent <br/>
 * <b>类描述：</b> 类似与Spring的Context上下文，提供依赖注入的对象<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:22<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Singleton
@Component(modules = {AppModule.class,
        OkhttpModule.class,
        RetrofitModule.class,
        LocalServiceModule.class,
        TaobaoIPLocationServiceModule.class})
public interface AppComponent {
    MainComponent addSub(MainModule mainModule);
}
