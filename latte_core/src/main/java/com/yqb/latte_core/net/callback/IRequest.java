package com.yqb.latte_core.net.callback;

/**
 * Author   yaoqinbao
 * Time     2019/5/10
 */

//方便处理loading的显示
public interface IRequest {

    void onRequestStart();

    void onRequestEnd();
}
