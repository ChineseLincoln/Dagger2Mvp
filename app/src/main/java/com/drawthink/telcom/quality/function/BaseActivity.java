package com.drawthink.telcom.quality.function;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.ToastUtils;
import com.drawthink.telcom.quality.component.AppComponent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

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
        injectDagger(QualityApplication.getAppComponent());
        if(presenter != null) {
            presenter.bindLifeCycle(
                    RxLifecycleAndroid.bindActivity(lifecycle()));
        }
        afterViews();
    }

    protected abstract void injectDagger(AppComponent appComponent);

    @Override
    public void showWait() {
        WaitScreen waitScreen = new WaitScreen(this);
        waitScreens.push(waitScreen);
        handler.postDelayed(waitScreen::show, 200);
    }

    @Override
    public void showWait(String message) {
        WaitScreen waitScreen = new WaitScreen(this);
        waitScreens.push(waitScreen);
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
    }

    public boolean isCustomerView(){
        return true;
    }

    public abstract int bindLayout();

    public abstract void afterViews();
}
