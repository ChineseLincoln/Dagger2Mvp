package org.unreal.common.core.preference;


import com.blankj.utilcode.util.SPUtils;

/**
 * <b>类名称：</b> EncryptionPreference <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年02月13日 15:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class AppPreference {

    private static final String USER_ID = "userId";

    private SPUtils spUtils;

    public AppPreference(SPUtils spUtils) {
        this.spUtils = spUtils;
    }

    public String getUserId(){
        return spUtils.getString(USER_ID);
    }

    public boolean hasUserId() {
        return spUtils.contains(USER_ID);
    }

    public void removeUserId() {
        spUtils.remove(USER_ID);
    }

    public void put(String userActivity, String s) {
        spUtils.put(userActivity,s);
    }

    public String getString(String userActivity) {
        return spUtils.getString(userActivity);
    }
}
