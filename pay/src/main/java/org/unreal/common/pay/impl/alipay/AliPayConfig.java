package org.unreal.common.pay.impl.alipay;

import android.app.Activity;

/**
 * <b>类名称：</b> AliPayConfig <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月02日 17:46<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class AliPayConfig {

    private String orderInfo;
    private Activity context;

    private AliPayConfig() {

    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public Activity getContext() {
        return context;
    }

    public static class Builder {
        private String orderInfo;
        private Activity context;

        public Builder() {
            super();
        }

        public AliPayConfig.Builder with(Activity context) {
            this.context = context;
            return this;
        }

        /**
         * 设置支付宝支付OrderInfo
         *
         * @param orderInfo
         * @return
         */
        public AliPayConfig.Builder setOrderInfo(String orderInfo) {
            this.orderInfo = orderInfo;
            return this;
        }

        public AliPayConfig build() {
            AliPayConfig aliPayPayConfig = new AliPayConfig();
            aliPayPayConfig.context = this.context;
            aliPayPayConfig.orderInfo = this.orderInfo;
            return aliPayPayConfig;
        }
    }
}