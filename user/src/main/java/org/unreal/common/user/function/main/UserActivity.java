package org.unreal.common.user.function.main;

import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import org.unreal.common.core.base.BaseActivity;
import org.unreal.common.core.component.CoreComponent;
import org.unreal.common.core.preference.AppPreference;
import org.unreal.common.user.R;
import org.unreal.common.user.R2;
import org.unreal.common.user.function.main.component.DaggerUserComponent;
import org.unreal.common.user.function.main.contract.UserContract;
import org.unreal.common.user.function.main.module.UserModule;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * <b>类名称：</b> UserActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月25日 16:35<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UserActivity extends BaseActivity<UserContract.Presenter>
        implements UserContract.View {

    @Inject
    AppPreference appPreference;

    @Inject
    UserContract.Presenter presenter;
    @BindView(R2.id.editText)
    EditText editText;
    @BindView(R2.id.button)
    Button button;

    @Override
    protected void injectDagger(CoreComponent coreComponent) {
        DaggerUserComponent.builder()
                .coreComponent(coreComponent)
                .userModule(new UserModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_user;
    }

    @Override
    public void afterViews() {
        appPreference.put("UserActivity", "say hello");
        Log.e("UserActivity", "save Preference");
        String userActivity = appPreference.getString("UserActivity");
        Log.e("UserActivity", "save Preference result is --->" + userActivity);
        presenter.loadData();
    }
}
