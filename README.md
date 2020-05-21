# 安卓项目工程模板

[![travis-ci](https://travis-ci.org/gzu-liyujiang/AliyunGradleConfig.svg?branch=master)](https://travis-ci.org/gzu-liyujiang/AliyunGradleConfig)
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

- 阿里云远程仓库加速
- 发布到maven仓库
- 代码混淆、资源混淆
- 多渠道自动打包

### 项目模板文件介绍

- .gitignore  通用的GIT版本控制文件忽略规则
- .travis.yml  Github项目的特拉维斯持续集成配置
- app/proguard-common.pro  通用的混淆规则
- app/build.gradle 多渠道打包APK
- build.gradle Gradle项目构建管理
- gradle/publish.gradle Gradle项目发布到Maven仓库及上传到jcenter
- gradle/resguard.gradle Gradle项目资源文件混淆配置管理

在天朝使用jcenter、mavenCentral及google三个远程仓库，Gradle Sync会很慢，google仓库甚至需要[科学上网](https://github.com/hugetiny/awesome-vpn)才能访问。为了加快Gradle Sync速度，一招教你优先用 [阿里云仓库服务](https://maven.aliyun.com/mvn/view) 的仓库作为下载源。

### 一劳永逸之道

将本项目的[grdadle/init.d/init.gradle](/gradle/init.d/init.gradle)复制到`USER_HOME/.gradle/`下即可。
`USER_HOME`在Windows上大约为`C:/Users/liyujiang/.gradle/`，在Linux上大约为`/home/liyujiang/.gradle/`。   
`init.d/init.gradle`内容为：
```gradle
buildscript {
    repositories {
        maven { url 'https://maven.aliyun.com/repositories/jcenter' }
        maven { url 'https://maven.aliyun.com/repositories/google' }
    }
}
    
allprojects {
    repositories {
        maven { url 'https://maven.aliyun.com/repositories/jcenter' }
        maven { url 'https://maven.aliyun.com/repositories/google' }
        maven { url 'https://maven.aliyun.com/repository/central' }
        maven { url "https://jitpack.io" }
    }
}
```

### Maven仓库列表
<table>
    <tr>
        <th>仓库名</th>
        <th> 简介</th>
        <th> 实际地址</th>
        <th> 使用地址</th>
    </tr>
    <tr>
        <td>jcenter</td>
        <td>JFrog公司提供的仓库</td>
        <td align="left">http://jcenter.bintray.com</td>
        <td align="left">https://maven.aliyun.com/repository/jcenter <br/> https://maven.aliyun.com/nexus/content/repositories/jcenter</td>
    </tr>
    <tr>
        <td>mavenLocal</td>
        <td>本台电脑上的仓库</td>
        <td align="left">{USER_HOME}/.m2/repository</td>
        <td align="left">C:/Users/liyujiang/.m2/repository (Windows) <br/> /home/liyujiang/.m2/repository (Linux)</td>
    </tr>
    <tr>
        <td>mavenCentral</td>
        <td>Sonatype公司提供的中央库</td>
        <td align="left">http://central.maven.org/maven2</td>
        <td align="left">https://maven.aliyun.com/repository/central <br/> https://maven.aliyun.com/nexus/content/repositories/central</td>
    </tr>
    <tr>
        <td>google</td>
        <td>Google公司提供的仓库</td>
        <td align="left">https://maven.google.com</td>
        <td align="left">https://maven.aliyun.com/repository/google <br/> https://maven.aliyun.com/nexus/content/repositories/google <br/> https://dl.google.com/dl/android/maven2</td>
    </tr>
    <tr>
        <td>jitpack</td>
        <td>JitPack提供的仓库</td>
        <td align="left">https://jitpack.io</td>
        <td align="left">https://jitpack.io</td>
    </tr>
    <tr>
        <td>public</td>
        <td align="left" colspan="2">jcenter和mavenCentral的聚合仓库</td>
        <td align="left">https://maven.aliyun.com/repository/public <br/> https://maven.aliyun.com/nexus/content/groups/public</td>
    </tr>
    <tr>
        <td>gradle-plugin</td>
        <td>Gradle插件仓库</td>
        <td align="left">https://plugins.gradle.org/m2</td>
        <td align="left"> https://maven.aliyun.com/repository/gradle-plugin <br/> https://maven.aliyun.com/nexus/content/repositories/gradle-plugin</td>
    </tr>
</table>

### 阿里云镜像源配置
在项目根目录下的`build.gradle`的`allprojects.repositories`闭包内的最前面添加阿里云的仓库地址：    
```groovy
buildscript {
    repositories {
        jcenter()
        google()
    }
}

allprojects {
    repositories {
        flatDir { dirs "libs" }
        mavenLocal()
        maven {
            url 'https://maven.aliyun.com/repository/jcenter'
        }
        maven {
            url 'https://maven.aliyun.com/repository/central'
        }
        maven {
            url 'https://maven.aliyun.com/repository/google'
        }
        jcenter()
        google()
    }
}
```

### 项目发布到Maven仓库

项目发布到Maven仓库前，需要基于某个`git commit`创建相应发布版本的`tag`，推送该`tag`才会触发。

- 项目发布到`jitpack`，需要使用github账号[登录到JitPack](https://jitpack.io)，`Look up`相应的库然后去`Get it`。
- 项目发布到`jcenter`，需要[登录到Bintray](https://bintray.com/login)后选择`maven`创建相应应的包(Add a Package)，修改`publish.gradle`中的以下下信息：

```groovy
def pomLibGroupName = 'com.github.gzuliyujiang'
def pomLibArtifactId = '[TODO] Repository Name'
def pomLibVersion = new Date().format("yyyy.M.d")
def pomLibDescription = '[TODO] Repository description for Android'
def pomSiteUrl = 'https://github.com/liyujiang-gzu/[TODO] Repository Name'
def pomGitUrl = 'https://github.com/gzu-liyujiang/[TODO] Repository Name'
def pomIssueUrl = 'https://github.com/gzu-liyujiang/[TODO] Repository Name/issues'
def pomLicenses = ["MIT"]
//开发者信息
def pomDeveloperId = 'liyujiang-gzu'
def pomDeveloperOrg = 'gzu-liyujiang'
def pomDeveloperName = '李玉江'
def pomDeveloperEmail = 'liyujiang_tk@yeah.net'
```
