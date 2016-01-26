package com.android.mvc;

import android.os.Bundle;
import android.widget.TextView;

import com.android.libcore.Toast.T;
import com.android.libcore.net.NetError;
import com.android.libcore_ui.activity.BaseActivity;
import com.android.libcore_ui.dialog.LoadingDialog;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-01-25
 */
public class WeatherActivity extends BaseActivity implements OnLoadWeatherCallback{
    private TextView tv_name;
    private TextView tv_temperature;
    private TextView tv_wind_d;
    private TextView tv_wind_s;
    private TextView tv_time;

    private LoadingDialog ld;

    private WeatherModel weatherModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_temperature = (TextView) findViewById(R.id.tv_temperature);
        tv_wind_d = (TextView) findViewById(R.id.tv_wind_d);
        tv_wind_s = (TextView) findViewById(R.id.tv_wind_s);
        tv_time = (TextView) findViewById(R.id.tv_time);

        weatherModel = new WeatherModelImpl(this);
        ld = new LoadingDialog(this);
        ld.setLoadingText("正在获取天气...");
        ld.show();
        weatherModel.getWeather(this);
    }

    private void onShowWeather(WeatherInfo weatherInfo){
        tv_name.setText(weatherInfo.city);
        tv_temperature.setText(weatherInfo.temp+"");
        tv_wind_d.setText(weatherInfo.WD);
        tv_wind_s.setText(weatherInfo.WS);
        tv_time.setText(weatherInfo.time);
    }

    @Override
    public void onLoadSuccess(WeatherInfo info) {
        ld.dismiss();
        onShowWeather(info);
    }

    @Override
    public void onError(NetError error) {
        ld.dismiss();
        T.getInstance().showShort(error.errorCode +" "+ error.errorMessage);
    }
}
