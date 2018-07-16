package com.ktu.dev.melvin.whatsanyone;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Melvin on 12/20/2017.
 */

public class Messages_Adapter extends RecyclerView.Adapter<Messages_Adapter.ViewHolder> {
    private List<Messages_Data> listitems;
    private Context context;
    Messages_Adapter(List<Messages_Data> listitems, Context context) {
        this.listitems = listitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.messages_card_view,parent,false);
        return new ViewHolder(v);

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Messages_Data list=listitems.get(position);
        holder.message.setText(list.getMessage());
        holder.feed=list;
       }
    @Override
    public int getItemCount() {
        return listitems.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
            TextView message;
            Messages_Data feed;
        ViewHolder(final View itemView){
                super(itemView);
                message=itemView.findViewById(R.id.cardTextView);
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try{
                            MainActivity.message.setText(feed.getMessage());
                        }catch (Exception ignored){
                        }
                    }
                });
            }
        }

}
