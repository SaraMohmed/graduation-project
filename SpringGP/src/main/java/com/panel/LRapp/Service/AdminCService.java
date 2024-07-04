package com.panel.LRapp.Service;

import com.panel.LRapp.Entity.AdminChallenge;
import com.panel.LRapp.response.AdminCResponse;

import java.util.List;

public interface AdminCService {
    AdminCResponse save(AdminChallenge adminChallenge);
    void delete(int id);

    AdminCResponse update(AdminChallenge adminChallenge);
//    CRAdminCU updateCR(String token, AdminDTO adminDTO);
    AdminCResponse findById(int id);

    List<AdminChallenge> findByName(String name);

    List<AdminChallenge> findAll();
}
