package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.feelingsDTO;
import com.panel.LRapp.Entity.feelings;
import com.panel.LRapp.response.feelingsResponse;

import java.util.List;

public interface feelingsService {

   feelingsResponse save(feelingsDTO fDTO);
   void delete(int id);
   feelingsResponse update(feelingsDTO fDTO);
   feelingsResponse findById(int id);
   List<feelings> findByName(String name);

   List<feelings> findAll();

   feelingsResponse saveF(feelings f);
}
