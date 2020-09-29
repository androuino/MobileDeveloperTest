package com.josapedmoreno.mobiledevelopertest.ui.screens.favorites;

import androidx.fragment.app.Fragment;

import com.google.auto.value.AutoValue;
import com.zhuinden.simplestackextensions.fragments.DefaultFragmentKey;

@AutoValue
public abstract class FavoritesFragmentKey extends DefaultFragmentKey {
    public static FavoritesFragmentKey create() {
        return new AutoValue_FavoritesFragmentKey();
    }

    @Override
    protected Fragment instantiateFragment() {
        return new FavoritesFragment();
    }
}
