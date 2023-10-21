package com.JJEP.JJEP.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {

    private List<String> listUsers = new ArrayList<>();
    private List<String> listApplications = new ArrayList<>();

    public void addToTheUsersList() {

        listUsers.add("Johnatan");
        listUsers.add("Kylie");
        listUsers.add("Roberto");
        listUsers.add("Max");
        listUsers.add("Yan");
        listUsers.add("Jalol");
        listUsers.add("Damir");

    }

    public void addToTheApplicationsList() {

        listApplications.add("Application 1");
        listApplications.add("Application 2");
        listApplications.add("Application 3");
        listApplications.add("Application 4");
        listApplications.add("Application 5");
        listApplications.add("Application 6");


    }

    public List<String> getUsers() {
        return listUsers;
    }
    public List<String> getApplications() { return listApplications; }

}
