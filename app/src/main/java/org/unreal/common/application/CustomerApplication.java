package org.unreal.common.application;

import org.unreal.common.core.base.BaseApplication;
import org.unreal.common.core.core.UnrealCore;

/**
 * <b>类名称：</b> CustomerApplication <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 15:47<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class CustomerApplication extends BaseApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        UnrealCore.init(this);

    }

    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
