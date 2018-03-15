package huang.com.retrofit;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Writer;

import huang.com.retrofit.entiry.Book;
import huang.com.retrofit.entiry.HomeBean;
import huang.com.retrofit.entiry.Mission;
import huang.com.retrofit.entiry.RecommendBen;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Spinner mSpinner;
    private TextView text_info;
    private String leftstr= "\"\\{";
    private String rightstr= "\\}\"";
    private String toppic = "\"topic@\\{";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    3);//自定义的code
        }


        mSpinner = (Spinner) findViewById(R.id.spinner);
        mSpinner.setSelection(0, true);

        text_info = (TextView) findViewById(R.id.text_info);


        findViewById(R.id.get_info).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RetrofitHelper.getInstance(MainActivity.this).getApi360Service().getHomeJson().
//                        enqueue(new Callback<String>() {
//                            @Override
//                            public void onResponse(Call<String> call, Response<String> response) {
//                                Log.e("test",response.body().toString());
//                            }
//
//                            @Override
//                            public void onFailure(Call<String> call, Throwable t) {
//                                Log.e("test","onFailure"+t.toString());
//                            }
//                        });

                RetrofitHelper.getInstance(MainActivity.this).getApi360Service().getHomeJson().
                        subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<HomeBean>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(HomeBean homeBean) {
                                Log.e("qing","homeBean=="+homeBean.toString());
                            }
                        });
//                RetrofitHelper.getInstance(MainActivity.this).getApi360Service().getJsonStr().
//                        map(new Func1<String, RecommendBen>() {
//                            @Override
//                            public RecommendBen call(String s) {
//                                String json = s.replaceAll(leftstr,"{").replaceAll(rightstr,"}").replaceAll(toppic,"{").replaceAll("\n","");
//                                Log.e("qing","s2=="+s);
//                                try {
//                                    JSONObject jsonObject = new JSONObject(json);
//                                    JSONArray jsonArray = jsonObject.getJSONArray("templates");
//                                    for (int i= 0;i<jsonArray.length();i++){
//                                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                        Log.e("qing","jsonObject1=="+jsonObject1.toString());
//                                    }
//
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//
//
//                                Gson gson = new Gson();
//                                return gson.fromJson(json,RecommendBen.class);
//                            }
//                        }).
//                        subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Subscriber<RecommendBen>() {
//                            @Override
//                            public void onCompleted() {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                Log.e("qing","onError=="+ e.getMessage());
//                            }
//
//                            @Override
//                            public void onNext(RecommendBen recommendBen) {
//                                Log.e("qing","onNext=="+recommendBen.toString());
//                            }
//                        });
            }
        });

        findViewById(R.id.start_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForPackage(MainActivity.this,"huang.com.path");
            }
        });

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public  void startActivityForPackage(Context context, String packName) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packName);
        context.startActivity(intent);
    }


    private void weritToFile(String msg){
        String path  = Environment.getExternalStorageDirectory().getAbsolutePath()+"/huangjinqing.txt";
        try {
            File file = new File(path);
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(msg.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

