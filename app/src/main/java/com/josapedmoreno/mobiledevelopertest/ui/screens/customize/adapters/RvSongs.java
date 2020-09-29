package com.josapedmoreno.mobiledevelopertest.ui.screens.customize.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.siyamed.shapeimageview.RoundedImageView;
import com.josapedmoreno.mobiledevelopertest.R;
import com.josapedmoreno.mobiledevelopertest.ui.screens.customize.CustomRecyclerView;
import com.josapedmoreno.mobiledevelopertest.ui.screens.home.HomeFragmentVM;
import com.josapedmoreno.mobiledevelopertest.utils.data.SongInfoModel;

import java.util.ArrayList;

public class RvSongs extends CustomRecyclerView {
    private static final String TAG = RvSongs.class.getName();
    private Context context;
    private ArrayList<SongInfoModel> songLst;
    private HomeFragmentVM viewModel;

    public RvSongs(ArrayList<SongInfoModel> songList, HomeFragmentVM viewModel) {
        this.songLst = songList;
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        switch (viewType) {
            case R.layout.item_song:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_song, parent, false);
                return new ViewHolder(view);
            default:
                throw new IllegalArgumentException(parent.getContext().getString(R.string.illegal_argument_exception));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == R.layout.item_song) ((RvSongs.ViewHolder)holder).bind(songLst, position);
    }

    @Override
    public int getItemCount() {
        return songLst.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_song;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void updateList(ArrayList<SongInfoModel> newList, Activity activity) {
        Callback callback = new Callback(this.songLst, newList);
        Disposable diff = getDiff(callback)
                .subscribeOn(Schedulers.newThread())
                .subscribe(diffResult -> {
                    this.songLst.clear();
                    this.songLst.addAll(newList);
                    activity.runOnUiThread(() -> {
                        diffResult.dispatchUpdatesTo(this);
                    });
                });
    }

    private Observable<DiffUtil.DiffResult> getDiff(Callback callback) {
        return Observable.fromCallable(() -> DiffUtil.calculateDiff(callback));
    }

    public final class ViewHolder extends RecyclerView.ViewHolder {
        protected final TextView tvSongTitle;
        protected final TextView tvSongArtist;
        protected final RoundedImageView rivAlbumArt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSongTitle = itemView.findViewById(R.id.tvSongTitle);
            tvSongArtist = itemView.findViewById(R.id.tvSongArtist);
            rivAlbumArt = itemView.findViewById(R.id.rivAlbumArt);
        }

        public final void bind(ArrayList<SongInfoModel> list, int position) {
            SongInfoModel model = list.get(position);
            tvSongTitle.setText(model.getTrackName());
            tvSongArtist.setText(model.getArtistName());
            String temp = model.getArtworkUrl100().replace("http:", "https:");
            Glide.with(context).load(temp).placeholder(R.drawable.cloud).error(R.drawable.cloud).into(rivAlbumArt);
        }
    }
}
