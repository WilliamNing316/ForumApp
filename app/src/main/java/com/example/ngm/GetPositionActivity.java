package com.example.ngm;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
//import com.amap.api.services.core.AMapException;
//import com.amap.api.services.core.LatLonPoint;
//import com.amap.api.services.core.ServiceSettings;
//import com.amap.api.services.geocoder.GeocodeResult;
//import com.amap.api.services.geocoder.GeocodeSearch;
//import com.amap.api.services.geocoder.RegeocodeAddress;
//import com.amap.api.services.geocoder.RegeocodeQuery;
//import com.amap.api.services.geocoder.RegeocodeResult;
//
//public class GetPositionActivity extends AppCompatActivity {
//    //地理位置
//    private GeocodeSearch geocoderSearch;
//    public static LatLonPoint latLonPoint;
//    private double longe;
//    private double widge;
//
//    //声明AMapLocationClient类对象
//    AMapLocationClient mLocationClient = null;
//    //声明AMapLocationClientOption对象
//    public AMapLocationClientOption mLocationOption = null;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_get_position);
//        try {
//            geocoderSearch = new GeocodeSearch(this);
//        } catch (AMapException e) {
//            e.printStackTrace();
//        }
//        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
//            @Override
//            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
//                RegeocodeAddress addr = regeocodeResult.getRegeocodeAddress();
////                etResult.setText("经度:" + etLng.getText() + "、纬度:" + etLat.getText() + "的地址为：\n" + addr.getFormatAddress());
//                Log.e("地址：",addr.getFormatAddress());
//            }
//
//            @Override
//            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
//
//            }
//
//        });
//        try {
//            getCurrentLocationLatLng();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
////        geocoderSearch.setOnGeocodeSearchListener(new GeocodeSearch.OnGeocodeSearchListener() {
////            @Override
////            public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int i) {
////                Log.e("result",regeocodeResult.getRegeocodeAddress().getCity());
////                Log.e(" ","===================");
////            }
////
////            @Override
////            public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
////
////            }
////        });
//
//
//// 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
//
////        RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200,GeocodeSearch.AMAP);
////
////        geocoderSearch.getFromLocationAsyn(query);
//
//
//    }
//
//    public void getCurrentPosition(double longe,double widge){
////
//        latLonPoint = new LatLonPoint(longe,widge);
//        geocoderSearch.getFromLocationAsyn(new RegeocodeQuery(new LatLonPoint(longe, widge), 20 // 区域半径
//                , GeocodeSearch.GPS));
//        Log.e("hao","");
//    }
//
//    private void getCurrentLocationLatLng() throws Exception {
//        ServiceSettings.updatePrivacyAgree(this,true);
//        ServiceSettings.updatePrivacyShow(this,true,true);
//        //初始化定位
//        mLocationClient = new AMapLocationClient(this);
//        //设置定位回调监听
//        mLocationClient.setLocationListener(mLocationListener);
//        //初始化AMapLocationClientOption对象
//        mLocationOption = new AMapLocationClientOption();
//
//        Log.e("falult",1+"");
//        // 同时使用网络定位和GPS定位,优先返回最高精度的定位结果,以及对应的地址描述信息
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        Log.e("falult",2+"");
//        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。默认连续定位 切最低时间间隔为1000ms
//        mLocationOption.setOnceLocation(true);
//
////获取最近3s内精度最高的一次定位结果：
////设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//        mLocationOption.setOnceLocationLatest(true);
////        mLocationOption.setInterval(35000);
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        Log.e("falult",3+"");
//    }
//    public AMapLocationListener mLocationListener = amapLocation -> {
//        ServiceSettings.updatePrivacyAgree(this,true);
//        ServiceSettings.updatePrivacyShow(this,true,true);
//        Log.e("falult",4+"");
//        if (amapLocation != null) {
//            Log.e("falult",5+"");
//            if (amapLocation.getErrorCode() == 0) {
//                //定位成功回调信息，设置相关消息
//                //获取当前定位结果来源，如网络定位结果，详见定位类型表
//                Log.e("falult",6+"");
//                amapLocation.getLocationType();
//
//                //获取纬度
//                double currentLat = amapLocation.getLatitude();
//                //获取经度
//                double currentLon = amapLocation.getLongitude();
//                //获取当前城市位置
////                    mTextViewLocation.setText(amapLocation.getCity());
//                Log.e("city",amapLocation.getCountry());
//                System.out.println(amapLocation.getCountry());
//                Log.e("currentLocation", "currentLat : " + currentLat + " currentLon : " + currentLon);
////                binding.text.setText(amapLocation.getCountry()+currentLat);
//                getCurrentPosition(currentLat,currentLon);
//
////                latLonPoint = new LatLonPoint(currentLat,currentLon);
//                //获取精度信息
////                amapLocation.getAccuracy();
//            } else {
//                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
//                Log.e("AmapError", "location Error, ErrCode:"
//                        + amapLocation.getErrorCode() + ", errInfo:"
//                        + amapLocation.getErrorInfo());
//            }
//        }
//    };
//
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        if (mLocationClient != null) {
//            // 启动定位
//            ServiceSettings.updatePrivacyAgree(this,true);
//            ServiceSettings.updatePrivacyShow(this,true,true);
//            mLocationClient.startLocation();
////            AMapLocation.getErrorCode();
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (mLocationClient != null) {
//            //停止定位
//            mLocationClient.stopLocation();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        if (mLocationClient != null) {
//            //销毁定位客户端
//            mLocationClient.onDestroy();
//        }
//    }
//}








import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.CoordinateConverter;
import com.amap.api.location.DPoint;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.ServiceSettings;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.GeocodeSearch.OnGeocodeSearchListener;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;

public class GetPositionActivity extends AppCompatActivity implements AMapLocationListener, OnGeocodeSearchListener {
    private TextView locationTextView;
    private AMapLocationClient locationClient;
    private GeocodeSearch geocodeSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_position);
        ServiceSettings.updatePrivacyAgree(this,true);
        ServiceSettings.updatePrivacyShow(this,true,true);

        locationTextView = findViewById(R.id.locationTextView);

        // 初始化定位客户端

        try {
            locationClient = new AMapLocationClient(this);
            Log.e("7777","rtqreyqe5y");
        } catch (Exception e) {
            e.printStackTrace();
        }

        locationClient.setLocationListener(this);

        // 初始化逆地理编码搜索
        try {
            geocodeSearch = new GeocodeSearch(this);
        } catch (AMapException e) {
            e.printStackTrace();
        }
        geocodeSearch.setOnGeocodeSearchListener(this);

        // 配置定位参数
        AMapLocationClientOption locationOption = new AMapLocationClientOption();
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        locationOption.setNeedAddress(true);
        locationClient.setLocationOption(locationOption);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 启动定位
        locationClient.startLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // 停止定位
        locationClient.stopLocation();
    }

    @Override
    public void onLocationChanged(AMapLocation location) {
        if (location != null) {
            // 获取定位结果中的经纬度
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();

            // 将经纬度坐标转换为高德地图坐标
            CoordinateConverter converter = new CoordinateConverter(this);
            converter.from(CoordinateConverter.CoordType.GPS);
            try {
                converter.coord(new DPoint(latitude, longitude));
            } catch (Exception e) {
                e.printStackTrace();
            }
            DPoint amapLatLng = null;
            try {
                amapLatLng = converter.convert();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 发起逆地理编码请求
            LatLonPoint latLonPoint = new LatLonPoint(amapLatLng.getLatitude(), amapLatLng.getLongitude());
            RegeocodeQuery query = new RegeocodeQuery(latLonPoint, 200, GeocodeSearch.AMAP);
            geocodeSearch.getFromLocationAsyn(query);
        }}

    // 在逆地理编码回调中处理结果
    @Override
    public void onRegeocodeSearched(RegeocodeResult result, int resultCode) {
            if (resultCode == 1000) {
                if (result != null && result.getRegeocodeAddress() != null) {
                    // 获取位置描述信息
                    String address = result.getRegeocodeAddress().getFormatAddress();
                    locationTextView.setText(address);
                } else {
                    locationTextView.setText("未能获取位置描述");
                }
            } else {
                locationTextView.setText("逆地理编码查询失败");
            }
        }

        @Override
        public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {
            // 不用处理正地理编码的回调方法
        }
    }




