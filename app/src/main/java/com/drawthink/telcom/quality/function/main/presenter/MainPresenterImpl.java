package com.drawthink.telcom.quality.function.main.presenter;

import com.drawthink.telcom.quality.component.NetComponent;
import com.drawthink.telcom.quality.function.BasePresenterImpl;
import com.drawthink.telcom.quality.function.main.contract.MainContract;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Retrofit;

/**
 * <b>类名称：</b> MainPresenterImpl <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 17:05<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class MainPresenterImpl
        extends BasePresenterImpl<MainContract.View>
        implements MainContract.Presenter {

    public MainPresenterImpl(MainContract.View view) {
        super(view);
    }

    @Override
    public void injectNetComponent(NetComponent netComponent) {
        netComponent.inject(this);
    }

    @Override
    public void loadData() {
    }

}
