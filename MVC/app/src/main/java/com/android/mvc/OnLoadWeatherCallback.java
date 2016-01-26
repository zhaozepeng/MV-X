package com.android.mvc;

import com.android.libcore.net.NetError;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-01-25
 */
public interface OnLoadWeatherCallback {
    void onLoadSuccess(WeatherInfo info);
    void onError(NetError error);
}
