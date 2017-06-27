package com.example.amitwalke.financialhdemo.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.amitwalke.financialhdemo.R;
import com.example.amitwalke.financialhdemo.adapter.RecyclerAdapter;
import com.example.amitwalke.financialhdemo.model.UserDataParent;
import com.example.amitwalke.financialhdemo.network.ApiClient;
import com.example.amitwalke.financialhdemo.network.GetRequestCall;
import com.example.amitwalke.financialhdemo.util.DialogUtil;
import com.example.amitwalke.financialhdemo.util.NetworkUtil;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity  implements RecyclerAdapter.OnUserclick{
    private static final String TAG = "MainActivity";
    RecyclerAdapter mAdapter;
    RecyclerView mRecyclerView;
    ArrayList<UserDataParent> mUserDataParents=new ArrayList<>();
    RecyclerView.LayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mRecyclerView=(RecyclerView)findViewById(R.id.mRecyclerView);
         manager=new LinearLayoutManager(getApplicationContext());

        final ProgressDialog mProgressDialog=new ProgressDialog(this);


        if(NetworkUtil.isAvailable(this)){
            mProgressDialog.setTitle("Please Wait");
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            ApiClient.getList(new Callback<List<UserDataParent>>() {
                @Override
                public void onResponse(Response<List<UserDataParent>> response, Retrofit retrofit) {
                    mProgressDialog.cancel();
                    if(response.body()!=null){
                        mUserDataParents=(ArrayList)response.body();
                        mAdapter=new RecyclerAdapter(MainActivity.this,mUserDataParents,MainActivity.this);
                        mRecyclerView.setLayoutManager(manager);
                        mRecyclerView.setAdapter(mAdapter);
                    }else {
                        Log.i(TAG, "onResponse: ==>"+response.body());
                    }
                }
                @Override
                public void onFailure(Throwable t) {
                    mProgressDialog.cancel();
                    Log.i(TAG, "onFailure: ERROR"+t.getMessage());
                }
            });
        }else {
            DialogUtil.showNoNetworkAlert(this);
        }


    }
    @Override
    public void onUserClickItem(int posistion) {
        Log.i(TAG, "onUserClickItem: POSITION==>"+posistion);
        UserDataParent mParent=mUserDataParents.get(posistion);
//        Bundle mBundle=new Bundle();
        Intent mIntent=new Intent(this,UserDetailActivity.class);
        mIntent.putExtra("parent",mParent);
        startActivity(mIntent);


    }

}
