package com.panel.LRapp.Service;


import com.panel.LRapp.Dto.HabitsDTO;
import com.panel.LRapp.Entity.Habits;
import com.panel.LRapp.response.HabitsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public interface HabitsService {

    HabitsResponse findById(int id);
    List<Habits> findAll(String t);

    HabitsResponse save(HabitsDTO habitsDto,String t);

    HabitsResponse update(HabitsDTO habitsDto);

    String deleteById(int id);

    String deleteHabits(int id, int indx);
}
