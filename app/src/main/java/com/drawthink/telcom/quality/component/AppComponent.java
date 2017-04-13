package com.drawthink.telcom.quality.component;

import com.drawthink.telcom.quality.module.AppModule;
import com.github.aleksandermielczarek.napkin.scope.AppScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * <b>类名称：</b> AppComponent <br/>
 * <b>类描述：</b> <br/>
 * <b>创建人：</b> Lincoln <br/>
 * <b>修改人：</b> Lincoln <br/>
 * <b>修改时间：</b> 2017年04月01日 14:47<br/>
 * <b>修改备注：</b> <br/>
 *
 * @version 1.0.0 <br/>
 */
@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {

}
