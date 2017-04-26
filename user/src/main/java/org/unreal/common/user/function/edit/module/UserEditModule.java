package org.unreal.common.user.function.edit.module;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;

import org.unreal.common.user.function.edit.contract.UserEditContract;
import org.unreal.common.user.function.edit.presenter.UserEditPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> UserEditModule <br/>
 * <b>类描述：</b> UserEdit Dagger2 Module<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017-4-26 15:18:57 <br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class UserEditModule {

    private UserEditContract.View view;

    public UserEditModule(UserEditContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    UserEditContract.View providerView() {
        return view;
    }

    @ActivityScope
    @Provides
    UserEditContract.Presenter providerPresenter(UserEditContract.View view) {
        return new UserEditPresenterImpl(view);
    }
}

