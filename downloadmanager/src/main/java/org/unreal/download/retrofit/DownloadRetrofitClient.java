package org.unreal.download.retrofit;

import com.cm.retrofit2.converter.file.FileConverterFactory;
import com.cm.retrofit2.converter.file.body.HttpClientHelper;
import com.cm.retrofit2.converter.file.body.ProgressResponseListener;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * <b>类名称：</b> DownloadRetorfitClient <br  />
 * <b>类描述：</b> <br  />
 * <b>创建人：</b> wqq <br  />
 * <b>修改人：</b> wqq <br  />
 * <b>修改时间：</b> 2016年07月04日 15:10<br  />
 * <b>修改备注：</b> <br  />
 *
 * @version 1.0.0 <br  />
 */

public class DownloadRetrofitClient {

    private static final String HOST = "http://fir.im/";

    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(FileConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()); //添加FileConverterFactory

    /**
     * 创建带响应进度(下载进度)回调的service
     */
    public static <T> T createResponseService(Class<T> tClass, ProgressResponseListener listener){
        //通过HttpClientHelper获得已经添加了自定义ResponseBody的OkHttpClient
        OkHttpClient client = HttpClientHelper
                .addProgressResponseListener(new OkHttpClient.Builder(),listener)
                .build();
        return builder
                .client(client)
                .build()
                .create(tClass);
    }
}
