package com.josapedmoreno.mobiledevelopertest.utils.data;

import java.util.ArrayList;

public class ResultModel {
    private int resultCount;
    ArrayList<SongInfoModel> results;

    public ResultModel(int resultCount, ArrayList<SongInfoModel> results) {
        this.resultCount = resultCount;
        this.results = results;
    }

    public int getResultCount() {
        return resultCount;
    }

    public ArrayList<SongInfoModel> getResults() {
        return results;
    }
}
