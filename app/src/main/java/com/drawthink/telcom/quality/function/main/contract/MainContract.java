package com.drawthink.telcom.quality.function.main.contract;

import com.drawthink.telcom.quality.function.BasePresenter;
import com.drawthink.telcom.quality.function.BaseView;

/**
 * <b>类名称：</b> MainContract <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 15:36<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface MainContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter{
        void loadData();

    }
}
