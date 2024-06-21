package com.panel.LRapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "feelings")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class feelings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "advice")
    private List<String> advice;

    @Column(name = "file")
    private List<String> file;

    public feelings(String name, String description, List<String> advice, List<String> file) {
        this.name = name;
        this.description = description;
        this.advice = advice;
        this.file = file;
    }
}
