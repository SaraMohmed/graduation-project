package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.feelingsDTO;

import com.panel.LRapp.Entity.feelings;
import com.panel.LRapp.Repo.feelingsRepo;

import com.panel.LRapp.response.feelingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class feelingsServiceImpl implements feelingsService{
    @Autowired
    private feelingsRepo fRepo;


    @Override
    public feelingsResponse save(feelingsDTO fDTO) {
        feelings f=new feelings(fDTO.getName()
                ,fDTO.getDescription()
                ,fDTO.getAdvice()
                ,fDTO.getFile());

        return new feelingsResponse("add feeling Successfully",fRepo.save(f));
    }

    @Override
    public void delete(int id) {
        fRepo.deleteById(id);
    }

    @Override
    public feelingsResponse update(feelingsDTO fDTO) {
        Optional<feelings> f=fRepo.findById(fDTO.getId());
        if(f.isEmpty()){
            return new feelingsResponse("this feeling not found",null);
        }else{
            f.get().setName(fDTO.getName());
            f.get().setDescription(fDTO.getDescription());
            f.get().setAdvice(fDTO.getAdvice());
            return new feelingsResponse("update feeling Successfully",fRepo.save(f.get()));
        }

    }

    @Override
    public feelingsResponse findById(int id) {

        Optional<feelings> f=fRepo.findById(id);
        if(f.isEmpty()){
            return new feelingsResponse("this feeling not found",null);
        }else {
            return new feelingsResponse("feeling found", f.get());
        }
    }

    @Override
    public List<feelings> findByName(String name) {
        List<feelings> f=fRepo.findByName(name);
        return f;
    }

    @Override
    public List<feelings> findAll() {
        return fRepo.findAll();
    }

    @Override
    public feelingsResponse saveF(feelings f) {
        return new feelingsResponse("Added Successfully",fRepo.save(f));
    }
}
