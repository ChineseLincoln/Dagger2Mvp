package org.unreal.dagger.function.main.component;

import org.unreal.dagger.ActivityScope;
import org.unreal.dagger.function.main.MainActivity;
import org.unreal.dagger.function.main.module.MainModule;

import dagger.Subcomponent;

/**
 * <b>类名称：</b> MainComponent <br/>
 * <b>类描述：</b> Component <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:43<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@ActivityScope
@Subcomponent(modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
