package org.unreal.common.pay.impl.weixin;

import android.widget.Toast;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.unreal.common.pay.CheckStateListener;
import org.unreal.common.pay.PayFunction;
import org.unreal.common.pay.PayResultListener;

/**
 * <b>类名称：</b> WeiXinPay <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月03日 11:15<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class WeiXinPay implements PayFunction, IWXAPIEventHandler {

    //微信支付核心api
    private IWXAPI mWXApi;
    private PayResultListener listener;
    private WeiXinPayConfig config;

    public WeiXinPay(PayResultListener listener) {
        this.listener = listener;
    }

    @Override
    public void payOrder() {
        PayReq request = new PayReq();
        request.appId = config.getAppId();
        request.partnerId = config.getPartnerId();
        request.prepayId = config.getPrepayId();
        request.packageValue =
                config.getPackageValue() != null
                        ? config.getPackageValue() : "Sign=WXPay";
        request.nonceStr = config.getNonceStr();
        request.timeStamp = config.getTimeStamp();
        request.sign = config.getSign();
        mWXApi.sendReq(request);
    }

    @Override
    public void checkPayState(CheckStateListener checkStateListener) {
        if (listener != null) {
            if (!mWXApi.isWXAppInstalled()) {
                Toast.makeText(config.getContext(), "没有安装微信,无法使用微信支付!"
                        , Toast.LENGTH_SHORT).show();
            }
            if (!mWXApi.isWXAppSupportAPI()) {
                Toast.makeText(config.getContext(), "当前微信版本过低,无法使用微信支付,请升级微信!"
                        , Toast.LENGTH_SHORT).show();
            }
            checkStateListener.checkState(mWXApi.isWXAppInstalled() && mWXApi.isWXAppSupportAPI());
        }
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
//        0	成功	展示成功页面
//        -1	错误	可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等。
//        -2	用户取消	无需处理。发生场景：用户不支付了，点击取消，返回APP。
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (this.listener != null) {
                if (baseResp.errCode == BaseResp.ErrCode.ERR_OK) { //        0 成功	展示成功页面
                    Toast.makeText(config.getContext(), "微信支付成功", Toast.LENGTH_SHORT).show();
                    this.listener.onPaySuccess();
                } else {//  -1	错误       -2	用户取消
                    Toast.makeText(config.getContext(), "微信支付失败", Toast.LENGTH_SHORT).show();
                    this.listener.onPayFailure();
                }

            }
        }
    }

    public WeiXinPay setConfig(WeiXinPayConfig config) {
        this.config = config;
        mWXApi = WXAPIFactory.createWXAPI(config.getContext(), null);
        mWXApi.handleIntent(config.getContext().getIntent(), this);
        mWXApi.registerApp(config.getAppId());
        return this;
    }
}
