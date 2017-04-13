package com.drawthink.telcom.quality.function.main.module;

import com.drawthink.telcom.quality.function.main.contract.MainContract;
import com.drawthink.telcom.quality.function.main.presenter.MainPresenterImpl;
import com.github.aleksandermielczarek.napkin.scope.ActivityScope;

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
public class MainModule {

    private MainContract.View view;

    public MainModule(MainContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MainContract.View providerView(){
        return view;
    }

    @ActivityScope
    @Provides
    MainContract.Presenter providerPresenter(MainContract.View view){
        return new MainPresenterImpl(view);
    }
}
