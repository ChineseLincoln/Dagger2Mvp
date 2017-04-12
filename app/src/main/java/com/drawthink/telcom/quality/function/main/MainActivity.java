package com.drawthink.telcom.quality.function.main;

import android.widget.Button;

import com.blankj.utilcode.utils.AppUtils;
import com.drawthink.telcom.quality.R;
import com.drawthink.telcom.quality.component.AppComponent;
import com.drawthink.telcom.quality.function.ToolBarActivity;
import com.drawthink.telcom.quality.function.main.component.DaggerMainComponent;
import com.drawthink.telcom.quality.function.main.contract.MainContract;
import com.drawthink.telcom.quality.function.main.module.MainModule;

import org.unreal.update.UpdateManager;

import butterknife.BindView;


public class MainActivity
        extends ToolBarActivity<MainContract.Presenter>
        implements MainContract.View {

    @BindView(R.id.button)
    Button button;

    @Override
    public String setTitle() {
        return "首页";
    }

    @Override
    protected void injectDagger(AppComponent appComponent) {
        DaggerMainComponent.builder()
                .appComponent(appComponent)
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
//        new UpdateManager(this).checkUpdate(AppUtils.getAppVersionCode(this),
//                Throwable::printStackTrace);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void afterViews() {
    }
}
