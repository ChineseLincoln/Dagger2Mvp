package org.unreal.common.pay.impl.unionbank;

import android.app.Activity;

/**
 * <b>类名称：</b> UnionBankPayConfig <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月03日 14:23<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UnionBankPayConfig {

    private Activity context;
    private String tradeCode;
    private String serverModel;

    private UnionBankPayConfig() {
    }

    public Activity getContext() {
        return context;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public String getServerModel() {
        return serverModel;
    }

    public static class Builder {
        private Activity context;
        private String tradeCode;
        private String serverModel;

        public Builder() {
            super();
        }

        public UnionBankPayConfig.Builder with(Activity context) {
            this.context = context;
            return this;
        }

        /**
         * 设置银联支付Tn
         *
         * @param tradeCode
         * @return
         */
        public UnionBankPayConfig.Builder setTradeCode(String tradeCode) {
            this.tradeCode = tradeCode;
            return this;
        }

        /**
         * 设置银联支付服务模式
         *
         * @param serverModel “00” – 银联正式环境 ,“01” – 银联测试环境，该环境中不发生真实交易
         * @return
         */
        public UnionBankPayConfig.Builder setServerModel(String serverModel) {
            this.serverModel = serverModel;
            return this;
        }

        public UnionBankPayConfig build() {
            UnionBankPayConfig unionBankPayConfig = new UnionBankPayConfig();
            unionBankPayConfig.context = this.context;
            unionBankPayConfig.tradeCode = this.tradeCode;
            unionBankPayConfig.serverModel = this.serverModel;
            return unionBankPayConfig;
        }
    }
}
