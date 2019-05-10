package com.yqb.latte_core.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;
import android.util.Log;

import com.yqb.latte_core.R;
import com.yqb.latte_core.delegates.LatteDelegate;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Author   yaoqinbao
 * Time     2019/5/9
 */

/**
 * 不希望之后new这个对象实例，所以用abstract关键字
 */
public abstract class ProxyActivity extends SupportActivity {

    private static final String TAG = "ProxyActivity";

    public abstract LatteDelegate setRootDelegate();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        initContainer(savedInstanceState);
    }

    private void initContainer(@Nullable Bundle savedInstanceState) {
        Log.i(TAG, "initContainer: ");
        final ContentFrameLayout container = new ContentFrameLayout(this);
        container.setId(R.id.delegate_container);
        setContentView(container);
        if (savedInstanceState == null) {
            loadRootFragment(R.id.delegate_container, setRootDelegate());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //主动调用gc，但不一定执行
        System.gc();
        System.runFinalization();
    }
}
