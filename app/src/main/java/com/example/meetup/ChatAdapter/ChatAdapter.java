package com.example.meetup.ChatAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.Models.ChatModel;
import com.example.meetup.R;
import com.example.meetup.ViewHolder.ChatViewHolder;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {


    List<ChatModel> listOfChatModel = Collections.emptyList();
    Context context;

    public ChatAdapter(List<ChatModel> listOfChatModel, Context context) {
        this.listOfChatModel = listOfChatModel;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View chatView = inflater.inflate(R.layout.chat_card,parent,false);

        ChatViewHolder viewHolder = new ChatViewHolder(chatView);

        return viewHolder;




        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ChatViewHolder holder, int position) {
        int index = holder.getAdapterPosition();
        String currentTime = listOfChatModel.get(position).getUserId();
        long timeLong = Long.parseLong(currentTime);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        Date date = new Date(timeLong);
        String time = simpleDateFormat.format(date);
//        holder.userID.setText(listOfChatModel.get(position).getUserId());
        holder.userID.setText(time);
        holder.message.setText(listOfChatModel.get(position).getMessage());



    }

    @Override
    public int getItemCount() {
        return listOfChatModel.size();
    }
}
