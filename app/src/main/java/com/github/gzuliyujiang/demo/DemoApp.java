/**
 * Copyright (c) 2019-2020 gzu-liyujiang <1032694760@qq.com>
 */
package com.github.gzuliyujiang.demo;

import android.app.Application;

import com.github.gzuliyujiang.logger.Logger;

/**
 * Created by liyujiang on 2020/5/20.
 */
public class DemoApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.ENABLE = BuildConfig.DEBUG;
    }

}
