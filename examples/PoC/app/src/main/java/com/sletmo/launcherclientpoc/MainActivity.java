package com.sletmo.launcherclientpoc;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.google.android.libraries.launcherclient.LauncherClient;
import com.google.android.libraries.launcherclient.LauncherClientCallbacksAdapter;

public class MainActivity extends AppCompatActivity {
    private LauncherClient launcherClient;
    private CallbacksAdapter callbacksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            int flags = getPackageManager().getApplicationInfo(getPackageName(), 0).flags;
            int isSystem = flags & 0x2;
            int isActualSystem = flags & ApplicationInfo.FLAG_SYSTEM;
            Log.d("MainActivity", "Flags: " + Integer.toHexString(flags));
            Log.d("MainActivity", "isSystem: " + isSystem);
            Log.d("MainActivity", "isActualSystem: " + isActualSystem);
        }
        catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (launcherClient == null) {
            callbacksAdapter = new CallbacksAdapter();
            launcherClient = new LauncherClient(this, callbacksAdapter, true);
        }

        Log.d("MainActivity", "Attaching...");
        launcherClient.onAttachedToWindow();
        Log.d("MainActivity", launcherClient.toString());
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        if (launcherClient != null) {
            launcherClient.onDetachedFromWindow();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (launcherClient != null) {
            launcherClient.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (launcherClient != null) {
            launcherClient.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (launcherClient != null) {
            launcherClient.onDestroy();
        }
    }

    class CallbacksAdapter extends LauncherClientCallbacksAdapter {
        @Override
        public void onOverlayScrollChanged(float progress) {
            Log.d("CallbacksAdapter", "onOverlayScrollChanged: \n  progress: " + progress);
        }

        @Override
        public void onServiceStateChanged(boolean overlayAttached, boolean hotwordActive) {
            Log.d("CallbacksAdapter", "onServiceStateChanged: \n  overlayAttached: " + overlayAttached + "\n  hotwordActive: " + hotwordActive);
        }
    }
}
