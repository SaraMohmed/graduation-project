package com.panel.LRapp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "adminChallenge")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class AdminChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "icon")
    private String icon;

    @Column(name = "name")
    private String name;

    @Column(name = "Description")
    private String description;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "adminChallenge",cascade = CascadeType.ALL)
    private List<AdminCDays> adminCDays;


    @Column(name = "rate")
    private boolean isDone;

    public AdminChallenge(String icon, String name, String description, List<AdminCDays> adminCDays, boolean isDone) {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.adminCDays = adminCDays;
        this.isDone = isDone;
    }

    public AdminChallenge(String icon, String name, String description, boolean isDone) {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.isDone = isDone;
    }
}
