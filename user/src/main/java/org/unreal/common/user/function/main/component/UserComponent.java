package org.unreal.common.user.function.main.component;

import com.github.aleksandermielczarek.napkin.scope.UserScope;

import org.unreal.common.core.component.CoreComponent;
import org.unreal.common.user.function.main.UserActivity;
import org.unreal.common.user.function.main.module.UserModule;

import dagger.Component;

/**
 * <b>类名称：</b> UserComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 16:42<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@UserScope
@Component(modules = UserModule.class, dependencies = CoreComponent.class)
public interface UserComponent {

    void inject(UserActivity userActivity);
}
