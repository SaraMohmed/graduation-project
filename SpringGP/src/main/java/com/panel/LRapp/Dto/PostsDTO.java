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
    private boolean userLiked;
    private boolean userDisLiked;
    private boolean userId;

    public PostsDTO( int like, int disLike) {

        this.like = like;
        this.disLike = disLike;
    }

}
