# V2 版本说明
1. presenter 和Activity\Fragment　通过范型模式绑定关系，不需要在实现类中注入Presenter ,　父类ToolbarActivity,BaseActivity,BaseFragment已经绑定好了
1. presenterImpl类中，可以使用＠Inject注解，注入相关Service的实现类
1. 统一使用config.gradle管理依赖版本
