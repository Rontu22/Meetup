package com.example.meetup.ViewHolder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.R;

import org.jetbrains.annotations.NotNull;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public TextView userEmail;
    public View view;

    public UserViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        userEmail = (TextView)itemView.findViewById(R.id.userEmail);
        view = itemView;
    }
}
