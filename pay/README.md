# 配置说明
## Step1 引用模块
```
compile project(":pay")
```

## Step2 替换yourpackage 为app包名
1. 替换 src -> main -> java -> yourpackage 
1. pay module 下 AndroidMainifest.xml 中 WXPayEntryActivity 包名
 
## Step3 参照App项目下 MainActivity 调用支付