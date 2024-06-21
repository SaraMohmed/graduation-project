package com.panel.LRapp.Service;

import com.panel.LRapp.Entity.AdminChallenge;
import com.panel.LRapp.Entity.UserChallengePrivate;
import com.panel.LRapp.response.AdminCResponse;
import com.panel.LRapp.response.UCPrivateResponse;

import java.util.List;

public interface UCPrivateService {
    UCPrivateResponse save(UserChallengePrivate userChallengePrivate,String t);
    void delete(int id);
    UCPrivateResponse update(UserChallengePrivate userChallengePrivate);
    UCPrivateResponse findById(int id);

    List<UserChallengePrivate> findByName(String name);

    List<UserChallengePrivate> findAll();
}
