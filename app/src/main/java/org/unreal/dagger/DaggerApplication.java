package org.unreal.dagger;

import android.app.Application;
import android.content.Context;

/**
 * <b>类名称：</b> DaggerApplication <br/>
 * <b>类描述：</b> Application类<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:21<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class DaggerApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static DaggerApplication get(Context context) {
        return (DaggerApplication) context.getApplicationContext();
    }

    private void setupApplicationComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        if(appComponent == null){
            this.setupApplicationComponent();
        }
        return appComponent;
    }

}
