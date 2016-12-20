# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/gaoruishan/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
#
################## common 保持公共部分不被混淆
-keepparameternames
-renamesourcefileattribute SourceFile
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,EnclosingMethod

# 所有的注解.
-keepattributes *Annotation*

# 公共类,成员和方法
-keep public class * {
    public protected *;
}
-keepattributes *JavascriptInterface*
# 本地方法名和类名
-keepclasseswithmembernames class * {
    native <methods>;
}

#不混淆Serializable接口的子类中指定的某些成员变量和方法
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# 基本应用程序类.
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# view视图和Context,set
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}

# 所有特殊构造和本身
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 可能的onClick处理
-keepclassmembers class * extends android.content.Context {
   public void *(android.view.View);
   public void *(android.view.MenuItem);
}

# 所有实现Parcelable
-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}

# 访问R文件的内部，静态类

-keepclassmembers class **.R$* {
  public static <fields>;
}

# 注释Javascript 接口
-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

# 请求接口许可证
-keep public interface com.android.vending.licensing.ILicensingService

-dontnote com.android.vending.licensing.ILicensingService


##################### 高德地图使用混淆
# 在生成apk进行代码混淆时进行如下配置(如果爆出warning，在报出warning的包加入类似的语句：-dontwarn 包名)
#3D 地图
-keep   class com.amap.api.mapcore.**{*;}
-keep   class com.amap.api.maps.**{*;}
-keep   class com.autonavi.amap.mapcore.*{*;}

#定位
-keep   class com.amap.api.location.**{*;}
-keep   class com.aps.**{*;}

#搜索
-keep   class com.amap.api.services.**{*;}

#2D地图
-keep class com.amap.api.maps2d.**{*;}
-keep class com.amap.api.mapcore2d.**{*;}

#导航
-keep class com.amap.api.navi.**{*;}
-keep class com.autonavi.**{*;}

################### region for xUtils第三方库
-keepattributes Exceptions,InnerClasses,Signature,Deprecated,*Annotation*,Synthetic,EnclosingMethod

-keep public class org.xutils.** {
    public protected *;
}
-keep public interface org.xutils.** {
    public protected *;
}
-keepclassmembers class * extends org.xutils.** {
    public protected *;
}
-keepclassmembers class * extends org.xutils.http.RequestParams {*;}
-keepclassmembers class * {
   void *(android.view.View);
   *** *Click(...);
   *** *Event(...);
}
#########################
-dontusemixedcaseclassnames
-dontshrink
-dontoptimize
-dontpreverify
-dontwarn com.umeng.comm.**
-dontwarn com.umeng.commm.**
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-keep class  activeandroid.** {*;}
-keep class com.umeng.** {*;}
-keep class android.** {*;}
-keepattributes *Annotation*


-ignorewarnings
-keep class org.apache.http.** {*;}
-dontwarn  org.apache.http.**
-keep class org.apache.http.* {*;}
-dontwarn  org.apache.http.*
-keep,allowshrinking class org.android.agoo.service.* {
    public <fields>;
    public <methods>;
}
-keep,allowshrinking class com.umeng.message.* {
    public <fields>;
    public <methods>;
}

-keep public class com.umeng.community.example.R$*{
    *;
}

-keep class com.umeng.comm.push.UmengPushImpl {
    public * ;
}
-keep class android.support.v4.** {*;}
-dontwarn android.webkit.WebView

-dontwarn com.tencent.weibo.sdk.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes SourceFile,LineNumberTable
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**
-keep public class com.tencent.** {*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

# adding push
-keep class com.umeng.message.* {
        public <fields>;
        public <methods>;
}

-keep class com.umeng.message.protobuffer.* {
        public <fields>;
        public <methods>;
}

-keep class com.squareup.wire.* {
        public <fields>;
        public <methods>;
}

-keep class com.umeng.message.local.* {
        public <fields>;
        public <methods>;
}
-keep class org.android.agoo.impl.*{
        public <fields>;
        public <methods>;
}

-dontwarn com.xiaomi.**

-dontwarn com.ut.mini.**

-keep class org.android.agoo.service.* {*;}

-keep class org.android.spdy.**{*;}

-keep public class com.umeng.community.example.R$*{
    public static final int *;
}
-keepattributes Exceptions,InnerClasses,Signature,EnclosingMethod
-keepattributes SourceFile,LineNumberTable
-keepattributes *Annotation*
#开源工程集成的依赖关系

#umeng_community_library_common_ui依赖umeng_community_core，和login（）；
#umeng_community_library_weibo／umeng_community_library_discuss依赖
#umeng_community_core，umeng_community_login，umeng_community_share，umeng_community_push，umeng_community_library_common_ui，umeng_community_location
#umeng_community_library_db依赖umeng_community_core
#如果使用androidstudio，三个工程都依赖同一个jar包，这个三个工程又同时被某个app工程依赖，可能会造成编译错误，推荐的解决办法是，新建一个commonlib工程，将所有需要依赖的jar放进去，在commonlib工程的build.gradle 文件中加入
#`dependencies {
#    compile fileTree(dir: 'libs', include: ['*.jar'])
#    }
#artifacts.add("default", file('xxxxxx'))`
#用artifacts.add所有的jar包。再让需要依赖jar包的工程都依赖commonlib即可
#分享相关混淆
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes SourceFile,LineNumberTable
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public class com.umeng.socialize.* {*;}
-keep public class javax.**
-keep public class android.webkit.**
-keep public class com.tencent.** {*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}

-keep class com.iflytek.**{*;}

#AndFix热修复
-keep class * extends java.lang.annotation.Annotation
-keepclasseswithmembernames class * {
    native <methods>;
}