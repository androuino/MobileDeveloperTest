package com.josapedmoreno.mobiledevelopertest.ui.screens.customize.adapters;

import androidx.recyclerview.widget.DiffUtil;

import com.josapedmoreno.mobiledevelopertest.utils.data.SongInfoModel;

import java.util.ArrayList;

import timber.log.Timber;

public class Callback extends DiffUtil.Callback {
    private final static String TAG = Callback.class.getName();
    private ArrayList<SongInfoModel> oldList;
    private ArrayList<SongInfoModel> newList;

    public Callback(ArrayList<SongInfoModel> oldList, ArrayList<SongInfoModel> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        try {
            return oldList.get(oldItemPosition).getWrapperType().equals(newList.get(newItemPosition).getWrapperType());
        } catch (Exception e) {
            Timber.tag(TAG).e("Error on " + TAG + "->areItemsTheSame()");
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        try {
            SongInfoModel old = oldList.get(oldItemPosition);
            SongInfoModel nw = newList.get(newItemPosition);
            return old.getWrapperType().equals(nw.getArtistName());
        } catch (Exception e) {
            Timber.tag(TAG).e("Error on " + TAG + "->areContentsTheSame()");
        }
        return false;
    }
}
