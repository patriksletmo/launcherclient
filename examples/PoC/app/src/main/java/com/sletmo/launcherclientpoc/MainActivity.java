package com.sletmo.launcherclientpoc;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.libraries.launcherclient.LauncherClient;
import com.google.android.libraries.launcherclient.LauncherClientCallbacksAdapter;

public class MainActivity extends AppCompatActivity {
    private LauncherClient launcherClient;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connect to Google Now
        CallbacksAdapter callbacksAdapter = new CallbacksAdapter();
        launcherClient = new LauncherClient(this, callbacksAdapter, true);

        // Set up view pager starting at index 1
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new SimpleFragmentPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    // Update the overlay scroll if we're at the left-most page
                    launcherClient.updateMove(1f - positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state) {
                    case ViewPager.SCROLL_STATE_IDLE:
                        // Stop scroll and set current item to 1 as this is required for this demo
                        launcherClient.endMove();
                        viewPager.setCurrentItem(1);
                        break;
                    case ViewPager.SCROLL_STATE_DRAGGING:
                    case ViewPager.SCROLL_STATE_SETTLING:
                        // Start scroll on both user drag and settling even to support usage of
                        // viewPager.setCurrentItem(0, true). Setting current item without animating
                        // is unsupported in this demo.
                        launcherClient.startMove();
                        break;
                }
            }
        });
    }

    public void showAnimated(View sender) {
        viewPager.setCurrentItem(0, true);
    }

    /* Below is various event forwarding to Google Now */

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();

        if (launcherClient != null) {
            launcherClient.onAttachedToWindow();
        }
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
