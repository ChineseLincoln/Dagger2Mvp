package org.unreal.common.pay;

/**
 * <b>类名称：</b> PayResultListener <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月03日 09:34<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public interface PayResultListener {
    void onPaySuccess();
    void onPayFailure();
    void onPayConfirming();
}
