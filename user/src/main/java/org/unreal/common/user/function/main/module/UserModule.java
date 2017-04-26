package org.unreal.common.user.function.main.module;

import com.github.aleksandermielczarek.napkin.scope.UserScope;

import org.unreal.common.user.function.main.contract.UserContract;
import org.unreal.common.user.function.main.presenter.UserPresenterImpl;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> MainModule <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 15:35<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class UserModule {

    private UserContract.View view;

    public UserModule(UserContract.View view) {
        this.view = view;
    }

    @UserScope
    @Provides
    UserContract.View providerView(){
        return view;
    }

    @UserScope
    @Provides
    UserContract.Presenter providerPresenter(UserContract.View view){
        return new UserPresenterImpl(view);
    }

}
