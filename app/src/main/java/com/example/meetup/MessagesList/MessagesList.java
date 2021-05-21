package com.example.meetup.MessagesList;

import com.example.meetup.Models.ChatModel;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MessagesList {
    public List<ChatModel> chatList = new ArrayList<>();

    public void addChat(ChatModel chat)
    {
        chatList.add(chat);
    }
    public List<ChatModel> getChatList(){
        List<ChatModel> uniqueList = new ArrayList<>();
        Set<ChatModel> uniqueListSet = new LinkedHashSet<>();


        for(ChatModel chat : chatList)
        {
            uniqueListSet.add(chat);
        }
        for(ChatModel chat : uniqueListSet)
        {
            uniqueList.add(chat);
        }



        return uniqueList;
    }
}
