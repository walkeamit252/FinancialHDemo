package com.example.amitwalke.financialhdemo.network;

import java.io.IOException;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import android.text.TextUtils;

import okio.Buffer;

/**
 * Created by amitpadekar on 27/11/15.
 */
public class ApiLogIntercepter implements Interceptor
{
	private static final String LOG_TAG = "REQ_LOG";

	@Override
	public Response intercept(Chain chain) throws IOException
	{
		Request request = chain.request();
		Request.Builder builder = request.newBuilder();
        builder.header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.73 Safari/537.36");
		builder.header("Accept", "application/json ");
		builder.header("Content-Type", "application/json");

		Request newRequest = builder.build();

		long t1 = System.nanoTime();
		Response response = chain.proceed(newRequest);
		long t2 = System.nanoTime();

		return response;
	}

	public static String bodyToString(Request request)
	{
		try
		{
			if (request.body() != null)
			{
				final Request copy = request.newBuilder().build();
				final Buffer buffer = new Buffer();
				copy.body().writeTo(buffer);
				return buffer.readUtf8();
			}
			return "";
		} catch (final IOException e)
		{
			return "did not work";
		}
	}

}
