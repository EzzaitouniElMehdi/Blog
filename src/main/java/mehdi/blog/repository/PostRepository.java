package mehdi.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mehdi.blog.model.Categorie;
import mehdi.blog.model.Comment;
import mehdi.blog.model.Post;
import mehdi.blog.model.Utilisateur;



@Repository
@Transactional
public interface PostRepository extends JpaRepository<Post, Integer> {
	
    @Query("Select p from Post p where p.id=:id")
	Post getPost(@Param("id") int id);

	@Query("Select p from Post p ")
	List<Post> getAllPosts();
	
	
    @Query("SELECT p FROM Post p WHERE p.id = :id")
    Comment getPostById(@Param("id") int id);
    
	List<Post> findByUtilisateur(Utilisateur user);
	List<Post> findByCategorie(Categorie categorie);
	
    List<Post> findByUtilisateurId(int utilisateurId);

	


}
