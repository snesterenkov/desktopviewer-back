package com.eklib.desktopviewer.api.v1.model;

import com.eklib.desktopviewer.persistance.model.User;

import java.util.List;

/**
 * Created by vadim on 24.09.2014.
 */
public class ListUser {


    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

}