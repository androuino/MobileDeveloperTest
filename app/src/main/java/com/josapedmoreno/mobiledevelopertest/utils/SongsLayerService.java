package com.josapedmoreno.mobiledevelopertest.utils;

import com.josapedmoreno.mobiledevelopertest.utils.data.ResultModel;

import retrofit2.Call;
import retrofit2.http.GET;

import static com.josapedmoreno.mobiledevelopertest.Constant.GET_ARTIST;
import static com.josapedmoreno.mobiledevelopertest.Constant.POP_SONGS;
import static com.josapedmoreno.mobiledevelopertest.Constant.UPC;

public interface SongsLayerService {
    @GET(POP_SONGS)
    Call<ResultModel> getPopSongs();

    @GET(UPC)
    Call<ResultModel> getUPC();

    @GET(GET_ARTIST)
    Call<ResultModel> getArtist();
}
