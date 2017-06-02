package yourpackage.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * <b>类名称：</b> WXPayEntryActivity <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> MaTing <br/>
 * <b>修改人：</b> MaTing <br/>
 * <b>修改时间：</b> 2017 年 05 月 25 日 17:46<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class WXPayEntryActivity  extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        api = WXAPIFactory.createWXAPI(this, null);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        /**
         * 微信支付成功回调会开启一个activity，并执行onResp方法，我不希望出现这个界面，所以finish了，在这之前，我发送一个广播
         * 在广播中我做了回调后的操作
         *
         * 如果，你的界面是activity，可以改造这个类为你的回调界面（我的是fragment，所以不用他的回调activity）
         */
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            //发送广播，为intent添加的String必须一致，接收广播处
            LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("org.unreal.pay.weiXinPayResult").putExtra("errCode", resp.errCode));
        }
        WXPayEntryActivity.this.finish();
    }
}