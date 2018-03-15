package huang.com.retrofit;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import huang.com.retrofit.api.Api360Service;
import huang.com.retrofit.api.ApiService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by huangjinqing on 2018/1/10.
 */

public class RetrofitHelper {


    private Context mCntext;

    private OkHttpClient mOkHttpClient ;
    GsonConverterFactory factory = GsonConverterFactory.create(new GsonBuilder().create());
    private static RetrofitHelper instance = null;
    private Retrofit mRetrofit = null;
    public static RetrofitHelper getInstance(Context context){
        if (instance == null){
            instance = new RetrofitHelper(context);
        }
        return instance;
    }
    private RetrofitHelper(Context mContext){
        mCntext = mContext;
        mOkHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .addInterceptor(new UserAgentInterceptor())
                .build();
        init();
    }

    private void init() {
        resetApp();
    }

    private void resetApp() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.douban.com/v2/")
                .client(mOkHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    public ApiService getServer(){
        return mRetrofit.create(ApiService.class);
    }




    /**
     * 添加UA拦截器，B站请求API需要加上UA才能正常使用
     */
    private static class UserAgentInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 7.0; zh-cn; PRA-AL00X Build/HONORPRA-AL00X) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30;360appstore")
                    .removeHeader("Content-Type")
                    .addHeader("Content-Type"," text/html;charset=utf-8")
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }


    public Api360Service getApi360Service(){
       return new Retrofit.Builder()
                .baseUrl("http://recommend.api.sj.360.cn/")
                .client(mOkHttpClient)
                .addConverterFactory(factory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                 .addConverterFactory(ScalarsConverterFactory.create())
                .build().create(Api360Service.class);
    }

