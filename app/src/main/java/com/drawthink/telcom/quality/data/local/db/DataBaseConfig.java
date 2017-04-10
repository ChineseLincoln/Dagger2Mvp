package com.drawthink.telcom.quality.data.local.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * <b>类名称：</b> DataBaseConfig <br/>
 * <b>类描述：</b> 数据库名称信息、版本信息<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 上午11:10<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@Database(name = DataBaseConfig.NAME, version = DataBaseConfig.VERSION)
public class DataBaseConfig {

    static final String NAME = "hospital";
    static final int VERSION = 8;


}
