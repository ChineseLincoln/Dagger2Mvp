package debug;

import org.unreal.common.core.base.BaseApplication;
import org.unreal.common.user.BuildConfig;

/**
 * <b>类名称：</b> UserApplication <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年05月16日 10:53<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UserApplication extends BaseApplication {
    @Override
    public boolean isDebug() {
        return BuildConfig.DEBUG;
    }
}
