package aitelbhiri.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import aitelbhiri.blog.model.Categorie;
import aitelbhiri.blog.model.Comment;
import aitelbhiri.blog.model.Post;
import aitelbhiri.blog.model.Utilisateur;

public class PostTest {

    @Test
    public void testPostGetterSetter() {
        Post post = new Post();
        post.setId(1);
        post.setTitle("Introduction to JUnit");
        post.setBody("JUnit is a popular testing framework for Java.");
        post.setCreateDate(new Date());
        post.setImageUrl("https://example.com/image.jpg");

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setUsername("john_doe");
        post.setUtilisateur(utilisateur);

        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment();
        comment.setId(1);
        comment.setBody("Great post!");
        comment.setPost(post);
        comments.add(comment);

        post.setComments(comments);

        Categorie categorie = new Categorie();
        categorie.setIdCategorie(1);
        categorie.setTitle("Java Programming");
        post.setCategorie(categorie);

        assertNotNull(post.getId());
        assertEquals(1, post.getId());
        assertEquals("Introduction to JUnit", post.getTitle());
        assertEquals("JUnit is a popular testing framework for Java.", post.getBody());
        assertNotNull(post.getCreateDate());
        assertEquals("https://example.com/image.jpg", post.getImageUrl());
        assertEquals(utilisateur, post.getUtilisateur());
        assertEquals(comments, post.getComments());
        assertEquals(categorie, post.getCategorie());
    }

    // Ajoutez d'autres méthodes de test selon vos besoins.
}
