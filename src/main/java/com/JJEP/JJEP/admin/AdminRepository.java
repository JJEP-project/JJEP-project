package com.JJEP.JJEP.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    private List<String> listUsers = new ArrayList<>();

    public void addToTheList() {

        listUsers.add("Johnatan");
        listUsers.add("Kylie");
        listUsers.add("Roberto");
        listUsers.add("Max");
        listUsers.add("Yan");
        listUsers.add("Jalol");
        listUsers.add("Damir");

    }

    public List<String> getUsers() {
        return listUsers;
    }

}
