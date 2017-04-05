package org.unreal.dagger.function.main.contract;

import org.unreal.dagger.data.http.taobao.vo.TaobaoIPLocationInfo;

/**
 * <b>类名称：</b> MainContract <br/>
 * <b>类描述：</b> 关联接口类 声明View 和 Presenter的方法<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月11日 下午5:42<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface MainContract {
    interface View{

        void showLocationInfo(TaobaoIPLocationInfo taobaoIPLocationInfo);

        void showError(String message);
    }

    interface  presenter{

    }
}
