# flutter_umeng

友盟统计插件
android 基于友盟 7.5.3 开发

## android 部分

参考[官方文档](https://dev.umeng.com/analytics/android-doc/integration#2_2_1)

### 设置 meta-data

```xml
    <application>
        <meta-data android:value="YOUR_APP_KEY" android:name="5ba4b0cdf1f556970400019c"/>
        <meta-data android:value="Channel ID" android:name="self"/>
    </application>
```

### 代码混淆

android 中如果你做了代码混淆,需要根据官方文档的提示加入如下代码

```java
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
```
