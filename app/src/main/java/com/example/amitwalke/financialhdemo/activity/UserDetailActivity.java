package com.example.amitwalke.financialhdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.amitwalke.financialhdemo.R;
import com.example.amitwalke.financialhdemo.model.UserDataParent;

public class UserDetailActivity extends AppCompatActivity {

    Button mButtonClose;
    UserDataParent mParent;
    TextView mTextViewFullName,mTextViewMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        mTextViewFullName=(TextView)findViewById(R.id.mTextViewFullName);
        mTextViewMobile=(TextView)findViewById(R.id.mTextViewMobile);
        mParent=(UserDataParent)(getIntent().getSerializableExtra("parent"));
        mButtonClose=(Button)findViewById(R.id.mButtonClose);
        mTextViewFullName.setText(mParent.getUserFirstName()+" "+mParent.getUserLastName());
        mTextViewMobile.setText(mParent.getUserMobile());
        mButtonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
