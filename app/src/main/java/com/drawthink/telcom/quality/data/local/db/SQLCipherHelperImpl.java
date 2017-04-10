package com.drawthink.telcom.quality.data.local.db;

import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.structure.database.DatabaseHelperListener;
import com.raizlabs.dbflow.android.sqlcipher.SQLCipherOpenHelper;

/**
 * <b>类名称：</b> SQLCipherHelperImpl <br/>
 * <b>类描述：</b> 用于SQLCipher加密<br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2016年09月19日 上午11:11<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
public class SQLCipherHelperImpl extends SQLCipherOpenHelper {
    public SQLCipherHelperImpl(DatabaseDefinition databaseDefinition, DatabaseHelperListener listener) {
        super(databaseDefinition, listener);
    }

    @Override
    protected String getCipherSecret() {
        return "hospital@drawthink@5670007";
    }
}
