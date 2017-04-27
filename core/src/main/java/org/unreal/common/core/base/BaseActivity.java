package org.unreal.common.core.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import org.unreal.common.core.component.CoreComponent;
import org.unreal.common.core.core.ActivityTaskManager;
import org.unreal.common.core.core.UnrealCore;
import org.unreal.widget.window.WaitScreen;

import java.util.Stack;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * <b>类名称：</b> BaseActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 17:26<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BaseActivity<P extends BasePresenter>
        extends RxAppCompatActivity
        implements BaseView{

    @Inject
    public P presenter;

    private Unbinder unbinder;

    Stack<WaitScreen> waitScreens = new Stack<>();

    Handler handler = new Handler();

    public CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
        if(isCustomerView()){
            setContentView(bindLayout());
        }
        unbinder = ButterKnife.bind(this);
        injectDagger(UnrealCore.getCoreComponent());
        if(presenter != null) {
            presenter.bindLifeCycle(
                    RxLifecycleAndroid.bindActivity(lifecycle()));
        }
        afterViews();
        ActivityTaskManager.getInstance().pushActivity(this);
    }


    protected abstract void injectDagger(CoreComponent coreComponent);

    @Override
    public void showWait() {
        WaitScreen waitScreen = new WaitScreen(this);
        waitScreens.push(waitScreen);
        waitScreen.show();
        handler.postDelayed(waitScreen::show, 200);
    }

    @Override
    public void showWait(String message) {
        WaitScreen waitScreen = new WaitScreen(this);
        waitScreens.push(waitScreen);
        waitScreen.show(message);
        handler.postDelayed(()-> waitScreen.show(message) , 200);
    }

    @Override
    public void hideWait(WaitScreen.OnAnimationEnd onAnimationEnd) {
        WaitScreen waitScreen = waitScreens.pop();
        handler.postDelayed(()-> waitScreen.close(onAnimationEnd) , 100);
    }

    @Override
    public void closeWait() {
        WaitScreen waitScreen = waitScreens.pop();
        handler.postDelayed(waitScreen::dismiss, 100);
    }

    @Override
    public void toast(String message) {
        ToastUtils.showLongToastSafe(message);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        while (!waitScreens.empty()) {
            WaitScreen waitScreen = waitScreens.pop();
            waitScreen.dismiss();
        }
        ActivityTaskManager.getInstance().removeActivity(this);
    }

    @Override
    public final void finishAll() {
        ActivityTaskManager.getInstance().finshAllActivities();
    }

    @SafeVarargs
    @Override
    public final void finish(Class<? extends Activity>... activityClasses) {
        ActivityTaskManager.getInstance().finshActivities(activityClasses);
    }

    @Override
    public Context getContext() {
        return this;
    }

    public boolean isCustomerView(){
        return true;
    }

    public abstract int bindLayout();

    public abstract void afterViews();
}
