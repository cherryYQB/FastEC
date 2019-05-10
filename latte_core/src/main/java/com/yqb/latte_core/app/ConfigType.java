package com.yqb.latte_core.app;

/**
 * Author   yaoqinbao
 * Time     2019/5/7
 */

/**
 * ConfigType是枚举类，在整个应用程序里面是唯一的单例，并且只能被初始化一次
 * API_HOST：配置网络请求的域名
 * APPLICATION_CONTEXT：全局的上下文
 * CONFIG_READY：控制初始化配置是否完成
 * ICON：存储自己的初始化项目
 */
public enum ConfigType {
    API_HOST,
    APPLICATION_CONTEXT,
    CONFIG_READY,
    ICON
}
