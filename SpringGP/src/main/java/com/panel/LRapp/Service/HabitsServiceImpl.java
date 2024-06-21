package com.panel.LRapp.Service;


import com.panel.LRapp.Dto.HabitsDTO;
import com.panel.LRapp.Entity.Habits;
import com.panel.LRapp.Entity.Token;
import com.panel.LRapp.Entity.User;
import com.panel.LRapp.Repo.HabitsRepo;
import com.panel.LRapp.Repo.TokenRepository;
import com.panel.LRapp.Repo.UserRepo;
import com.panel.LRapp.response.HabitsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitsServiceImpl implements HabitsService{

    @Autowired
    private HabitsRepo habitsRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public HabitsResponse findById(int id) {
        if(habitsRepo.findById(id).isEmpty()){
            return new HabitsResponse("Habit not found",null);
        }
        else{
            return new HabitsResponse("Habit found",habitsRepo.findById(id).get());
        }
    }

    @Override
    public List<Habits> findAll(String token) {
        Token t= tokenRepository.findByToken(token.substring(7));
        User user=userRepo.findByEmail(t.getUser().getEmail());
        return user.getHabits();
    }


    @Override
    public HabitsResponse save(HabitsDTO habitsDto,String token) {

        Token t= tokenRepository.findByToken(token.substring(7));
        User user=userRepo.findByEmail(t.getUser().getEmail());
        Habits habits = new Habits(habitsDto.getTitle(),habitsDto.getStartDate(),habitsDto.getEndDate(),false);
        habits.setUser(user);
        return new HabitsResponse("Habit save successfully",habitsRepo.save(habits));
    }



    @Override
    public HabitsResponse update(HabitsDTO habitsDto) {
        Optional<Habits> habits = habitsRepo.findById(habitsDto.getId());
        habits.get().setTitle(habitsDto.getTitle());
        habits.get().setStartDate(habitsDto.getStartDate());
        habits.get().setEndDate(habitsDto.getEndDate());
        habits.get().setDone(habitsDto.isDone());
        return new HabitsResponse("Habit updated Successfully",habitsRepo.save(habits.get()));
    }

    @Override
    public String deleteById(int id) {
        Optional<Habits> habits = habitsRepo.findById(id);
        if(habits.isEmpty()){
            return "Habit not found";
        }
        else{
            habitsRepo.deleteById(id);
            return "Habit deleted Successfully";
        }
    }

    @Override
    public String deleteHabits(int id, int indx) {
        Optional<Habits> habits = habitsRepo.findById(id);
        habits.get().getTitle().set(indx,null);
        return "deleted";
    }


}
