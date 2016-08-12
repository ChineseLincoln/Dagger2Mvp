package org.unreal.dagger.function.main.presenter;

import android.content.SharedPreferences;

import org.unreal.dagger.data.http.local.service.UserService;
import org.unreal.dagger.data.http.taobao.service.TaobaoIPLocationService;
import org.unreal.dagger.function.main.contract.MainContract;

import javax.inject.Inject;

/**
 * <b>类名称：</b> Main <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年08月12日 下午4:34<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class MainPresenter implements MainContract.presenter {

    private final MainContract.View view;
    private final SharedPreferences sharedPreferences;
    private final TaobaoIPLocationService locationService;
    private final UserService userService;

    @Inject
    public MainPresenter(MainContract.View view,
                         SharedPreferences sharedPreferences,
                         TaobaoIPLocationService locationService,
                         UserService userService) {
        this.view = view;
        this.sharedPreferences = sharedPreferences;
        this.locationService = locationService;
        this.userService = userService;
    }

    public void main(){
        locationService.getIPInfo("myip")

    }
}
