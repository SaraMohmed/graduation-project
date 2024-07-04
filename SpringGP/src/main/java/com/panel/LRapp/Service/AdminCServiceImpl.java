package com.panel.LRapp.Service;


import com.panel.LRapp.Dto.AdminDTO;
import com.panel.LRapp.Entity.AdminCDays;
import com.panel.LRapp.Entity.AdminChallenge;

import com.panel.LRapp.Entity.Token;
import com.panel.LRapp.Entity.User;
import com.panel.LRapp.Repo.AdminCRepo;
import com.panel.LRapp.Repo.TokenRepository;
import com.panel.LRapp.Repo.UserRepo;
import com.panel.LRapp.response.AdminCResponse;

import com.panel.LRapp.response.CRAdminCU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminCServiceImpl implements AdminCService{
    @Autowired
    private AdminCRepo adminCRepo;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepo userRepo;

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

        AdminChallenge adminC=new AdminChallenge(adminChallenge.getIcon1(),adminChallenge.getIcon2(), adminChallenge.getName(), adminChallenge.getDescription(), false);

        List<AdminCDays> adminCDays=new ArrayList<>();
        for (AdminCDays adminIn: adminChallenge.getAdminCDays()){
            AdminCDays adminCDays1=new AdminCDays(adminIn.getName(),adminIn.getFile(),adminIn.getContent(),adminC,0);
            adminCDays1.setAdminChallenge(adminC);
            adminCDays.add(adminCDays1);
        }

        adminC.setAdminCDays(adminCDays);

        return new AdminCResponse("تمت الاضافه بنجاح :)",adminCRepo.save(adminChallenge));
    }

    @Override
    public void delete(int id) {
         adminCRepo.deleteById(id);
    }


//    @Override
//    public AdminCResponse update(AdminChallenge adminChallenge) {
//        Optional<AdminChallenge> adminChallenge1=adminCRepo.findById(adminChallenge.getId());
//        if(adminChallenge1.isEmpty()){
//            return new AdminCResponse("لم يتم العثور على هذا التحدي :(",null);
//        }else{
//            adminChallenge1.get().setName(adminChallenge.getName());
//            adminChallenge1.get().setDescription(adminChallenge.getDescription());
//            adminChallenge1.get().setIcon1(adminChallenge.getIcon1());
//            adminChallenge1.get().setIcon2(adminChallenge.getIcon2());
//            adminChallenge1.get().setDone(adminChallenge.isDone());
//            adminChallenge1.get().setAdminCDays(adminChallenge.getAdminCDays());
//
//            return new AdminCResponse("تم التعديل بنجاح :)",adminCRepo.save(adminChallenge1.get()));
//        }
//    }

    @Override
    public AdminCResponse update(AdminChallenge adminChallenge) {
        Optional<AdminChallenge> adminChallenge1=adminCRepo.findById(adminChallenge.getId());
        if(adminChallenge1.isEmpty()){
            return new AdminCResponse("لم يتم العثور على هذا التحدي :(",null);
        }else{
            adminChallenge1.get().setName(adminChallenge.getName());
            adminChallenge1.get().setDescription(adminChallenge.getDescription());
            adminChallenge1.get().setIcon1(adminChallenge.getIcon1());
            adminChallenge1.get().setIcon2(adminChallenge.getIcon2());


            List<AdminCDays> adminCDays=adminChallenge1.get().getAdminCDays();
            List<AdminCDays> adminCDays1=adminChallenge.getAdminCDays();
            for (int i = 0; i < adminCDays1.size(); i++) {
                adminChallenge1.get().getAdminCDays().get(i).setName(adminCDays1.get(i).getName());
                adminChallenge1.get().getAdminCDays().get(i).setContent(adminCDays1.get(i).getContent());

            }
            adminChallenge1.get().setAdminCDays(adminCDays);

            return new AdminCResponse("تم التعديل بنجاح :)",adminCRepo.save(adminChallenge1.get()));
        }
    }

//    @Override
//    public CRAdminCU updateCR(String token, AdminDTO adminDTO) {
//        Token to= tokenRepository.findByToken(token.substring(7));
//        User user=userRepo.findByEmail(to.getUser().getEmail());
//        Optional<AdminChallenge> adminChallenge=adminCRepo.findById(adminDTO.getId());
//        List<AdminChallenge> adminChallenges=user.getAdminChallengeSet();
//        for(int i=0;i<adminChallenges.size();i++){
//            if(adminChallenges.get(i)==adminChallenge.get()){
//                user.getAdminChallengeSet().get(i).setDone(adminDTO.isDone1());
//                for (int j = 0; j < adminChallenge.get().getAdminCDays().size(); j++) {
//                    user.getAdminChallengeSet().get(i).getAdminCDays().get(j).setDone(adminDTO.getDays().get(i).isDone2());
//                }
//            }
//        }
//        userRepo.save(user);
//
//        return new CRAdminCU("تم التعديل بنجاح :)");
//    }

    @Override
    public AdminCResponse findById(int id) {
        Optional<AdminChallenge> adminChallenge1=adminCRepo.findById(id);
        if(adminChallenge1.isEmpty()){
            return new AdminCResponse("لم يتم العثور على هذا التحدي :(",null);
        }
        return new AdminCResponse("تم العثور على التحدي بنجاح :)",adminChallenge1.get());
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
