package com.drawthink.telcom.quality.function;

import android.app.Application;

import com.drawthink.telcom.quality.component.AppComponent;
import com.drawthink.telcom.quality.component.DaggerAppComponent;
import com.drawthink.telcom.quality.module.AppModule;

/**
 * <b>类名称：</b> QualityApplication <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 14:47<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class QualityApplication extends Application{

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        appComponent =
                DaggerAppComponent.builder().
                        appModule(new AppModule(this))
                        .build();
    }

    public static AppComponent getAppComponent() {
        if(appComponent!=null) {
            return appComponent;
        }
        throw new NullPointerException("Application appComponent is Null");
    }
}
