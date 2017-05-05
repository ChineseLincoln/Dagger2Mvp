package org.unreal.common.core.preference;

import android.support.annotation.Nullable;
import android.util.Base64;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;

import org.unreal.common.core.config.EncryptionStore;

import java.util.Map;

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

    /**
     * SP中写入String类型value
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, @Nullable String value) {
        spUtils.put(key, enCode(value));
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code null}
     */
    public String getString(String key) {
        return getString(key, "");
    }

    /**
     * SP中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(String key, String defaultValue) {
        return deCode(spUtils.getString(key, defaultValue));
    }


    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return spUtils.getAll();
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public void remove(String key) {
        spUtils.remove(key);
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(String key) {
        return spUtils.contains(key);
    }

    /**
     * SP中清除所有数据
     */
    public void clear() {
        spUtils.clear();
    }

    private String enCode(String enCodeData) {
        return new String(Base64.encode(
                EncryptUtils.encrypt3DES(padding(enCodeData)
                        , EncryptionStore.DES_KEY), Base64.DEFAULT));
    }

    private String deCode(String deCodeData) {
        if (StringUtils.isEmpty(deCodeData)) {
            return "";
        }
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
