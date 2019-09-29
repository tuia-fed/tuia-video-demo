# 接入指南

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

  2.app下的build.gradle添加：(最小支持minSdkVersion 15)

  dependencies {
        implementation ('com.tuia:tm:1.2.2.2-release'){
                transitive = true
        }
   }
```
方式二.maven依赖
```

  <dependency>
      <groupId>com.tuia</groupId>
      <artifactId>tm</artifactId>
      <version>1.2.2.2-release</version>
      <type>pom</type>
  </dependency>
    
```

#### 二.权限(sdk内部已
