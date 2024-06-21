package com.panel.LRapp.Service;

import com.panel.LRapp.Entity.Token;
import com.panel.LRapp.Entity.User;
import com.panel.LRapp.Entity.UserChallengePrivate;

import com.panel.LRapp.Repo.TokenRepository;
import com.panel.LRapp.Repo.UCPrivateRepo;
import com.panel.LRapp.Repo.UserRepo;
import com.panel.LRapp.response.UCPrivateResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UCPrivateServiceImpl implements UCPrivateService {

    @Autowired
    private UCPrivateRepo ucPrivateRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenRepository tokenRepository;
    @Override
    public UCPrivateResponse save(UserChallengePrivate userChallengePrivate,String token) {
        Token t= tokenRepository.findByToken(token.substring(7));
        User user=userRepo.findByEmail(t.getUser().getEmail());
        userChallengePrivate.setUser(user);
        return new UCPrivateResponse("Challenge Saved Successfully :) ",ucPrivateRepo.save(userChallengePrivate));

    }

    @Override
    public void delete(int id) {
         ucPrivateRepo.deleteById(id);
    }

    @Override
    public UCPrivateResponse update(UserChallengePrivate userChallengePrivate) {
        Optional<UserChallengePrivate> userChallengePrivate1=ucPrivateRepo.findById(userChallengePrivate.getId());
        if(userChallengePrivate1.isEmpty()){
            return new UCPrivateResponse("this Challenge not found",null);
        }else{
            userChallengePrivate1.get().setName(userChallengePrivate.getName());
            userChallengePrivate1.get().setDescription(userChallengePrivate.getDescription());

            userChallengePrivate1.get().setDays(userChallengePrivate.getDays());


            return new UCPrivateResponse("update challenge Successfully",ucPrivateRepo.save(userChallengePrivate1.get()));
        }
    }

    @Override
    public UCPrivateResponse findById(int id) {
        Optional<UserChallengePrivate> userChallengePrivate=ucPrivateRepo.findById(id);
        if(userChallengePrivate.isEmpty()){
            return new UCPrivateResponse("this Challenge not found",null);
        }
        return new UCPrivateResponse(" Challenge found",userChallengePrivate.get());

    }

    @Override
    public List<UserChallengePrivate> findByName(String name) {
        return ucPrivateRepo.findByName(name);
    }

    @Override
    public List<UserChallengePrivate> findAll() {
        return ucPrivateRepo.findAll();
    }
}
