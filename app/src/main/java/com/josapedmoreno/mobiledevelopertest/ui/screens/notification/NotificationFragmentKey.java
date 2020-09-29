package com.josapedmoreno.mobiledevelopertest.ui.screens.notification;

import androidx.fragment.app.Fragment;

import com.google.auto.value.AutoValue;
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey;

@AutoValue
public abstract class NotificationFragmentKey extends DefaultFragmentKey {
    public static NotificationFragmentKey create() {
        return new AutoValue_NotificationFragmentKey();
    }

    @Override
    protected Fragment instantiateFragment() {
        return new NotificationFragment();
    }
}
