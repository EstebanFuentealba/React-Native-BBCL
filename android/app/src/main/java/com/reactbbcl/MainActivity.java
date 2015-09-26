package com.reactbbcl;

import com.reactbbcl.Globals;

import android.graphics.Color;
import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.content.Intent;
import android.util.Log;
import java.util.*;
import java.lang.Runnable;
import android.content.Context;



import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;


import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;


public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {

    private static final String TAG = "MainActivity";
    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReactRootView = new ReactRootView(this);
        mReactRootView.setBackgroundColor(Color.parseColor("#1d4e90"));
        mReactInstanceManager = ReactInstanceManager.builder()
                .setApplication(getApplication())
                .setBundleAssetName("index.android.bundle")
                .setJSMainModuleName("index.android")
                .addPackage(new MainReactPackage())
                .addPackage(new BBCLPackage())
                .setUseDeveloperSupport(BuildConfig.DEBUG)
                .setInitialLifecycleState(LifecycleState.RESUMED)
                .build();

        Globals g = Globals.getInstance();
        Bundle mBundle = new Bundle();
        mBundle.putString("jsonBBCL", g.getData());
        mReactRootView.startReactApplication(mReactInstanceManager, "reactbbcl", mBundle);
        setContentView(mReactRootView);

        //mReactRootView.setBackgroundColor(Color.TRANSPARENT);
    }

    private class BBCLPackage implements ReactPackage {

      @Override
      public List<NativeModule> createNativeModules(ReactApplicationContext context) {
          List<NativeModule> modules = new ArrayList<>();
          modules.add(new BBCL(context));
          return modules;
      }
      @Override
      public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
      }
      @Override
      public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Arrays.<ViewManager>asList();
      }
    }

    private class BBCL extends ReactContextBaseJavaModule {
      private Context context;

      public BBCL(ReactApplicationContext reactContext) {
        super(reactContext);

        this.context = (Context) reactContext;
      }
      @Override
      public String getName() {
        return "BBCL";
      }
        @ReactMethod
        public void setDefaultColor() {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              mReactRootView.setBackgroundColor(Color.TRANSPARENT);
            }
          });

        }
    }


    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void invokeDefaultOnBackPressed() {
      super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mReactInstanceManager != null) {
            mReactInstanceManager.onResume(this);
        }
    }
}
