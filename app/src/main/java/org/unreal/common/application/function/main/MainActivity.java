package org.unreal.common.application.function.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;

import org.unreal.common.application.R;
import org.unreal.common.config.RouterConfig;
import org.unreal.common.pay.PayFacade;
import org.unreal.common.pay.PayResultListener;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private PayFacade facade;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initPayFacade();
    }

    private void initPayFacade() {
        facade = new PayFacade(new PayResultListener(){

            @Override
            public void onPaySuccess() {

            }

            @Override
            public void onPayFailure() {

            }

            @Override
            public void onPayConfirming() {

            }
        });
    }

    @OnClick({R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button2:
//                facade.pay(new AliPayConfig.Builder().with(this).build());
                ARouter.getInstance().build(RouterConfig.USER_MAIN).navigation();
                break;
            case R.id.button3:
//                facade.pay(new WeiXinPayConfig.Builder().with(this).build());
                break;
            case R.id.button4:
//                facade.pay(new UnionBankPayConfig.Builder().with(this).build());
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(facade.isUnionBank()){
            facade.unionProcessResult(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
