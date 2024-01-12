package aitelbhiri.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import aitelbhiri.blog.model.Comment;
import aitelbhiri.blog.repository.CommentRepository;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(int id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(int id, Comment updatedComment) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));

        // Mise à jour des attributs de la réclamation existante avec les attributs de la réclamation mise à jour
        existingComment.setBody(updatedComment.getBody());
        existingComment.setCreateDate(updatedComment.getCreateDate());
        existingComment.setUtilisateur(updatedComment.getUtilisateur());
        existingComment.setPost(updatedComment.getPost());
        // Vous pouvez mettre à jour d'autres attributs selon vos besoins

        return commentRepository.save(existingComment);
    }

    public void deleteComment(int id) {
        Comment existingComment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));

        commentRepository.delete(existingComment);
    }

    public List<Comment> getCommentsByUtilisateurId(int utilisateurId) {
        return commentRepository.findByUtilisateurId(utilisateurId);
    }

    public List<Comment> getCommentsByPostId(int postId) {
        return commentRepository.findByPostId(postId);
    }
}
