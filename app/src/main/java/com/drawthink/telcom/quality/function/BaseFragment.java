package com.drawthink.telcom.quality.function;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.drawthink.telcom.quality.component.AppComponent;

import org.unreal.widget.window.WaitScreen;

import java.util.Stack;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

/**
 * <b>类名称：</b> BaseFragment <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月05日 15:05<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public abstract class BaseFragment<P extends BasePresenter>
        extends Fragment implements BaseView{

    @Inject
    public P presenter;

    private Unbinder unbinder;

    Stack<WaitScreen> waitScreens = new Stack<>();

    Handler handler = new Handler();

    public CompositeDisposable compositeDisposable;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDagger(QualityApplication.getAppComponent());
        compositeDisposable = new CompositeDisposable();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(),container, false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    protected abstract int bindLayout();

    protected abstract void injectDagger(AppComponent appComponent);

    @Override
    public void showWait() {
        WaitScreen waitScreen = new WaitScreen(getActivity());
        waitScreens.push(waitScreen);
        handler.postDelayed(waitScreen::show, 200);
    }

    @Override
    public void showWait(String message) {
        WaitScreen waitScreen = new WaitScreen(getActivity());
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
    public void finish() {
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        while (!waitScreens.empty()) {
            WaitScreen waitScreen = waitScreens.pop();
            waitScreen.dismiss();
        }
    }
}
