package org.unreal.common.user.http.component;

import org.unreal.common.core.component.NetComponent;
import com.github.aleksandermielczarek.napkin.scope.UserScope;
import org.unreal.common.user.http.module.UserHttpModule;

import dagger.Component;

/**
 * <b>类名称：</b> UserHttpComponent <br/>
 * <b>类描述：</b> User Dagger2 Http Component<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017-4-26 13:51:52 <br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@UserScope
@Component(dependencies = NetComponent.class , modules = UserHttpModule.class)
public interface UserHttpComponent {
}

