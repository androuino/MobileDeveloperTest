package com.josapedmoreno.mobiledevelopertest.ui.screens.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.josapedmoreno.mobiledevelopertest.R;
import com.josapedmoreno.mobiledevelopertest.databinding.FragmentHomeBinding;
import com.josapedmoreno.mobiledevelopertest.ui.base.BaseFragment;
import com.josapedmoreno.mobiledevelopertest.ui.screens.customize.adapters.RvSongs;
import com.josapedmoreno.mobiledevelopertest.utils.CustomLinearLayout;
import com.josapedmoreno.mobiledevelopertest.utils.data.SongInfoModel;

import java.util.ArrayList;

import butterknife.BindView;
import timber.log.Timber;

public class HomeFragment extends BaseFragment implements LifecycleOwner {
    private static final String TAG = HomeFragment.class.getName();
    private HomeFragmentVM viewModel = null;
    private FragmentHomeBinding viewBinding;
    private RvSongs rvSongs;
    private RecyclerView rvSongAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeFragmentVM.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = FragmentHomeBinding.inflate(inflater, container, false);
        viewBinding.setViewModel(viewModel);
        viewBinding.setLifecycleOwner(this);
        return viewBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (rvSongAdapter != null) {
            Timber.tag(TAG).d("rvSongAdapter is initialized");
        } else {
            rvSongAdapter = view.findViewById(R.id.rvSongAdapter);
            initAdapter();
        }
        initObservables();
    }

    private void initAdapter() {
        ArrayList<SongInfoModel> list = new ArrayList<>(1);
        rvSongs = new RvSongs(list, viewModel);
        rvSongAdapter.setAdapter(rvSongs);
        rvSongAdapter.setLayoutManager(new CustomLinearLayout(getActivity()));
        rvSongs.notifyDataSetChanged();
    }

    private void initObservables() {
        viewModel.getList.observe(getViewLifecycleOwner(), songInfoModels -> {
            if (songInfoModels.size() > 0) {
                Timber.tag(TAG).d(String.valueOf(songInfoModels.size()));
                rvSongs.updateList(songInfoModels, getActivity());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.getList.removeObservers(getViewLifecycleOwner());
    }
}
