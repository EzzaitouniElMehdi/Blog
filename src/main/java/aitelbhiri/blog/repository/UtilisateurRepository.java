package aitelbhiri.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import aitelbhiri.blog.model.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

	@Query("SELECT u FROM Utilisateur u WHERE u.id = :id")
	Utilisateur getUtilisateurById(@Param("id") int id);

	@Query("SELECT u FROM Utilisateur u")
	List<Utilisateur> getAllUtilisateurs();

	@Query("SELECT u FROM Utilisateur u WHERE u.email = :email")
	Utilisateur findUtilisateurByEmail(String email);

	Boolean existsByEmail(String email);

	Boolean existsByUsername(String username);

	Optional<Utilisateur> findByUsername(String username);

	long count();
}
