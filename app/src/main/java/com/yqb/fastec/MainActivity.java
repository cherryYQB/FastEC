package com.yqb.fastec;

import android.util.Log;

import com.yqb.latte_core.activities.ProxyActivity;
import com.yqb.latte_core.delegates.LatteDelegate;

public class MainActivity extends ProxyActivity {

    private static final String TAG = "MainActivity";
    
    @Override
    public LatteDelegate setRootDelegate() {
        Log.i(TAG, "setRootDelegate: ");
        return new ExampleDelegate();
    }
}
