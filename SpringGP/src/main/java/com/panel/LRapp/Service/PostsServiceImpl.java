package com.panel.LRapp.Service;

import com.panel.LRapp.Dto.PostsDTO;
import com.panel.LRapp.Dto.post;
import com.panel.LRapp.Entity.Posts;
import com.panel.LRapp.Entity.Token;
import com.panel.LRapp.Entity.User;
import com.panel.LRapp.Repo.PostsRepo;
import com.panel.LRapp.Repo.TokenRepository;
import com.panel.LRapp.Repo.UserRepo;
import com.panel.LRapp.response.PostList;
import com.panel.LRapp.response.PostResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostsServiceImpl implements PostsService{
    @Autowired
    private PostsRepo postsRepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public PostResponse getPostById(int id) {
//        Token t= tokenRepository.findByToken(token.substring(7));
//        User user=userRepo.findByEmail(t.getUser().getEmail());
        if (postsRepo.findById(id).isEmpty()){
            return new PostResponse("post not found", null);
        }

        else{
            return new PostResponse("post found", postsRepo.findById(id).get());
        }
    }

    @Override
    public PostList findAll() {
        return new PostList(postsRepo.findAll());
    }

    @Override
    public PostResponse createNewPost(post postDto, String token) {

        Token t= tokenRepository.findByToken(token.substring(7));
        User user=userRepo.findByEmail(t.getUser().getEmail());

        Posts p=new Posts(postDto.getContent(),0,0, user.getName(), user.getImage());
        p.setUser(user);

        return new PostResponse("Post added successfully",postsRepo.save(p));
    }

//    @Override
//    public PostResponse update(PostsDTO postDto) {
//
//        Optional<Posts> p=postsRepo.findById(postDto.getId());
//        if(postDto.getLike()==0) {
//            p.get().setDisLike(postDto.getDisLike());
//
//        }else if (postDto.getDisLike()==0) {
//            p.get().setLike(postDto.getLike());
//        }
//
//        return new PostResponse("updated",postsRepo.save(p.get()));
//    }


    @Transactional
    @Override
    public String delete(int id, String token) {

        String tokenValue = token.substring(7);
        Token t = tokenRepository.findByToken(tokenValue);

        if (t == null || t.getUser() == null) {
            return "Invalid token";
        }

        User user = userRepo.findByEmail(t.getUser().getEmail());
        Optional<Posts> optionalPost = postsRepo.findById(id);

        if (optionalPost.isEmpty()) {
            return "Post not found to delete";
        }

        Posts post = optionalPost.get();

        if (!post.getUser().equals(user)) {
            return "User not authorized to delete this post";
        }

        user.getPosts().remove(post);

        postsRepo.delete(post);
        return "Deleted";
    }

    @Override
    public PostResponse update(PostsDTO postDto, String token) {

        String tokenValue = token.substring(7);
        Token t = tokenRepository.findByToken(tokenValue);

        if (t == null || t.getUser() == null) {
            return new PostResponse("Invalid token", null);
        }

        User user = userRepo.findByEmail(t.getUser().getEmail());
        Optional<Posts> p = postsRepo.findById(postDto.getId());

        if (p.isEmpty()) {
            return new PostResponse( "Post not found to update", null);
        }

        if (!p.get().getUser().equals(user)) {
            return new PostResponse("User not authorized to delete this post",null);
        }

        p.get().setDisLike(postDto.getDisLike());
        p.get().setLike(postDto.getLike());
        p.get().setUserLiked(postDto.isUserLiked());
        p.get().setUserDisLiked(postDto.isUserDisLiked());


        return new PostResponse("updated",postsRepo.save(p.get()));
    }


//    @Override
//    public PostResponse updateLike(PostsDTO postDto) {
//        return null;
//    }
//
//    @Override
//    public PostResponse updateDisLike(PostsDTO postDto) {
//        return null;
//    }
}
