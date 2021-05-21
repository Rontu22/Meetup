package com.example.meetup.ViewHolder;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.R;

import org.jetbrains.annotations.NotNull;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    public TextView userID;
    public TextView message;
    public View view;




    public ChatViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        userID = (TextView)itemView.findViewById(R.id.chatUserID);
        message = (TextView) itemView.findViewById(R.id.message);
        view = itemView;

    }
}
