package com.josapedmoreno.mobiledevelopertest.ui.screens.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;

import com.josapedmoreno.mobiledevelopertest.databinding.FragmentNotificationBinding;
import com.josapedmoreno.mobiledevelopertest.ui.base.BaseFragment;

public class NotificationFragment extends BaseFragment implements LifecycleOwner {
    private static final String TAG = "NotificationFragment";
    private NotificationFragmentVM viewModel = null;
    private FragmentNotificationBinding viewBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentNotificationBinding.inflate(inflater, container, false);
        viewBinding.setViewModel(viewModel);
        viewBinding.setLifecycleOwner(this);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
