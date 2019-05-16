package com.yqb.latte_core.app;

/**
 * Author   yaoqinbao
 * Time     2019/5/7
 */

import android.content.Context;

import java.util.HashMap;

/**
 * 加入final，不希望其他人去继承/更改Latte
 * 对外的工具类，所以里面都是静态的方法
 */
public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }

}
