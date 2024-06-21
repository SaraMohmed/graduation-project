package com.panel.LRapp.Repo;


import com.panel.LRapp.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepo extends JpaRepository<Tasks, Integer> {

//    @Query("SELECT f FROM Tasks f WHERE f.title LIKE %:name%")
//    List<Tasks> findByName(@Param("name") String name);

}
