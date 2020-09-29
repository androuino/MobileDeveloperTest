package com.josapedmoreno.mobiledevelopertest.ui.screens.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.josapedmoreno.mobiledevelopertest.ui.base.BaseViewModel;
import com.josapedmoreno.mobiledevelopertest.utils.SongsLayerService;
import com.josapedmoreno.mobiledevelopertest.utils.data.ResultModel;
import com.josapedmoreno.mobiledevelopertest.utils.data.SongInfoModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class HomeFragmentVM extends BaseViewModel {
    private MutableLiveData<ArrayList<SongInfoModel>> songsList = new MutableLiveData<>();
    private final static String TAG = "HomeFragmentVM";
    private SongsLayerService service = retrofit.create(SongsLayerService.class);
    private Gson gson = new Gson();

    {
        getPopSongs();
    }

    LiveData<ArrayList<SongInfoModel>> getList = songsList;

    public void getUPC() {
        if (service != null) {
            Call<ResultModel> call = service.getUPC();
            call.enqueue(new Callback<ResultModel>() {
                @Override
                public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        ResultModel model = gson.fromJson(gson.toJson(response.body()), ResultModel.class);
                        for (SongInfoModel item : model.getResults()) {
                            Timber.tag(TAG).d(String.valueOf(model.getResultCount()));
                            Timber.tag(TAG).d(item.getArtistName());
                            Timber.tag(TAG).d(item.getPrimaryGenreName());
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResultModel> call, Throwable t) {

                }
            });
        }
    }

    private void getPopSongs() {
        if (service != null) {
            ArrayList<SongInfoModel> list = new ArrayList<>();
            Call<ResultModel> call = service.getArtist();
            call.enqueue(new Callback<ResultModel>() {
                @Override
                public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                    if (response.isSuccessful()) {
                        assert response.body() != null;
                        ResultModel model = gson.fromJson(gson.toJson(response.body()), ResultModel.class);
                        list.addAll(model.getResults());
                    }
                    songsList.postValue(list);
                }

                @Override
                public void onFailure(Call<ResultModel> call, Throwable t) {

                }
            });
        }
    }
}
