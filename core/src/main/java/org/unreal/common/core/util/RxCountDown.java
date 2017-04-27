package org.unreal.common.core.util;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * <b>类名称：</b> RxCountDown <br/>
 * <b>类描述：</b> Rx倒计时工具<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 16-9-26 上午10:06<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */

public class RxCountDown {
    public static Flowable<Integer> countdown(int time) {
        if (time < 0) time = 0;

        final int countTime = time;
        return Flowable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map(increaseTime -> countTime - increaseTime.intValue())
                .take(countTime + 1);
    }
}
