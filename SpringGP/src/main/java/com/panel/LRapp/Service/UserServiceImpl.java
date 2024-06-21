package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.UserDTO;
import com.panel.LRapp.Dto.loginDTO;
import com.panel.LRapp.Entity.*;
import com.panel.LRapp.Repo.TokenRepository;
import com.panel.LRapp.Repo.UserRepo;
import com.panel.LRapp.response.AuthenticationResponse;
import com.panel.LRapp.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StorageService storageService;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse addUser(UserDTO userDTO) {
        String image="https://www10.0zz0.com/2024/06/19/15/268042234.jpg";
        User user =new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getPhone(),
                userDTO.getRole(),
                image
        );
        code c=new code();
        c.setCode("1");
        user.setCo(c);
//        ImageData imageData=new ImageData();
//        imageData.setName("initial image");
//        imageData.setType("png");
//        imageData.setImageData(null);
//        user.setImageData(imageData);

//        List<Posts> p=null;
//        user.setPosts(p);

        if(! (userDTO.getPassword().equals(userDTO.getConfirmPass())))
            return new AuthenticationResponse("Password and Confirm Password must be Same :(", false,null,null);
        if(!(userDTO.getPhone().length()==11))
            return new AuthenticationResponse("Phone must be 11 number :(", false,null,null);
        if(! (userDTO.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new AuthenticationResponse("Email not Valid :(", false,null,null);
        if(! (userRepo.findByEmail(userDTO.getEmail())==null))
            return new AuthenticationResponse("Email Existed , please Sign in :)", false,null,null);

        User savedUser=userRepo.save(user);

        Map<String , Object> extraClaims = new HashMap<>();
        String jwtToken = jwtService.createToken(user , extraClaims);
        saveUserToken(savedUser, jwtToken);
        return new AuthenticationResponse("Registered Successfully :)", true,savedUser,jwtToken);
    }

    private void saveUserToken(User user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    UserDTO userDTO;
    @Override
    public AuthenticationResponse loginUser(loginDTO login) {

        String msg = "";
        if (!(login.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new AuthenticationResponse("Email not Valid :(", false, null, null);

        User user1 = userRepo.findByEmail(login.getEmail());
        if(user1!=null) {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            User user = userRepo.findByEmail(login.getEmail());
            Map<String, Object> extraClaims = new HashMap<>();
            String jwtToken = jwtService.createToken(user, extraClaims);
            revokeAllTokenByUser(user);
            saveUserToken(user, jwtToken);
            return new AuthenticationResponse("Login Success", true,user,jwtToken);
        }else{
            return new AuthenticationResponse("Login failed", false,null,null);
        }
//           if (false) {
//                String password = login.getPassword();

//                if (user.getPassword().equals(login.getPassword())) {
//                   Optional<User> user1 = userRepo.findByEmail(login.getEmail());
//                    if (user1.isPresent()) {
  //             return new AuthenticationResponse("Login Success", true,user,jwtToken);
//                    } else {
//                        return new AuthenticationResponse("Login Failed", false,null,null);
//                    }
//                } else {
//                return new AuthenticationResponse("Login failed", false,null,null);
//                    // return new AuthenticationResponse("password Not Match", false,null,null);
//                }
//            }

//            else {
//                return new AuthenticationResponse("Email not exits", false,null,null);
//            }

    }



    private void revokeAllTokenByUser(User user) {
        List<Token> validTokens = tokenRepository.findAllTokensByUser(user.getId());
        if(validTokens.isEmpty()) {
            return;
        }

        validTokens.forEach(t-> {
            t.setExpired(true);
            t.setRevoked(true);
        });

        tokenRepository.saveAll(validTokens);
    }
    @Override
    public void deleteById(int id){
        userRepo.deleteById(id);
    }

    @Override
    public LoginResponse findById(int id) {
        if(id<=0){
            return new LoginResponse("not found user with id <=0 :(", false,null);
        }
        if(userRepo.findById(id).isEmpty()){
            return new LoginResponse("not found user with id : "+id, false,null);
        }

        return new LoginResponse("user found", true,userRepo.findById(id).get());
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }
    @Override
    public LoginResponse update(String name, String email, String pass, String phone) {

        if(! (email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")))
            return new LoginResponse("Email not Valid :(", false,null);
        if(userRepo.findByEmail(email)==null)
            return new LoginResponse("Email Not Existed ", false,null);
        if(!(phone.length()==11))
            return new LoginResponse("Phone must be 11 number :(", false,null);
        // Retrieve the entity object
        Optional<User> optionalUser = Optional.ofNullable(userRepo.findByEmail(email));

        if (optionalUser.isPresent()) {
            // Modify the fields of the entity object
            User user = optionalUser.get();
            user.setName(name);
            user.setPassword(pass);
            user.setPhone(phone);
            // Save the entity
            userRepo.save(user);
            return new LoginResponse("profile updated",true,user);
        }
        return null;
    }
    @Override
    public User checkEmailExist(String email){
        return userRepo.findByEmail(email);}

    @Override
    public void save(User user){
        userRepo.save(user);
    }
    @Override
    public User findByMail(String email){
      return userRepo.findByEmail(email);
    }


     }
