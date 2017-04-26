package org.unreal.common.user.function.edit;

import org.unreal.common.user.R;
import org.unreal.common.core.base.BaseActivity;
import org.unreal.common.core.component.CoreComponent;
import org.unreal.common.user.function.edit.component.DaggerUserEditComponent;
import org.unreal.common.user.function.edit.contract.UserEditContract;
import org.unreal.common.user.function.edit.module.UserEditModule;

/**
 * <b>类名称：</b> UserEditActivity <br/>
 * <b>类描述：</b> UserEdit Dagger2 Activity<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017-4-26 15:18:57 <br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UserEditActivity
        extends BaseActivity<UserEditContract.Presenter>
        implements UserEditContract.View {

    @Override
    protected void injectDagger(CoreComponent coreComponent) {
        //自动生成Dagger2注入代码,不要删除
        DaggerUserEditComponent.builder()
                .coreComponent(coreComponent)
                .userEditModule(new UserEditModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_user_edit;

    }

    @Override
    public void afterViews() {
        //todo 此处处理界面逻辑
    }

}
