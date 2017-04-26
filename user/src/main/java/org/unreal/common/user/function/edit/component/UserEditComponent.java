package org.unreal.common.user.function.edit.component;

import org.unreal.common.core.component.CoreComponent;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;

import org.unreal.common.user.function.edit.UserEditActivity;
import org.unreal.common.user.function.edit.module.UserEditModule;

import dagger.Component;

/**
 * <b>类名称：</b> UserEditComponent <br/>
 * <b>类描述：</b> UserEdit Dagger2 Component<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017-4-26 15:18:57 <br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@ActivityScope
@Component(dependencies = CoreComponent.class, modules = UserEditModule.class)
public interface UserEditComponent {
    void inject(UserEditActivity UserEditActivity);
}

