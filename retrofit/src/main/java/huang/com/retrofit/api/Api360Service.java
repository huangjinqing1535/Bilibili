package huang.com.retrofit.api;

import huang.com.retrofit.entiry.Book;
import huang.com.retrofit.entiry.HomeBean;
import huang.com.retrofit.entiry.RecommendBen;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huangjinqing on 2018/3/15.
 */

public interface Api360Service {
    @GET("home?&ch=110033&from=recommend&prepage=recommend_essential&curpage=recommend&page=1&action_type=&action_page=1&action_num=0&src=360appstore&pkg=com.qihoo.appstore&os=24&os_version=7.0&vc=300070177&v=7.1.77&md=PRA-AL00X&sn=4.394670136694171&cpu=&ca1=armeabi-v7a&ca2=armeabi&m=af72271bdf5dde55ee8cec2752114388&m2=60fb190678e7891fdfe9ee336f866e89&ppi=1080_1812&startCount=1&pvc=260&pvn=2.6.0&re=1&tid=0&cpc=1&snt=-1&nt=1&gender=0&age=24&newuser=0&theme=2&br=HONOR&carrier_id=70120&s_3pk=1&webp=1")
    Observable<HomeBean> getHomeJson();

    @GET("home?&ch=110033&from=recommend&prepage=recommend_essential&curpage=recommend&page=1&action_type=&action_page=1&action_num=0&src=360appstore&pkg=com.qihoo.appstore&os=24&os_version=7.0&vc=300070177&v=7.1.77&md=PRA-AL00X&sn=4.394670136694171&cpu=&ca1=armeabi-v7a&ca2=armeabi&m=af72271bdf5dde55ee8cec2752114388&m2=60fb190678e7891fdfe9ee336f866e89&ppi=1080_1812&startCount=1&pvc=260&pvn=2.6.0&re=1&tid=0&cpc=1&snt=-1&nt=1&gender=0&age=24&newuser=0&theme=2&br=HONOR&carrier_id=70120&s_3pk=1&webp=1")
    Observable<RecommendBen> getGson();

    @GET("home?&ch=110033&from=recommend&prepage=recommend_essential&curpage=recommend&page=1&action_type=&action_page=1&action_num=0&src=360appstore&pkg=com.qihoo.appstore&os=24&os_version=7.0&vc=300070177&v=7.1.77&md=PRA-AL00X&sn=4.394670136694171&cpu=&ca1=armeabi-v7a&ca2=armeabi&m=af72271bdf5dde55ee8cec2752114388&m2=60fb190678e7891fdfe9ee336f866e89&ppi=1080_1812&startCount=1&pvc=260&pvn=2.6.0&re=1&tid=0&cpc=1&snt=-1&nt=1&gender=0&age=24&newuser=0&theme=2&br=HONOR&carrier_id=70120&s_3pk=1&webp=1")
    Observable<String> getJsonStr();
