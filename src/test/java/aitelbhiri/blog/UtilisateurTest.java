package aitelbhiri.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import mehdi.blog.model.ERole;
import mehdi.blog.model.Post;
import mehdi.blog.model.Role;
import mehdi.blog.model.Utilisateur;

public class UtilisateurTest {

    @Test
    public void testUtilisateurGetterSetter() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setUsername("john_doe");
        utilisateur.setNom("Doe");
        utilisateur.setPrenom("John");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setPassword("password");

        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        role.setId(1);
        role.setName(ERole.ROLE_ADMIN);
        roles.add(role);

        utilisateur.setRoles(roles);

        List<Post> posts = List.of(new Post(), new Post());
        utilisateur.setPosts(posts);

        assertNotNull(utilisateur.getId());
        assertEquals(1, utilisateur.getId());
        assertEquals("john_doe", utilisateur.getUsername());
        assertEquals("Doe", utilisateur.getNom());
        assertEquals("John", utilisateur.getPrenom());
        assertEquals("john.doe@example.com", utilisateur.getEmail());
        assertEquals("password", utilisateur.getPassword());
        assertEquals(roles, utilisateur.getRoles());
        assertEquals(posts, utilisateur.getPosts());
    }

    // Ajoutez d'autres méthodes de test selon vos besoins.
}
