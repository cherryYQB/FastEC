package com.yqb.latte_core.net;

/**
 * Author   yaoqinbao
 * Time     2019/5/9
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 * <p>
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 */

/**
 * 对于网络框架，要想灵活，（需要传入参数、对顺序无要求、传入什么用什么），用建造者模式比较合适------AlertDialog
 * 网络请求参数：url、常用的值、文件、回调、等待的加载loading
 */

import android.content.Context;

import com.yqb.latte_core.net.callback.IError;
import com.yqb.latte_core.net.callback.IFailure;
import com.yqb.latte_core.net.callback.IRequest;
import com.yqb.latte_core.net.callback.ISuccess;
import com.yqb.latte_core.net.callback.RequestCallbacks;
import com.yqb.latte_core.ui.LatteLoader;
import com.yqb.latte_core.ui.LoaderStyle;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * RestClient在每次build的时候都会生成一个全新的实例，而参数是一次构造完成，不允许更改
 */
public class RestClient {

    //private final Map<String, Object> PARAMS;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    //用final修饰，不允许更改，书写上用final修饰一般都是大写
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final Context CONTEXT;

    //final声明的变量如果没有进行赋值，必须在构造方法里进行赋值
    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError error,
                      RequestBody body,
                      LoaderStyle loaderStyle,
                      Context context) {
        this.URL = url;
        //this.PARAMS = params;
        PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.BODY = body;
        this.CONTEXT = context;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LatteLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }
    }

    private Callback<String> getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
