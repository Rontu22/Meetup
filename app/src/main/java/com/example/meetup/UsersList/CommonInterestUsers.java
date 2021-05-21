package com.example.meetup.UsersList;

import com.example.meetup.Models.Users;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class CommonInterestUsers {
    public  List<Users> usersList = new ArrayList<>();
//    private List<Users> uniqueList = new ArrayList<>();

    //private Set<Users> uniqueUsers = new LinkedHashSet<>();

    public List<Users> uniqueUsersList()
    {
        List<Users> uniqueList = new ArrayList<>();
        Set<Users> uniqueUsers = new LinkedHashSet<>();
        for (Users user : usersList){
            uniqueUsers.add(user);
        }

        for(Users user : uniqueUsers){
            uniqueList.add(user);
        }
        return  uniqueList;
    }

    public void addUser(Users user)
    {
        usersList.add(user);
    }

}
