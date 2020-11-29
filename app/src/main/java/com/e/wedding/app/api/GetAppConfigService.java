package com.e.wedding.app.api;

import com.e.wedding.app.model.AppConfiguration;
import com.e.wedding.app.utils.Values;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAppConfigService {
    @GET(Values.APP_CONFIG_URL)
    Call<AppConfiguration> getAppConfig();
}
