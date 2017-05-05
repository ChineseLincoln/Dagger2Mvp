package org.unreal.common.pay.impl.weixin;

import android.app.Activity;

/**
 * <b>类名称：</b> WeiXinPayConfig <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月02日 17:46<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class WeiXinPayConfig {
    private String appId;
    private String partnerId;
    private String prepayId;
    private String packageValue;
    private String nonceStr;
    private String timeStamp;
    private String sign;
    private Activity context;

    private WeiXinPayConfig() {

    }

    public Activity getContext() {
        return context;
    }

    public String getAppId() {
        return appId;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public static class Builder {
        private Activity context;
        //微信支付AppID
        private String appId;
        //微信支付商户号
        private String partnerId;
        //预支付码（重要）
        private String prepayId;
        //"Sign=WXPay"
        private String packageValue = "Sign=WXPay";

        private String nonceStr;
        //时间戳
        private String timeStamp;
        //签名
        private String sign;

        public Builder() {
            super();
        }

        public Builder with(Activity context) {
            this.context = context;
            return this;
        }

        /**
         * 设置微信支付AppID
         *
         * @param appId
         * @return
         */
        public Builder setAppId(String appId) {
            this.appId = appId;
            return this;
        }

        /**
         * 微信支付商户号
         *
         * @param partnerId
         * @return
         */
        public Builder setPartnerId(String partnerId) {
            this.partnerId = partnerId;
            return this;
        }

        /**
         * 设置预支付码（重要）
         *
         * @param prepayId
         * @return
         */
        public Builder setPrepayId(String prepayId) {
            this.prepayId = prepayId;
            return this;
        }


        /**
         * 设置
         *
         * @param packageValue
         * @return
         */
        public Builder setPackageValue(String packageValue) {
            this.packageValue = packageValue;
            return this;
        }


        /**
         * 设置
         *
         * @param nonceStr
         * @return
         */
        public Builder setNonceStr(String nonceStr) {
            this.nonceStr = nonceStr;
            return this;
        }

        /**
         * 设置时间戳
         *
         * @param timeStamp
         * @return
         */
        public Builder setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        /**
         * 设置签名
         *
         * @param sign
         * @return
         */
        public Builder setSign(String sign) {
            this.sign = sign;
            return this;
        }


        public WeiXinPayConfig build() {
            WeiXinPayConfig weiXinPayConfig = new WeiXinPayConfig();
            weiXinPayConfig.context = this.context;
            //微信支付AppID
            weiXinPayConfig.appId = this.appId;
            //微信支付商户号
            weiXinPayConfig.partnerId = this.partnerId;
            //预支付码（重要）
            weiXinPayConfig.prepayId = this.prepayId;
            //"Sign=WXPay"
            weiXinPayConfig.packageValue = this.packageValue;

            weiXinPayConfig.nonceStr = this.nonceStr;
            //时间戳
            weiXinPayConfig.timeStamp = this.timeStamp;
            //签名
            weiXinPayConfig.sign = this.sign;

            return weiXinPayConfig;
        }
    }
}
