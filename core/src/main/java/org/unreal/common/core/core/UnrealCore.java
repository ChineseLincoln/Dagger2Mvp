package org.unreal.common.core.core;

import android.app.Application;

import org.unreal.common.core.component.CoreComponent;
import org.unreal.common.core.component.DaggerCoreComponent;
import org.unreal.common.core.component.DaggerNetComponent;
import org.unreal.common.core.component.NetComponent;
import org.unreal.common.core.module.CoreModule;

/**
 * <b>类名称：</b> UnrealCore <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 14:04<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public final class UnrealCore{

    private static CoreComponent coreComponent;
    private static NetComponent netComponent;
    private static ActivityTaskManager taskManager;

    private UnrealCore() throws IllegalAccessException {
        throw new IllegalAccessException("can not new instance");
    }

    public static void init(Application application){
        if(coreComponent == null) {
            coreComponent = DaggerCoreComponent
                    .builder()
                    .coreModule(new CoreModule(application))
                    .build();
        }
        if(netComponent == null) {
            netComponent = DaggerNetComponent
                    .builder()
                    .coreComponent(coreComponent)
                    .build();
        }
    }

    public static CoreComponent getCoreComponent() {
        if(coreComponent == null){
            throw new IllegalStateException("Unreal Core need init at Application");
        }
        return coreComponent;
    }

    public static NetComponent getNetComponent() {
        if(netComponent == null){
            throw new IllegalStateException("Unreal Core need init at Application");
        }
        return netComponent;
    }
}