package com.panel.LRapp.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TasksDto {
    private int id;

    private List<String> title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private boolean done;

    public TasksDto(List<String> title, Date date, boolean done) {
        this.title = title;
        this.date = date;
        this.done = done;
    }
}
