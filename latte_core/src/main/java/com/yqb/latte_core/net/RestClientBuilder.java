package com.yqb.latte_core.net;

/**
 * Author   yaoqinbao
 * Time     2019/5/10
 */

import android.content.Context;

import com.yqb.latte_core.net.callback.IError;
import com.yqb.latte_core.net.callback.IFailure;
import com.yqb.latte_core.net.callback.IRequest;
import com.yqb.latte_core.net.callback.ISuccess;
import com.yqb.latte_core.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 将建造者和数组类分开，不使用静态内部类
 */
public class RestClientBuilder {

    //private Map<String, Object> mParams;  这里直接在RestCreator里面去设置个全局变量
    //private static final Map<String, Object> PARAMS = RestCreator.PARAMS;   再进行优化，使用惰性加载，更加严谨
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    //书写规范，类变量以m开头
    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;
    private RequestBody mBody = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private Context mContext = null;

    //不加public修饰，除了此module中的类（如RestClient等），不允许外部类去new这个RestClientBuilder类
    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        //this.mParams = params;
        PARAMS.putAll(params);
        return this;
    }

    //重载，解决不传入map，而传入键值对的兼容
    public final RestClientBuilder params(String key, Object value) {
        /*if (mParams == null) {               //Retrofit不允许传入的Map为空，不然会有异常，所以这里以键值对形式传入，需要进行判断
            mParams = new WeakHashMap<>();   //请求的值当内部不用的时候，希望系统回收，所以用WeakHashMap
        }*/    //使用惰性加载，肯定不为null
        //this.mParams.put(key, value);
        PARAMS.put(key, value);
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    //传入原始数据
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    //回调处理
    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    /*private Map<String, Object> checkParams() {
        if (mParams == null) {
            return new WeakHashMap<>();
        }
        return mParams;
    }*/   //惰性加载，不为null，不需要检查

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClient build() {
        return new RestClient(mUrl, /*mParams*/PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody, mLoaderStyle, mFile, mContext);
    }
}
