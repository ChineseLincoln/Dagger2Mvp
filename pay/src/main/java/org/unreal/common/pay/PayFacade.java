package org.unreal.common.pay;

import android.content.Intent;

import org.unreal.common.pay.impl.alipay.AliPay;
import org.unreal.common.pay.impl.alipay.AliPayConfig;
import org.unreal.common.pay.impl.unionbank.UnionBankPay;
import org.unreal.common.pay.impl.unionbank.UnionBankPayConfig;
import org.unreal.common.pay.impl.weixin.WeiXinPay;
import org.unreal.common.pay.impl.weixin.WeiXinPayConfig;

/**
 * <b>类名称：</b> PayUtils <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月03日 09:38<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class PayFacade {

    private static UnionBankPay unionBankPay;
    private static WeiXinPay weiXinPay;
    private static AliPay aliPay;

    boolean unionBank = false;

    public PayFacade(PayResultListener listener){
        if(aliPay == null) {
            aliPay = new AliPay(listener);
        }
        if(weiXinPay == null) {
            weiXinPay = new WeiXinPay(listener);
        }
        if(unionBankPay == null) {
            unionBankPay = new UnionBankPay(listener);
        }
    }

    public boolean isUnionBank() {
        return unionBank;
    }

    public void unionProcessResult(Intent intent){
        unionBankPay.processPayResponse(intent);
    }

    public void pay(final AliPayConfig aliPayConfig) {
        aliPay.setConfig(aliPayConfig);
        pay(aliPay);
        unionBank = false;
    }

    public void pay(WeiXinPayConfig weiXinPayConfig){
        weiXinPay.setConfig(weiXinPayConfig);
        pay(weiXinPay);
        unionBank = false;
    }

    public void pay(UnionBankPayConfig unionBankPayConfig){
        unionBankPay.setConfig(unionBankPayConfig);
        pay(unionBankPay);
        unionBank = true;
    }

    private void pay(final PayFunction payModule){
        payModule.checkPayState(new CheckStateListener(){

            @Override
            public void checkState(boolean state) {
                if(state){
                    payModule.payOrder();
                }
            }
        });
    }
}