# 接入指南

#### 一.依赖引入
方式一.Gradle依赖（推荐）
```
   1.目的build.gradle文件中添加

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

  2.app下的build.gradle添加：(最小支持minSdkVersion 15)

  dependencies {
        implementation ('com.tuia:tm:1.1.0.0-release'){
                transitive = true
        }
   }
```
方式二.maven依赖
```

  <dependency>
      <groupId>com.tuia</groupId>
      <artifactId>tm</artifactId>
      <version>1.1.0.0-release</version>
      <type>pom</type>
  </dependency>
    
```
 方式三.aar引入(媒体特殊定制,不建议单独使用)
 ```
    Gson库比较通用需要单独引入
    aar下载地址：
    https://yun.duiba.com.cn/magic/sdk/1.0.0.0/magic-sdk-1.0.0.0.aar
 
 ```
#### 二.权限(sdk内部已经处理相关权限问题，如果遇到冲突咨询对应开发即可)
```

  <uses-permission android:name="android.permission.INTERNET"/>

```
#### 三.使用
1.创建MagicVideoView对象
```

  MagicVideoView magicVideoView =new MagicVideoView(MagicApp.getApp(),
                "userId","appId","appkey","slotId",
                ”deviceId“,new MagicVideoListener() {

            @Override
            public void onMagicRequestAd() {
                Log.d("onMagicRequest","onMagicRequestRewardVideo");
            }

            @Override
            public void onMagicAdSuccessed() {
                Log.d("onMagicRequest","onMagicAdSuccessed");
            }

            @Override
            public void onMagicAdEmpty() {
                Log.d("onMagicRequest","onMagicAdEmpty");
            }

            @Override
            public void onMagicAdFailed(Response<String> response) {
                Log.d("onMagicRequest","onMagicAdFailed"+response.body());
            }

            @Override
            public void onMagicRewarded(String msg) {
                Log.d("onMagicRequest","onMagicReward"+msg);
                ToastUtils.showShort(msg);
            }
        });
	
```
参考demo中的MainActivity使用,替换对应申请的appId,appKey,slotId,userId，对应字段释义如下，字段均必填 

| 名称 | 类型 | 备注 |
| :---------------------: | :---------------------: | :----------------------: |
| appId | Long | 推啊媒体ID，通过TUIA媒体平台注册获得 |
| appKey | String | 媒体公钥，通过TUIA媒体平台注册获得 |
| slotId | Long | 广告位ID，通过TUIA媒体平台注册获得 |
| userId | String | 媒体的用户ID，用于发送对应的奖励 |
| deviceId | String | 媒体的用户deviceId，用于用户唯一性确认 |
| MagicVideoListener |  | 广告回调监听 |

2.初始化(在Application初始onCreate方法中调用)
```

   MagicSDK.init(Application);
       
```
3.请求广告,加载广告(方式一和方式二任选一种)
```

   方式一.缓存模式（先缓存,在需要的时候调用广告加载）
  	1.请求广告
	
   		magicVideoView.loadAd();
		
        2.加载广告
	
   		if (magicVideoView.checkLocalData()){
       			magicVideoView.openNewVideoTask(MainActivity.this,false);
   		}else {
       			magicVideoView.openNewVideoTask(MainActivity.this,true);
   		}
   
   方式二.在线模式(直接请求,加载广告)
   
   		magicVideoView.openNewVideoTask(MainActivity.this,true);
       
```
3.返回奖励信息   在onMagicRewarded方法中会返回上报的奖励信息，JSON字符串如下
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

 4.在activity的onDestroy中处理
```

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (magicVideoView!=null){
           magicVideoView.destory();
        }
    }
    
```    

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

#### 六.对接问题

  咨询对应开发或者直接git提交问题




