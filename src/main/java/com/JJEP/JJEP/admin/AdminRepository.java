package com.JJEP.JJEP.admin;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AdminRepository {
    private List<String> listApplications = new ArrayList<>();


    public void addToTheApplicationsList() {

        listApplications.add("Application 1");
        listApplications.add("Application 2");
        listApplications.add("Application 3");
        listApplications.add("Application 4");
        listApplications.add("Application 5");
        listApplications.add("Application 6");


    }

    public List<String> getApplications() { return listApplications; }

}
