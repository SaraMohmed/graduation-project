package com.panel.LRapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private List<String> title;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @Column(columnDefinition = "varchar(255) default 'false'")
    private boolean done;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id")
    private User user;


    public Tasks(List<String> title, Date date, boolean done) {
        this.title = title;
        this.date = date;
        this.done = done;
    }
}
