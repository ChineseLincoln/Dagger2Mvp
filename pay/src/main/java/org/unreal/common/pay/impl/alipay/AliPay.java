package org.unreal.common.pay.impl.alipay;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.ta.utdid2.android.utils.StringUtils;

import org.unreal.common.pay.CheckStateListener;
import org.unreal.common.pay.PayFunction;
import org.unreal.common.pay.PayResultListener;

import java.util.Map;

/**
 * <b>类名称：</b> AliPay <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月03日 09:32<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class AliPay implements PayFunction {

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_CHECK_FLAG = 2;

    private AliPayConfig config;
    private PayResultListener listener;
    private Handler mHandler;
    private CheckStateListener checkStateListener;

    public AliPay(PayResultListener listener) {
        this.listener = listener;
    }

    @Override
    public void payOrder() {
        Runnable checkRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象

                PayTask payTask = new PayTask(config.getContext());
                // 调用查询接口，获取查询结果
                Map<String, String> result = payTask.payV2(config.getOrderInfo(), true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(checkRunnable);
        payThread.start();
    }

    @Override
    public void checkPayState(CheckStateListener checkStateListener) {
        this.checkStateListener = checkStateListener;
        Runnable checkRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象

                PayTask payTask = new PayTask(config.getContext());
                // 调用查询接口，获取查询结果
                String result = payTask.getVersion();

                Message msg = new Message();
                msg.what = SDK_CHECK_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread checkThread = new Thread(checkRunnable);
        checkThread.start();

    }

    public AliPay setConfig(final AliPayConfig config) {
        this.config = config;
        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
//                        String resultInfo = payResult.getResult();
// 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
// 判断resultStatus 为非“9000”则代表可能支付失败
// “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
// 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
        mHandler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case SDK_PAY_FLAG: {
                        PayResult payResult = new PayResult((Map<String, String>) msg.obj);

                        // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
//                        String resultInfo = payResult.getResult();

                        String resultStatus = payResult.getResultStatus();
                        // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                        if (TextUtils.equals(resultStatus, "9000")) {
                            Toast.makeText(config.getContext(), "支付宝支付成功", Toast.LENGTH_SHORT).show();
                            if (listener != null) listener.onPaySuccess();
                        } else {
                            // 判断resultStatus 为非“9000”则代表可能支付失败
                            // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                            if (TextUtils.equals(resultStatus, "8000")) {
                                Toast.makeText(config.getContext(), "支付宝支付结果确认中", Toast.LENGTH_SHORT).show();
                                if (listener != null) listener.onPayConfirming();

                            } else {
                                // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                                Toast.makeText(config.getContext(), "支付宝支付失败", Toast.LENGTH_SHORT).show();
                                if (listener != null) listener.onPayFailure();
                            }
                        }
                        break;
                    }
                    case SDK_CHECK_FLAG: {
                        if (listener != null) {
                            if (StringUtils.isEmpty(msg.obj.toString())) {
                                Toast.makeText(config.getContext(), "没有安装支付宝或支付宝版本过低,无法使用支付宝支付!"
                                        , Toast.LENGTH_SHORT).show();
                                checkStateListener.checkState(false);
                            } else {
                                checkStateListener.checkState(true);
                            }
                        }
                        break;
                    }
                    default:
                        break;
                }
            }

        };
        return this;
    }
}
