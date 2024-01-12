package aitelbhiri.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import aitelbhiri.blog.model.Post;
import aitelbhiri.blog.repository.PostRepository;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post addPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(int id, Post updatedPost) {
    	Post existingPost = getPostById(id);

        // Mise à jour des attributs de la réclamation existante avec les attributs de la réclamation mise à jour
        existingPost.setTitle(updatedPost.getTitle());
        existingPost.setBody(updatedPost.getBody());
        existingPost.setCreateDate(updatedPost.getCreateDate());
        existingPost.setUtilisateur(updatedPost.getUtilisateur());
        existingPost.setComments(updatedPost.getComments());
        existingPost.setImageUrl(updatedPost.getImageUrl());

        		
        // Vous pouvez mettre à jour d'autres attributs selon vos besoins

        return postRepository.save(existingPost);
    }

    public void deletePost(int id) {
    	Post existingPost = getPostById(id);
    	postRepository.delete(existingPost);
    }
    
    public List<Post> getPostsByUtilisateurId(int utilisateurId) {
        return postRepository.findByUtilisateurId(utilisateurId);
    }


}
