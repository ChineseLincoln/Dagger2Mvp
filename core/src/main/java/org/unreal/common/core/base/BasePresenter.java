package org.unreal.common.core.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * <b>类名称：</b> BasePresenter <br/>
 * <b>类描述：</b> 空接口,仅用来限制范型<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月05日 10:07<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface BasePresenter {

    void bindLifeCycle(LifecycleTransformer<Object> objectLifecycleTransformer);
}
