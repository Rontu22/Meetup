package com.example.meetup.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetup.Models.Users;
import com.example.meetup.R;
import com.example.meetup.ViewHolder.UserViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

public class UserDataAdapter extends RecyclerView.Adapter<UserViewHolder> {

    List<Users> list = Collections.emptyList();
    Context context;

    public UserDataAdapter(List<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.user_card,parent,false);

        UserViewHolder viewHolder = new UserViewHolder(userView);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserViewHolder holder, int position) {
        int index = holder.getAdapterPosition();
        holder.userEmail.setText(list.get(position).getEmail());






    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
