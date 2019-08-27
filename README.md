# 接入指南

服务端对接领奖：https://yun.duiba.com.cn/tuiasdk激励互动开发文档.pdf

Android对接领奖：
一.依赖引入
项目的build.gradle文件中添加
       
       buildscript {
        repositories {
                google()
                jcenter()
                maven { url "https://jitpack.io" }
                maven { url "https://dl.bintray.com/luozeyu/maven" }
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
                        maven { url "https://dl.bintray.com/luozeyu/maven" }
                 }
          }

app下的build.gradle添加：

    dependencies {
        implementation ('com.tm:tm:1.0.0.0'){
                transitive = true
        }
     }

二.权限(sdk内部已经处理相关权限问题，如果遇到冲突咨询对应开发即可)

        <uses-permission android:name="android.permission.INTERNET"/>
   
三.使用(参考demo中的MainActivity使用,替换对应申请的appId,appKey,slotId,userId(媒体自己的用户id))

       1.创建MagicVideoView对象
         MagicVideoView magicVideoView =new MagicVideoView(getApplication(),"userId","appId","appkey","slotId");
       2.初始化
              magicVideoView.init(new MagicVideoListener() {

                    @Override
                    public void onMagicRequestAd() {
                        Log.d("onMagicRequest","onMagicRequestAd");
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
                        Log.d("onMagicRequest","onMagicRewarded"+msg);
                    }
                });
                
                onMagicRewarded msg->json字符串
                {"finishType":1,"orderId":"168408070629","userId":"1233","timestamp":"1566791113031","prizeFlag":"ecb-video-sdk","appKey":"4W8ReCvDm4fy3Fpn52MgPgUWmdfS","sign":"5093659d6bf802d1a407df81d6aab9f9","score":null,"reason":"-1","url":null}
                
                userId String 用户 id，用户在媒体的唯一识别信息，来源于活动链接中的&userId=xxx，由媒体拼接提供
                timestamp1 Number 时间戳
                prizeFlag1 2 String 请求上报的奖励在对接方媒体系统内的奖励标识，用于标识具体的奖励类型，由媒体提供
                orderId1 String 推啊订单号，具有唯一性，幂等由媒体保障
                appKey1 String 媒体公钥
                sign1 String 签名
                score Number 如果是数值类型的奖励，则同时请求充值对应的数值 score，比如积分、金币、倍数等
                
          3.在activity的onDestroy中处理
              @Override
              protected void onDestroy() {
                     super.onDestroy();
                     if (magicVideoView!=null){
                             magicVideoView.destory();
                     }
              }
                
四.混淆

          -keep class com.qs.magic.sdk.**{ *;}     

五.对接问题

        咨询对应开发或者直接git提交问题


