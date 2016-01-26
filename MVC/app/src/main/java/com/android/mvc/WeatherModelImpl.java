package com.android.mvc;

import android.content.Context;

import com.android.libcore.log.L;
import com.android.libcore.net.NetError;
import com.android.libcore.net.netapi.BaseNetApi;
import com.android.libcore_ui.net.NetApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-01-25
 */
public class WeatherModelImpl implements WeatherModel{

    private Context mContext;

    public WeatherModelImpl(Context context){
        mContext = context;
    }

    @Override
    public void getWeather(final OnLoadWeatherCallback callback) {
        NetApi.getInstance().jsonObjectRequest(mContext, "http://www.weather.com.cn/data/sk/101010100.html",
                new HashMap<String, String>(), new BaseNetApi.OnNetCallback<JSONObject>() {

            @Override
            public void onSuccess(JSONObject jsonObject) {
                try {
                    jsonObject = new JSONObject(jsonObject.getString("weatherinfo"));
                    WeatherInfo info = new WeatherInfo();
                    info.city = jsonObject.getString("city");
                    info.temp = Double.parseDouble(jsonObject.getString("temp"));
                    info.WD = jsonObject.getString("WD");
                    info.WS = jsonObject.getString("WS");
                    info.time = jsonObject.getString("time");
                    callback.onLoadSuccess(info);
                } catch (JSONException e) {
                    L.e(e);
                }
            }

            @Override
            public void onFail(NetError netError) {
                callback.onError(netError);
            }
        });
    }
}
