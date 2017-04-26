package org.unreal.common.core.http;

import android.content.Context;


import org.unreal.common.core.R;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * <b>类名称：</b> LocalSSLFactory <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年03月29日 16:11<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class LocalSSLFactory {
    private static final String CLIENT_AGREEMENT = "TLS";//使用协议

    public static final String KEY_STORE_TYPE_P12 = "PKCS12";//证书类型

    public static final String KEY_STORE_TYPE_BKS = "bks";//证书类型

    private static final String KEY_STORE_PASSWORD = "drawthink";//证书密码（应该是客户端证书密码）
    private static final String KEY_STORE_TRUST_PASSWORD = "drawthink";//授信证书密码（应该是服务端证书密码）

    public static SSLSocketFactory getSocketFactory(Context context , String keyStoreType) {
        InputStream trust_input = context.getResources().openRawResource(R.raw.client_trust);//服务器授信证书
        InputStream client_input = context.getResources().openRawResource(R.raw.client);//客户端证书
        try {
            SSLContext sslContext = SSLContext.getInstance(CLIENT_AGREEMENT);
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(trust_input, KEY_STORE_TRUST_PASSWORD.toCharArray());
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(client_input, KEY_STORE_PASSWORD.toCharArray());
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(trustStore);

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, KEY_STORE_PASSWORD.toCharArray());
            sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                trust_input.close();
                client_input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
