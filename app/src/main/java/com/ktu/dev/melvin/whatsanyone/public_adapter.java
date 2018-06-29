package com.ktu.dev.melvin.whatsanyone;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Melvin on 12/20/2017.
 */

public class public_adapter extends RecyclerView.Adapter<public_adapter.ViewHolder> {
    List<public_data> listitems;
    private Context context;
    public public_adapter(List<public_data> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_card_view,parent,false);
        ViewHolder v1=new ViewHolder(v);
        return v1;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        public_data list=listitems.get(position);
        holder.message.setText(list.getMessage());
        holder.feed=list;
       }
    @Override
    public int getItemCount() {
        return listitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView message;
            public_data feed;
            EditText editText;
        public ViewHolder( final View itemView){
                super(itemView);
                message=itemView.findViewById(R.id.cardTextView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            MainActivity.message.setText(feed.getMessage());
                        }catch (Exception e){
                        };

                    }
                });

            }
        }

}
