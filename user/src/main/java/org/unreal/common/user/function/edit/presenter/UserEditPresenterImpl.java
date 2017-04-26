package org.unreal.common.user.function.edit.presenter;

import org.unreal.common.core.base.BasePresenterImpl;
import org.unreal.common.core.component.NetComponent;
import org.unreal.common.user.function.edit.contract.UserEditContract;

/**
 * <b>类名称：</b> UserEditPresenter 实现类<br/>
 * <b>类描述：</b> UserEdit Dagger2 Presenter Impl<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017-4-26 15:18:57 <br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UserEditPresenterImpl
        extends BasePresenterImpl<UserEditContract.View>
        implements UserEditContract.Presenter {

    public UserEditPresenterImpl(UserEditContract.View view) {
        super(view);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        DaggerUserHttpHttpComponent
                .builder()
                .netComponent(netComponent)
                .build()
                .inject(this);
    }
}

