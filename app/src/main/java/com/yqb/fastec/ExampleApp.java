package com.yqb.fastec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.yqb.latte_core.app.Latte;
import com.yqb.latte_ec.icon.FontEcModule;

/**
 * Author   yaoqinbao
 * Time     2019/5/7
 */
public class ExampleApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())   //引用第三方的字体图片库
                .withIcon(new FontEcModule())        //引用自定义的字体图片库
                .withApiHost("http://127.0.0.1/")    //对接后台，分测试和上线用host，这里统一配置 便于修改
                .configure();
    }
}
