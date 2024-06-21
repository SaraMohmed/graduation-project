package com.panel.LRapp.Service;


import com.panel.LRapp.Entity.AdminChallenge;

import com.panel.LRapp.Repo.AdminCRepo;
import com.panel.LRapp.response.AdminCResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class AdminCServiceImpl implements AdminCService{
    @Autowired
    private AdminCRepo adminCRepo;

    @Override
    public AdminCResponse save(AdminChallenge adminChallenge) {
//        AdminChallenge adminC=new AdminChallenge(adminChallenge.getIcon(), adminChallenge.getName(), adminChallenge.getDescription(),adminChallenge.getAdminCDays(), 0);
//
//        List<AdminCDays> adminCDays=new ArrayList<>();
//        for (AdminCDays adminIn: adminChallenge.getAdminCDays()){
//           AdminCDays adminCDays1=new AdminCDays(adminIn.getFile(),adminIn.getContent(),0);
//           adminCDays1.setAdminChallenge(adminC);
//           adminCDays.add(adminCDays1);
//        }
//
//        adminC.setAdminCDays(adminCDays);

        return new AdminCResponse("Challenge Saved Successfully :) ",adminCRepo.save(adminChallenge));
    }

    @Override
    public void delete(int id) {
         adminCRepo.deleteById(id);
    }

    @Override
    public AdminCResponse update(AdminChallenge adminChallenge) {
        Optional<AdminChallenge> adminChallenge1=adminCRepo.findById(adminChallenge.getId());
        if(adminChallenge1.isEmpty()){
            return new AdminCResponse("this Challenge not found",null);
        }else{
            adminChallenge1.get().setName(adminChallenge.getName());
            adminChallenge1.get().setDescription(adminChallenge.getDescription());
            adminChallenge1.get().setIcon(adminChallenge.getIcon());
            adminChallenge1.get().setDone(adminChallenge.isDone());
            adminChallenge1.get().setAdminCDays(adminChallenge.getAdminCDays());

            return new AdminCResponse("update Challenge Successfully",adminCRepo.save(adminChallenge1.get()));
        }
    }

    @Override
    public AdminCResponse findById(int id) {
        Optional<AdminChallenge> adminChallenge1=adminCRepo.findById(id);
        if(adminChallenge1.isEmpty()){
            return new AdminCResponse("this Challenge not found",null);
        }
        return new AdminCResponse(" Challenge found",adminChallenge1.get());
    }

    @Override
    public List<AdminChallenge> findByName(String name) {
        return adminCRepo.findByName(name);
    }

    @Override
    public List<AdminChallenge> findAll() {
        return adminCRepo.findAll();
    }
}
