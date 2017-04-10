package org.unreal.download.service;

import java.io.File;

import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * <b>类名称：</b> DownloadService <br  />
 * <b>类描述：</b> <br  />
 * <b>创建人：</b> wqq <br  />
 * <b>修改人：</b> wqq <br  />
 * <b>修改时间：</b> 2016年07月04日 15:12<br  />
 * <b>修改备注：</b> <br  />
 *
 * @version 1.0.0 <br  />
 */

public interface DownloadService {
    @Streaming
    @GET
    Observable<File> downloadWithDynamicUrl(@Url String downloadUrl);
}
