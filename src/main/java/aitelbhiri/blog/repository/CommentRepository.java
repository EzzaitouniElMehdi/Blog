package aitelbhiri.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import aitelbhiri.blog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
	
    Comment getCommentById(int id);

    List<Comment> findAll();

    List<Comment> findByUtilisateurId(int utilisateurId);
    
    // Add the following method for finding comments by postId
    List<Comment> findByPostId(int postId);
}
