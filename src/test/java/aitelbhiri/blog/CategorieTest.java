package aitelbhiri.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import aitelbhiri.blog.model.Categorie;
import aitelbhiri.blog.model.Post;

public class CategorieTest {

    @Test
    public void testCategorieGetterSetter() {
        Categorie categorie = new Categorie();
        categorie.setIdCategorie(1);
        categorie.setTitle("Java Programming");

        List<Post> posts = List.of(new Post(), new Post());
        categorie.setPosts(posts);

        assertNotNull(categorie.getIdCategorie());
        assertEquals(1, categorie.getIdCategorie());
        assertEquals("Java Programming", categorie.getTitle());
        assertEquals(posts, categorie.getPosts());
    }

    // Ajoutez d'autres méthodes de test selon vos besoins.
}
