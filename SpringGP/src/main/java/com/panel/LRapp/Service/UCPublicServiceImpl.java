package com.panel.LRapp.Service;


import com.panel.LRapp.Entity.Token;
import com.panel.LRapp.Entity.User;
import com.panel.LRapp.Entity.UserChallengePublic;
import com.panel.LRapp.Repo.TokenRepository;
import com.panel.LRapp.Repo.UCPublicRepo;

import com.panel.LRapp.Repo.UserRepo;
import com.panel.LRapp.response.UCPublicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UCPublicServiceImpl implements UCPublicService{
    @Autowired
    private UCPublicRepo ucPublicRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public UCPublicResponse save(UserChallengePublic userChallengePublic,String token) {
        Token t= tokenRepository.findByToken(token.substring(7));
        User user=userRepo.findByEmail(t.getUser().getEmail());
        userChallengePublic.setUser(user);
        return new UCPublicResponse("Challenge Saved Successfully :) ",ucPublicRepo.save(userChallengePublic),user.getName(),user.getImage());
    }

    @Override
    public void delete(int id) {
         ucPublicRepo.deleteById(id);
    }

    @Override
    public UCPublicResponse update(UserChallengePublic userChallengePublic, String token) {
        Token t= tokenRepository.findByToken(token.substring(7));
        User user=userRepo.findByEmail(t.getUser().getEmail());
        Optional<UserChallengePublic> userChallengePublic1=ucPublicRepo.findById(userChallengePublic.getId());
        if(userChallengePublic1.isEmpty()){
            return new UCPublicResponse("this Challenge not found",null,null,null);
        }else{

            userChallengePublic1.get().setName(userChallengePublic.getName());
            userChallengePublic1.get().setDescription(userChallengePublic.getDescription());

            userChallengePublic1.get().setDays(userChallengePublic.getDays());


            return new UCPublicResponse("update Challenge Successfully",ucPublicRepo.save(userChallengePublic1.get()),user.getName(),user.getImage());
        }
    }

    @Override
    public UCPublicResponse findById(int id,String token) {
        Token t= tokenRepository.findByToken(token.substring(7));
        User user=userRepo.findByEmail(t.getUser().getEmail());
        Optional<UserChallengePublic> userChallengePublic=ucPublicRepo.findById(id);
        if(userChallengePublic.isEmpty()){
            return new UCPublicResponse("this Challenge not found",null,null,null);
        }
        return new UCPublicResponse(" Challenge found",userChallengePublic.get(),user.getName(),user.getImage());

    }

    @Override
    public List<UserChallengePublic> findByName(String name) {
        return ucPublicRepo.findByName(name);
    }

    @Override
    public List<UserChallengePublic> findAll() {
        return ucPublicRepo.findAll();
    }
}
