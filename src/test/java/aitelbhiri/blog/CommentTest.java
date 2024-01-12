package aitelbhiri.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

import mehdi.blog.model.Comment;
import mehdi.blog.model.Post;
import mehdi.blog.model.Utilisateur;

public class CommentTest {

    @Test
    public void testCommentGetterSetter() {
        Comment comment = new Comment();
        comment.setId(1);
        comment.setBody("Great comment!");
        comment.setCreateDate(new Date());

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setUsername("john_doe");
        comment.setUtilisateur(utilisateur);

        Post post = new Post();
        post.setId(1);
        post.setTitle("Introduction to JUnit");
        comment.setPost(post);

        assertNotNull(comment.getId());
        assertEquals(1, comment.getId());
        assertEquals("Great comment!", comment.getBody());
        assertNotNull(comment.getCreateDate());
        assertEquals(utilisateur, comment.getUtilisateur());
        assertEquals(post, comment.getPost());
    }

    // Ajoutez d'autres méthodes de test selon vos besoins.
}
