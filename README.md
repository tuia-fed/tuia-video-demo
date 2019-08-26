# 接入指南
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

         MagicVideoView magicVideoView =new MagicVideoView(getApplication(),
                        "userId","appId","appkey","slotId");
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
                
四.混淆

          -keep class com.qs.magic.sdk.**{ *;}     

五.对接问题

        咨询对应开发或者直接git提交问题
