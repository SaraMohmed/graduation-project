package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.UserDTO;
import com.panel.LRapp.Dto.loginDTO;
import com.panel.LRapp.Entity.User;
import com.panel.LRapp.response.AuthenticationResponse;
import com.panel.LRapp.response.LoginResponse;

import java.util.List;

public interface UserService {

    AuthenticationResponse addUser(UserDTO userDTO);

    AuthenticationResponse loginUser(loginDTO login);

    void deleteById(int id);

    LoginResponse findById(int id);

    List<User> findAll();

    LoginResponse update(String name, String email, String pass, String phone);

    User checkEmailExist(String email);

    void save(User user);


    User findByMail(String email);


}
