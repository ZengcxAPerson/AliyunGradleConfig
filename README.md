
在天朝使用jcenter、mavenCentral及google三个远程仓库，Gradle Sync会很慢，google仓库甚至需要[科学上网](https://github.com/hugetiny/awesome-vpn)才能访问。为了加快Gradle Sync速度，一招教你优先用 [阿里云仓库服务](https://maven.aliyun.com/mvn/view) 的仓库作为下载源。
[![LICENSE](https://img.shields.io/badge/license-Anti%20996-blue.svg)](https://github.com/996icu/996.ICU/blob/master/LICENSE)

### 一劳永逸之道

将本项目的[grdadle/init.d/init.gradle](/gradle/init.d/init.gradle)复制到`USER_HOME/.gradle/`下即可。
`USER_HOME`在Windows上大约为`C:/Users/liyujiang/`，在Linux上大约为`/home/liyujiang/.gradle/`。   
`init.d/init.gradle`内容为：
```gradle
buildscript {
    repositories {
        maven{ url 'https://maven.aliyun.com/repository/public'}
        maven { url 'https://maven.aliyun.com/repositories/jcenter' }
        maven { url 'https://maven.aliyun.com/repositories/google' }
        maven { url 'https://maven.aliyun.com/repository/central' }
    }
}
    
allprojects {
    repositories {
        maven{ url 'https://maven.aliyun.com/repository/public'}
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
        mavenCentral()
        google()
    }
}
```

### 项目模板文件介绍
- .gitignore  通用的Gradle项目版本控制文件忽略规则
- .travis.yml  Github项目的特拉维斯持续集成配置
- app/proguard-rules.pro  通用的混淆规则
- build.gradle Gradle项目构建管理
- publish.gradle Gradle项目发布到Maven仓库及上传到jcenter
- version.gradle Gradle项目依赖项版本统一管理
- publishToMavenLocal.cmd 一键执行发布Gradle项目到本地的Maven仓库
- bintrayUpload.cmd 一键执行上传已发布到本地Maven仓库的项目到jcenter
