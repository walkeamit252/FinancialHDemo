package com.example.amitwalke.financialhdemo.network;

import java.util.ArrayList;
import java.util.List;

import com.example.amitwalke.financialhdemo.model.UserDataParent;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.PATCH;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by amitpadekar on 27/11/15.
 */
public interface IApiClient
{
	@GET("fetchuserinfo")
	public Call<List<UserDataParent>> getList();
}
