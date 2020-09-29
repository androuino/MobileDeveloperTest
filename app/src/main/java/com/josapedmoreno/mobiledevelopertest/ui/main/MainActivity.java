package com.josapedmoreno.mobiledevelopertest.ui.main;

import android.os.Bundle;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.josapedmoreno.mobiledevelopertest.R;
import com.josapedmoreno.mobiledevelopertest.ui.base.BaseActivity;
import com.josapedmoreno.mobiledevelopertest.ui.screens.favorites.FavoritesFragmentKey;
import com.josapedmoreno.mobiledevelopertest.ui.screens.home.HomeFragmentKey;
import com.josapedmoreno.mobiledevelopertest.ui.screens.notification.NotificationFragmentKey;
import com.zhuinden.simplestack.Backstack;
import com.zhuinden.simplestack.History;
import com.zhuinden.simplestack.SimpleStateChanger;
import com.zhuinden.simplestack.StateChange;
import com.zhuinden.simplestack.navigator.Navigator;
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentStateChanger;

import javax.annotation.Nonnull;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements SimpleStateChanger.NavigationHandler {
    private static String TAG = "MainActivity";

    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    @BindView(R.id.root)
    ViewGroup root;

    DefaultFragmentStateChanger fragmentStateChanger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigation.setOnNavigationItemSelectedListener(item -> {
            Backstack backstack = Navigator.getBackstack(this);
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    backstack.setHistory(History.of(HomeFragmentKey.create()), StateChange.REPLACE);
                    return true;
                case R.id.navigation_favorites:
                    backstack.setHistory(History.of(FavoritesFragmentKey.create()), StateChange.REPLACE);
                    return true;
                case R.id.navigation_notifications:
                    backstack.setHistory(History.of(NotificationFragmentKey.create()), StateChange.REPLACE);
                    return true;
            }
            return false;
        });

        fragmentStateChanger = new DefaultFragmentStateChanger(getSupportFragmentManager(), R.id.root);

        Navigator.configure().setStateChanger(new SimpleStateChanger(this))
                .install(this, root, History.single(HomeFragmentKey.create()));
    }

    @Override
    public void onBackPressed() {
        if(!Navigator.onBackPressed(this)) {
            super.onBackPressed();
        }
    }

    @Override
    public void onNavigationEvent(@Nonnull StateChange stateChange) {
        fragmentStateChanger.handleStateChange(stateChange);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}