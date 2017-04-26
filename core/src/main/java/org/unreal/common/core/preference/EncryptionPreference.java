package org.unreal.common.core.preference;

import android.util.Base64;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;

import org.unreal.common.core.config.EncryptionStore;

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
public class EncryptionPreference {

    private SPUtils spUtils;

    public EncryptionPreference(SPUtils spUtils) {
        this.spUtils = spUtils;
    }

    private String enCode(String enCodeData) {
        return new String(Base64.encode(
                EncryptUtils.encrypt3DES(padding(enCodeData)
                        , EncryptionStore.DES_KEY), Base64.DEFAULT));
    }

    private String deCode(String deCodeData) {
        return new String(EncryptUtils.decryptBase64_3DES(
                deCodeData.getBytes()
                , EncryptionStore.DES_KEY)).trim();
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
