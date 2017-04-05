package org.unreal.dagger.function.main.module;

import org.unreal.dagger.ActivityScope;
import org.unreal.dagger.function.main.contract.MainContract;

import dagger.Module;
import dagger.Provides;

/**
 * <b>类名称：</b> MainModule <br/>
 * <b>类描述：</b> Module 类<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:45<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Module
public class MainModule {

    private MainContract.View view;

    public MainModule(MainContract.View view){
        this.view = view;
    }

    @ActivityScope
    @Provides
    public MainContract.View providerView(){
        return view;
    }

}
