package com.panel.LRapp.UserController;


import com.panel.LRapp.Entity.UserChallengePublic;
import com.panel.LRapp.Service.UCPublicService;

import com.panel.LRapp.response.UCPublicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/UserChallengePublic")
public class UserCPublicController {
    @Autowired
    private UCPublicService ucPublicService;

    @PostMapping("/save")
    public UCPublicResponse addChallenge(@RequestBody UserChallengePublic userChallengePublic,@RequestHeader("Authorization") String t) {

        return ucPublicService.save(userChallengePublic,t);
    }

    @DeleteMapping("/delete")
    public String deleteChallenge(@RequestParam("ChallengeId") int id){
        ucPublicService.delete(id);
        return "Challenge delete Successfully";
    }

    @GetMapping("/findChallenge")
    public UCPublicResponse findById(@RequestParam("ChallengeId") int id,@RequestHeader("Authorization") String t){
        return ucPublicService.findById(id,t);
    }

    @GetMapping("/search")
    public List<UserChallengePublic> search(@RequestParam("search") String name){
        return ucPublicService.findByName(name);
    }

    @GetMapping("/findAll")
    public List<UserChallengePublic> findAllChallenge(){
        return ucPublicService.findAll();
    }

    @PutMapping("/update")
    public UCPublicResponse updateChallenge(@RequestBody UserChallengePublic userChallengePublic,@RequestHeader("Authorization") String t){
        return ucPublicService.update(userChallengePublic,t);
    }

}
