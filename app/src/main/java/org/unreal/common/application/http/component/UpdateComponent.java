package org.unreal.common.application.http.component;

import com.github.aleksandermielczarek.napkin.scope.UserScope;

import org.unreal.common.application.http.module.UpdateRetrofitModule;
import org.unreal.common.core.component.NetComponent;
import org.unreal.common.update.UpdateManager;

import dagger.Component;

/**
 * <b>类名称：</b> UpdateComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 13:58<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@UserScope
@Component(modules = UpdateRetrofitModule.class,
        dependencies = {NetComponent.class})
public interface UpdateComponent {

    void inject(UpdateManager updateManager);
}
