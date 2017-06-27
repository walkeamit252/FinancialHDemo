package com.example.amitwalke.financialhdemo.network;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;

import com.example.amitwalke.financialhdemo.model.UserDataParent;
import com.google.gson.JsonObject;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by amit walke on 27/7/17.
 */
public class ApiClient
{

	private static Retrofit mRetrofit;
	static String BASE_URL ="https://www.financialhospital.in/adminpanel/users/user_ws.php/";
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
	private static final MediaType MEDIA_TYPE_JPEG = MediaType.parse("image/jpeg");

	private static Retrofit getRetrofitInstance()
	{
		if (null == mRetrofit)
		{
			OkHttpClient client = new OkHttpClient();
			client.setReadTimeout(50, TimeUnit.SECONDS);
			client.setConnectTimeout(50,TimeUnit.SECONDS);
			client.interceptors().add(new ApiLogIntercepter());
			Retrofit.Builder builder = new Retrofit.Builder();
			builder.baseUrl(BASE_URL);
			builder.addConverterFactory(GsonConverterFactory.create());
			builder.client(client);
			mRetrofit = builder.build();
		}
		return mRetrofit;
	}

	public static void getList(Callback<List<UserDataParent>> listener)
	{
		Call<List<UserDataParent>> call = getRetrofitInstance().create(IApiClient.class).getList();
		call.enqueue(listener);
	}

	public static String parseError(ResponseBody errorBody)
	{
		String error = "";
		try
		{
			error = errorBody.string();
			JSONObject data = new JSONObject(error);
			if (data.has("error"))
				error = data.getString("error");
		} catch (Exception e)
		{
			error = e.getMessage();
		}
		return error.equals("") ? "Unknown Error" : error;
	}
}
