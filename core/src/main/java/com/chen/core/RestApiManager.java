package com.chen.core;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.chen.common.util.NetUtil;
import com.chen.core.api.CommonApi;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chenxianglin on 2018/1/16.
 * Class note:
 */

public class RestApiManager {
    private static final String BASE_URL = "baseUrl";
    private static final int HTTP_RESPONSE_CACHE_MAX_SIZE = 10 * 1024 * 1024;

    private static RestApiManager mInstance;
    private Context mContext;
    private String mCacheDir;
    private String mHeader;

    //1.初始化参数，缓存，模式，header
    //2.创建OkHttpClient，cache,baseUrl,factory，及host，ssl，https的认证，log日志等拦截器的配置
    //3.创建interceptor，配置基本参数，上述2
    //4.选择模式，创建Retrofit


    public static RestApiManager getInstance() {
        if (mInstance == null) {
            synchronized (RestApiManager.class) {
                if (mInstance == null) {
                    mInstance = new RestApiManager();
                }
            }
        }
        return mInstance;
    }

    private RestApiManager init(Context context, String value) {
        mContext = context.getApplicationContext();
        if (mCacheDir == null) {
            mCacheDir = mContext.getCacheDir() + "/responses";
        }
        if (mHeader == null) {
            mHeader = initHeader(context, value);
            Log.d("rest_api", mHeader);
        }
        return this;
    }

    private String initHeader(Context context, String value) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("channelid=");
        if (value == null) {
            buffer.append("unknown");
        } else {
            buffer.append(value);
        }
        buffer.append("&").append("something==").append("sm");
        return buffer.toString();
    }

    private void init(Context context) {
//        Interceptor interceptor = new ConnectInterceptor();
        HttpLoggingInterceptor mInterceptor = new HttpLoggingInterceptor();
        mInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient().newBuilder()
                //添加拦截
                .addInterceptor(mInterceptor)
                //错误重试
                .retryOnConnectionFailure(true)
                //超时
                .connectTimeout(30, TimeUnit.SECONDS)
                //设置缓存目录
                .cache(getCache(context))//cache
                //设置缓存
                .addInterceptor(getCacheInterceptor())
                //调试数据库，debug
                .addNetworkInterceptor(new StethoInterceptor())
                //host 认证
//                .sslSocketFactory(null,null)
//                .hostnameVerifier(null)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CommonApi apiService = retrofit.create(CommonApi.class);
    }

    private Cache getCache(Context context) {
        Cache cache = null;
        File appDataDir = context.getFilesDir();
        File nameFile = context.getDir("dir", Context.MODE_PRIVATE);
        File baseDir = Environment.getExternalStorageDirectory().getAbsoluteFile();
        return new Cache(nameFile, HTTP_RESPONSE_CACHE_MAX_SIZE);
    }

    //有网和没网都先读缓存，统一缓存策略，降低服务器压力。
    private Interceptor getCacheInterceptor() {
        Interceptor cacheInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = chain.proceed(request);

                String cacheControl = request.cacheControl().toString();
                if (TextUtils.isEmpty(cacheControl)) {
                    cacheControl = "public, max-age=60";
                }
                return response.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
        };
        return cacheInterceptor;
    }

    private Interceptor cacheInterceptor(final Context context) {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();

                if (!NetUtil.isConnectNet(context)) {
                    request = request.newBuilder()
                            //强制使用缓存
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                }

                Response response = chain.proceed(request);

                if (NetUtil.isConnectNet(context)) {
                    //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                    String cacheControl = request.cacheControl().toString();
                    Log.i("Http", "has network ,cacheControl=" + cacheControl);
                    return response.newBuilder()
                            .header("Cache-Control", cacheControl)
                            .removeHeader("Pragma")
                            .build();
                } else {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    Log.i("Http", "network error ,maxStale=" + maxStale);
                    return response.newBuilder()
                            .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                            .removeHeader("Pragma")
                            .build();
                }

            }
        };
    }
}
