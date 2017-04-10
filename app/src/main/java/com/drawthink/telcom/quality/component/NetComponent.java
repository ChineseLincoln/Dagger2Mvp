package com.drawthink.telcom.quality.component;

import com.drawthink.telcom.quality.data.remote.OkHttpModule;
import com.drawthink.telcom.quality.data.remote.RetrofitModule;
import com.drawthink.telcom.quality.component.field.NetScoped;
import com.drawthink.telcom.quality.function.main.presenter.MainPresenterImpl;

import dagger.Component;

/**
 * <b>类名称：</b> NetComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 16:23<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@NetScoped
@Component(dependencies = AppComponent.class,
        modules = {OkHttpModule.class,
                RetrofitModule.class})
public interface NetComponent {

    void inject(MainPresenterImpl mainPresenter);
}
