package com.drawthink.telcom.quality.function.main.component;

import com.drawthink.telcom.quality.component.AppComponent;
import com.drawthink.telcom.quality.function.main.MainActivity;
import com.drawthink.telcom.quality.function.main.module.MainModule;
import com.github.aleksandermielczarek.napkin.scope.ActivityScope;

import dagger.Component;

/**
 * <b>类名称：</b> MainComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 15:35<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@ActivityScope
@Component(dependencies = AppComponent.class , modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
