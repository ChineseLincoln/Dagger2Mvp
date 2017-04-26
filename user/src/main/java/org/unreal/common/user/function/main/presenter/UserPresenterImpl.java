package org.unreal.common.user.function.main.presenter;

import org.unreal.common.core.base.BasePresenterImpl;
import org.unreal.common.core.component.NetComponent;
import org.unreal.common.core.http.covert.LocalResponseTransformer;
import org.unreal.common.user.http.component.DaggerUserHttpComponent;
import org.unreal.common.user.http.server.UserServer;
import org.unreal.common.user.function.main.contract.UserContract;

import javax.inject.Inject;

/**
 * <b>类名称：</b> UserPresenterImpl <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 17:05<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UserPresenterImpl
        extends BasePresenterImpl<UserContract.View>
        implements UserContract.Presenter {

    @Inject
    UserServer userServer;

    public UserPresenterImpl(UserContract.View view) {
        super(view);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        DaggerUserHttpComponent
                .builder()
                .netComponent(netComponent)
                .build()
                .inject(this);
    }

    @Override
    public void loadData() {
        userServer.login()
                .compose(new LocalResponseTransformer<>())
                .doOnSubscribe(disposable -> {
                    view.showWait();
                })
                .subscribe(user -> {

                }, throwable -> {
                    view.toast(throwable.getMessage());
                    view.hideWait(null);
                });
    }

}
