package org.unreal.common.core.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import org.unreal.common.core.core.UnrealCore;

/**
 * <b>类名称：</b> DebugApplication <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年05月16日 10:54<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UnrealCore.init(this);
        Utils.init(this);
        if (isDebug()) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }else {
            new LogUtils.Builder().setLogSwitch(false);
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }

    public abstract boolean isDebug();
}
