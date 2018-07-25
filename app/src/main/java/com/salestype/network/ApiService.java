package com.salestype.network;

import com.salestype.model.Login;
import com.salestype.model.Stock;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ravi on 20/02/18.
 */

public interface ApiService {
//    // Register new user
//    @FormUrlEncoded
//    @POST("notes/user/register")
//    Single<User> register(@Field("device_id") String deviceId);
//
    //lohin
       @FormUrlEncoded
@POST("VerifyUser")
Single<Login> Login(@Field("UserId") String userid, @Field("Password") String Password );
    // getstock

    @FormUrlEncoded
    @POST("GetStockDetails")
    Single<Stock> GetStock(@Field("vanid") String vid, @Field("branchid") String branchid );
//
//    // Fetch all notes
//    @GET("notes/all")
//    Single<List<Note>> fetchAllNotes();
//    // Update single note
//    @FormUrlEncoded
//    @PUT("notes/{id}")
//    Completable updateNote(@Path("id") int noteId, @Field("note") String note);
//
//    // Delete note
//    @DELETE("notes/{id}")
//    Completable deleteNote(@Path("id") int noteId);
}
