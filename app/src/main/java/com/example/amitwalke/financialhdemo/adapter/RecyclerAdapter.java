package com.example.amitwalke.financialhdemo.adapter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amitwalke.financialhdemo.model.UserDataParent;
import com.example.amitwalke.financialhdemo.R;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.DataViewHolder> {

    private ArrayList<UserDataParent> mParentArrayList;
    Context mContext;
    OnUserclick mOnUserclick;

    public class DataViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewName,mTextViewMob,mTextViewDesig;
        RelativeLayout mReclativeLayout;
        public DataViewHolder(View view) {
            super(view);
            mTextViewName = (TextView) view.findViewById(R.id.mTextViewName);
            mTextViewMob = (TextView) view.findViewById(R.id.mTextViewMob);
            mReclativeLayout=(RelativeLayout) view.findViewById(R.id.mReclativeLayout);
        //    mTextViewDesig = (TextView) view.findViewById(R.id.mTextViewDesig);
        }
    }

    public RecyclerAdapter(Context mContext,ArrayList<UserDataParent> mParentArrayList,OnUserclick mOnUserclick) {

       Collections.sort(mParentArrayList, new Comparator<UserDataParent>() {
            @Override
            public int compare(UserDataParent o1, UserDataParent o2) {
                return o1.getUserFirstName().compareTo(o2.getUserFirstName());
            }
        });
        this.mParentArrayList = mParentArrayList;
        this.mContext =mContext;
        this.mOnUserclick=mOnUserclick;
    }


    @Override
    public DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, parent, false);
        return new DataViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        final UserDataParent item = mParentArrayList.get(position);
        holder.mTextViewName.setText(item.getUserFirstName()+" "+item.getUserLastName());
        holder.mTextViewMob.setText(item.getUserMobile());

        if(position%2==0){
            holder.mReclativeLayout.setBackgroundColor(R.color.black_light);
        }else {
           // holder.mReclativeLayout.setBackgroundColor(android.R.color.holo_blue_light);
        }

     //   holder.mTextViewDesig.setText(item.get);
        holder.mReclativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,item.getUserFirstName(),Toast.LENGTH_SHORT).show();
                mOnUserclick.onUserClickItem(position);
            }
        });
        if(position==getItemCount()-1){
            Toast.makeText(mContext,"Reach End Of List",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
         if(mParentArrayList!=null)
         return mParentArrayList.size();
        else
            return 0;
    }

    public interface OnUserclick{
        public void onUserClickItem(int posistion);
    }

}