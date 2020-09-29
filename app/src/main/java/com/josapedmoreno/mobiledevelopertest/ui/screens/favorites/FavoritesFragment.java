package com.josapedmoreno.mobiledevelopertest.ui.screens.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.josapedmoreno.mobiledevelopertest.databinding.FragmentFavoritesBinding;
import com.josapedmoreno.mobiledevelopertest.ui.base.BaseFragment;

public class FavoritesFragment extends BaseFragment implements LifecycleOwner {
    private FavoritesFragmentVM viewModel = null;
    private FragmentFavoritesBinding viewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentFavoritesBinding.inflate(inflater, container, false);
        viewBinding.setViewModel(viewModel);
        viewBinding.setLifecycleOwner(this);
        return viewBinding.getRoot();
    }
}
