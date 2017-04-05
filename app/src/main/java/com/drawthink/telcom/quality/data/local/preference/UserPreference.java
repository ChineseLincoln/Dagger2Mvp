package com.drawthink.telcom.quality.data.local.preference;

import android.util.Base64;

import com.blankj.utilcode.utils.EncodeUtils;
import com.blankj.utilcode.utils.EncryptUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.drawthink.telcom.quality.config.KeyStore;

/**
 * <b>类名称：</b> UserPreference <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年02月13日 15:48<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class UserPreference {

    private static final String USER_MOBILE = "userMobile";

    private static final String USER_NAME = "userName";

    private static final String USER_ID = "userId";

    private SPUtils spUtils;

    public UserPreference(SPUtils spUtils) {
        this.spUtils = spUtils;
    }

    public String getUserId(){
        return deCode(spUtils.getString(USER_ID));
    }

    public String getUserMobile(){
        return deCode(spUtils.getString(USER_MOBILE));
    }

    public String getUserName(){
        return deCode(spUtils.getString(USER_NAME));
    }
//
//    public void saveUser(User user){
//        spUtils.putString(USER_MOBILE,enCode(user.getMobile()));
//        spUtils.putString(USER_NAME, enCode(user.getUserName()));
//        spUtils.putString(USER_ID, enCode(user.getId()));
//    }

    public boolean hasUserId() {
        return spUtils.contains(USER_ID);
    }

    public void removeUserId() {
        spUtils.remove(USER_ID);
    }

    private String enCode(String enCodeData) {
        return new String(Base64.encode(
                EncryptUtils.encrypt3DES(padding(enCodeData)
                        , KeyStore.DES_KEY), Base64.DEFAULT));
    }

    private String deCode(String deCodeData) {
        return new String(EncryptUtils.decryptBase64_3DES(
                deCodeData.getBytes()
                , KeyStore.DES_KEY)).trim();
    }


    private byte[] padding(String arg_text) {
        byte[] encrypt = arg_text.getBytes();

        if (encrypt.length % 8 != 0) { //not a multiple of 8
            //create a new array with a size which is a multiple of 8
            byte[] padded = new byte[encrypt.length + 8 - (encrypt.length % 8)];

            //copy the old array into it
            System.arraycopy(encrypt, 0, padded, 0, encrypt.length);
            encrypt = padded;
        }
        return encrypt;
    }
}
