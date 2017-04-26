package org.unreal.common.user.function.http.component;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;

import org.unreal.common.core.component.NetComponent;
import org.unreal.common.user.function.http.module.UserHttpModule;
import org.unreal.common.user.function.main.presenter.UserPresenterImpl;

import dagger.Component;

/**
 * <b>类名称：</b> UserHttpComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 17:11<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@ActivityScope
@Component(modules = UserHttpModule.class , dependencies = NetComponent.class)
public interface UserHttpComponent {


    void inject(UserPresenterImpl userPresenter);
}
