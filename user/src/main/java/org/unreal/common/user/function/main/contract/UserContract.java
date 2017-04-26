package org.unreal.common.user.function.main.contract;

import org.unreal.common.core.base.BasePresenter;
import org.unreal.common.core.base.BaseView;

/**
 * <b>类名称：</b> UserContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 16:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface UserContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter {
        void loadData();

    }
}
