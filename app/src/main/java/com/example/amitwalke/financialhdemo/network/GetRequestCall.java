package com.example.amitwalke.financialhdemo.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;


public class GetRequestCall extends AsyncTask<String,String,String> {

    Context mContext;
    private static final String TAG = "GetRequestCall";
    ProgressDialog mProgressDialog;
    TextView mTextViewResponse;
    GetCallBack mGetCallBack;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog.show();
    }

   public GetRequestCall(Context mContext, GetCallBack mGetCallBack){
       this.mContext=mContext;
       this.mGetCallBack=mGetCallBack;
       mProgressDialog=new ProgressDialog(mContext);
       mProgressDialog.setTitle("Please Wait");
    }

    @Override
    protected String doInBackground(String... params)  {
        String link = params[0];
        Log.i(TAG, "doInBackground: address==>"+link);
        String finalData="",data="";
        URL url;
        try {
              url = new URL(link);
            // replace with your url
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
                   conn.connect();
            InputStream is=conn.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is,"UTF-8"));

            while((data=br.readLine())!=null){
                finalData+=data+"\n";
            }
        }catch (Exception e){
            Log.i(TAG, "doInBackground: Exception==>"+e.getMessage());
        }
        return finalData;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Log.i(TAG, "onPostExecute: "+s);
        mProgressDialog.cancel();
        try{
            mGetCallBack.onResponseSuccess(new JSONObject(s));
        }catch (Exception e){
            Log.i(TAG, "onPostExecute: exception"+e.getMessage());
        }
        
    }

    public interface GetCallBack{
        public void onResponseSuccess(JSONObject mJsonObject);
    }




}
