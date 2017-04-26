package org.unreal.common.core.base;

import com.trello.rxlifecycle2.LifecycleTransformer;

import org.unreal.common.core.component.NetComponent;
import org.unreal.common.core.core.UnrealCore;

/**
 * <b>类名称：</b> BasePresenterImpl <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 16:27<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter {

    protected V view;

    private LifecycleTransformer<Object> objectLifecycleTransformer;

    public BasePresenterImpl(V view){
        this.view = view;
        injectNetComponent(UnrealCore.getNetComponent());
    }

    public abstract void injectNetComponent(NetComponent netComponent);

    @Override
    public void bindLifeCycle(LifecycleTransformer<Object> objectLifecycleTransformer) {
        this.objectLifecycleTransformer = objectLifecycleTransformer;
    }
}