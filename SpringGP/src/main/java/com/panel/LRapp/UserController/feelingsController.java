package com.panel.LRapp.UserController;


import com.panel.LRapp.Dto.feelingsDTO;

import com.panel.LRapp.Entity.feelings;
import com.panel.LRapp.Service.feelingsService;

import com.panel.LRapp.response.feelingsResponse;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/feelings")
public class feelingsController {

    @Autowired
    private feelingsService fService;

    @PostMapping("/save")
    public feelingsResponse addFeeling(@RequestBody feelingsDTO fDto){

        return fService.save(fDto);
    }

    @DeleteMapping("/delete")
    public String deleteFeeling(@RequestParam("feelingId") int id){
        fService.delete(id);
        return "feeling delete Successfully";
    }

    @GetMapping("/findfeeling")
    public feelingsResponse findById(@RequestParam("feelingId") int id){
        return fService.findById(id);
    }

    @GetMapping("/search")
    public List<feelings> search(@RequestParam("search") String name){
        return fService.findByName(name);
    }

    @GetMapping("/findAll")
    public List<feelings> findAllfeelings(){
        return fService.findAll();
    }

    @PutMapping("/update")
    public feelingsResponse updateFeeling(@RequestBody feelingsDTO fDTO){
        return fService.update(fDTO);
    }



}
