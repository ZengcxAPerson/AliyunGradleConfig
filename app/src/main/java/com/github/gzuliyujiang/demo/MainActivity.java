/**
 * Copyright (c) 2019-2020 gzu-liyujiang <1032694760@qq.com>
 */
package com.github.gzuliyujiang.demo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.gzuliyujiang.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.print("测试");
    }
}
