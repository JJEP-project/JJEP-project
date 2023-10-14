package com.JJEP.JJEP.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    private List<AdminUserModel> listUsers = new ArrayList<>();

    public void addToTheList() {

        AdminUserModel user1 = new AdminUserModel();
        user1.setUsername("babyka");
        user1.setEmail("pochtolion@gmail.com");
        user1.setFullname("Fedor Dostoevsky");

        listUsers.add(user1);

        AdminUserModel user2 = new AdminUserModel();
        user2.setUsername("kilkavmasle");
        user2.setEmail("dura@gmail.com");
        user2.setFullname("Veronika pavlovna");
        listUsers.add(user2);

    }

    public List<AdminUserModel> getUsers() {
        return listUsers;
    }

}
