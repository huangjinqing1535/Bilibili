package huang.com.retrofit.api;

import java.util.List;

import huang.com.retrofit.entiry.Book;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by huangjinqing on 2018/1/4.
 */

public interface ApiService {


    @GET("book/search")
    Observable<Book> getSearchBooks(@Query("q") String name,
                                    @Query("tag") String tag, @Query("start") int start,
                                    @Query("count") int count);



