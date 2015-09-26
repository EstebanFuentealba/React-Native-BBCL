package com.reactbbcl;

import com.reactbbcl.HTTPClient;
import com.reactbbcl.Globals;
import java.lang.Runnable;
import java.lang.Thread;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.LifecycleState;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;

public class SplashScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        /**
         * Showing splashscreen while making network calls to download necessary
         * data before launching the app Will use AsyncTask to make http call
         */

        new PrefetchData().execute();

    }

    /**
     * Async Task to make http call
     */
    private class PrefetchData extends AsyncTask<Void, Void, Void> {

        protected final Intent i = new Intent(getApplicationContext(), MainActivity.class);
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // before making http calls

        }

        @Override
        protected Void doInBackground(Void... arg0) {
             Globals g = Globals.getInstance();
             g.setData("{\"success\":false}");
             try {
               // After completing http call
               // will close this activity and lauch main activity
               HTTPClient client = new HTTPClient();
               String json = (String)client.getJSON("http://www.biobiochile.cl/est/data_portada.json");
               g.setData(json);
               //i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            startActivity(i);
            overridePendingTransition(R.layout.activityfadein, R.layout.splashfadeout);
            // close this activity
            finish();
        }
    }
}
