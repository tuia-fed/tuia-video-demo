# 接入指南

文档版本 | 修订日期| 修订说明
---|---|---
V1.0.0.0 | 20190906 | 初始化SDK,同时支持支持激励视频类和激励互动类型广告
V1.2.0.0 | 20190916 | 1.增加关闭事件回调 2.解决奖励回调多次问题
V1.2.2.2 | 20190929 | 1.增加非全屏视频类活动导航栏 2.优化下载器
V1.2.2.7 | 20191012 | 1.优化广告请求方式 2.优化下载性能  3.增加关键日志 4.增加关闭事件的发奖通知
V1.2.2.8 | 20191021 | 1.优化发奖回调时机 2.禁用webview 缩放控制,避免引起内存泄漏 3.增加手动集成方式


## 手动集成方式

[手动集成方式点击查看](http://yun.duiba.com.cn/tuia/sdk/html/激励SDK-V1.2.2.8-手动集成方式.html) 

## 自动集成方式（推荐）
#### 一.依赖引入
方式一.Gradle依赖（推荐）
```
   1.在build.gradle文件中添加

   buildscript {
        repositories {
            google()
            jcenter()
            maven { url "https://jitpack.io" }
            maven { url "https://dl.bintray.com/sunjiangrong/maven" }
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:3.4.2'
        }
    }

    allprojects {
        repositories {
            google()
            jcenter()
            maven { url "https://jitpack.io" }
            maven { url "https://dl.bintray.com/sunjiangrong/maven" }
        }
    }

  2.app下的build.gradle添加：(本SDK最低可运行于API Level 15，最高支持API Level 28)

  dependencies {
        implementation ('com.tuia:tm:1.2.2.8-release'){
                transitive = true
        }
   }
```

#### 二.权限(sdk内部已经处理相关权限问题，如果遇到冲突咨询对应开发即可)
```
  <uses-permission android:name="android.permission.INTERNET"/>
```
#### 三.使用
1.初始化(在Application初始onCreate方法中调用,必须初始化不然会导致奔溃)

```
   MagicSDK.init(Application);

```

2.创建MagicVideoView对象
```
   private MagicVideoView magicVideoView;
   
   if(magicVideoView == null){
               magicVideoView =new MagicVideoView(MagicApp.getApp(),
                   "userId","appId","appkey","slotId",
                   ”deviceId“,new MagicVideoListener() {
               @Override
               public void onMagicRequestAd() {
                    //请求广告回调
                   Log.d("onMagicRequest","onMagicRequestRewardVideo");
               }
               @Override
               public void onMagicAdSuccessed() {
                    //请求广告成功回调
                   Log.d("onMagicRequest","onMagicAdSuccessed");
               }
               @Override
               public void onMagicAdEmpty() {
                   //请求广告为空回调
                   Log.d("onMagicRequest","onMagicAdEmpty");
               }
               
               @Override
               public void onMagicAdFailed(Response<String> response) {
                    //请求广告失败回调
                   Log.d("onMagicRequest","onMagicAdFailed"+response.body());
               }
               
               @Override
               public void onMagicAdShow() {
                    //广告展示回调
                    Log.d("onMagicRequest","onMagicAdShow");
               }
               
               @Override
               public void onMagicRewarded(String msg) {
                    //发放奖励回调   只有发奖的时候会回调
                   Log.d("onMagicRequest","onMagicReward"+msg);
               }
               
               @Override
               public void onMagicAdClose(String s) {
                    //广告关闭事件回调   s只有发奖的时候会回调参数，不发奖时回到为空字符串或null
                    Log.d("onMagicRequest","onMagicAdClose"+s);
                }
           });
   }

```
参考demo中的MainActivity使用,替换对应申请的appId,appKey,slotId,userId，对应字段释义如下，字段均必填

| 名称 | 类型 | 备注 |
| :---------------------: | :---------------------: | :----------------------: |
| userId | String | 媒体的用户ID，用于发送对应的奖励 |
| appId | Long | 推啊媒体ID，通过TUIA媒体平台注册获得 |
| appKey | String | 媒体公钥，通过TUIA媒体平台注册获得 |
| slotId | Long | 广告位ID，通过TUIA媒体平台注册获得 |
| deviceId | String | 媒体的用户deviceId，用于用户唯一性确认 |
| MagicVideoListener |  | 广告回调监听 |


3.请求广告,加载广告(方式一和方式二任选一种)
```
   方式一.缓存模式（先缓存,在需要的时候调用广告加载）
  	1.请求广告
        if(magicVideoView != null){
   		    magicVideoView.loadAd();
   		}

        2.加载广告

        if(magicVideoView != null){
   		    if (magicVideoView.checkLocalData()){
       			    magicVideoView.openNewVideoTask(MainActivity.this,false);
   		    }else {
       			    magicVideoView.openNewVideoTask(MainActivity.this,true);
   		    }
   		}

   方式二.在线模式(直接请求,加载广告)
        if(magicVideoView != null){
   		    magicVideoView.openNewVideoTask(MainActivity.this,true);
   		}

```
4.在合适时机销毁广告控件MagicVideoView,避免内存泄漏等
```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (magicVideoView!=null){
           magicVideoView.destory();
        }
    }

```
5.返回奖励信息   在onMagicRewarded方法中会返回上报的奖励信息，JSON字符串如下
```
{
	"finishType" : 1,
	"orderId" : "168408070629",
	"userId" : "123",
	"timestamp" : "1566791113031",
	"prizeFlag" : "XXX",
	"appKey" : "4W8ReCvDm4fy3Fpn52MgPgUWmdfS",
	"sign" : "5093659d6bf802d1a407df81d6aab9f9",
	"score" : null,
	"reason" : "-1",
	"url" : null
}
```
| 名称 | 类型 | 备注 |
| :---------------------: | :---------------------: | :----------------------: |
| userId | String | 用户 id，用户在媒体的唯一识别信息，来源于活动链接中的&userId=xxx，由媒体拼接提供 |
| timestamp | Number | 时间戳 |
| prizeFlag | String | 常量，请求上报的奖励在对接方媒体系统内的奖励标识，用于标识具体的奖励类型，由媒体提供 |
| orderId | String | 推啊订单号，具有唯一性，幂等由媒体保障 |
| appKey | String | 媒体公钥 |
| sign | String | 签名 |
| score | Number | 如果是数值类型的奖励，则同时请求充值对应的数值 score，比如积分、金币、倍数 |



#### 四.发放奖励流程
![Alt text](http://yun.tuisnake.com/docking_ducument/%E4%B8%8A%E6%8A%A5%E6%B5%81%E7%A8%8B.png)
通过时序图和第三章节可知，媒体只需在监听事件中监听奖励上报，由客户端处理后续实际奖励发放逻辑即可。
如果，无法实现客户端上报逻辑则可以选择服务端上报流程，即由媒体服务端直接从推啊接收上报信息，为此须先对接
[奖励上报API对接文档](http://yun.tuisnake.com/docking_ducument/%E5%A5%96%E5%8A%B1%E4%B8%8A%E6%8A%A5API%E5%AF%B9%E6%8E%A5%E6%96%87%E6%A1%A3.pdf) （注意：无论选择哪种上报方式，当对接方式为SDK时上述步骤都要完成以上步骤，同时 onMagicRewarded 方法都会上报信息）

#### 五.混淆

``` 
-dontwarn com.qs.magic.sdk.**
-keep class com.qs.magic.sdk.**{ *;}
```

#### 六.验收
```
自测环节：
    激励视频类广告加载正常（视频类广告正常播放，倒计时正常开始） - 下载类广告可以下载安装（重要） - 倒计时结束或者视屏播放完成时能正常退出并回调奖励信息
    激励互动类广告加载正常（互动活动能正常参与，正常出券） - 下载类广告可以下载安装（重要） - 抽到奖励券时能正常退出并回调奖励信息
验收环节：
    提供测试包给我们测试验收，验收进度问题反馈，上线
```



