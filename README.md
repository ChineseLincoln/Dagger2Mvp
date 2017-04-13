# V2 版本说明
1. presenter 和Activity\Fragment　通过范型模式绑定关系，不需要在实现类中注入Presenter ,　父类ToolbarActivity,BaseActivity,BaseFragment已经绑定好了
1. presenterImpl类中，可以使用＠Inject注解，注入相关Service的实现类
1. 统一使用config.gradle管理依赖版本
1. 可使用的Dagger2 scope 注解 

|注解|生命周期|
|---|：-----|
|@AppScope|app生命周期|
|@ActivityScope|Activity生命周期|
|@FragmentScope|Fragments生命周期|
|@ServiceScope|Service生命周期|
|@ReceiverScope|Receiver生命周期
|@ProviderScope|ContentProvider生命周期|
|@UserScope|用户自定义生命周期（如自定义View）|
|@SessionScope|网络请求相关生命周期|