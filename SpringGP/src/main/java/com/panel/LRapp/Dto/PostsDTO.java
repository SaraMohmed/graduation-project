package com.panel.LRapp.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PostsDTO {

    private int id;
    private int like;
    private int disLike;

    public PostsDTO( int like, int disLike) {

        this.like = like;
        this.disLike = disLike;
    }
}
