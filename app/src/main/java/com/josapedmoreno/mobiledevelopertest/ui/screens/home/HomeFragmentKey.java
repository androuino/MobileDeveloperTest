package com.josapedmoreno.mobiledevelopertest.ui.screens.home;

import androidx.fragment.app.Fragment;

import com.google.auto.value.AutoValue;
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey;

@AutoValue
public abstract class HomeFragmentKey extends DefaultFragmentKey {
    public static HomeFragmentKey create() { return new AutoValue_HomeFragmentKey(); }

    @Override
    protected Fragment instantiateFragment() {
        return new HomeFragment();
    }
}
