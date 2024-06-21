package com.panel.LRapp.UserController;


import com.panel.LRapp.Entity.AdminCDays;
import com.panel.LRapp.Entity.AdminChallenge;

import com.panel.LRapp.Service.AdminCService;
import com.panel.LRapp.response.AdminCResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/adminChallenge")
public class AdminChController {
    @Autowired
    private AdminCService adminCService;

    @PostMapping("/save")
    public AdminCResponse addChallenge(@RequestBody AdminChallenge adminChallenge) {

        AdminChallenge adminC=new AdminChallenge(adminChallenge.getIcon(), adminChallenge.getName(), adminChallenge.getDescription(), false);

        List<AdminCDays> adminCDays=new ArrayList<>();
        for (AdminCDays adminIn: adminChallenge.getAdminCDays()){
            AdminCDays adminCDays1=new AdminCDays(adminIn.getFile(),adminIn.getContent(),0);
            adminCDays1.setAdminChallenge(adminC);
            adminCDays.add(adminCDays1);
        }

        adminC.setAdminCDays(adminCDays);
        return adminCService.save(adminC);
    }

    @DeleteMapping("/delete")
    public String deleteChallenge(@RequestParam("ChallengeId") int id){
        adminCService.delete(id);
        return "Challenge delete Successfully";
    }

    @GetMapping("/findChallenge")
    public AdminCResponse findById(@RequestParam("ChallengeId") int id){
        return adminCService.findById(id);
    }

    @GetMapping("/search")
    public List<AdminChallenge> search(@RequestParam("search") String name){
        return adminCService.findByName(name);
    }

    @GetMapping("/findAll")
    public List<AdminChallenge> findAllChallenge(){
        return adminCService.findAll();
    }

    @PutMapping("/update")
    public AdminCResponse updateChallenge(@RequestBody AdminChallenge adminChallenge){
        return adminCService.update(adminChallenge);
    }


}
