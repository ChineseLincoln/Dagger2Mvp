package org.unreal.common.pay.impl.unionbank;

import android.content.Intent;
import android.widget.Toast;

import com.unionpay.UPPayAssistEx;

import org.unreal.common.pay.CheckStateListener;
import org.unreal.common.pay.PayFunction;
import org.unreal.common.pay.PayResultListener;

/**
 * <b>类名称：</b> UnionBankPay <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月03日 14:23<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UnionBankPay implements PayFunction {
    private PayResultListener listener;
    private UnionBankPayConfig config;

    public UnionBankPay(PayResultListener listener) {
        this.listener = listener;
    }

    @Override
    public void payOrder() {
        UPPayAssistEx.startPay(config.getContext()
                , null
                , null
                , config.getTradeCode()
                , config.getServerModel());
    }

    @Override
    public void checkPayState(CheckStateListener checkStateListener) {
        checkStateListener.checkState(true);
    }

    public void processPayResponse(Intent data) {
        if (data == null) {
            return;
        }

        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if ("success".equalsIgnoreCase(str)) {

            // 17-3-3 此处任务只要返回Success 即认为支付成功,此处逻辑会有风险
            // 如果想对结果数据验签，可使用下面这段代码，但建议不验签，直接去商户后台查询交易结果
            // result_data结构见c）result_data参数说明
//            if (data.hasExtra("result_data")) {
////                String result = data.getExtras().getString("result_data");
////                try {
////                    JSONObject resultJson = new JSONObject(result);
//////                    String sign = resultJson.getString("sign");
//////                    String dataOrg = resultJson.getString("data");
////                    // 此处的verify建议送去商户后台做验签
////                    // 如要放在手机端验，则代码必须支持更新证书
//////                    boolean ret = verify(dataOrg, sign, mMode);
//////                    if (ret) {
//////                        // 验签成功，显示支付结果
//////                        msg = "支付成功！";
//////                    } else {
//////                        // 验签失败
//////                        msg = "支付失败！";
//////                    }
////                } catch (JSONException e) {
////                }
//            }
            // 结果result_data为成功时，去商户后台查询一下再展示成功
            Toast.makeText(config.getContext(), "银联支付成功", Toast.LENGTH_SHORT).show();
            if (listener != null) {
                listener.onPaySuccess();
            }
        } else if ("fail".equalsIgnoreCase(str)) {
            Toast.makeText(config.getContext(), "银联支付失败", Toast.LENGTH_SHORT).show();
            if (listener != null) {
                listener.onPayFailure();
            }
        } else if ("cancel".equalsIgnoreCase(str)) {
            if (listener != null) {
                Toast.makeText(config.getContext(), "银联支付失败,用户取消了支付", Toast.LENGTH_SHORT).show();
                listener.onPaySuccess();
            }
        }
    }

    public UnionBankPay setConfig(UnionBankPayConfig config) {
        this.config = config;
        return this;
    }
}
