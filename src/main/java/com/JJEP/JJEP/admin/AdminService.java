package com.JJEP.JJEP.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public List<String> getApplications() { return adminRepository.getApplications(); }

    public void addToTheApplicationsList() { adminRepository.addToTheApplicationsList(); }

}
